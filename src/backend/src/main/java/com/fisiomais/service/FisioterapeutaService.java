package com.fisiomais.service;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.repository.FisioterapeutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FisioterapeutaService {
    private final FisioterapeutaRepository fisioterapeutaRepository;

    @Autowired
    public FisioterapeutaService(FisioterapeutaRepository fisioterapeutaRepository) {
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Transactional(readOnly = true)
    public List<Fisioterapeuta> findAll() {
        return fisioterapeutaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Fisioterapeuta> findAll(Pageable pageable) {
        return fisioterapeutaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Fisioterapeuta> findById(Long id) {
        return fisioterapeutaRepository.findById(id);
    }

    @Transactional
    public Fisioterapeuta save(Fisioterapeuta fisioterapeuta) {
        validateFisioterapeuta(fisioterapeuta);
        return fisioterapeutaRepository.save(fisioterapeuta);
    }

    @Transactional
    public Fisioterapeuta update(Long id, Fisioterapeuta fisioterapeuta) {
        Optional<Fisioterapeuta> existingFisioterapeuta = fisioterapeutaRepository.findById(id);
        if(existingFisioterapeuta.isPresent()) {
            validateFisioterapeuta(fisioterapeuta);
            fisioterapeuta.set_id(null);
            return fisioterapeutaRepository.save(fisioterapeuta);
        } else {
            throw new RuntimeException("Fisioterapeuta not found");
        }
    }

    private void validateFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        if(fisioterapeuta == null){
            throw new IllegalArgumentException("O fisioterapeuta não pode ser nulo");
        }

        if(fisioterapeuta.getNome() == null || fisioterapeuta.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("O nome do fisioterapeuta não pode ser nulo ou vazio");
        }

        if(fisioterapeuta.getEmail() == null || fisioterapeuta.getEmail().trim().isEmpty()){
            throw new IllegalArgumentException("O email do fisioterapeuta não pode ser nulo ou vazio");
        }

        if(!fisioterapeuta.getNome().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalArgumentException("O email do fisioterapeuta não é válido");
        }

        if(fisioterapeuta.getPassword() == null || fisioterapeuta.getPassword().length() != 8){
            throw new IllegalArgumentException("A senha do fisioterapeuta não pode ser nula e deve ter 8 caracteres");
        }

        if(fisioterapeuta.getTelefone() == null || !fisioterapeuta.getTelefone().matches("^[0-9]{11}$")){
            throw new IllegalArgumentException("O telefone do fisioterapeuta não pode ser nulo e deve ter 11 dígitos");
        }

        if(fisioterapeuta.getEndereco() != null && fisioterapeuta.getEndereco().length() > 200){
            throw new IllegalArgumentException("O endereço do fisioterapeuta não pode ter mais de 200 caracteres");
        }
    }

    @Transactional
    public void deleteById(Long id) {
        fisioterapeutaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Fisioterapeuta> findByEspecialidade(String especialidade) {
        return fisioterapeutaRepository.findByEspecialidade(especialidade);
    }
    
}
