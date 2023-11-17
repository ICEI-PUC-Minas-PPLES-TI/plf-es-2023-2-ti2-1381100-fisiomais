package com.fisiomais.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.fisiomais.model.enums.Genero;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO implements Serializable{
    private Integer id;
    private Date create_time;
    private String nome;
    private String email;
    private String dataNascimento;
    private String cpf;
    private String telefone;
    private Genero genero;
    private String endereco;
    private String password;
}
