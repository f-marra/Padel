package com.example.padel.repositories;

import com.example.padel.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    Utente findById(int id);
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    List<Utente> findByEmail(String email);
    List<Utente> findByNomeAndCognome(String nome, String cognome);
    boolean existsByEmail(String email);
    List<Utente> findByIndirizzo(String indirizzo);
    boolean existsById(int id);
}//UtenteRepository
