package com.fisiomais.repository;

import com.fisiomais.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    List<Agenda> findByFisioterapeuta_Id(Integer fisioterapeutaId);

    List<Agenda> findByDiaAndFisioterapeuta_Id(Byte dia, Integer fisioterapeutaId);

    List<Agenda> findByDisponivelAndFisioterapeuta_Id(Boolean disponivel, Integer fisioterapeutaId);

    List<Agenda> findByFisioterapeuta_IdAndDiaAndDisponivel(
            Integer fisioterapeutaId, Byte dia, Boolean disponivel);

    List<Agenda> findByHorarioInicioBeforeAndHorarioFimAfter(
            Time horarioInicio, Time horarioFim);

    List<Agenda> findByFisioterapeuta_IdAndDiaAndHorarioInicioLessThanEqualAndHorarioFimGreaterThanEqual(
            Integer fisioterapeutaId, Byte dia, Time horarioInicio, Time horarioFim);

    List<Agenda> findByFisioterapeuta_IdAndDiaAndHorarioInicioGreaterThanEqualAndHorarioFimLessThanEqual(
            Integer fisioterapeutaId, Byte dia, Time horarioInicio, Time horarioFim);
}
