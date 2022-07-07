package com.example.padel.services;

import com.example.padel.entities.Utente;
import com.example.padel.repositories.UtenteRepository;
import com.example.padel.support.eccezioni.EmailEsistenteException;
import com.example.padel.support.eccezioni.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class UtenteService{

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente aggiungiUtente(Utente utente) throws EmailEsistenteException {
        if(utenteRepository.existsByEmail(utente.getEmail()))
            throw new EmailEsistenteException();
        utenteRepository.save(utente);
       // entityManager.refresh(utente);
        return utente;
    }//aggiungiUtente

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Utente eliminaUtente(int id) throws UtenteNonTrovatoException {
        if(!utenteRepository.existsById(id))
            throw new UtenteNonTrovatoException();
        Utente u = utenteRepository.findById(id);
        utenteRepository.delete(u);
        return u;
    }

    @Transactional (readOnly = true)
    public List<Utente> trovaTuttiGliUtenti(int nPage, int size, String sort){
        Pageable page = PageRequest.of(nPage,size, Sort.by(sort));
        Page<Utente> res = utenteRepository.findAll(page);
        return res.getContent();
    }//trovaTutti

    @Transactional (readOnly = true)
    public List<Utente> trovaUtente (String nome, String cognome){
        return utenteRepository.findByNomeAndCognome(nome,cognome);
    }//trovaUtente


    
}//UtenteService
