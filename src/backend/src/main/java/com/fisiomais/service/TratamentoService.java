package com.fisiomais.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.fisiomais.model.Tratamento;
import com.fisiomais.repository.TratamentoRepository;

@Service
public class TratamentoService {
    
     @Autowired
    private TratamentoRepository tratamentoRepository;

    @Transactional
    public Tratamento createTratamento(Tratamento obj){
        obj = this.tratamentoRepository.save(obj);
        return obj;
        
    }

    public List<Tratamento> findByPacienteId(Integer id){
        List<Tratamento> tratamentos = tratamentoRepository.findByPaciente__id(id);
        return tratamentos;
    }

    public List<Tratamento> findByFisioterapeutaId(Integer id){
        List<Tratamento> tratamentos = tratamentoRepository.findByFisioterapeuta__id(id);
        return tratamentos;
    }
    public Tratamento findTratamentoByTitulo(String titulo){
        Tratamento tratamento = tratamentoRepository.findTratamentoByTitulo(titulo);
        return tratamento;
    }

    @Transactional
    public Tratamento update(Tratamento obj) {
        Tratamento newObj = this.tratamentoRepository.findBy_Id(obj.getId());
        return this.tratamentoRepository.save(newObj);
    }
}
