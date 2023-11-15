package com.fisiomais.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.bodys.FisioterapeutaResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "fisioterapeuta")
public class Fisioterapeuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
    private Date create_time;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(length = 200)
    private String endereco;

    @Column(name = "controle_automatico", nullable = false)
    private Boolean automatic;
}
