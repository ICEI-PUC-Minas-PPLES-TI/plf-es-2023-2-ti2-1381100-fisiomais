package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.indicators.NovosFisioterapeutasMetrics;

@Repository
public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Integer> {
    List<Fisioterapeuta> findAll();

    Page<Fisioterapeuta> findAll(Pageable pageable);

    List<Fisioterapeuta> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

    Fisioterapeuta findByEmail(String email);

    @Query(value = "SELECT ROUND(((COUNT(*) - SUB.total_last_month) / SUB.total_last_month) * 100, 2) FROM fisioterapeuta CROSS JOIN (SELECT COUNT(*) AS total_last_month FROM fisioterapeuta WHERE create_time >= DATE_SUB(CURRENT_DATE, INTERVAL 2 MONTH) AND create_time < DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) SUB WHERE create_time >= DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)", nativeQuery = true)
    Double calculateTaxaCrescimento();

    @Query(value = "SELECT ROUND((SUM(CASE WHEN nome IS NOT NULL AND email IS NOT NULL AND telefone IS NOT NULL AND endereco IS NOT NULL THEN 1 ELSE 0 END) / COUNT(*)) * 100, 2) FROM fisioterapeuta", nativeQuery = true)
    Double calculateIndicePerfisCompletos();

}
