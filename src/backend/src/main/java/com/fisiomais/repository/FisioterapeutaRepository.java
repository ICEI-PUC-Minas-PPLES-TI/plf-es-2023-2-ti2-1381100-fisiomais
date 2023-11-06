package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Fisioterapeuta;

@Repository
public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Long> {

    List<Fisioterapeuta> findAll();

    Page<Fisioterapeuta> findAll(Pageable pageable);

    List<Fisioterapeuta> findByEspecialidade(String especialidade);

    List<Fisioterapeuta> findByNomeContainingIgnoreCase(String nome);
}
