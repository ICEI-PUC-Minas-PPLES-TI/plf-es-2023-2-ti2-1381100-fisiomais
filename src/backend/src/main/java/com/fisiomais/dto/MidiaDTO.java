package com.fisiomais.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import com.fisiomais.model.enums.TipoArquivo;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MidiaDTO implements Serializable{
    private Integer id;
    private Integer fisioterapeutaId;
    private Date createTime;
    private TipoArquivo type;
    private byte[] arquivo;
    private String linkArquivo;
    private String titulo;
    private String descricao;
}
