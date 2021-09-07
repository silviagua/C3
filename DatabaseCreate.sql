DROP  TABLE IF EXISTS CATEGORIA;
CREATE TABLE CATEGORIA (
    ID int NOT NULL,
    Nome varchar(100) NOT NULL,
    Descrizione varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);
insert into CATEGORIA values (1,'Alimentari','Vendita alimentari');
insert into CATEGORIA values (2,'Indumenti','Vendita indumenti');
insert into CATEGORIA values (3,'Prodotti casa','Vendita prodotti per la casa');
insert into CATEGORIA values (4,'Intimo','Vendita abbigliamento intimo uomo donna e bambino');


DROP  TABLE IF EXISTS NEGOZIO;
CREATE TABLE NEGOZIO (
    ID int NOT NULL AUTO_INCREMENT,
    Nome varchar(100) NOT NULL,
    OraApertura varchar(50) NOT NULL,
    OraChiusura varchar(50) NOT NULL,
    ID_Categoria int NOT NULL,
    PRIMARY KEY (ID),
	FOREIGN KEY (ID_Categoria) REFERENCES CATEGORIA(ID)
);

insert into Negozio (Nome, OraApertura, OraChiusura, ID_Categoria) values ('Alimentari Gino','09:00','20:00',1);
insert into Negozio (Nome, OraApertura, OraChiusura, ID_Categoria) values ('Vestiti Chiari','09:30','20:00',2);
insert into Negozio (Nome, OraApertura, OraChiusura, ID_Categoria) values ('Acqua & Sapone','08:30','20:00',3);
insert into Negozio (Nome, OraApertura, OraChiusura, ID_Categoria) values ('Tzenis','10:00','20:30',4);


DROP TABLE IF EXISTS PRODOTTO;
CREATE TABLE PRODOTTO (
    ID int NOT NULL AUTO_INCREMENT,
    Nome varchar(100) NOT NULL,
    Descrizione varchar(255),
    ID_Negozio int not null,
    Prezzo decimal(8,2),
    Iva int,
    PRIMARY KEY(ID),
    FOREIGN KEY (ID_Negozio) REFERENCES NEGOZIO(ID)
);


insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('Latte fresco','latte fresco del contadino',1,1.7,22);
insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('Mele pl','Mele pink lady confezione da 4',1,3,22);
insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('Pane','pane farina 00 da 0,5 kg',1,1.45,22);

insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('Maglia basic M','Maglia ovs basic colore bianco',2,5.15,22);
insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('T-shirt basic S','T-shirt ovs basic',2,4,22);
insert into Prodotto (Nome, Descrizione, ID_Negozio, Prezzo, Iva) values ('T-shirt basic M','T-shirt M ovs basic',2,4,22);


DROP  TABLE IF EXISTS RUOLO;
CREATE TABLE RUOLO (
    ID int NOT NULL,
    Nome varchar(100) NOT NULL,
    Descrizione varchar(255) NOT NULL,
    PRIMARY KEY (ID)
);

insert into ruolo values (1,'ADMIN','Amministratore sistema');
insert into ruolo values (2,'GESTN','Gestore Negozio');
insert into ruolo values (3,'COMM','Commesso');
insert into ruolo values (4,'USER','Cliente');
insert into ruolo values (5,'CORR','Corriere');

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente(
	ID int NOT NULL AUTO_INCREMENT,
	Nome VARCHAR(100) NOT NULL,
	Cognome VARCHAR(100) NOT NULL,
	Mail VARCHAR(100) NOT NULL,
	Password char(64) NOT NULL,
	Telefono varchar(100),
        userName varchar(20),
	id_ruolo int NOT NULL,
	ID_NEGOZIO INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (ID_NEGOZIO) References NEGOZIO(ID),
	FOREIGN KEY (ID_RUOLO) References RUOLO(ID),
	UNIQUE (Mail),
	UNIQUE (userName)
);

insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Silvia','Gualdesi','silvia@gualdesi.it',SHA2('utente1', 256),'333333','utente1',4,null);
insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Maria','Gualdesi','maria@gualdesi.it',SHA2('commesso1', 256), '333333','commesso1',3,1);
insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Lara','Gualdesi','lara@gualdesi.it',SHA2('commesso2', 256), '333333','commesso2',3,2);
insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Mario','Rossi','mariorossi@gmail.com',SHA2('corriere1', 256), '333333','corriere1',5,null);
insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Maria','Rossi','mariarossi@gmail.com',SHA2('corriere2', 256), '333333','corriere2',5,null);
insert into Utente (Nome, Cognome, Mail, Password, Telefono, userName, Id_ruolo, Id_Negozio) values ('Luigi','Rossi','luigirossi@gmail.com',SHA2('utente2', 256), '333333','utente2',4,null);


DROP TABLE IF EXISTS VENDITA;
CREATE TABLE VENDITA (
    ID int NOT NULL AUTO_INCREMENT,
    Etichetta char(36) not null,
    Data date NOT NULL,
    Importo decimal(8,2) not null,
    Iva decimal(8,2) not null,
    pagato boolean not null,
    ID_Utente int NOT NULL,
    ID_Negozio int NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (ID_Utente) REFERENCES UTENTE(ID),
    FOREIGN KEY (ID_Negozio) REFERENCES NEGOZIO(ID)
);

DROP TABLE IF EXISTS PRDOTTO_VENDITA;
CREATE TABLE PRODOTTO_VENDITA (
    ID int NOT NULL AUTO_INCREMENT,
    ID_Prodotto int NOT NULL,
    ID_Vendita int NOT NULL,
    Quantita int NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (ID_Prodotto) REFERENCES PRODOTTO(ID),
    FOREIGN KEY (ID_Vendita) REFERENCES VENDITA(ID)
);



DROP TABLE IF EXISTS TIPO_DESTINAZIONE;
CREATE TABLE TIPO_DESTINAZIONE(
	ID int NOT NULL,
	NOME CHAR(100) NOT NULL,
	DESCRIZIONE VARCHAR(244),
	PRIMARY KEY (ID)
);
insert into TIPO_DESTINAZIONE values (1,'Indirizzo','');
insert into TIPO_DESTINAZIONE values (2,'Locker','');

DROP TABLE IF EXISTS Stato_Pacco;
CREATE TABLE Stato_Pacco(
	ID int NOT NULL,
	NOME CHAR(100) NOT NULL,
	DESCRIZIONE VARCHAR(244),
	PRIMARY KEY (ID)
);

insert into Stato_Pacco values (1,'Creato','');
insert into Stato_Pacco values (2,'Da Consegnare','');
insert into Stato_Pacco values (3,'In Consegna','');
insert into Stato_Pacco values (4,'Ritirato','');
insert into Stato_Pacco values (5,'Consegnato a Locker','');

DROP TABLE IF EXISTS Locker;
CREATE TABLE Locker(
	ID int NOT NULL AUTO_INCREMENT,
	Nome CHAR(100) NOT NULL,
	Descrizione VARCHAR(255) NOT NULL,
	Indirizzo Varchar(255) NOT NULL,
	NumCelle int,
	Celle varchar(255), 
	PRIMARY KEY(ID)
);

insert into Locker(Nome, Descrizione, Indirizzo, NumCelle, Celle) values ('Locker1','Locker 1','Via Locker 1',5,"00000");
insert into Locker(Nome, Descrizione, Indirizzo, NumCelle, Celle) values ('Locker2','Locker 2','Via Locker 2',10,"0000000000");
  
DROP TABLE IF EXISTS Pacco;
CREATE TABLE Pacco (
    ID int NOT NULL AUTO_INCREMENT,
    Etichetta char(36) not null,
    ID_Tipo_Destinazione int not null,
    ID_Stato_Pacco int not null,
    Indirizzo varchar(255),
    ID_Locker int,
    ID_VENDITA INT NOT NULL,
    ID_CORRIERE int, 
    PRIMARY KEY (ID),
	FOREIGN KEY (ID_Tipo_Destinazione) REFERENCES TIPO_DESTINAZIONE(ID),
	FOREIGN KEY (ID_Stato_Pacco) REFERENCES Stato_Pacco(ID),
	FOREIGN KEY (ID_Locker) REFERENCES Locker(ID),
	FOREIGN KEY (ID_Vendita) REFERENCES VENDITA(ID),
	FOREIGN KEY (ID_CORRIERE) REFERENCES UTENTE(ID)
);    
 


	


