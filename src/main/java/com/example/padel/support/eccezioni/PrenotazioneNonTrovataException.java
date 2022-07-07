package com.example.padel.support.eccezioni;

public class PrenotazioneNonTrovataException extends Exception {

    public PrenotazioneNonTrovataException(){
        System.out.println("prenotazione non trovata");
    }
}
