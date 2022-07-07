package com.example.padel.repositories;

import com.example.padel.entities.Campo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampoRepository extends JpaRepository<Campo,Integer> {
    List<Campo> findByTipo(String tipo);
    Campo findById(int id);
    boolean existsById(int id);

    @Query("SELECT  c " +
            "FROM Campo c " +
            "WHERE c.id not in ( SELECT p1.campo FROM Prenotazione p1 WHERE p1.data = :data and p1.ora = :ora ) ")
    List<Campo> findCampoLibero(@Param("data")String data, @Param("ora")String ora);
}//CampoRepository
