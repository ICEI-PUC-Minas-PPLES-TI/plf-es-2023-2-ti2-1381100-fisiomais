package com.fisiomais.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
class ExercicioHasMidiasKey implements Serializable {
    @Column(name = "midia__id")
    private Integer midiaId;

    @Column(name = "midia_fisioterapeuta__id")
    private Integer midiaFisioterapeutaId;

    @Column(name = "exercicio__id")
    private Integer exercicioId;
}

@Data
@Entity
@Table(name = "exercicio_has_midias")
public class ExercicioHasMidias {
    @EmbeddedId
    private ExercicioHasMidiasKey id;

    @ManyToOne
    @MapsId("midiaId")
    @JoinColumn(name = "midia__id")
    private Midia midia;

    @ManyToOne
    @MapsId("midiaFisioterapeutaId")
    @JoinColumn(name = "midia_fisioterapeuta__id")
    private Midia midiaFisioterapeuta;

    @ManyToOne
    @MapsId("exercicioId")
    @JoinColumn(name = "exercicio__id")
    private Exercicio exercicio;
}