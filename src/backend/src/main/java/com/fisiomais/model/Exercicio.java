package com.fisiomais.model;

import lombok.Data;
import java.sql.Date;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao", nullable = false)
    private String descricao;
}