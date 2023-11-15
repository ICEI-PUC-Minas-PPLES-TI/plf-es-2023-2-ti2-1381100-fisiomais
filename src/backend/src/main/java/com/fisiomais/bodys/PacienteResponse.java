package com.fisiomais.bodys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.Paciente;
import com.fisiomais.model.enums.Genero;

public record PacienteResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String endereco,
        @JsonFormat(pattern = "dd/MM/yyyy") Date dataNascimento,
        Genero genero) {

    public static PacienteResponse toPacienteResponse(Paciente paciente) {
        String nome = new String(paciente.getNome().getBytes(StandardCharsets.ISO_8859_1),
                                                StandardCharsets.UTF_8);
        String endereco = new String(paciente.getEndereco().getBytes(StandardCharsets.ISO_8859_1),
                                                StandardCharsets.UTF_8);

        return new PacienteResponse(paciente.getId(),
                nome,
                paciente.getEmail(),
                paciente.getTelefone(),
                endereco,
                paciente.getDataNascimento(),
                paciente.getGenero());
    }
}
