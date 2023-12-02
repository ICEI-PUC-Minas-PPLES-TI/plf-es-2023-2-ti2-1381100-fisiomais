package com.fisiomais.model.indicators;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidiaUtilizationMetrics {
    private Long totalExercicios;
    private Long midiasComExercicios;
    private Double taxaUtilizacao;
}

