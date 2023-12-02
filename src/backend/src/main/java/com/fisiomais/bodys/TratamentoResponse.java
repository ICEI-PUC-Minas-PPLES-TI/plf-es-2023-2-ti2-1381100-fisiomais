package com.fisiomais.bodys;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public record TratamentoResponse (
    PacienteResponse paciente,
    FisioterapeutaResponse fisioterapeuta,
    String titulo,
    String observacoes,
    String feedback,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC") Date endDate
    ){

}
