package com.fisiomais.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fisiomais.bodys.NovoTratamentoRequest;
import com.fisiomais.bodys.TratamentoResponse;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Tratamento;
import com.fisiomais.model.indicators.MidiaUtilizationMetrics;
import com.fisiomais.service.TratamentoService;
import com.fisiomais.util.TratamentoUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/tratamento")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tratamento", description = "Tratamento API")
public class TratamentoController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TratamentoController.class);
    private final TratamentoService tratamentoService;
    private final TratamentoUtil tratamentoUtil;

    public TratamentoController(TratamentoService tratamentoService, TratamentoUtil tratamentoUtil) {
        this.tratamentoService = tratamentoService;
        this.tratamentoUtil = tratamentoUtil;
    }

    @PostMapping
    @Operation(summary = "Criar novo tratamento para paciente", description = "Criar um novo tratamento.")
    @ApiResponse(responseCode = "201", description = "Tratamento criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tratamento.class)))
    public ResponseEntity<TratamentoResponse> createTratamento(@RequestBody NovoTratamentoRequest tratamento) {
        Tratamento novoTratamentoMapped = tratamentoUtil.convertToTratamento(tratamento);
        TratamentoResponse newTratamento = tratamentoService.createTratamento(novoTratamentoMapped);
        return new ResponseEntity<>(newTratamento, HttpStatus.CREATED);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Tratamento>> findByPacienteId(@PathVariable Integer id) {
        List<Tratamento> obj = this.tratamentoService.findByPacienteId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/fisioterapeuta/{id}")
    public ResponseEntity<List<Tratamento>> findByFisioterapeutaId(@PathVariable Integer id) {
        List<Tratamento> obj = this.tratamentoService.findByFisioterapeutaId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Tratamento> findTratamentoByTitulo(@PathVariable String titulo) {
        Tratamento obj = this.tratamentoService.findTratamentoByTitulo(titulo);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamento> putTratamento(@PathVariable Integer id, @RequestBody Tratamento tratamento) {
        tratamento.setId(id);
        this.tratamentoService.update(tratamento);
        return ResponseEntity.ok().body(tratamento);
    }

    @GetMapping("/taxa-utilizacao")
    @Operation(summary = "Obter taxa de utilização de mídias nos exercícios", description = "Obter a taxa de utilização de mídias nos exercícios.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<MidiaUtilizationMetrics> getTaxaUtilizacao() {
        try {
            List<MidiaUtilizationMetrics> taxaUtilizacao = tratamentoService.getTaxaUtilizacao();
            logger.info("Taxa de utilização:");
            // logger.info("Total de exercicios: {}", taxaUtilizacao.getTotalExercicios());
            // logger.info("Total de midias com exercicios: {}", taxaUtilizacao.getMidiasComExercicios());
            // logger.info("Taxa de utilização: {}%", taxaUtilizacao.getTaxaUtilizacao());
            return new ResponseEntity<>(taxaUtilizacao.get(0), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
