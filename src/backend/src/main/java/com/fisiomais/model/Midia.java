package com.fisiomais.model;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.enums.TipoArquivo;

@Data
@Entity
@Table(name = "midia")
public class Midia {
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

    @Column(nullable = false, columnDefinition = "ENUM('Video', 'Imagem', 'GIF')")
    @Enumerated(EnumType.STRING)
    private TipoArquivo type;

    @Lob
    private byte[] arquivo;

    @Column(name = "link_arquivo", length = 1000)
    private String linkArquivo;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descricao;
}
