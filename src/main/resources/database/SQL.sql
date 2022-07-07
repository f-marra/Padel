create schema partite;
use partite;

create table utente(
    id integer AUTO_INCREMENT PRIMARY KEY,
    nome varchar(50),
    cognome varchar(50),
    email varchar(70),
    indirizzo varchar (70)
);

create table campo(
    id integer AUTO_INCREMENT PRIMARY KEY,
    tipo varchar(20)
);

create table prenotazione(
    id integer AUTO_INCREMENT PRIMARY KEY,
    data varchar (10),
    ora varchar(10),
    campo integer,
    utente integer,
    foreign key (campo) references campo(id),
    foreign key (utente) references utente(id)
);
