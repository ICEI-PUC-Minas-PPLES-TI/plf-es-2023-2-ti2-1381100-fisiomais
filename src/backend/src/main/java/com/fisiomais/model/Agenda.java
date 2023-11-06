package com.fisiomais.model;

import lombok.Data;

import java.sql.Date;
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

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;

    @Column(name = "dia", nullable = false)
    private Byte dia;

    @Column(name = "horario_inicio")
    private Time horarioInicio;

    @Column(name = "horario_fim")
    private Time horarioFim;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", nullable = false)
    private Fisioterapeuta fisioterapeuta;
}

