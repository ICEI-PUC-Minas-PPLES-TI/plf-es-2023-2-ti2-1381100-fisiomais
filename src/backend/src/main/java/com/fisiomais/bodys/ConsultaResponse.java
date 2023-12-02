package com.fisiomais.bodys;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.enums.StatusConsulta;

public record ConsultaResponse(
        Integer pacienteId,
        Integer fisioterapeutaId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC") Date dataEHora,
        String observacoes,
        StatusConsulta status,
        String link) {

        public static List<ConsultaResponse> toResponse(List<Consulta> consultasByFisioterapeuta) {
                return consultasByFisioterapeuta.stream().map(consulta -> new ConsultaResponse(
                                consulta.getPaciente().getId(),
                                consulta.getFisioterapeuta().getId(),
                                consulta.getDataEHora(),
                                consulta.getObservacoes(),
                                consulta.getConfirmacao(),
                                consulta.getLink())).toList();
        }
}
