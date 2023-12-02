package com.fisiomais.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AgendaRequest {

    @NotNull(message = "O campo 'dia' é obrigatório.")
    private Byte dia;

    @NotNull(message = "O campo 'horarioInicio' é obrigatório.")
    private String horarioInicio;

    @NotNull(message = "O campo 'horarioFim' é obrigatório.")
    private String horarioFim;

    @NotNull(message = "O campo 'disponivel' é obrigatório.")
    private Boolean disponivel;

    @NotNull(message = "O campo 'fisioterapeutaId' é obrigatório.")
    private Integer fisioterapeutaId;

    public Time getHorarioInicioAsTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = sdf.parse(horarioInicio);
        return new Time(date.getTime());
    }

    public Time getHorarioFimAsTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = sdf.parse(horarioFim);
        return new Time(date.getTime());
    }
}
