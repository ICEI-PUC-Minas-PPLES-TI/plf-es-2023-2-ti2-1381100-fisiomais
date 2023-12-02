package com.fisiomais.bodys;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.enums.StatusConsulta;

public record ConsultaResponseAgenda(
                PacienteResponse paciente,
                FisioterapeutaResponse fisioterapeuta,
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC") Date dataEHora,
                String observacoes,
                StatusConsulta status,
                String link) {

        public static List<ConsultaResponseAgenda> toResponse(List<Consulta> consultasByFisioterapeuta) {
                return consultasByFisioterapeuta.stream().map(consulta -> new ConsultaResponseAgenda(
                                PacienteResponse.toPacienteResponse(consulta.getPaciente()),
                                FisioterapeutaResponse.toFisioterapeutaResponse(consulta.getFisioterapeuta()),
                                consulta.getDataEHora(),
                                consulta.getObservacoes(),
                                consulta.getConfirmacao(),
                                consulta.getLink())).toList();
        }

        public static ConsultaResponseAgenda toResponse(Consulta consultasByFisioterapeuta) {
                return new ConsultaResponseAgenda(
                                PacienteResponse.toPacienteResponse(consultasByFisioterapeuta.getPaciente()),
                                FisioterapeutaResponse.toFisioterapeutaResponse(
                                                consultasByFisioterapeuta.getFisioterapeuta()),
                                consultasByFisioterapeuta.getDataEHora(),
                                consultasByFisioterapeuta.getObservacoes(),
                                consultasByFisioterapeuta.getConfirmacao(),
                                consultasByFisioterapeuta.getLink());
        }
}
