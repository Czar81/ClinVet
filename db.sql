DROP DATABASE IF EXISTS clinica_veterinaria;
CREATE DATABASE clinica_veterinaria CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clinica_veterinaria;

CREATE TABLE cliente (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(60)  NOT NULL,
    apellidos VARCHAR(80)  NOT NULL,
    telefono  VARCHAR(20)  NOT NULL,
    correo    VARCHAR(100)
);

CREATE TABLE mascota (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(60) NOT NULL,
    idCliente  INT         NOT NULL,
    especie    VARCHAR(40) NOT NULL,
    raza       VARCHAR(60),
    FOREIGN KEY (idCliente) REFERENCES cliente(id)
);

CREATE TABLE veterinario (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(60) NOT NULL,
    apellidos    VARCHAR(80) NOT NULL,
    especialidad VARCHAR(60)
);

CREATE TABLE cita (
    idCita        INT AUTO_INCREMENT PRIMARY KEY,
    idMascota     INT          NOT NULL,
    idVeterinario INT          NOT NULL,
    fechaHora     DATETIME     NOT NULL,
    motivo        VARCHAR(200),
    estado        VARCHAR(20)  NOT NULL DEFAULT 'Agendada',
    FOREIGN KEY (idMascota)     REFERENCES mascota(id),
    FOREIGN KEY (idVeterinario) REFERENCES veterinario(id)
);

-- Datos de ejemplo
INSERT INTO cliente (nombre, apellidos, telefono, correo) VALUES
('Laura', 'Mora Vargas', '88881111', 'laura@correo.com'),
('Carlos', 'Rojas Solano', '87772222', 'carlos@correo.com');

INSERT INTO mascota (nombre, idCliente, especie, raza) VALUES
('Rocky', 1, 'Perro', 'Labrador'),
('Misu', 1, 'Gato', 'Siames'),
('Luna', 2, 'Perro', 'Poodle');

INSERT INTO veterinario (nombre, apellidos, especialidad) VALUES
('Andrea', 'Campos Jimenez', 'Medicina general'),
('Diego', 'Alfaro Quesada', 'Cirugia');

INSERT INTO cita (idMascota, idVeterinario, fechaHora, motivo, estado) VALUES
(1, 1, '2026-11-20 09:00:00', 'Vacunacion anual', 'Agendada'),
(3, 2, '2026-11-21 14:30:00', 'Revision post operatoria', 'Agendada');