package com.fisiomais.service;

import org.springframework.stereotype.Service;

import com.fisiomais.dto.PacienteDTO;
import com.fisiomais.model.Paciente;
import com.fisiomais.repository.PacienteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteDTO> getAllPacientes() {
        // Convert Paciente entities to PacienteDTOs, if necessary
        // This is where you can implement the conversion logic
        // For simplicity, we assume PacienteDTO is identical to Paciente

        List<Paciente> pacientes = pacienteRepository.findAll();
        // You may use a mapping library (e.g., ModelMapper) to convert entities to DTOs
        // For simplicity, we assume the DTO structure is identical to the entity
        return convertPacientesToDTOs(pacientes);
    }

    public PacienteDTO getPacienteById(int id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente != null) {
            // Convert the Paciente entity to a PacienteDTO, if necessary
            // For simplicity, we assume the DTO structure is identical to the entity
            return toDTO(paciente);
        }
        return null; // Return null if the Paciente is not found
    }

    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {
        // Convert PacienteDTO to Paciente entity, if necessary
        // For simplicity, we assume the DTO structure is identical to the entity
        Paciente paciente = toEntity(pacienteDTO);

        // Save the Paciente entity to the database
        Paciente savedPaciente = pacienteRepository.save(paciente);

        // Convert the saved Paciente entity back to PacienteDTO
        return toDTO(savedPaciente);
    }

    public PacienteDTO updatePaciente(Integer id, PacienteDTO pacienteDTO) {
        Paciente existingPaciente = pacienteRepository.findById(id).orElse(null);
        if (existingPaciente != null) {
            // Update the existing Paciente entity with values from the PacienteDTO
            // You can implement this update logic according to your requirements
            // For simplicity, we assume the DTO structure is identical to the entity
            existingPaciente.setNome(pacienteDTO.getNome());
            existingPaciente.setEmail(pacienteDTO.getEmail());
            // Parse the String date to Date using a specific date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format as needed

            Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(pacienteDTO.getDataNascimento());
            } catch (ParseException e) {
                // Handle the parsing exception
                throw new RuntimeException("Error parsing date. Please provide a valid date format.");
            }

            existingPaciente.setDataNascimento(parsedDate);
            existingPaciente.setCpf(pacienteDTO.getCpf());
            existingPaciente.setTelefone(pacienteDTO.getTelefone());
            existingPaciente.setGenero(pacienteDTO.getGenero());
            existingPaciente.setEndereco(pacienteDTO.getEndereco());

            // Save the updated Paciente entity to the database
            Paciente updatedPaciente = pacienteRepository.save(existingPaciente);

            // Convert the updated Paciente entity back to PacienteDTO
            return toDTO(updatedPaciente);
        }
        return null; // Return null if the Paciente is not found
    }

    public boolean deletePaciente(Integer id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true; // Return true if the Paciente is deleted
        }
        return false; // Return false if the Paciente is not found
    }

    // Additional methods for custom business logic, validation, etc.
    // You can define these methods based on your project's requirements

    // Helper methods for converting between Paciente and PacienteDTO
    private PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setCreate_time(paciente.getCreate_time());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setPassword(paciente.getPassword());
        // Format the date as a String
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
