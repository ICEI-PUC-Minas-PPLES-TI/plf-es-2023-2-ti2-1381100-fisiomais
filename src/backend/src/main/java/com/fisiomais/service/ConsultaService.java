package com.fisiomais.service;

import com.fisiomais.bodys.ConsultaResponse;
import com.fisiomais.bodys.ConsultaResponseAgenda;
import com.fisiomais.bodys.FisioterapeutaResponse;
import com.fisiomais.bodys.NovaConsultaRequest;
import com.fisiomais.bodys.PacienteResponse;
import com.fisiomais.entities.ConferenceEventData;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.enums.StatusConsulta;
import com.fisiomais.model.indicators.CancelationMetrics;
import com.fisiomais.model.indicators.ConfirmationMetrics;
import com.fisiomais.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final GoogleCalendarService googleCalendarService;

    public ConsultaService(ConsultaRepository consultaRepository, GoogleCalendarService googleCalendarService) {
        this.consultaRepository = consultaRepository;
        this.googleCalendarService = googleCalendarService;
    }

    public List<Consulta> getConsultasForDate(LocalDate start, LocalDate end) {
        Date startOfDay = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return consultaRepository.findByDataEHoraBetween(startOfDay, endOfDay);
    }

    public Consulta updateConsultaStatus(Integer consultaId, StatusConsulta status) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);
        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consulta.setConfirmacao(status);
            return consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Consulta não encontrada para o ID: " + consultaId);
        }
    }

    public ConsultaResponse addConsulta(Consulta consulta) {
        if (consulta.getFisioterapeuta().getAutomatic()) {
            ConferenceEventData consultaCriada = googleCalendarService.criarEventoConsulta(consulta);
            consulta.setGoogleEventId(consultaCriada.eventId());
            consulta.setLink(consultaCriada.meetLink());
        }

        Consulta consultaSalva = consultaRepository.save(consulta);
        return toConsultaResponse(consultaSalva);
    }

    public void deleteConsulta(Integer consultaId) {
        consultaRepository.deleteById(consultaId);
    }

    public Consulta getConsultaById(Integer consultaId) {
        return consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o ID: " + consultaId));
    }

    public List<Consulta> getConsultasByStatus(StatusConsulta status) {
        return consultaRepository.findByConfirmacao(status);
    }

    public List<Consulta> getConsultasByPacienteId(Integer pacienteId) {
        return consultaRepository.findByPaciente_Id(pacienteId);
    }

    public Consulta marcarConsultaComoConcluida(Integer consultaId) {
        Consulta consulta = getConsultaById(consultaId);
        consulta.setConfirmacao(StatusConsulta.realizado);
        return consultaRepository.save(consulta);
    }

    public Consulta cancelarConsulta(Integer consultaId) {
        Consulta consulta = getConsultaById(consultaId);
        consulta.setConfirmacao(StatusConsulta.cancelado);
        return consultaRepository.save(consulta);
    }

    public Consulta reagendarConsulta(Integer consultaId, Date novaDataHora) {
        Consulta consulta = getConsultaById(consultaId);
        consulta.setDataEHora(novaDataHora);
        return consultaRepository.save(consulta);
    }

    public void notificarPaciente(Integer consultaId, String mensagem) {
    }

    public Consulta confirmarPresenca(Integer consultaId) {
        Consulta consulta = getConsultaById(consultaId);
        consulta.setConfirmacao(StatusConsulta.confirmado);
        return consultaRepository.save(consulta);
    }

    public Consulta registrarFeedback(Integer consultaId, String feedback) {
        Consulta consulta = getConsultaById(consultaId);
        consulta.setObservacoes(feedback);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public ConsultaResponse toConsultaResponse(Consulta consulta) {
        String obsevacoesConsulta = consulta.getObservacoes() != null
                ? new String(consulta.getObservacoes().getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8)
                : null;
        return new ConsultaResponse(
                consulta.getPaciente().getId(),
                consulta.getFisioterapeuta().getId(),
                consulta.getDataEHora(),
                obsevacoesConsulta,
                consulta.getConfirmacao(),
                consulta.getLink());
    }

    public List<ConsultaResponseAgenda> toConsultaResponseAgenda(List<Consulta> consultaList) {
        List<ConsultaResponseAgenda> consultasResponse = new ArrayList<>();
        for (Consulta c : consultaList) {
            consultasResponse.add(ConsultaResponseAgenda.toResponse(c));
        }
        return consultasResponse;
    }

    public List<Consulta> getConsultasByFisioterapeuta(Integer fisioterapeutaId) {
        return consultaRepository.findByFisioterapeutaId(fisioterapeutaId);
    }

    public double getTaxaConclusao() {
        return consultaRepository.calculateTaxaConclusao();
    }

    public double getTaxaReagendamento() {
        return consultaRepository.calculateTaxaReagendamento();
    }

    public ConfirmationMetrics getTaxaConfirmacao(Integer mes, Integer ano) {
        return consultaRepository.getConfirmationMetricsForMonthAndYear(mes, ano);
    }

    public CancelationMetrics getTaxaCancelamento() {
        return consultaRepository.getCancelationMetrics();
    }
}
