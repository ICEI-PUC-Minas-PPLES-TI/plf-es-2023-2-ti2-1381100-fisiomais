package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Midia;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Integer> {

    List<Midia> findAll();

    Page<Midia> findAll(Pageable pageable);

    List<Midia> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT m FROM Midia m WHERE m.titulo LIKE %?1%")
    List<Midia> findMidiasByTitulo(String nome);

    @Query(value = "SELECT * FROM midia WHERE id = ?1", nativeQuery = true)
    List<Midia> findMidiasById(Integer id);

    List<Midia> findMidiaByFisioterapeuta(Fisioterapeuta fisioterapeuta);
}