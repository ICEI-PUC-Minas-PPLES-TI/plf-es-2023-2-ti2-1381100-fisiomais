package com.fisiomais.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.Spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fisiomais.bodys.ExercicioRequest;
import com.fisiomais.bodys.ExercicioResponse;
import com.fisiomais.controller.ConsultaController;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.exception.NotFoundException;
import com.fisiomais.model.Exercicio;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Midia;
import com.fisiomais.repository.ExercicioRepository;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.MidiaRepository;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;
    private final MidiaRepository midiaRepository;
    private final TokenService tokenService;
    private static final Logger logger = LogManager.getLogger(ExercicioService.class);

    public ExercicioService(ExercicioRepository exercicioRepository,
            MidiaRepository midiaRepository,
            TokenService tokenServices) {
        this.exercicioRepository = exercicioRepository;
        this.midiaRepository = midiaRepository;
        this.tokenService = tokenServices;
    }

    public List<Exercicio> findExerciciosByMidia(Midia midia) {
        return exercicioRepository.findExerciciosByMidia(midia);
    }

    public void deleteMidia(Exercicio exercicio, Midia midia) {
        Exercicio exercicioToDelete = exercicioRepository.findById(exercicio.getId()).orElseThrow();
        exercicioToDelete.getMidias().remove(midia);
        exercicioRepository.save(exercicioToDelete);
    }

    public List<Exercicio> getAllExercicios(String token) {
        String loggedUserEmail = this.tokenService.getSubject(this.tokenService.getTokenFromBearer(token));
        if (loggedUserEmail.equals("fisiomaisclinicas@gmail.com")) {
            logger.info("Buscando todos os exercícios  para o administrador");
            return exercicioRepository.findAll();
        }

        throw new BusinessException("Você não tem permissão para acessar esse recurso");
    }

    public List<ExercicioResponse> getExercicioByFisioterapeuta(Fisioterapeuta fisioterapeuta, String token) {
        String loggedUserEmail = this.tokenService.getSubject(this.tokenService.getTokenFromBearer(token));
        if (!loggedUserEmail.equals(fisioterapeuta.getEmail())
                || !loggedUserEmail.equals("fisiomaisclinicas@gmail.com")) {
            throw new BusinessException("Você não tem permissão para acessar esse recurso");
        }
        return ExercicioResponse
                .toExercicioResponse(exercicioRepository.findExerciciosByFisioterapeuta(fisioterapeuta));
    }

    public ExercicioResponse getExercicioById(Integer id, String token) {
        String loggedUserEmail = this.tokenService.getSubject(this.tokenService.getTokenFromBearer(token));
        Optional<Exercicio> exercicio = exercicioRepository.findById(id);
        if (exercicio.isPresent()) {
            if (!loggedUserEmail.equals("fisiomaisclinicas@gmail.com")
                    || !loggedUserEmail.equals(exercicio.get().getFisioterapeuta().getEmail())) {
                throw new BusinessException("Você não tem permissão para acessar esse recurso");
            }
            return ExercicioResponse.toExercicioResponse(exercicio.get());
        }

        throw new NotFoundException("Exercicio de id " + id + " não encontrado");
    }

    public ExercicioResponse createExercicio(ExercicioRequest exercicioDTO, String token) {
        List<Midia> midias = new ArrayList<>();
        try {
            midias.addAll(midiaRepository.findAllById(exercicioDTO.midias()));
        } catch (Exception e) {
            throw new NotFoundException("Erro ao buscar midias [" + exercicioDTO.midias() + "]");
        }

        String loggedUserEmail = this.tokenService.getSubject(this.tokenService.getTokenFromBearer(token));

        if (midias.stream()
                .anyMatch(midia -> !midia.getFisioterapeuta()
                        .getEmail()
                        .equals(loggedUserEmail))) {
            throw new NotFoundException("Você não pode criar um exercício com mídias de outro fisioterapeuta");
        }

        Fisioterapeuta fisioterapeuta = midias.get(0).getFisioterapeuta();

        Exercicio exercicio = ExercicioRequest.toEntity(exercicioDTO, fisioterapeuta, midias);

        Exercicio savedExercicio = exercicioRepository.save(exercicio);

        if (savedExercicio == null) {
            throw new BusinessException("Erro ao salvar exercício");
        }

        return ExercicioResponse.toExercicioResponse(savedExercicio);
    }

    public void deleteExercicio(Integer id, String token) {
        Optional<Exercicio> existingExercicio = exercicioRepository.findById(id);

        if (existingExercicio.isPresent()) {
            Exercicio exercicio = existingExercicio.get();

            String loggedUserEmail = this.tokenService.getSubject(this.tokenService.getTokenFromBearer(token));

            if (!exercicio.getFisioterapeuta()
                    .getEmail()
                    .equals(loggedUserEmail)) {
                throw new NotFoundException("Você não pode excluir um exercício de outro fisioterapeuta");
            }

            exercicioRepository.delete(exercicio);
        } else {
            throw new NotFoundException("Exercício de id " + id + " não encontrado");
        }
    }
}