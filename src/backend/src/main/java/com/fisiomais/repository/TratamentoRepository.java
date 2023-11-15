package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Tratamento;


@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Integer> {

    @Query(value = "SELECT t FROM Tratamento t WHERE t.paciente._id = :id")
    List<Tratamento> findByPaciente__id(@Param("id")Integer id);

    @Query(value = "SELECT t FROM Tratamento t WHERE t.fisioterapeuta._id = :id")
    List<Tratamento> findByFisioterapeuta__id(@Param("id")Integer id);

    @Query(value = "SELECT t FROM Tratamento t WHERE t.titulo = :titulo")
    Tratamento findTratamentoByTitulo(@Param("titulo")String titulo);

    Tratamento findBy_Id(Integer id);
} 