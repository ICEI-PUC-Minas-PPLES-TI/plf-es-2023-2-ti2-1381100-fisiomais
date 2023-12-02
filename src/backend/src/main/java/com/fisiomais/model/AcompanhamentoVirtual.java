package com.fisiomais.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acompanhamento_virtual")
public class AcompanhamentoVirtual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_sessao")
    private Date dataSessao;

    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "recursos")
    private String recursos;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "avaliacao")
    private String avaliacao;
}
