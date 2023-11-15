package com.fisiomais.controller;

import com.fisiomais.bodys.AgendaResponse;
import com.fisiomais.model.Agenda;
import com.fisiomais.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
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
    public ResponseEntity<Agenda> createAgenda(@RequestBody Agenda agenda) {
        Agenda newAgenda = agendaService.saveAgenda(agenda);
        return new ResponseEntity<>(newAgenda, HttpStatus.CREATED);
    }

    @PutMapping("/{agendaId}")
    public ResponseEntity<Agenda> updateAgenda(@PathVariable Integer agendaId, @RequestBody Agenda agenda) {
        agenda.setId(agendaId);
        Agenda updatedAgenda = agendaService.updateAgenda(agenda);
        return ResponseEntity.ok(updatedAgenda);
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
