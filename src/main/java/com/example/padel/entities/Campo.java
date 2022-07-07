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
@Table(name = "campo", schema= "partite")
public class Campo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name="tipo")
    private String tipo;

    @OneToMany(mappedBy = "campo", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    

}//Pitch
