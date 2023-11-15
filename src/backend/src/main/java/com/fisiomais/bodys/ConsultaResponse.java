package com.fisiomais.bodys;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fisiomais.model.enums.StatusConsulta;

public record ConsultaResponse(
        PacienteResponse paciente,
        FisioterapeutaResponse fisioterapeuta,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC") Date dataHora,
        String observacoes,
        StatusConsulta status,
        String link) {
}
