CREATE TABLE JUGADORES(
                          ID int not null AUTO_INCREMENT,
                          nombre varchar(50) not null unique,
                          contrasena varchar(60) not null,
                          email varchar(255) not null unique,
                          XP bigint not null default 0,
                          PRIMARY KEY (ID)
);

CREATE TABLE MISIONES(
    ID int not null AUTO_INCREMENT,
    nombre varchar(255) not nulL,
    XP int not null,
    TIPO varchar(20),
    PRIMARY KEY (ID)

);

    CREATE TABLE progreso_mision (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_jugador INT,
    id_mision INT,
    estado ENUM('EN_CURSO', 'COMPLETADA'),
    FOREIGN KEY (id_jugador) REFERENCES jugadores(id),
    FOREIGN KEY (id_mision) REFERENCES misiones(id)
);