package com.example.padel.repositories;

import com.example.padel.entities.Campo;
import com.example.padel.entities.Prenotazione;
import com.example.padel.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Integer> {

    List<Prenotazione> findByDataAndOra(String data, String ora);
    Prenotazione findById(int id);
    List<Prenotazione> findByUtente(Utente u);
    List<Prenotazione> findByCampo(Campo c);
    List<Prenotazione> findByData(String data);
    List<Prenotazione> findByUtenteAndData(Utente u, String data);
    List<Prenotazione> findByCampoAndData(Campo c, String data);
    Prenotazione findByDataAndOraAndCampo (String data, String ora, Campo c);
    boolean existsByDataAndOra(String data, String ora);
}//PrenotazioneRepository
