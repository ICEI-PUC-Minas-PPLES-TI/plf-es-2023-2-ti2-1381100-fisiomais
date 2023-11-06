package com.fisiomais.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @ManyToOne
    @JoinColumn(name = "paciente__id", nullable = false)
    private Paciente paciente;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "end_date")
    private Date endDate;

    @Lob
    @Column(name = "feedback")
    private String feedback;
}
