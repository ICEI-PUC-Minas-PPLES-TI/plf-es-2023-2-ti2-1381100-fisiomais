package com.fisiomais.controller;

import com.fisiomais.dto.AgendaRequest;
import com.fisiomais.bodys.AgendaResponse;
import com.fisiomais.model.Agenda;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.service.AgendaService;
import com.fisiomais.service.FisioterapeutaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final FisioterapeutaService fisioterapeutaService;

    @Autowired
    public AgendaController(AgendaService agendaService, FisioterapeutaService fisioterapeutaService) {
        this.agendaService = agendaService;
        this.fisioterapeutaService = fisioterapeutaService;
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}")
    public ResponseEntity<List<AgendaResponse>> getAgendasByFisioterapeuta(@PathVariable Integer fisioterapeutaId) {
        List<AgendaResponse> agendas = agendaService.getAgendasByFisioterapeuta(fisioterapeutaId);
        return new ResponseEntity<>(agendas, HttpStatus.OK);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}/dia/{dia}")
    public ResponseEntity<List<Agenda>> getAgendasByDiaAndFisioterapeuta(@PathVariable Byte dia, @PathVariable Integer fisioterapeutaId) {
        List<Agenda> agendas = agendaService.getAgendasByDiaAndFisioterapeuta(dia, fisioterapeutaId);
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}/disponibilidade")
    public ResponseEntity<List<Agenda>> getAgendasByDisponibilidadeAndFisioterapeuta(@RequestParam Boolean disponivel, @PathVariable Integer fisioterapeutaId) {
        List<Agenda> agendas = agendaService.getAgendasByDisponibilidadeAndFisioterapeuta(disponivel, fisioterapeutaId);
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}/dia/{dia}/horario")
    public ResponseEntity<List<Agenda>> getAgendasByFisioterapeutaDiaEHorario(
            @PathVariable Integer fisioterapeutaId,
            @PathVariable Byte dia,
            @RequestParam Time horarioInicio,
            @RequestParam Time horarioFim) {
        List<Agenda> agendas = agendaService.getAgendasByFisioterapeutaDiaEHorario(fisioterapeutaId, dia, horarioInicio, horarioFim);
        return ResponseEntity.ok(agendas);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}/dia/{dia}/disponivel")
    public ResponseEntity<List<Agenda>> getAgendasDisponiveisByFisioterapeutaAndDia(
            @PathVariable Integer fisioterapeutaId,
            @PathVariable Byte dia,
            @RequestParam Boolean disponivel) {
        List<Agenda> agendas = agendaService.getAgendasDisponiveisByFisioterapeutaAndDia(fisioterapeutaId, dia, disponivel);
        return ResponseEntity.ok(agendas);
    }

    @PostMapping
    public ResponseEntity<?> createAgenda(@RequestBody @Valid AgendaRequest agendaRequest) {
        try {
            System.out.println("Fisioterapeuta ID recebido: " + agendaRequest.getFisioterapeutaId());

            if (agendaRequest.getFisioterapeutaId() == null) {
                return ResponseEntity.badRequest().body("O ID do fisioterapeuta não pode ser nulo.");
            }
            Fisioterapeuta fisioterapeuta = fisioterapeutaService.findById(agendaRequest.getFisioterapeutaId())
                    .orElseThrow(() -> new IllegalArgumentException("Fisioterapeuta não encontrado com o ID: " + agendaRequest.getFisioterapeutaId()));

            Agenda agenda = new Agenda();
            agenda.setFisioterapeuta(fisioterapeuta);
            agenda.setDia(agendaRequest.getDia());
            agenda.setHorarioInicio(agendaRequest.getHorarioInicioAsTime());
            agenda.setHorarioFim(agendaRequest.getHorarioFimAsTime());
            agenda.setDisponivel(agendaRequest.getDisponivel());

            Agenda newAgenda = agendaService.saveAgenda(agenda);
            return ResponseEntity.ok(newAgenda);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro ao criar a agenda.");
        }
    }
    @PutMapping("/{agendaId}")
    public ResponseEntity<Agenda> updateAgenda(@PathVariable Integer agendaId, @RequestBody Agenda updatedAgenda) {
        Agenda existingAgenda = agendaService.getAgendaById(agendaId);

        Fisioterapeuta fisioterapeuta = existingAgenda.getFisioterapeuta();

        existingAgenda.setDia(updatedAgenda.getDia());
        existingAgenda.setHorarioInicio(updatedAgenda.getHorarioInicio());
        existingAgenda.setHorarioFim(updatedAgenda.getHorarioFim());
        existingAgenda.setDisponivel(updatedAgenda.getDisponivel());

        existingAgenda.setFisioterapeuta(fisioterapeuta);

        Agenda savedAgenda = agendaService.updateAgenda(existingAgenda);

        return ResponseEntity.ok(savedAgenda);
    }


    @GetMapping("/{agendaId}")
    public ResponseEntity<Agenda> getAgendaById(@PathVariable Integer agendaId) {
        Agenda agenda = agendaService.getAgendaById(agendaId);
        return ResponseEntity.ok(agenda);
    }

    @DeleteMapping("/{agendaId}")
    public ResponseEntity<?> deleteAgenda(@PathVariable Integer agendaId) {
        agendaService.deleteAgenda(agendaId);
        return ResponseEntity.ok().build();
    }
}
