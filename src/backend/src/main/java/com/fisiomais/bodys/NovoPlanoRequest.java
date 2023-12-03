package com.fisiomais.bodys;

import java.util.Date;

public record NovoPlanoRequest(
  String tituloTexto, 
  String conteudoTexto, 
  Date dataInicio,
  String frequencia, 
  Integer[] midias,
  String formatacao
) {}
