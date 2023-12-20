-----DATA POR DEFAULT----
use DB_SisGesAcademica
GO

--DATA TIPO DE USUARIOS
INSERT INTO SISTEMA.TIPO_USUARIOS VALUES
('Profesor'),('Alumno'),('Administrativo')
GO
--DATA GRADOS
INSERT COLEGIO.GRADO VALUES
('Primero'),
('Segundo'),
('Tercero'),
('Cuarto'),
('Quinto'),
('Sexto')

--DATA NIVELES
INSERT COLEGIO.NIVEL VALUES
('Primaria'),
('Secundaria')
GO
--DATA CURSOS
--CURSOS DE PRIMARIA
DECLARE @c int = 1
WHILE @c<=6
begin
INSERT INTO COLEGIO.CURSOS VALUES
('Matematica',@c,1),
('Lengua y Literatura',@c,1),
('Ciencias Sociales',@c,1),
('Educaci�n F�sica',@c,1)
set @c=@c+1
end
GO
--CURSOS SECUNDARIA
DECLARE @c int = 1
WHILE @c<=5
begin
INSERT INTO COLEGIO.CURSOS VALUES
('Matematica Lineal',@c,2),
('Matematica No Lineal',@c,2),
('Estructura de Datos :/',@c,2),
('Lengua y Literatura',@c,2),
('Ciencias Sociales',@c,2),
('Educaci�n F�sica',@c,2),
('Fisica',@c,2),
('Etica y Ciudadania',@c,2)
set @c=@c+1
end
GO

--DATA SECCIONES: SON 6 GRADOS EN PRIMARIA, SON 5 GRADOS EN SECUNDARIA Y CADA GRADO TIENE 3 SECCIONES Y CADA SECCION TIENE 1 AULA
--(33 SECCIONES)
INSERT INTO COLEGIO.SECCIONES VALUES ('A'),('B'),('C')
--DATA AULAS
---Aulas Primaria(118)--
INSERT INTO COLEGIO.AULAS VALUES ('1', 1, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('2', 1, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('3', 1, 1, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('4', 2, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('5', 2, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('6', 2, 1, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('7', 3, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('8', 3, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('9', 3, 1, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('10', 4, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('11', 4, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('12', 4, 1, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('13', 5, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('14', 5, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('15', 5, 1, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('16', 6, 1, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('17', 6, 1, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('18', 6, 1, 3, 30)
--Aulas secundaria(119-133)---
INSERT INTO COLEGIO.AULAS VALUES ('19', 1, 2, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('20', 1, 2, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('21', 1, 2, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('22', 2, 2, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('23', 2, 2, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('24', 2, 2, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('25', 3, 2, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('26', 3, 2, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('27', 3, 2, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('28', 4, 2, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('29', 4, 2, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('30', 4, 2, 3, 30)
INSERT INTO COLEGIO.AULAS VALUES ('31', 5, 2, 1, 30)
INSERT INTO COLEGIO.AULAS VALUES ('32', 5, 2, 2, 30)
INSERT INTO COLEGIO.AULAS VALUES ('33', 5, 2, 3, 30)



