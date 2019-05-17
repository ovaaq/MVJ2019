/*
Mokkivarausjarjestelma
DDL- & DML-luontikoodi
*/

DROP DATABASE IF EXISTS mokki; -- poistetaan tietokanta, mikäli on jo olemassa

CREATE DATABASE mokki; -- luodaan uusi tietokanta

USE mokki; -- otetaan kayttoon

CREATE TABLE TOIMIPISTEET
(
  toimipiste_ID INT NOT NULL AUTO_INCREMENT,
  nimi VARCHAR(30) NOT NULL,
  puh_nro VARCHAR (20) NOT NULL,
  lahiosoite VARCHAR(30) NOT NULL,
  postinumero VARCHAR (10) NOT NULL,
  paikkakunta VARCHAR(30) NOT NULL,
  email VARCHAR(30), -- ei pakollinen
  itime TIMESTAMP,
  iby VARCHAR (20),
  utime TIMESTAMP,
  uby VARCHAR (20),
  PRIMARY KEY (toimipiste_ID)
);

CREATE TABLE MP
(
  mp_id INT NOT NULL AUTO_INCREMENT,
  kategoria VARCHAR (10) NOT NULL,
  nimi VARCHAR(30) NOT NULL,
  kapasiteetti INT, -- ei pakollinen
  pinta_ala INT, -- ei pakollinen
  kuvaus VARCHAR(200), -- ei pakollinen
  hinta DOUBLE NOT NULL,
  alv DOUBLE NOT NULL,
  itime TIMESTAMP,
  iby VARCHAR (20),
  utime TIMESTAMP,
  uby VARCHAR (20),
  toimipiste_ID INT NOT NULL,
  PRIMARY KEY (mp_id),
  FOREIGN KEY (toimipiste_ID) REFERENCES TOIMIPISTEET(toimipiste_ID) ON DELETE CASCADE
);

CREATE TABLE ASIAKKAAT
(
  asiakas_ID INT NOT NULL AUTO_INCREMENT,
  etunimi VARCHAR(20) NOT NULL,
  sukunimi VARCHAR(20) NOT NULL,
  lahiosoite VARCHAR(30) NOT NULL,
  postinumero VARCHAR (10) NOT NULL,
  paikkakunta VARCHAR(30) NOT NULL,
  email VARCHAR(30),
  puh_nro VARCHAR(20) NOT NULL,
  itime TIMESTAMP,
  iby VARCHAR (20),
  utime TIMESTAMP,
  uby VARCHAR (20),
  PRIMARY KEY (asiakas_ID)
);

CREATE TABLE VARAUKSET
(
  varaus_ID INT NOT NULL AUTO_INCREMENT,
  lasku_pvm  DATE, -- tähän tulee päivämäärä
  varauspaiva DATE NOT NULL, -- pvm-muoto
  alku_pvm DATE NOT NULL, -- pvm-muoto
  loppu_pvm DATE NOT NULL, -- pvm-muoto
  lasku_erapaiva DATE, -- tähän voisi laittaa +14vrk lasku_pvm alkaen
  viitenumero VARCHAR(30) NOT NULL, -- tähän kohtaan pitäisi luoda joku randomi numerosarja java-koodissa, jotta se muodostaa automaattisesti viitenumeron
  viivastyskorko DOUBLE DEFAULT 8.0, -- tähän vakio viivästyskorko
  huomautusaika VARCHAR(10) DEFAULT '14 pv.', -- vakio 14pv.
  maksuehto VARCHAR(10) DEFAULT '14 pv.', -- vakio 14pv.
  maksettu VARCHAR(10) DEFAULT 'maksamaton',
  piilotettu VARCHAR(10) DEFAULT 'ei',
  
  itime TIMESTAMP,
  iby VARCHAR (20),
  utime TIMESTAMP,
  uby VARCHAR (20),
  asiakas_ID INT NOT NULL,
  toimipiste_ID INT NOT NULL,
  PRIMARY KEY (varaus_ID),
  FOREIGN KEY (asiakas_ID) REFERENCES ASIAKKAAT(asiakas_ID) ON DELETE CASCADE,
  FOREIGN KEY (toimipiste_ID) REFERENCES TOIMIPISTEET(toimipiste_ID) ON DELETE CASCADE
);

CREATE TABLE VARAUKSENHALLINTA
(
  mp_id INT NOT NULL,
  varaus_ID INT NOT NULL,
  PRIMARY KEY (mp_id, varaus_ID),
  FOREIGN KEY (mp_id) REFERENCES MP(mp_id) ON DELETE CASCADE,
  FOREIGN KEY (varaus_ID) REFERENCES VARAUKSET(varaus_ID) ON DELETE CASCADE
);

/*
Lisätty pari toimipistetta, mokkia ja palvelua sekä asiakkaita ja varauksia
*/

INSERT INTO TOIMIPISTEET (nimi, puh_nro, lahiosoite, postinumero, paikkakunta, email) VALUES ('Vantaa', '0401234567', 'Kuusikkotie 11 C 2', '01380', 'Vantaa', 'vantaa@village.fi');
INSERT INTO TOIMIPISTEET (nimi, puh_nro, lahiosoite, postinumero, paikkakunta, email) VALUES ('Kuopio', '0405555555', 'Kuopion tori 666', '70100', 'Kuopio', 'kuopio@village.fi');

INSERT INTO MP (kapasiteetti, pinta_ala, kuvaus, hinta, kategoria, nimi, alv, toimipiste_id) VALUES ('6', '80', '4-6 hengen Villa rauhallisessa Vantaan Kuusikossa hyvien palvelujen lahella', '250.0', 'mokki', 'Villa Vantaa', '10.0', '1');
INSERT INTO MP (kuvaus, hinta, kategoria, nimi, alv, toimipiste_id) VALUES ('Aamupalatarjoilu suoraan huoneistoon. Monipuolinen meriaamiainen. Saatavilla gluteenittomana.', '50', 'palvelu', 'Aamupala huoneistoon', '24.0', '1');
INSERT INTO MP (kapasiteetti, pinta_ala, kuvaus, hinta, kategoria, nimi, alv, toimipiste_id) VALUES ('4', '50', '3-4 hengen mokki Kupion torin kupeessa', '175.0', 'mokki', 'Casa de Kuopio', '10.0', '2');
INSERT INTO MP (kuvaus, hinta, kategoria, nimi, alv, toimipiste_id) VALUES ('45 minuutin kokovartalohieronta SPA-centerissa', '40', 'palvelu', 'Hieronta', '24.0', '2');

  -- ASIAKKAAT
  
  INSERT INTO ASIAKKAAT (etunimi, sukunimi, postinumero, paikkakunta, lahiosoite, email, puh_nro)
  VALUES ('Aku', 'Ankka', '80140', 'Joensuu', 'Ankkatie 10', 'akuankka@zoo.fi', '0402132435');
  
  INSERT INTO ASIAKKAAT (etunimi, sukunimi, postinumero, paikkakunta, lahiosoite, email, puh_nro)
  VALUES ('Musta', 'Naamio', '32215', 'Utsjoki', 'helvetinkylmätie 5', 'musta@naamio.com', '0504400689');
  
  INSERT INTO ASIAKKAAT (etunimi, sukunimi, postinumero, paikkakunta, lahiosoite, email, puh_nro)
  VALUES ('Jesse', 'Nurminen', '00980', 'Helsinki', 'Itäkatu 7', 'jessenur@nokia.fi', '040093526');
  
  -- VARAUKSET
  
  
  
