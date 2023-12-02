package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Exercicio;
import com.fisiomais.model.Midia;
import com.fisiomais.model.indicators.MidiaUtilizationMetrics;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
    @Query("SELECT e FROM Exercicio e JOIN e.midias m WHERE m = :midia")
    List<Exercicio> findExerciciosByMidia(@Param("midia") Midia midia);

    @Query("SELECT new com.fisiomais.model.indicators.MidiaUtilizationMetrics("
            + "COUNT(DISTINCT e.id) AS totalExercicios, "
            + "COUNT(DISTINCT em.midia.id) AS midiasComExercicios, "
            + "CAST((COUNT(DISTINCT em.midia.id) / COUNT(DISTINCT e.id)) * 100 AS DOUBLE) AS taxaUtilizacao) "
            + "FROM Exercicio e "
            + "LEFT JOIN ExercicioHasMidias em ON em.midia.id = e.id")
    List<MidiaUtilizationMetrics> getTaxaUtilizacao();
}
