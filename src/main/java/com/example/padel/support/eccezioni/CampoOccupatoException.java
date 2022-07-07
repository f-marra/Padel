package com.example.padel.support.eccezioni;

public class CampoOccupatoException extends Exception{
    public CampoOccupatoException(){
        System.out.println("campo già occupato per quella data e quell'orario");
    }
}
