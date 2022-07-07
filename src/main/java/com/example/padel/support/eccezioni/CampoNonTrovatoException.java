package com.example.padel.support.eccezioni;

import ch.qos.logback.core.net.SyslogOutputStream;

public class CampoNonTrovatoException extends Exception{
    public CampoNonTrovatoException(){
        System.out.println("campo non trovato");
    }
}
