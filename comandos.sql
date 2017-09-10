CREATE TABLE Agenda (
    ID      SERIAL        PRIMARY KEY,
    Data    VARCHAR (14)
);
CREATE TABLE Convenios (
    ID       SERIAL        PRIMARY KEY,
    Nome     VARCHAR (30) NOT NULL,
    Telefone VARCHAR (30)
);
CREATE TABLE Pacientes (
    ID             SERIAL       PRIMARY KEY,
    Nome           VARCHAR (30) NOT NULL,
    RG             VARCHAR (10),
    DataNascimento VARCHAR (10),
    Telefone       VARCHAR (15),
    Celular        VARCHAR (15),
    Estado         VARCHAR (2),
    Cidade         VARCHAR (30),
    Bairro         VARCHAR (30),
    Rua            VARCHAR (30),
    Número         VARCHAR (8),
    Complemento    VARCHAR (40)
);
