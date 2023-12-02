package com.fisiomais.controller;

import com.fisiomais.model.AcompanhamentoVirtual;
import com.fisiomais.service.AcompanhamentoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/acompanhamento")
public class AcompanhamentoVirtualController {

    @Autowired
    private AcompanhamentoService AcompanhamentoService;

    @PostMapping
    public ResponseEntity<AcompanhamentoVirtual> criarAcompanhamento(@RequestBody AcompanhamentoVirtual acompanhamento) {
        try {
            AcompanhamentoVirtual salvo = AcompanhamentoService.salvar(acompanhamento);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<AcompanhamentoVirtual>> listarTodos() {
        List<AcompanhamentoVirtual> acompanhamentos = AcompanhamentoService.buscarTodos();
        return new ResponseEntity<>(acompanhamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcompanhamentoVirtual> buscarPorId(@PathVariable Long id) {
        Optional<AcompanhamentoVirtual> acompanhamento = AcompanhamentoService.buscarPorId(id);
        return acompanhamento
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcompanhamentoVirtual> atualizarAcompanhamento(@PathVariable Long id, @RequestBody AcompanhamentoVirtual acompanhamento) {
        try {
            AcompanhamentoVirtual atualizado = AcompanhamentoService.atualizar(id, acompanhamento);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcompanhamento(@PathVariable Long id) {
        try {
            AcompanhamentoService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taxaSatisfacao")
    public ResponseEntity<Double> taxaSatisfacao() {
        Double taxa = AcompanhamentoService.getTaxaSatisfacao();
        return new ResponseEntity<>(taxa, HttpStatus.OK);
    }

    @GetMapping("/indiceAcompanhamento")
    public ResponseEntity<Double> indiceAcompanhamento() {
        Double indice = AcompanhamentoService.getIndiceAcompanhamento();
        return new ResponseEntity<>(indice, HttpStatus.OK);
    }
}
