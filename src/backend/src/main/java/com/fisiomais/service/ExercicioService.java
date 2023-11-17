package com.fisiomais.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fisiomais.model.Exercicio;
import com.fisiomais.model.Midia;
import com.fisiomais.repository.ExercicioRepository;

@Service
public class ExercicioService {
    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public List<Exercicio> findExerciciosByMidia(Midia midia) {
        return exercicioRepository.findExerciciosByMidia(midia);
    }

    public void deleteMidia(Exercicio exercicio, Midia midia) {
        Exercicio exercicioToDelete = exercicioRepository.findById(exercicio.getId()).orElseThrow();
        exercicioToDelete.getMidias().remove(midia);
        exercicioRepository.save(exercicioToDelete);
    }
}