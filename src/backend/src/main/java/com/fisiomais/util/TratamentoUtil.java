package com.fisiomais.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisiomais.bodys.NovoTratamentoRequest;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;
import com.fisiomais.model.Tratamento;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;

@Service
public class TratamentoUtil {
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    @Autowired
    public TratamentoUtil(PacienteRepository pacienteRepository, FisioterapeutaRepository fisioterapeutaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    public Tratamento convertToTratamento(NovoTratamentoRequest tratamento) {
        Paciente paciente = pacienteRepository.findById(tratamento.pacienteId()).orElseThrow(() -> new BusinessException("Paciente não encontrado"));
        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(tratamento.fisioterapeutaId()).orElseThrow(() -> new BusinessException("Fisioterapeuta não encontrado"));

        Tratamento novoTratamento = new Tratamento();
        novoTratamento.setPaciente(paciente);
        novoTratamento.setFisioterapeuta(fisioterapeuta);
        novoTratamento.setEndDate(tratamento.endDate());
        novoTratamento.setTitulo(tratamento.titulo());
        novoTratamento.setFeedback(tratamento.feedback());
        novoTratamento.setObservacoes(tratamento.observacoes());

        return novoTratamento;
    }
}
