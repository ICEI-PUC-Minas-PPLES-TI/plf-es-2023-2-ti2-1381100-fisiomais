package com.fisiomais.bodys;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;

public record UserCredentialsResponse(
        Integer id,
        String nome,
        String email,
        String telefone,
        String endereco,
        Boolean controle_automatico,
        String role) {
    public static UserCredentialsResponse parsePaciente(Paciente paciente) {
        return new UserCredentialsResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getEndereco(),
                false,
                "paciente");
    }

    public static UserCredentialsResponse parseFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        return new UserCredentialsResponse(
                fisioterapeuta.getId(),
                fisioterapeuta.getNome(),
                fisioterapeuta.getEmail(),
                fisioterapeuta.getTelefone(),
                fisioterapeuta.getEndereco(),
                fisioterapeuta.getAutomatic(),
                "fisioterapeuta");
    }
}
