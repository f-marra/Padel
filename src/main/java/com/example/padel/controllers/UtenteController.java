package com.example.padel.controllers;

import com.example.padel.entities.Utente;
import com.example.padel.services.UtenteService;
import com.example.padel.support.eccezioni.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/")
    public String welcome (){
        return "benvenuto";
    }

    @PostMapping(value = "/user/add")
    public ResponseEntity nuovoUtente(@RequestBody @Valid Utente utente){
        try{
            utenteService.aggiungiUtente(utente);
            return new ResponseEntity<>(utente, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("errore nella registrazione", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user/delete")
    public ResponseEntity eliminaUtente(@RequestParam int id){
        try{
            Utente utente=utenteService.eliminaUtente(id);
            return new ResponseEntity<>(utente, HttpStatus.OK);
        }
        catch(UtenteNonTrovatoException e){
            return new ResponseEntity<>("utente non trovato", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/findAll") //ricerca paginata
    public ResponseEntity getAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                 @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        List<Utente> result = utenteService.trovaTuttiGliUtenti(pageNumber, pageSize, sortBy);
            if ( result.size() <= 0 ) {
                return new ResponseEntity<>("No results!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);

    }//getAll

    @GetMapping("user/findUser")
    public ResponseEntity trovaUtente(@RequestParam(required = false)String nome, @RequestParam(required = false ) String cognome){
        List<Utente> ret = utenteService.trovaUtente(nome,cognome);
        if(ret.size()<=0)
            return new ResponseEntity<>("No results!", HttpStatus.BAD_REQUEST);
        System.out.println(nome +" "+ cognome);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }//trovaUtente



}//class
