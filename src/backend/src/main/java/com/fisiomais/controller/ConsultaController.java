package com.fisiomais.controller;

import com.fisiomais.bodys.ConsultaResponse;
import com.fisiomais.bodys.NovaConsultaRequest;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.enums.StatusConsulta;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.service.ConsultaService;
import com.fisiomais.util.ConsultaUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/consulta")
@Tag(name = "Consultas", description = "Endpoints para gerenciamento de consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaUtil consultaUtil;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    @Autowired
    public ConsultaController(ConsultaService consultaService, ConsultaUtil consultaUtil,
            FisioterapeutaRepository fisioterapeutaRepository) {
        this.consultaService = consultaService;
        this.consultaUtil = consultaUtil;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @GetMapping("/all")
    @Operation(summary = "Obter todas as consultas", description = "Obter uma lista de todas as consultas cadastradas no sistema.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Consulta>> getAllConsultas() {
        List<Consulta> consultas = consultaService.getAllConsultas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/date")
    @Operation(summary = "Obter consultas por data", description = "Obter uma lista de consultas com base em um range de datas.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "400", description = "Datas inválidas")
    public ResponseEntity<List<Consulta>> getConsultasForDateRange(
            @Parameter(name = "startDate", description = "Data inicio para pesquisa", example = "10-10-2021") @RequestParam String startDate,
            @Parameter(name = "endDate", description = "Data final para pesquisa", example = "14-10-2024") @RequestParam String endDate) {
        try {
            LocalDate parsedDateStart = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate parsedDateEnd = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            List<Consulta> consultas = consultaService.getConsultasForDate(parsedDateStart, parsedDateEnd);
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException("Datas inválidas. Formato esperado: dd-MM-yyyy");
        }
    }

    @GetMapping("/id/{consultaId}")
    @Operation(summary = "Obter consulta por ID", description = "Obter uma consulta específica com base no seu ID.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    public ResponseEntity<Consulta> getConsultaById(
            @Parameter(name = "consultaId", description = "Id da consulta a ser pesquisada") @PathVariable Integer consultaId) {
        Consulta consulta = consultaService.getConsultaById(consultaId);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Obter consultas por status", description = "Obter uma lista de consultas com base em um status.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "400", description = "Status inválido")
    public ResponseEntity<List<Consulta>> getConsultasByStatus(
            @Parameter(name = "status", description = "Status da consulta a ser pesquisada", examples = @ExampleObject(name = "Status Válidos", value = "pendente, confirmado, cancelado, realizado")) @PathVariable(required = true) String status) {
        StatusConsulta statusConsulta = consultaUtil.convertToStatusConsulta(status);

        if (statusConsulta != null) {
            List<Consulta> consultas = consultaService.getConsultasByStatus(statusConsulta);
            return new ResponseEntity<>(consultas, HttpStatus.OK);
        } else {
            throw new IllegalArgumentException(
                    "Status inválido. Valores aceitos: pendente, confirmado, cancelado, realizado");
        }
    }

    @GetMapping("/status/{status}/fisioterapeuta/{fisioterapeutaId}")
    @Operation(summary = "Obter consultas por status e fisioterapeuta", description = "Obter uma lista de consultas com base em um status e fisioterapeuta.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "400", description = "Status ou fisioterapeuta inválido")
    public ResponseEntity<List<Consulta>> getConsultasByStatus(
            @Parameter(name = "status", description = "Status da consulta a ser pesquisada", examples = @ExampleObject(name = "Status Válidos", value = "pendente, confirmado, cancelado, realizado")) @PathVariable(required = true) String status,
            @Parameter(name = "fisioterapeutaId", description = "Id do fisioterapeuta a ser pesquisado") @PathVariable(required = false) Integer fisioterapeutaId) {
        StatusConsulta statusConsulta = consultaUtil.convertToStatusConsulta(status);

        // Verificar se o fisioterapeuta existe
        fisioterapeutaRepository
                .findById(fisioterapeutaId)
                .orElseThrow(() -> new BusinessException("Fisioterapeuta não encontrado"));

        if (statusConsulta != null) {
            List<Consulta> consultas = consultaService.getConsultasByStatus(statusConsulta);
            if (fisioterapeutaId != null) {
                consultas.removeIf(consulta -> consulta.getFisioterapeuta().getId() != fisioterapeutaId);
                return new ResponseEntity<>(consultas, HttpStatus.OK);
            }
        } else {
            throw new IllegalArgumentException(
                    "Status inválido. Valores aceitos: pendente, confirmado, cancelado, realizado");
        }
        return null;
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}")
    @Operation(summary = "Obter consultas por fisioterapeuta", description = "Obter uma lista de consultas com base em um fisioterapeuta.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "400", description = "Fisioterapeuta inválido")
    public ResponseEntity<List<Consulta>> getConsultasByFisioterapeutaId(
            @Parameter(name = "fisioterapeutaId", description = "Id do fisioterapeuta a ser pesquisado") @PathVariable(required = false) Integer fisioterapeutaId) {
        // Verificar se o fisioterapeuta existe
        fisioterapeutaRepository
                .findById(fisioterapeutaId)
                .orElseThrow(() -> new BusinessException("Fisioterapeuta não encontrado"));

        return new ResponseEntity<>(consultaService.getConsultasByFisioterapeuta(fisioterapeutaId), HttpStatus.OK);
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Obter consultas por ID do Paciente", description = "Obter uma lista de consultas associadas a um Paciente específico pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    public ResponseEntity<List<Consulta>> getConsultasByPacienteId(
            @Parameter(name = "pacienteId", description = "Id do paciente a ser pesquisado") @PathVariable Integer pacienteId) {
        List<Consulta> consultas = consultaService.getConsultasByPacienteId(pacienteId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Criar nova consulta", description = "Criar uma nova consulta e retornar a consulta criada.")
    @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)))
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    public ResponseEntity<ConsultaResponse> addConsulta(
            @Parameter(name = "Consulta", description = "Consulta a ser criada") @RequestBody NovaConsultaRequest consulta) {
        try {
            Consulta novaConsultaMapped = consultaUtil.convertToConsulta(consulta);
            ConsultaResponse newConsulta = consultaService.addConsulta(novaConsultaMapped);
            return new ResponseEntity<>(newConsulta, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PatchMapping("/status/{consultaId}")
    @Operation(summary = "Atualizar status da consulta", description = "Atualizar o status de uma consulta específica e retornar a consulta atualizada.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Consulta.class)))
    @ApiResponse(responseCode = "400", description = "Status inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    public ResponseEntity<Consulta> updateConsultaStatus(
            @Parameter(name = "consultaId", description = "Id da consulta a ser atualizada") @PathVariable Integer consultaId,
            @Parameter(name = "Status", description = "Status da consulta a ser atualizada", examples = @ExampleObject(name = "Status Válidos", value = "pendente, confirmado, cancelado, realizado")) @RequestBody String status) {
        try {
            StatusConsulta statusConsulta = consultaUtil.convertToStatusConsulta(status);
            Consulta consulta = consultaService.updateConsultaStatus(consultaId, statusConsulta);
            return new ResponseEntity<>(consulta, HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{consultaId}")
    @Operation(summary = "Deletar consulta", description = "Deletar uma consulta específica.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    public ResponseEntity<?> deleteConsulta(
            @Parameter(name = "consultaId", description = "Id da consulta a ser deletada") @PathVariable Integer consultaId) {
        consultaService.deleteConsulta(consultaId);
        return ResponseEntity.ok().build();
    }
}
