package com.fisiomais.model;

import lombok.Data;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "plano")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date create_time;

    @Column(length = 500, nullable = false)
    private String tituloTexto;

    @Column(length = 500, nullable = false)
    private String conteudoTexto;

    @Column(nullable = false, name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date dataInicio;

    @Column(nullable = false)
    private String frequencia;

    @Column(nullable = false)
    private Integer[] midias;

    @Column(nullable = false)
    private String formatacao;
}
