package com.fisiomais.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisiomais.bodys.NovaConsultaRequest;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;
import com.fisiomais.model.enums.StatusConsulta;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;
import com.fisiomais.service.ConsultaService;

import com.fisiomais.exception.BusinessException;

@Service
public class ConsultaUtil {

    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    @Autowired
    public ConsultaUtil(PacienteRepository pacienteRepository, FisioterapeutaRepository fisioterapeutaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    public StatusConsulta convertToStatusConsulta(String status) {
        try {
            return StatusConsulta.valueOf(status);
        } catch (IllegalArgumentException e) {
            // Handle invalid status
            return null;
        }
    }

    public Consulta convertToConsulta(NovaConsultaRequest consulta) {
        Paciente paciente = pacienteRepository.findById(consulta.pacienteId()).orElseThrow(() -> new BusinessException("Paciente não encontrado"));
        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(consulta.fisioterapeutaId()).orElseThrow(() -> new BusinessException("Fisioterapeuta não encontrado"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setPaciente(paciente);
        novaConsulta.setFisioterapeuta(fisioterapeuta);
        novaConsulta.setDataEHora(consulta.dataHora());

        if (fisioterapeuta.getAutomatic()) {
            novaConsulta.setConfirmacao(StatusConsulta.confirmado);
        } else{
            novaConsulta.setConfirmacao(StatusConsulta.pendente);
        }

        novaConsulta.setOwner_id(paciente.getId());

        return novaConsulta;
    }
}
