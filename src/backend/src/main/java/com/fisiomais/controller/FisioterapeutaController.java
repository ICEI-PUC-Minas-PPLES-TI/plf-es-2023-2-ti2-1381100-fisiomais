package com.fisiomais.controller;

import com.fisiomais.bodys.FisioterapeutaNamesAndIdsResponse;
import com.fisiomais.dto.FisioterapeutaDTO;
import com.fisiomais.dto.indicators.NovosFisioterapeutasMetricsDTO;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.service.FisioterapeutaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.indicators.MidiaTypesMetrics;
import com.fisiomais.model.indicators.NovosFisioterapeutasMetrics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/fisioterapeuta")
@SecurityRequirement(name = "Bearer Authentication")
public class FisioterapeutaController {

    private final FisioterapeutaService fisioterapeutaService;

    public FisioterapeutaController(FisioterapeutaService fisioterapeutaService) {
        this.fisioterapeutaService = fisioterapeutaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Fisioterapeuta>> getAllFisioterapeutas() {
        List<Fisioterapeuta> fisioterapeutas = fisioterapeutaService.findAll();
        return ResponseEntity.ok(fisioterapeutas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fisioterapeuta>> getFisioterapeutaById(@PathVariable Integer id) {
        Optional<Fisioterapeuta> fisioterapeuta = fisioterapeutaService.findById(id);
        return ResponseEntity.ok(fisioterapeuta);
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<FisioterapeutaNamesAndIdsResponse>> getAllFisioterapeutasNames() {
        List<FisioterapeutaNamesAndIdsResponse> fisioterapeutas = fisioterapeutaService.findAllNames();
        return new ResponseEntity<>(fisioterapeutas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fisioterapeuta> createFisioterapeuta(@RequestBody FisioterapeutaDTO fisioterapeutaDTO) {
        Fisioterapeuta newFisioterapeuta = fisioterapeutaService.create(fisioterapeutaDTO);
        return ResponseEntity.ok(newFisioterapeuta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fisioterapeuta> updateFisioterapeuta(@PathVariable Integer id,
            @RequestBody FisioterapeutaDTO fisioterapeutaDTO) {
        Fisioterapeuta updatedFisioterapeuta = fisioterapeutaService.update(id, fisioterapeutaDTO);
        return ResponseEntity.ok(updatedFisioterapeuta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFisioterapeuta(@PathVariable Integer id) {
        fisioterapeutaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/taxaCrescimento")
    public ResponseEntity<Double> getTaxaCrescimento() {
        return ResponseEntity.ok(fisioterapeutaService.getTaxaCrescimento());
    }

    @GetMapping("/indicePerfisCompletos")
    public ResponseEntity<Double> getIndicePerfisCompletos() {
        return ResponseEntity.ok(fisioterapeutaService.getIndicePerfisCompletos());
    }
}
