package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Midia;
import com.fisiomais.model.indicators.MidiaTypesMetrics;

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

    @Query("SELECT new com.fisiomais.model.indicators.MidiaTypesMetrics("
            + "COUNT(DISTINCT m.id) AS totalMidias, "
            + "COUNT(DISTINCT CASE WHEN m.type = 'VIDEO' THEN m.id END) AS totalVideos, "
            + "COUNT(DISTINCT CASE WHEN m.type = 'IMAGEM' THEN m.id END) AS totalImagens, "
            + "COUNT(DISTINCT CASE WHEN m.type = 'GIF' THEN m.id END) AS totalGifs, "
            + "CAST((COUNT(DISTINCT CASE WHEN m.type = 'VIDEO' THEN m.id END) / COUNT(DISTINCT m.id)) * 100 AS DOUBLE) AS taxaVideos, "
            + "CAST((COUNT(DISTINCT CASE WHEN m.type = 'IMAGEM' THEN m.id END) / COUNT(DISTINCT m.id)) * 100 AS DOUBLE) AS taxaImagens, "
            + "CAST((COUNT(DISTINCT CASE WHEN m.type = 'GIF' THEN m.id END) / COUNT(DISTINCT m.id)) * 100 AS DOUBLE) AS taxaGifs) "
            + "FROM Midia m")
    List<MidiaTypesMetrics> getTaxaUtilizacao();
}