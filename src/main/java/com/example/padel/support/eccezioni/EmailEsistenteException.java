package com.example.padel.support.eccezioni;

public class EmailEsistenteException extends Exception {

    public EmailEsistenteException(){
        System.out.println("esiste già un account con questo indirizzo email");
    }
}//class
