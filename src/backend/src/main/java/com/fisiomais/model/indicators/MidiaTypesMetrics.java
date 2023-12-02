package com.fisiomais.model.indicators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MidiaTypesMetrics {
    private Long totalMidias;
    private Long totalVideos;
    private Long totalImagens;
    private Long totalGifsLong;
    private Double taxaVideos;
    private Double taxaImagens;
    private Double taxaGifs;
}

