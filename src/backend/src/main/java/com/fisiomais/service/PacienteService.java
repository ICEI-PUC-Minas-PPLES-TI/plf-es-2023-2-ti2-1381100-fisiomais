package com.fisiomais.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fisiomais.bodys.PacienteResponse;
import com.fisiomais.dto.PacienteDTO;
import com.fisiomais.exception.BusinessException;
import com.fisiomais.model.Paciente;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService{

    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public PacienteService(PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    public List<PacienteDTO> getAllPacientes() {

        List<Paciente> pacientes = pacienteRepository.findAll();
        return convertPacientesToDTOs(pacientes);
    }

    public PacienteDTO getPacienteById(int id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            return toDTO(paciente);
        }
        return null;
    }

    public PacienteResponse createPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = toEntity(pacienteDTO);

        if(pacienteRepository.findByEmail(paciente.getEmail()) != null || fisioterapeutaRepository.findByEmail(paciente.getEmail()) != null){
            throw new BusinessException("Email já cadastrado. Tente realizar o login.");
        }

        if(pacienteRepository.findByCpf(paciente.getCpf()) != null){
            throw new BusinessException("CPF já cadastrado. Tente realizar o login.");
        }

        Paciente savedPaciente = pacienteRepository.save(paciente);

        return PacienteResponse.toPacienteResponse(savedPaciente);
    }

    public PacienteDTO updatePaciente(Integer id, PacienteDTO pacienteDTO) {
        Paciente existingPaciente = pacienteRepository.findById(id).orElse(null);
        if (existingPaciente != null) {
            existingPaciente.setNome(pacienteDTO.getNome());
            existingPaciente.setEmail(pacienteDTO.getEmail());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(pacienteDTO.getDataNascimento());
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing date. Please provide a valid date format.");
            }

            existingPaciente.setDataNascimento(parsedDate);
            existingPaciente.setCpf(pacienteDTO.getCpf());
            existingPaciente.setTelefone(pacienteDTO.getTelefone());
            existingPaciente.setGenero(pacienteDTO.getGenero());
            existingPaciente.setEndereco(pacienteDTO.getEndereco());

            Paciente updatedPaciente = pacienteRepository.save(existingPaciente);

            return toDTO(updatedPaciente);
        }
        return null;
    }

    public boolean deletePaciente(Integer id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setCreate_time(paciente.getCreate_time());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setPassword(paciente.getPassword());
        pacienteDTO.setDataNascimento(paciente.getDataNascimento().toString());
        pacienteDTO.setCpf(paciente.getCpf());
        pacienteDTO.setTelefone(paciente.getTelefone());
        pacienteDTO.setGenero(paciente.getGenero());
        pacienteDTO.setEndereco(paciente.getEndereco());

        return pacienteDTO;
    }

    private Paciente toEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        paciente.setId(pacienteDTO.getId());
        paciente.setCreate_time(pacienteDTO.getCreate_time());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setPassword(pacienteDTO.getPassword());
        // Parse the date from the String representation
        try {
            paciente.setDataNascimento(dateFormat.parse(pacienteDTO.getDataNascimento()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please provide a valid date format.");
        }
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setGenero(pacienteDTO.getGenero());
        paciente.setEndereco(pacienteDTO.getEndereco());

        return paciente;
    }

    private List<PacienteDTO> convertPacientesToDTOs(List<Paciente> pacientes) {
        // Convert a list of Paciente entities to a list of PacienteDTOs
        return pacientes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
