package com.fisiomais.controller;

import com.fisiomais.dto.FisioterapeutaDTO;
import com.fisiomais.service.FisioterapeutaService;
import com.fisiomais.model.Fisioterapeuta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fisioterapeutas")
public class FisioterapeutaController {

    private final FisioterapeutaService fisioterapeutaService;

    public FisioterapeutaController(FisioterapeutaService fisioterapeutaService) {
        this.fisioterapeutaService = fisioterapeutaService;
    }

    @GetMapping
    public ResponseEntity<List<Fisioterapeuta>> getAllFisioterapeutas() {
        List<Fisioterapeuta> fisioterapeutas = fisioterapeutaService.findAll();
        return ResponseEntity.ok(fisioterapeutas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fisioterapeuta> getFisioterapeutaById(@PathVariable Long id) {
        Optional<Fisioterapeuta> fisioterapeutaOpt = fisioterapeutaService.findById(id);
        return fisioterapeutaOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Fisioterapeuta> createFisioterapeuta(@RequestBody FisioterapeutaDTO fisioterapeutaDTO) {
        Fisioterapeuta fisioterapeuta = convertToEntity(fisioterapeutaDTO);
        Fisioterapeuta newFisioterapeuta = fisioterapeutaService.save(fisioterapeuta);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFisioterapeuta);
    }

    private Fisioterapeuta convertToEntity(FisioterapeutaDTO fisioterapeutaDTO) {
        // Aqui você implementaria a lógica para converter um DTO para uma entidade
        // Pode ser algo assim, dependendo da estrutura do seu DTO e da sua entidade:
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        fisioterapeuta.setNome(fisioterapeutaDTO.getNome());
        // Sete os outros campos conforme necessário
        return fisioterapeuta;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFisioterapeuta(@PathVariable Long id) {
        fisioterapeutaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
