package com.fisiomais.model.indicators;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelationMetrics {
    @Column(name = "total_consultas")
    private Long totalConsultas;
    @Column(name = "consultas_canceladas")
    private Long consultasCanceladas;
    @Column(name = "taxa_cancelamento")
    private Float taxaCancelamento;
}