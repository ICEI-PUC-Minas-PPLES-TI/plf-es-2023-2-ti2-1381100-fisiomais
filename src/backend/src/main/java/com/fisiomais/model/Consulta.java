package com.fisiomais.model;

import lombok.Data;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.enums.StatusConsulta;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date create_time;

    @ManyToOne
    @JoinColumn(name = "paciente__id", referencedColumnName = "_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", referencedColumnName = "_id")
    private Fisioterapeuta fisioterapeuta;

    @Column(nullable = false, name = "data_e_hora")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date dataEHora;

    @Column(length = 500, nullable = true)
    private String observacoes;

    @Column(nullable = false, columnDefinition = "ENUM('confirmado', 'cancelado', 'realizado', 'pendente')")
    @Enumerated(EnumType.STRING)
    private StatusConsulta confirmacao;

    @Lob
    private String link;

    @Column(name = "google_event_id")
    private String googleEventId;
}
