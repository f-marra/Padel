package com.example.padel.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "prenotazione", schema = "partite")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "data", nullable = false)
    private String data;

    @Basic
    @Column(name = "ora", nullable = false)
    private String ora;

    @ManyToOne
    @JoinColumn(name = "campo", nullable = false )
    //@JsonIgnore
    private Campo campo;

    @ManyToOne
    @JoinColumn(name = "utente",nullable = false)
    //@JsonIgnore
    private Utente utente;

}
