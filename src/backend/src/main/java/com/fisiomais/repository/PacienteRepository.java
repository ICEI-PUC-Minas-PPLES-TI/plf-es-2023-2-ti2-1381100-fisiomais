package com.fisiomais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fisiomais.model.Paciente;
import com.fisiomais.model.enums.Genero;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Paciente findByEmail(String email);

    Paciente findByCpf(String cpf);

    List<Paciente> findByGenero(Genero genero);

    Page<Paciente> findByGenero(Genero genero, Pageable pageable);

    @Query("SELECT p FROM Paciente p WHERE p.nome LIKE %?1%")
    List<Paciente> findPacientesByNome(String nome);

    @Query(value = "SELECT * FROM paciente WHERE cpf = ?1", nativeQuery = true)
    List<Paciente> findPacientesByCpf(String cpf);
}