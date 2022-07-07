package com.example.padel.controllers;

import com.example.padel.entities.Prenotazione;
import com.example.padel.entities.Utente;
import com.example.padel.services.PrenotazioneService;
import com.example.padel.support.eccezioni.CampoNonTrovatoException;
import com.example.padel.support.eccezioni.CampoOccupatoException;
import com.example.padel.support.eccezioni.PrenotazioneNonTrovataException;
import com.example.padel.support.eccezioni.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/newPren")
    public ResponseEntity aggiungiPrenotazione(@RequestBody @Valid Prenotazione prenotazione){//Utente u, @RequestParam String data, @RequestParam String ora, @RequestBody @Valid Campo c){
        Prenotazione newPren;
        try{
           newPren = prenotazioneService.prenotaUnCampo(prenotazione);
           return new ResponseEntity<>(newPren,HttpStatus.OK);
        }
        catch(CampoOccupatoException coe){
            return new ResponseEntity<>("campo occupato,selezionare un'altra data o un altro orario", HttpStatus.BAD_REQUEST);
        }
        catch (CampoNonTrovatoException e) {
            return new ResponseEntity<>("campo non trovato", HttpStatus.BAD_REQUEST);
        }

    }//aggiungiPren

    @PostMapping("/elimina")
    public ResponseEntity eliminaPrenotazione(@RequestParam int id){
        Prenotazione newPren;
        try{
            newPren = prenotazioneService.eliminaPrenotazione(id);
            return new ResponseEntity<>(newPren,HttpStatus.OK);
        }
        catch (PrenotazioneNonTrovataException e) {
            return new ResponseEntity<>("prenotazione non trovata",HttpStatus.BAD_REQUEST);
        }

    }//EliminaPrenotazione

    //@PreAuthorize("hasAuthority('gestore')")
    @GetMapping("/prenUtente")
    public ResponseEntity getPrenotazioniUtente(@RequestBody @Valid Utente utente){
        try{
            List<Prenotazione> ret= prenotazioneService.trovaPrenotazioniUtente(utente);
            return new ResponseEntity<>(ret,HttpStatus.OK);
        }
        catch(UtenteNonTrovatoException e){
            return new ResponseEntity<>("utente non trovato", HttpStatus.BAD_REQUEST);
        }
    }//getprenotazioniUtente


    @GetMapping("/prenData")
    public ResponseEntity prenotazioniInData(@RequestParam String data){
        List<Prenotazione> ret= prenotazioneService.trovaPrenotazioniInData(data);
        if(ret.size()<= 0)
            return new ResponseEntity<>("nessuna prenotazione in data "+data,HttpStatus.BAD_REQUEST );
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }//prenotazioniInData

}//PrenotazioneController


