package com.fisiomais.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final PacienteRepository pacienteRepository;

    public AuthenticationService(FisioterapeutaRepository fisioterapeutaRepository,
            PacienteRepository pacienteRepository) {
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Paciente paciente = pacienteRepository.findByEmail(username);
        if (paciente != null) {
            return paciente;
        }

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findByEmail(username);
        if (fisioterapeuta != null) {
            return fisioterapeuta;
        }

        throw new UsernameNotFoundException("User not found");
    }

}
