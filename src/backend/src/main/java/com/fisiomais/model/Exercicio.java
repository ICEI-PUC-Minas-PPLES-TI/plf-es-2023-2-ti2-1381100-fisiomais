package com.fisiomais.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "exercicio")
public class Exercicio {

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
    @JoinColumn(name = "fisioterapeuta__id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "exercicio_has_midias",
            joinColumns = {@JoinColumn(name = "exercicio__id")},
            inverseJoinColumns = {@JoinColumn(name = "midia__id", referencedColumnName = "_id"),
                    @JoinColumn(name = "midia_fisioterapeuta__id", referencedColumnName = "fisioterapeuta__id")}
    )
    private List<Midia> midias;
}