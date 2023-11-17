package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Exercicio;
import com.fisiomais.model.Midia;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer> {
    @Query("SELECT e FROM Exercicio e JOIN e.midias m WHERE m = :midia")
    List<Exercicio> findExerciciosByMidia(@Param("midia") Midia midia);
}
