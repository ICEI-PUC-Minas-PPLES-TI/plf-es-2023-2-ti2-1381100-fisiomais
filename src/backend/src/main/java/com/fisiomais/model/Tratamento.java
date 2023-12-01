package com.fisiomais.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "tratamento")
public class Tratamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", referencedColumnName = "_id")
    private Fisioterapeuta fisioterapeuta;

    @ManyToOne
    @JoinColumn(name = "paciente__id", referencedColumnName = "_id")
    private Paciente paciente;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "end_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Lob
    @Column(name = "feedback")
    private String feedback;

    @ManyToMany
    @JoinTable(name = "tratamento_has_exercicios", joinColumns = {
            @JoinColumn(name = "tratamento__id") }, inverseJoinColumns = {
                    @JoinColumn(name = "exercicio__id")
            })
    private List<Exercicio> exercicios;
}
