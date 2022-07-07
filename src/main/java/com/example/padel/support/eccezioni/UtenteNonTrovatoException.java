package com.example.padel.support.eccezioni;

public class UtenteNonTrovatoException extends Exception{
    public UtenteNonTrovatoException(){
        System.out.println("utente non trovato");
    }
}
