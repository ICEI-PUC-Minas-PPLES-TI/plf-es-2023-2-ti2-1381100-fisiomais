package com.fisiomais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fisiomais.bodys.FisioterapeutaResponse;
import com.fisiomais.bodys.PacienteResponse;
import com.fisiomais.bodys.TratamentoResponse;
import com.fisiomais.exception.ServerException;
import com.fisiomais.model.Tratamento;
import com.fisiomais.model.indicators.MidiaUtilizationMetrics;
import com.fisiomais.repository.ExercicioRepository;
import com.fisiomais.repository.TratamentoRepository;

@Service
public class TratamentoService {

    private TratamentoRepository tratamentoRepository;
    private ExercicioRepository exercicioRepository;

    public TratamentoService(TratamentoRepository tratamentoRepository, ExercicioRepository exercicioRepository) {
        this.tratamentoRepository = tratamentoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public TratamentoResponse createTratamento(Tratamento obj) {
        obj = this.tratamentoRepository.save(obj);
        return this.toTratamentoResponse(obj);

    }

    public List<Tratamento> findByPacienteId(Integer id) {
        List<Tratamento> tratamentos = tratamentoRepository.findByPacienteId(id);
        return tratamentos;
    }

    public List<Tratamento> findByFisioterapeutaId(Integer id) {
        List<Tratamento> tratamentos = tratamentoRepository.findByFisioterapeutaId(id);
        return tratamentos;
    }

    public Tratamento findTratamentoByTitulo(String titulo) {
        Tratamento tratamento = tratamentoRepository.findTratamentoByTitulo(titulo);
        return tratamento;
    }

    @Transactional
    public Tratamento update(Tratamento obj) {
        Optional<Tratamento> newObj = this.tratamentoRepository.findById(obj.getId());
        newObj.get().setTitulo(obj.getTitulo());
        newObj.get().setObservacoes(obj.getObservacoes());
        newObj.get().setEndDate(obj.getEndDate());
        newObj.get().setFeedback(obj.getFeedback());
        return this.tratamentoRepository.save(newObj.get());
    }

    private TratamentoResponse toTratamentoResponse(Tratamento tratamento) {
        return new TratamentoResponse(
                PacienteResponse.toPacienteResponse(tratamento.getPaciente()),
                FisioterapeutaResponse.toFisioterapeutaResponse(tratamento.getFisioterapeuta()),
                tratamento.getTitulo(),
                tratamento.getObservacoes(),
                tratamento.getFeedback(),
                tratamento.getEndDate());
    }

    public List<MidiaUtilizationMetrics> getTaxaUtilizacao() {
        try {
            return exercicioRepository.getTaxaUtilizacao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("Não foi possível calcular a taxa de utilização das mídias.");
        }
    }
}
