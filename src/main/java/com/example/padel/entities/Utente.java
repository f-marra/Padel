package com.example.padel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity

@Table(name = "utente", schema="partite")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @Basic
    @Column(name ="nome",nullable = false)
    private String nome;

    @Basic
    @Column(name ="cognome",nullable = false)
    private String cognome;

    @Basic
    @Column(name ="email",nullable = false)
    private String email;

    @Basic
    @Column(name ="indirizzo")
    private String indirizzo;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

}//User
