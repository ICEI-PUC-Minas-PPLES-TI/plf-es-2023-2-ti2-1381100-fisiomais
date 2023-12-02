package com.fisiomais.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fisiomais.bodys.RequestUserCredentials;
import com.fisiomais.bodys.UserCredentialsResponse;
import com.fisiomais.dto.Login;
import com.fisiomais.exception.NotFoundException;
import com.fisiomais.exception.TokenExpirationException;
import com.fisiomais.exception.UserNotFoundExecption;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;
import com.fisiomais.model.User;
import com.fisiomais.repository.FisioterapeutaRepository;
import com.fisiomais.repository.PacienteRepository;
import com.fisiomais.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Authentications", description = "Endpoints para autenticação de usuários")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public AuthController(AuthenticationManager authenticationManager,
            TokenService tokenService,
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @PostMapping
    @Operation(summary = "Autentica um usuário", description = "Autentica um usuário e retorna um token do tipo Bearer, contendo as informações do usuário autenticado (id e email)")
    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    public ResponseEntity<String> login(
            @Parameter(description = "Objeto contendo as credenciais do usuário") @RequestBody Login login) {
        try {
            logger.info("Logging in user with email: {}", login.getEmail());
            logger.info("Logging in user with password: {}", login.getSenha());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    login.getEmail(), login.getSenha());

            Authentication authenticated = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User user = (User) authenticated.getPrincipal();

            logger.info("User " + user.getEmail() + " logged in");
            String token = tokenService.gerarToken(user);
            logger.info("Token generated for user " + user.getEmail() + ": " + token);

            // Retorna um ResponseEntity com o token
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (TokenExpiredException e) {
            // Trata a exceção específica de token expirado
            logger.error("Token expired: " + e.getMessage());
            throw new TokenExpirationException("Token expirado. Faça login novamente.", e);
        } catch (Exception e) {
            // Trata outras exceções
            logger.error("Error while logging in: " + e.getMessage());
            throw new UserNotFoundExecption("Erro durante o login. Verifique suas credenciais.", e);
        }
    }

    @PostMapping("/credentials/{id}")
    @Operation(summary = "Retorna as credenciais de um usuário", description = "Retorna as credenciais de um usuário, desde que o usuário autenticado seja o mesmo usuário que está sendo buscado")
    @ApiResponse(responseCode = "200", description = "Credenciais retornadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserCredentialsResponse.class)))
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    public ResponseEntity<UserCredentialsResponse> getCredentials(
            @Parameter(description = "ID do usuário a ser buscado") @PathVariable Integer id,
            @Parameter(description = "Objeto contendo as credenciais do usuário") @RequestBody RequestUserCredentials userCred) {
        logger.info("Getting credentials for user with id: {}", id);
        logger.info("User Credentials (Email): {}", userCred.email());

        // check if the id requested is the same as the authenticated user
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!authenticatedUser.getId().equals(id)) {
            logger.error("User {} is not authorized to get credentials for user with id: {}", authenticatedUser.getId(),
                    id);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        logger.info("Paciente email searched: {}", paciente.getEmail());
        if (paciente != null && paciente.getEmail().equals(userCred.email())) {
            return new ResponseEntity<>(UserCredentialsResponse.parsePaciente(paciente), HttpStatus.OK);
        }

        logger.info("Patient not found for id: {}", id);

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(id).orElse(null);
        logger.info("Fisioterapeuta email searched: {}", fisioterapeuta.getEmail());
        if (fisioterapeuta != null && fisioterapeuta.getEmail().equals(userCred.email())) {
            // if email is fisiomaisclinicas@gmail.com change the role to "admin"
            UserCredentialsResponse response = UserCredentialsResponse.parseFisioterapeuta(fisioterapeuta);
            if (response.email().equals("fisiomaisclinicas@gmail.com")) {
                response = new UserCredentialsResponse(response.id(), response.nome(), response.email(),
                        response.telefone(), response.endereco(), response.controle_automatico(), "admin");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        logger.error("User not found for id: {}", id);
        throw new NotFoundException();
    }

}
