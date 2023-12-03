package com.fisiomais.service;

import com.fisiomais.bodys.PlanoResponse;
import com.fisiomais.model.Plano;
import com.fisiomais.repository.PlanoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }


    public PlanoResponse addPlano(Plano Plano) {
        Plano planoSalvo = planoRepository.save(Plano);
        return toPlanoResponse(planoSalvo);
    }

    public PlanoResponse updatePlano(Integer planoId, Plano plano) {
        Optional<Plano> planoOptional = planoRepository.findById(planoId);

        if (planoOptional.isPresent()) {
            Plano planoSalvo = planoOptional.get();
            planoSalvo.setTituloTexto(plano.getTituloTexto());
            planoSalvo.setConteudoTexto(plano.getConteudoTexto());
            planoSalvo.setDataInicio(plano.getDataInicio());
            planoSalvo.setFrequencia(plano.getFrequencia());
            planoSalvo.setMidias(plano.getMidias());
            planoSalvo.setFormatacao(plano.getFormatacao());
            
            return toPlanoResponse(planoRepository.save(planoSalvo));
        } else {
            throw new RuntimeException("Plano não encontrado para o ID: " + planoId);
        }
    }

    public void deletePlano(Integer planoId) {
        planoRepository.deleteById(planoId);
    }

    public Plano getPlanoById(Integer planoId) {
        return planoRepository.findById(planoId)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado para o ID: " + planoId));
    }

    public List<Plano> getAllPlanos() {
        return planoRepository.findAll();
    }

    private PlanoResponse toPlanoResponse(Plano plano) {
        return new PlanoResponse(
          plano.getTituloTexto(),
          plano.getConteudoTexto(),
          plano.getDataInicio(),
          plano.getFrequencia(),
          plano.getMidias(),
          plano.getFormatacao()
        );
    }
}
