package com.fisiomais.bodys;

import java.nio.charset.StandardCharsets;

import com.fisiomais.model.Fisioterapeuta;

public record FisioterapeutaResponse(
                Integer id,
                String nome,
                String email,
                String telefone,
                String endereco,
                Boolean automatic) {

        public static FisioterapeutaResponse toFisioterapeutaResponse(Fisioterapeuta fisioterapeuta) {
                return new FisioterapeutaResponse(
                                fisioterapeuta.getId(),
                                new String(fisioterapeuta.getNome().getBytes(StandardCharsets.ISO_8859_1),
                                                StandardCharsets.UTF_8),
                                fisioterapeuta.getEmail(),
                                fisioterapeuta.getTelefone(),
                                new String(fisioterapeuta.getEndereco().getBytes(StandardCharsets.ISO_8859_1),
                                                StandardCharsets.UTF_8),
                                fisioterapeuta.getAutomatic());
        }
}
