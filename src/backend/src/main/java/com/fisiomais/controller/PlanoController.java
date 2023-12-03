package com.fisiomais.controller;

import com.fisiomais.bodys.PlanoResponse;
import com.fisiomais.bodys.NovoPlanoRequest;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Plano;
import com.fisiomais.service.PlanoService;
import com.fisiomais.util.PlanoUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
@Tag(name = "Planos", description = "Endpoints para gerenciamento de planos")
public class PlanoController {

    private final PlanoService planoService;
    private final PlanoUtil planoUtil;

    @Autowired
    public PlanoController(PlanoService planoService, PlanoUtil planoUtil) {
        this.planoService = planoService;
        this.planoUtil = planoUtil;
    }

    @GetMapping("/all")
    @Operation(summary = "Obter todos as planos", description = "Obter uma lista de todos os planos cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<List<Plano>> getAllPlanos() {
        List<Plano> planos = planoService.getAllPlanos();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/id/{planoId}")
    @Operation(summary = "Obter plano por ID", description = "Obter um plano específica com base no seu ID.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Plano não encontrada")
    public ResponseEntity<Plano> getPlanoById(
            @Parameter(name = "planoId", description = "Id do plano a ser pesquisado") @PathVariable Integer planoId) {
        Plano plano = planoService.getPlanoById(planoId);
        return new ResponseEntity<>(plano, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Criar novo plano", description = "Criar um novo plano e retornar a plano criada.")
    @ApiResponse(responseCode = "201", description = "Plano criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Plano.class)))
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessException.class)))
    public ResponseEntity<PlanoResponse> addPlano(
            @Parameter(name = "Plano", description = "Plano a ser criado") @RequestBody NovoPlanoRequest plano) {
        try {
            Plano novoPlanoMapped = planoUtil.convertToPlano(plano);
            PlanoResponse newPlano = planoService.addPlano(novoPlanoMapped);
            return new ResponseEntity<>(newPlano, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{planoId}")
    @Operation(summary = "Deletar plano", description = "Deletar um plano específico.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Plano não encontrada")
    public ResponseEntity<?> deletePlano(
            @Parameter(name = "planoId", description = "Id do plano a ser deletado") @PathVariable Integer planoId) {
        planoService.deletePlano(planoId);
        return ResponseEntity.ok().build();
    }
}
