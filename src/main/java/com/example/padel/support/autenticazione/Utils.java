package com.example.padel.support.autenticazione;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;


@UtilityClass //classe di utilità
@Log4j2 //riguarda il log
public class Utils { //ci creiamo questa classe per accedere alle credenziali del token in maniera pulita

    //prende colui che fa la richiesta
    public Jwt getPrincipal() {
        //security context è l'angolino in cui vengono memorizzate le info sulla sicurezza, prendiamo l'autenticazione e troviamo il principal ovvero l'e info sull'utente appena loggato
        return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getAuthServerId() {
        return getTokenNode().get("subject").asText();
    }

    public String getName() {
        return getTokenNode().get("sub").asText();
    } //prendiamo l'info dal token

    //l'indirizzo lo prendiamo da un token di autenticazione
    public String getEmail() {
        return getTokenNode().get("claims").get("preferred_username").asText();
    }

    //prende il l'oggetto jwt e lo converte in token node
    private JsonNode getTokenNode() {
        Jwt jwt = getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();//converte il token in una stringa
        String jwtAsString;
        JsonNode jsonNode;
        try {
            jwtAsString = objectMapper.writeValueAsString(jwt);
            jsonNode = objectMapper.readTree(jwtAsString);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Unable to retrieve user's info!");
        }
        return jsonNode;
    }


}*/

