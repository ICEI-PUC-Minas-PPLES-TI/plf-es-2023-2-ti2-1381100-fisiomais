package com.fisiomais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fisiomais.dto.MidiaDTO;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Midia;
import com.fisiomais.model.indicators.MidiaTypesMetrics;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.service.MidiaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/midia")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Mídias", description = "Endpoint para gerenciar mídias controladas pelos fisioterapeutas")
public class MidiaController {

    private final MidiaService midiaService;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public MidiaController(MidiaService midiaService, FisioterapeutaRepository fisioterapeutaRepository) {
        this.midiaService = midiaService;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @GetMapping
    @Operation(summary = "Obter todas as mídias", description = "Obter uma lista de todas as mídias.")
    public ResponseEntity<List<MidiaDTO>> getAllMidias() {
        List<MidiaDTO> midias = midiaService.getAllMidias();
        return new ResponseEntity<>(midias, HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    @Operation(summary = "Obter mídias por ID do Fisioterapeuta", description = "Obter mídias associadas a um Fisioterapeuta específico pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Fisioterapeuta não encontrado")
    public ResponseEntity<List<Midia>> getMidiaByFisioterapeuta(@PathVariable Integer id) {
        Optional<Fisioterapeuta> optionalFisioterapeuta = fisioterapeutaRepository.findById(id);

        System.out.println("Found this fisioterapeuta: " + optionalFisioterapeuta);

        if (optionalFisioterapeuta.isPresent()) {
            Fisioterapeuta fisioterapeuta = optionalFisioterapeuta.get();
            List<Midia> midiaList = midiaService.getMidiaByFisioterapeuta(fisioterapeuta);
            return new ResponseEntity<>(midiaList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter mídia por ID", description = "Obter um item de mídia específico com base no seu ID.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    @ApiResponse(responseCode = "404", description = "Mídia não encontrada")
    public ResponseEntity<MidiaDTO> getMidiaById(@PathVariable Integer id) {
        MidiaDTO midia = midiaService.getMidiaById(id);
        if (midia != null) {
            return new ResponseEntity<>(midia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Criar nova mídia", description = "Criar um novo item de mídia e retornar a mídia criada.")
    @ApiResponse(responseCode = "201", description = "Mídia criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de mídia inválidos fornecidos")
    public ResponseEntity<MidiaDTO> createMidia(@RequestBody MidiaDTO midiaDTO) {
        MidiaDTO createdMidia = midiaService.createMidia(midiaDTO);
        if (createdMidia.getArquivo() != null && createdMidia.getLinkArquivo() != null ||
                createdMidia.getArquivo() == null && createdMidia.getLinkArquivo() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdMidia, HttpStatus.CREATED);
    }

    @DeleteMapping("{ids}")
    @Operation(summary = "Excluir mídia", description = "Excluir um ou mais itens de mídia com base nos seus IDs.")
    @ApiResponse(responseCode = "200", description = "Mídia excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Mídia não encontrada")
    public ResponseEntity<Void> deleteMidia(@PathVariable List<Integer> ids) {
        System.out.println("Deleting midia with IDs: " + ids);
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException("No media IDs provided. Accepted format: /midia/12,78,5,4,1,2");
        }
        boolean deleted = midiaService.deleteMidia(ids);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/taxa-utilizacao")
    @Operation(summary = "Obter taxa de criação de tipos de mídia", description = "Obter a taxa de criação de tipos de mídia em comparação com o total de mídias.")
    @ApiResponse(responseCode = "200", description = "Operação bem-sucedida")
    public ResponseEntity<MidiaTypesMetrics> getTaxaUtilizacao() {
        try {
            List<MidiaTypesMetrics> taxaUtilizacao = midiaService.getTaxaUtilizacao();
            return new ResponseEntity<>(taxaUtilizacao.get(0), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
