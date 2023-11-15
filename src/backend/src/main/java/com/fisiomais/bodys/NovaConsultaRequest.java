package com.fisiomais.bodys;

import java.util.Date;

public record NovaConsultaRequest(Integer pacienteId, Integer fisioterapeutaId, Date dataHora) {
}
