package com.fisiomais.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fisiomais.bodys.FisioterapeutaResponse;
import com.fisiomais.bodys.PacienteResponse;
import com.fisiomais.bodys.TratamentoResponse;
import com.fisiomais.model.Tratamento;
import com.fisiomais.repository.TratamentoRepository;

@Service
public class TratamentoService {
    
     @Autowired
    private TratamentoRepository tratamentoRepository;

    @Transactional
    public TratamentoResponse createTratamento(Tratamento obj){
        obj = this.tratamentoRepository.save(obj);
        return this.toTratamentoResponse(obj);
        
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
        Optional<Tratamento> newObj = this.tratamentoRepository.findById(obj.getId());
        return this.tratamentoRepository.save(newObj.get());
    }

    private TratamentoResponse toTratamentoResponse(Tratamento tratamento) {
        return new TratamentoResponse(
                PacienteResponse.toPacienteResponse(tratamento.getPaciente()),
                FisioterapeutaResponse.toFisioterapeutaResponse(tratamento.getFisioterapeuta()),
                tratamento.getTitulo(),
                tratamento.getObservacoes(),
                tratamento.getFeedback(),
                tratamento.getEndDate()
                );
    }
}
