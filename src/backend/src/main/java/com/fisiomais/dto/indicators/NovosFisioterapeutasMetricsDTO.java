package com.fisiomais.dto.indicators;

import com.fisiomais.model.enums.Mes;
import com.fisiomais.model.indicators.NovosFisioterapeutasMetrics;

public record NovosFisioterapeutasMetricsDTO(
        String mes,
        Long numNovosCadastros) {

    static public NovosFisioterapeutasMetricsDTO toDTO(NovosFisioterapeutasMetrics metrics) {
        return new NovosFisioterapeutasMetricsDTO(
            Mes.toEnum(metrics.getMes()).getNome(),
            metrics.getNumNovosCadastros()
        );
    }
}
