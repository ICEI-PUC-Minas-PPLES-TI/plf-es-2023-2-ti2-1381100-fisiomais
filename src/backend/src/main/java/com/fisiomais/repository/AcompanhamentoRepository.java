package com.fisiomais.repository;

import com.fisiomais.model.AcompanhamentoVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AcompanhamentoRepository extends JpaRepository<AcompanhamentoVirtual, Long> {
    @Query(value = "SELECT COUNT(*) / COUNT(DISTINCT MONTH(data_sessao)) FROM acompanhamento_virtual WHERE data_sessao >= DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR)", nativeQuery = true)
    Double indiceAcompanhamento();

    @Query(value = "SELECT CONCAT(ROUND((SUM(CASE WHEN avaliacao IN ('Excelente', 'Bom') THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2)) AS taxa_satisfacao FROM acompanhamento_virtual", nativeQuery = true)
    Double calculateTaxaSatisfacao();
}
