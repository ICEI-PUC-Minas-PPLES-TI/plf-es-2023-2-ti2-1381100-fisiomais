package com.fisiomais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisiomais.model.Tratamento;

import com.fisiomais.service.TratamentoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/tratamento")
@Tag(name = "Tratamento", description = "Tratamento API")
public class TratamentoController {

    @Autowired
    private TratamentoService tratamentoService;     

    @PostMapping
    public ResponseEntity<Tratamento> createTratamento(@RequestBody Tratamento tratamento) {
        Tratamento newTratamento = tratamentoService.createTratamento(tratamento);
        return new ResponseEntity<>(newTratamento, HttpStatus.CREATED);
    }

    @GetMapping("/pac  ")
    public ResponseEntity <List<Tratamento>> findByPacienteId(@PathVariable Integer id){
        List<Tratamento> obj = this.tratamentoService.findByPacienteId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/fisioterapeuta/{id}")
    public ResponseEntity <List<Tratamento>> findByFisioterapeutaId(@PathVariable Integer id){
    List<Tratamento> obj = this.tratamentoService.findByFisioterapeutaId(id);
    return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<Tratamento> findTratamentoByTitulo(@PathVariable String titulo){
    Tratamento obj = this.tratamentoService.findTratamentoByTitulo(titulo);
    return ResponseEntity.ok().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamento> putTratamento(@PathVariable Integer id, @RequestBody Tratamento tratamento) {
        tratamento.setId(id);
        this.tratamentoService.update(tratamento);
        return ResponseEntity.ok().body(tratamento);
    }
}

