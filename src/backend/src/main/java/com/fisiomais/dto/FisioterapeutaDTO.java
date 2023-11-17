package com.fisiomais.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FisioterapeutaDTO implements Serializable {
    private Integer id;
    private String nome;
    private String email;
    private String password;
    private String telefone;
    private String endereco;
    private Boolean controleAutomatico;
}
