package com.fisiomais.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FisioterapeutaDTO implements Serializable {
    private Long id;
    private String nome;
    private String registroProfissional;
    private String especialidade;

}
