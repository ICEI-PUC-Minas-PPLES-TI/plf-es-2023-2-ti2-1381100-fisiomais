package com.fisiomais.service.interfaces;

import com.fisiomais.entities.ConferenceEventData;
import com.fisiomais.model.Consulta;

public interface IGoogleCalendarService {
    ConferenceEventData criarEventoConsulta(Consulta consulta); // Cria evento no Google Calendar e retorna o ID
    // void cancelarEventoConsulta(String googleEventId); // Cancela o evento no Google Calendar
}
