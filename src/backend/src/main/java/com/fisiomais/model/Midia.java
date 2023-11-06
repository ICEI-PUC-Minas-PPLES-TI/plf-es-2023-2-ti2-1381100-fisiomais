package com.fisiomais.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "midia")
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta__id", referencedColumnName = "_id")
    private Fisioterapeuta fisioterapeuta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMidia type;

    @Lob
    private byte[] arquivo;

    @Lob
    private String link_arquivo;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descricao;

    public enum TipoMidia {
        Vídeo, Imagem, GIF
    }
}

