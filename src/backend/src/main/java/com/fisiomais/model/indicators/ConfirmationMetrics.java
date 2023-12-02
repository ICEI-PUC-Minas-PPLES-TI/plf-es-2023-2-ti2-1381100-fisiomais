package com.fisiomais.model.indicators;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationMetrics {
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "ano")
    private Integer ano;
    @Column(name = "total_consultas")
    private Long totalConsultas;
    @Column(name = "consultas_confirmadas")
    private Long consultasConfirmadas;
    @Column(name = "taxa_canceladas")
    private Float taxaConfirmacao;
}