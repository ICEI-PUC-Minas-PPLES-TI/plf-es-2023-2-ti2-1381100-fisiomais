package com.fisiomais.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisiomais.bodys.NovoPlanoRequest;
import com.fisiomais.model.Plano;

@Service
public class PlanoUtil {

    @Autowired
    public PlanoUtil() {}

    public Plano convertToPlano(NovoPlanoRequest plano) {

        Plano novoPlano = new Plano();

        novoPlano.setTituloTexto(plano.tituloTexto());
        novoPlano.setConteudoTexto(plano.conteudoTexto());
        novoPlano.setDataInicio(plano.dataInicio());
        novoPlano.setFrequencia(plano.frequencia());
        novoPlano.setMidias(plano.midias());

        return novoPlano;
    }
}
