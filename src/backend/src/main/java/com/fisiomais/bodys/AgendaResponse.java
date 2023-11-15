package com.fisiomais.bodys;

import java.sql.Time;

public record AgendaResponse(
        Integer id,
        Boolean disponivel,
        Byte dia,
        Time horarioInicio,
        Time horarioFim,
        FisioterapeutaResponse fisioterapeuta) {}
