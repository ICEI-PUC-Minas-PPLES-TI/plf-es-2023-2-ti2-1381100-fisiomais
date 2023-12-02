package com.fisiomais.service;

import com.fisiomais.bodys.FisioterapeutaNamesAndIdsResponse;
import com.fisiomais.dto.FisioterapeutaDTO;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.indicators.NovosFisioterapeutasMetrics;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FisioterapeutaService {

    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final PacienteRepository pacienteRepository;

    public FisioterapeutaService(FisioterapeutaRepository fisioterapeutaRepository,
            PacienteRepository pacienteRepository) {
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Fisioterapeuta> findAll() {
        return fisioterapeutaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Fisioterapeuta> findAll(Pageable pageable) {
        return fisioterapeutaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Fisioterapeuta> findById(Integer id) {
        return fisioterapeutaRepository.findById(id);
    }

    @Transactional
    public Fisioterapeuta save(Fisioterapeuta fisioterapeuta) {
        validateFisioterapeuta(fisioterapeuta);

        if (pacienteRepository.findByEmail(fisioterapeuta.getEmail()) != null
                || fisioterapeutaRepository.findByEmail(fisioterapeuta.getEmail()) != null) {
            throw new BusinessException("Email já cadastrado. Tente realizar o login.");
        }

        return fisioterapeutaRepository.save(fisioterapeuta);
    }

    @Transactional
    public Fisioterapeuta update(Integer id, FisioterapeutaDTO fisioterapeutaDTO) {
        Optional<Fisioterapeuta> existingFisioterapeuta = fisioterapeutaRepository.findById(id);
        if (existingFisioterapeuta.isPresent()) {
            // validateFisioterapeuta(fisioterapeutaDTO);
            // fisioterapeutaDTO.set_id(null);
            // return fisioterapeutaRepository.save(fisioterapeutaDTO);
        } else {
            throw new RuntimeException("Fisioterapeuta not found");
        }
        return null;
    }

    private void validateFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        if (fisioterapeuta == null) {
            throw new BusinessException("O fisioterapeuta não pode ser nulo");
        }

        if (fisioterapeuta.getNome() == null || fisioterapeuta.getNome().trim().isEmpty()) {
            throw new BusinessException("O nome do fisioterapeuta não pode ser nulo ou vazio");
        }

        if (fisioterapeuta.getEmail() == null || fisioterapeuta.getEmail().trim().isEmpty()) {
            throw new BusinessException("O email do fisioterapeuta não pode ser nulo ou vazio");
        }

        if (!fisioterapeuta.getNome().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BusinessException("O email do fisioterapeuta não é válido");
        }

        if (fisioterapeuta.getPassword() == null || fisioterapeuta.getPassword().length() != 8) {
            throw new BusinessException("A senha do fisioterapeuta não pode ser nula e deve ter 8 caracteres");
        }

        if (fisioterapeuta.getTelefone() == null || !fisioterapeuta.getTelefone().matches("^[0-9]{11}$")) {
            throw new BusinessException("O telefone do fisioterapeuta não pode ser nulo e deve ter 11 dígitos");
        }

        if (fisioterapeuta.getEndereco() != null && fisioterapeuta.getEndereco().length() > 200) {
            throw new BusinessException("O endereço do fisioterapeuta não pode ter mais de 200 caracteres");
        }
    }

    @Transactional
    public void deleteById(Integer id) {
        fisioterapeutaRepository.deleteById(id);
    }

    // @Transactional(readOnly = true)
    // public List<Fisioterapeuta> findByEspecialidade(String especialidade) {
    // return fisioterapeutaRepository.findByEspecialidade(especialidade);
    // }

    public Fisioterapeuta create(FisioterapeutaDTO fisioterapeutaDTO) {
        if(pacienteRepository.findByEmail(fisioterapeutaDTO.getEmail()) != null || fisioterapeutaRepository.findByEmail(fisioterapeutaDTO.getEmail()) != null){
            throw new BusinessException("Email já cadastrado. Tente realizar o login.");
        }

        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(fisioterapeutaDTO.getNome());
        fisioterapeuta.setEmail(fisioterapeutaDTO.getEmail());
        fisioterapeuta.setPassword(fisioterapeutaDTO.getPassword());
        fisioterapeuta.setTelefone(fisioterapeutaDTO.getTelefone());
        fisioterapeuta.setEndereco(fisioterapeutaDTO.getEndereco());
        fisioterapeuta.setAutomatic(fisioterapeutaDTO.getControleAutomatico());

        return fisioterapeutaRepository.save(fisioterapeuta);
    }

    public List<FisioterapeutaNamesAndIdsResponse> findAllNames() {
        List<Fisioterapeuta> fisioterapeutas = fisioterapeutaRepository.findAll();
        List<FisioterapeutaNamesAndIdsResponse> fisioterapeutasNamesIds = new ArrayList<>();
        for (Fisioterapeuta fisioterapeuta : fisioterapeutas) {
            // Decode Titulo and Descricao from UTF-8
            String nome = null;

            try {
                nome = new String(fisioterapeuta.getNome().getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException("Error decoding string. Please provide a valid string format.");
            }

            fisioterapeutasNamesIds
                    .add(new FisioterapeutaNamesAndIdsResponse(nome, fisioterapeuta.getId()));
        }
        return fisioterapeutasNamesIds;
    }

    public Double getTaxaCrescimento() {
        return fisioterapeutaRepository.calculateTaxaCrescimento();
    }

    public Double getIndicePerfisCompletos() {
        return fisioterapeutaRepository.calculateIndicePerfisCompletos();
    }
}
