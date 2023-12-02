package com.fisiomais.service;

import com.fisiomais.model.AcompanhamentoVirtual;
import com.fisiomais.repository.AcompanhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AcompanhamentoService {

    @Autowired
    private AcompanhamentoRepository repository;

    public AcompanhamentoVirtual salvar(AcompanhamentoVirtual acompanhamento) throws IllegalArgumentException {
        validarAcompanhamento(acompanhamento);
        return repository.save(acompanhamento);
    }

    public List<AcompanhamentoVirtual> buscarTodos() {
        return repository.findAll();
    }

    public Optional<AcompanhamentoVirtual> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public AcompanhamentoVirtual atualizar(Long id, AcompanhamentoVirtual acompanhamento) throws IllegalArgumentException {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Acompanhamento com ID " + id + " não encontrado.");
        }
        validarAcompanhamento(acompanhamento);
        acompanhamento.setId(id);
        return repository.save(acompanhamento);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void validarAcompanhamento(AcompanhamentoVirtual acompanhamento) {
        if (acompanhamento.getDataSessao() == null) {
            throw new IllegalArgumentException("Data da sessão é obrigatória.");
        }
        if (acompanhamento.getPlataforma() == null || acompanhamento.getPlataforma().isEmpty()) {
            throw new IllegalArgumentException("Plataforma é obrigatória.");
        }
        Logger.getGlobal().info("Acompanhamento validado com sucesso.");
    }

    public Double getIndiceAcompanhamento() {
        return repository.indiceAcompanhamento();
    }

    public Double getTaxaSatisfacao() {
        return repository.calculateTaxaSatisfacao();
    }
}
