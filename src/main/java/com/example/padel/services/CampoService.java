package com.example.padel.services;

import com.example.padel.entities.Campo;
import com.example.padel.repositories.CampoRepository;
import com.example.padel.repositories.PrenotazioneRepository;
import com.example.padel.support.eccezioni.CampoNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CampoService {

    @Autowired
    private CampoRepository campoRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Transactional(readOnly = false)
    public Campo aggiungiCampo(String t){
        Campo c = new Campo();
        c.setTipo(t);
        campoRepository.save(c);
        //entityManager.refresh(c);
        return c;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public Campo eliminaCampo(int id) throws CampoNonTrovatoException {
        if(!campoRepository.existsById(id))
            throw new CampoNonTrovatoException();
        Campo c = campoRepository.findById(id);
        campoRepository.delete(c);
        return c;
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Campo> trovaCampiLiberi(String data, String ora){
        //if(prenotazioneRepository.existsByDataAndOra(data,ora))
        return campoRepository.findCampoLibero(data,ora);
        // return campoRepository.findAll();
    }//trovacampiLiberi


}//class
