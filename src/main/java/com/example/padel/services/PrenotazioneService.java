package com.example.padel.services;

import com.example.padel.entities.Prenotazione;
import com.example.padel.entities.Utente;
import com.example.padel.repositories.CampoRepository;
import com.example.padel.repositories.PrenotazioneRepository;
import com.example.padel.repositories.UtenteRepository;
import com.example.padel.support.eccezioni.CampoNonTrovatoException;
import com.example.padel.support.eccezioni.CampoOccupatoException;
import com.example.padel.support.eccezioni.PrenotazioneNonTrovataException;
import com.example.padel.support.eccezioni.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private CampoRepository campoRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Prenotazione prenotaUnCampo(Prenotazione newPren) throws CampoOccupatoException, CampoNonTrovatoException {//Utente u,String data, String ora, Campo c) throws CampoOccupatoException {

        if(prenotazioneRepository.findByDataAndOraAndCampo(newPren.getData(), newPren.getOra(), newPren.getCampo()) != null )
           throw new CampoOccupatoException();
        if (campoRepository.findById(newPren.getCampo().getId()) == null)
            throw new CampoNonTrovatoException();
        /*
        Prenotazione newPren = new Prenotazione();
        newPren.setUtente(u);
        newPren.setData(data);
        newPren.setOra(ora);
        newPren.setCampo(c);*/

        Prenotazione pren= prenotazioneRepository.save(newPren);
        //System.out.println(pren);
/*
        Utente u = pren.getUtente();
        Campo c = pren.getCampo();
        List<Prenotazione> prenCampo = c.getPrenotazioni();
        List<Prenotazione> prenUtente = u.getPrenotazioni();
        prenUtente.add(pren);
        prenCampo.add(pren);
        campoRepository.save(c);
        utenteRepository.save(u);

       // entityManager.refresh(c);
        //entityManager.refresh(u);

        //entityManager.refresh(pren);*/
        return pren;
    }//PUC

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Prenotazione> trovaPrenotazioniUtente(Utente utente) throws UtenteNonTrovatoException {
        if(!utenteRepository.existsByEmail(utente.getEmail()))
            throw new UtenteNonTrovatoException();
        return prenotazioneRepository.findByUtente(utente);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Prenotazione> trovaPrenotazioniInData(String data){
        return prenotazioneRepository.findByData(data);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public Prenotazione eliminaPrenotazione(int id) throws PrenotazioneNonTrovataException {
        if(prenotazioneRepository.findById(id) == null)
            throw new PrenotazioneNonTrovataException();
        Prenotazione p = prenotazioneRepository.findById(id);
        prenotazioneRepository.delete(p);
        return p;
    }//eliminaPrenotazione



}//class
