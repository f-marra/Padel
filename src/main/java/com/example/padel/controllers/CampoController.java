package com.example.padel.controllers;

import com.example.padel.entities.Campo;
import com.example.padel.services.CampoService;
import com.example.padel.support.eccezioni.CampoNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campo")
public class CampoController {

    @Autowired
    private CampoService campoService;

    //@PreAuthorize("hasAutority('gestore')")
    @PostMapping("/new")
    public ResponseEntity aggiungiCampo(@RequestParam String tipo){
        Campo ret = campoService.aggiungiCampo(tipo);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }//aggiungi campo

    @PostMapping("/delete")
    public ResponseEntity eliminaCampo(@RequestParam int id){
        try {
            Campo ret = campoService.eliminaCampo(id);
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        catch (CampoNonTrovatoException cne){
            return new ResponseEntity<>("campo non trovato", HttpStatus.BAD_REQUEST);
        }
    }//aggiungi campo

    @GetMapping("/liberi")
    public ResponseEntity trovaLiberi(@RequestParam(value="data") String data, @RequestParam(value="ora")String ora){
        List<Campo> ret = campoService.trovaCampiLiberi(data,ora);
        if(ret.size() <= 0)
            return new ResponseEntity<>("non ci sono campi liberi in quella data e in quell'orario", HttpStatus.OK);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }//trovaLiberi

}//campoController
