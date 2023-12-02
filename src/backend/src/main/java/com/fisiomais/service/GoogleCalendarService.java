package com.fisiomais.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fisiomais.util.UUIDGen;
import com.fisiomais.entities.ConferenceEventData;
import com.fisiomais.exception.ServerException;
import com.fisiomais.model.Consulta;
import com.fisiomais.model.Fisioterapeuta;
import com.fisiomais.model.Paciente;
import com.fisiomais.model.enums.StatusConsulta;
import com.fisiomais.service.interfaces.IGoogleCalendarService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.ConferenceSolutionKey;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.EntryPoint;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

@Service
public class GoogleCalendarService implements IGoogleCalendarService {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Fisiomais Service";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Calendar calendar; // Inicialize o objeto Calendar
    private static final Logger logger = LogManager.getLogger(GoogleCalendarService.class);
    private static final long DURACAO_CONSULTA_EM_MINUTOS = 30;
    private static final long DURACAO_CONSULTA_EM_MILISSEGUNDOS = DURACAO_CONSULTA_EM_MINUTOS * 60 * 1000;
    private static final String CREEN_COLOR_ID = "2";

    private String fisioName = null;
    private String pacienteName = null;
    private String observacoes = null;

    public GoogleCalendarService() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        calendar = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, this.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @Override
    public ConferenceEventData criarEventoConsulta(Consulta consulta) {
        this.fisioName = new String(consulta.getFisioterapeuta().getNome().getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);

        this.pacienteName = new String(consulta.getPaciente().getNome().getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);

        if (consulta.getConfirmacao() != StatusConsulta.confirmado) {
            logger.error("Consulta não confirmada\n" +
                    "id: " + consulta.get_id() +
                    "\nstatus: " + consulta.getConfirmacao()
                    + "\n\n");
            throw new ServerException("Consulta não confirmada");
        }

        Event event = construirEvento(consulta);
        try {
            Event createdEvent = inserirEventoNoCalendario(event);
            ConferenceEventData conferenceEventData = new ConferenceEventData(createdEvent.getId(),
                    createdEvent.getHangoutLink());
            logger.info("Evento criado no Google Calendar\n" +
                    "Conference data: " + conferenceEventData.toString() +
                    "\nCalendar link: " + createdEvent.getHtmlLink());
            return conferenceEventData;
        } catch (IOException e) {
            logger.error("Erro ao criar evento no Google Calendar: " + e.getMessage());
            throw new ServerException("Erro ao criar evento no Google Calendar");
        }
    }
    
    private Event construirEvento(Consulta consulta) {
        Event event = new Event()
                .setSummary("Consulta de " +
                        this.pacienteName +
                        " com Dr(a). " +
                        this.fisioName);

        if (consulta.getObservacoes() != null) {
            this.observacoes = new String(consulta.getObservacoes().getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);

            event.setDescription(this.observacoes);
        }

        Date dataEHora = consulta.getDataEHora();
        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(dataEHora))
                .setTimeZone("America/Sao_Paulo");
        event.setStart(start);

        EventDateTime end = calcularHoraTermino(dataEHora);
        event.setEnd(end);

        adicionarParticipantes(consulta, event);
        adicionarLembretes(event);
        adicionarConferencia(event, consulta.getFisioterapeuta().getEmail());

        event.setColorId(CREEN_COLOR_ID);

        return event;
    }

    private EventDateTime calcularHoraTermino(Date dataEHora) {
        return new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(
                        dataEHora.getTime() + DURACAO_CONSULTA_EM_MILISSEGUNDOS))
                .setTimeZone("America/Sao_Paulo");
    }

    private void adicionarParticipantes(Consulta consulta, Event event) {
        Paciente paciente = consulta.getPaciente();
        Fisioterapeuta fisioterapeuta = consulta.getFisioterapeuta();

        EventAttendee[] attendees = new EventAttendee[2];
        attendees[0] = new EventAttendee()
                .setEmail(paciente.getEmail())
                .setDisplayName(this.pacienteName);
        attendees[1] = new EventAttendee()
                .setEmail(fisioterapeuta.getEmail())
                .setDisplayName(this.fisioName)
                .setOrganizer(true);
        event.setAttendees(Arrays.asList(attendees));
    }

    private void adicionarLembretes(Event event) {
        EventReminder[] remindersOverride = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("email").setMinutes(12 * 60),
                new EventReminder().setMethod("popup").setMinutes(60),
                new EventReminder().setMethod("popup").setMinutes(30),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(remindersOverride));
        event.setReminders(reminders);
    }

    private void adicionarConferencia(Event event, String fisioterapeutaEmail) {
        ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
        conferenceSKey.setType("hangoutsMeet");

        CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
        createConferenceReq.setRequestId(UUIDGen.generateType1UUID());
        createConferenceReq.setConferenceSolutionKey(conferenceSKey);

        ConferenceData conferenceData = new ConferenceData();
        conferenceData.setCreateRequest(createConferenceReq);
        conferenceData.setEntryPoints(Arrays
                .asList(new EntryPoint()
                        .setEntryPointType("video")));

        event.setConferenceData(conferenceData);
    }

    private Event inserirEventoNoCalendario(Event event) throws IOException {
        String calendarId = "primary";
        return calendar
                .events()
                .insert(calendarId, event)
                .setConferenceDataVersion(1)
                .execute();
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .setApprovalPrompt("force") // Forces the consent screen every time for testing purposes
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8081).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        // Returns an authorized Credential object.
        return credential;
    }
}
