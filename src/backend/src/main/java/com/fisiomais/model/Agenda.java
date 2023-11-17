package com.fisiomais.model;

import lombok.Data;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date createTime;

    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;

    @Column(name = "dia", nullable = false)
    private Byte dia;

    @Column(name = "horario_inicio")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time horarioInicio;

    @Column(name = "horario_fim")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time horarioFim;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", nullable = false)
    private Fisioterapeuta fisioterapeuta;
}

