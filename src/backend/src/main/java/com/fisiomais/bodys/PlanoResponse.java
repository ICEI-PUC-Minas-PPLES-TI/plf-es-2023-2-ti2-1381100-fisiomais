package com.fisiomais.bodys;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PlanoResponse(
  String tituloTexto,
  String conteudoTexto,
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC") Date dataInicio,
  String frequencia,
  Integer[] midias,
  String formatacao
) {}
