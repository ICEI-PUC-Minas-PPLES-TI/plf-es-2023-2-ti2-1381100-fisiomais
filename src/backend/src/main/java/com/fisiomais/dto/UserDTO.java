package com.fisiomais.dto;

import com.fisiomais.enums.UserRole;

public record UserDTO(String nome, String email, String password, String telefone,
        String endereco, UserRole role, Integer id) {
}
