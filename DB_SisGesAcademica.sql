USE master
GO


IF EXISTS(SELECT name From master.sys.databases WHERE name ='DB_SisGesAcademica')
BEGIN
	DROP DATABASE DB_SisGesAcademica
END

CREATE DATABASE DB_SisGesAcademica
GO

USE DB_SisGesAcademica
GO

--------ESQUEMAS-----
CREATE SCHEMA SISTEMA--Todas las entidades referentes al sistema con operaciones dentro de el
GO
CREATE SCHEMA COLEGIO--todas las entidades que representen personas o transacciones en el colegio 
GO
--------TABLAS-------
CREATE TABLE SISTEMA.TIPO_USUARIOS(
idTipoUsuario INT IDENTITY(1,1) PRIMARY KEY,
tipoUsuario VARCHAR(50) NOT NULL --PROFESOR,ALUMNO,administrativo
)
GO

CREATE TABLE SISTEMA.USUARIOS(
idUsuario INT IDENTITY(1,1) PRIMARY KEY,
nombreUsuario VARCHAR(20) UNIQUE NOT NULL, --Se creara apartir de la combinacion del nombre y apellido de la persona
passwordUsuario VARCHAR(20) NOT NULL,
tipoUsuario INT REFERENCES SISTEMA.TIPO_USUARIOS(idTipoUsuario)
)--cada empleado autorizado que tenga acceso al sistema
GO

CREATE TABLE COLEGIO.GRADO(
idGrado INT IDENTITY PRIMARY KEY,
nombreGrado VARCHAR(20) NOT NULL
)
GO

CREATE TABLE COLEGIO.NIVEL(
idNivel INT IDENTITY PRIMARY KEY,
nombreNivel VARCHAR(20) NOT NULL
)
GO

CREATE TABLE COLEGIO.CURSOS(
idCurso INT IDENTITY(1,1) PRIMARY KEY,
nombreCurso VARCHAR(150) NOT NULL,
idGrado INT REFERENCES COLEGIO.GRADO(idGrado),
idNivel INT REFERENCES COLEGIO.NIVEL(idNivel)
)
GO

CREATE TABLE COLEGIO.SECCIONES(
idSeccion INT IDENTITY(1,1) PRIMARY KEY,
nombreSeccion VARCHAR(20) NOT NULL,
)
GO

CREATE TABLE COLEGIO.AULAS(
idAula INT IDENTITY(1,1) PRIMARY KEY,
numeroAula VARCHAR(50) NOT NULL,
idGrado INT REFERENCES COLEGIO.GRADO(idGrado),
idNivel INT REFERENCES COLEGIO.NIVEL(idNivel),
idSeccion INT REFERENCES COLEGIO.SECCIONES(idSeccion),
capacidadAlumnos INT NOT NULL CHECK(capacidadAlumnos>20),
)
GO

CREATE TABLE COLEGIO.ALUMNOS(
idAlumno INT IDENTITY(1,1) PRIMARY KEY,
nombreAlumno VARCHAR(150) NOT NULL,
apellidos VARCHAR(150) NOT NULL,
documentoIdentidad VARCHAR(10) UNIQUE NOT NULL,
telefonoEmergencia VARCHAR(11) NOT NULL,
direccion VARCHAR(150) NOT NULL,
genero CHAR(1)CHECK(genero IN('M','F')) ,--M o F
idUsuario INT
)
GO

CREATE TABLE COLEGIO.PROFESORES(
idProfesor INT IDENTITY(1,1) PRIMARY KEY,
nombreProfesor VARCHAR(150) NOT NULL,
apellidos VARCHAR(150) NOT NULL,
telefono VARCHAR(11) NOT NULL,
direccion VARCHAR(150) NOT NULL,
documentoIdentidad VARCHAR(10) UNIQUE NOT NULL,
idUsuario INT 
)--Entidades que trabajan de forma fisica en el colegio
GO

CREATE TABLE COLEGIO.NOTAS(
idNota INT IDENTITY(1,1) PRIMARY KEY,
notaPc1 FLOAT DEFAULT 0 CHECK(notaPc1 between 0 and 20),
notaPc2 FLOAT DEFAULT 0  CHECK(notaPc2 between 0 and 20),
notaPc3 FLOAT DEFAULT 0  CHECK(notaPc3 between 0 and 20),
notaExamen1 FLOAT DEFAULT 0  CHECK(notaExamen1 between 0 and 20),
notaExamen2 FLOAT DEFAULT 0  CHECK(notaExamen2  between 0 and 20),
notaExamen3 FLOAT DEFAULT 0  CHECK(notaExamen3 between 0 and 20),
)
GO

CREATE TABLE COLEGIO.CURSOS_ALUMNOS_NOTAS(
idAlumno INT REFERENCES COLEGIO.ALUMNOS(idAlumno),
idCurso INT REFERENCES COLEGIO.CURSOS(idCurso),
idNota INT REFERENCES COLEGIO.NOTAS(idNota)
)--Tabla compuesta para almacenar las notas del alumno en sus cursos
GO

CREATE TABLE COLEGIO.MATRICULA(
idMatricula INT IDENTITY(1,1) PRIMARY KEY,
idAlumno INT REFERENCES COLEGIO.ALUMNOS(idAlumno),
idAula INT REFERENCES COLEGIO.AULAS(idAula),
fechaMatricula DATETIME DEFAULT GETDATE(),
costoMatricula MONEY CHECK (costoMatricula>0) NOT NULL,
metodoPago VARCHAR(150),
periodoAcademico VARCHAR(30) NOT NULL,--año en el cual se inscribe el alumno
estadoMatricula VARCHAR(50) DEFAULT 'ACTIVO' CHECK(estadoMatricula IN('ACTIVO','INACTIVO')),
--estado que determinara si el alumno continua en el periodo de matricula
descuentos FLOAT DEFAULT 0 ,--en caso de ser necesesario
)
GO

--Tabla en la cual iran los cursos que enseña cada profesor
CREATE TABLE COLEGIO.CURSOS_PROFESORES(
idProfesor INT REFERENCES COLEGIO.PROFESORES(idProfesor),
idCurso INT REFERENCES COLEGIO.CURSOS(idCurso)
)
GO
--AULA A LA QUE PERTENECE EL ALUMNO
CREATE TABLE COLEGIO.ALUMNO_AULA(
idAlumno INT REFERENCES COLEGIO.ALUMNOS(idAlumno),
idAula INT REFERENCES COLEGIO.AULAS(idAula)
)
GO

CREATE TABLE COLEGIO.ACTIVIDADES(
idActividad INT IDENTITY PRIMARY KEY,
descripcion TEXT
)
GO

CREATE TABLE COLEGIO.ACTIVIDADES_CURSO_AULA(
idActividades INT REFERENCES COLEGIO.ACTIVIDADES(idActividad),
idCurso INT REFERENCES COLEGIO.CURSOS(idCurso),
idAula INT REFERENCES COLEGIO.AULAS(idAula)
)
GO
-----------------------------------------------------------------VISTAS------------------------------------------------


----------------------------------------------------------------FUNCIONES---------------------------------------------
--------------TIPO TABLE------
---FUNCION PARA DEVOLVER LOS DATOS DEL PROFESOR
CREATE FUNCTION uf_datosProfesorById(
@idProfesor INT
)
RETURNS TABLE
AS RETURN(
	SELECT p.*,cp.idCurso,c.nombreCurso
	FROM COLEGIO.CURSOS_PROFESORES cp 
	INNER JOIN COLEGIO.PROFESORES p ON cp.idCurso=p.idProfesor
	INNER JOIN COLEGIO.CURSOS c ON cp.idCurso=c.idCurso
	WHERE cp.idProfesor=@idProfesor
)
GO

CREATE FUNCTION uf_ObtenerDatosAlumnoByIdUsuario(
@idUsuario INT
)
RETURNS TABLE
AS
RETURN(
	SELECT a.*,m.estadoMatricula,m.fechaMatricula,m.idMatricula,m.periodoAcademico,ul.*,n.nombreNivel
	FROM COLEGIO.ALUMNOS a 
	INNER JOIN COLEGIO.MATRICULA m ON a.idAlumno=m.idAlumno
	INNER JOIN COLEGIO.ALUMNO_AULA au ON a.idAlumno = au.idAlumno
	INNER JOIN COLEGIO.AULAS ul ON au.idAula=ul.idAula
	INNER JOIN COLEGIO.NIVEL n ON ul.idNivel=n.idNivel
	INNER JOIN SISTEMA.USUARIOS u ON a.idUsuario=u.idUsuario
	WHERE u.idUsuario=@idUsuario
)
GO
---funcion para logear al usuario
CREATE FUNCTION uf_LogearUsuario(
@nombreUsuario VARCHAR(30),
@password VARCHAR(30)
)
RETURNS TABLE
AS RETURN(
	SELECT u.idUsuario,u.nombreUsuario,u.passwordUsuario,u.tipoUsuario
	FROM SISTEMA.USUARIOS u
	WHERE @nombreUsuario=u.nombreUsuario AND u.passwordUsuario=@password
)
GO
--Funcion para obtener todos lo cursos de un alumno por su id
CREATE FUNCTION uf_ObtenerCursosAlumno(
@idAlumno INT
)
RETURNS TABLE
AS RETURN(
	SELECT can.*,c.nombreCurso,p.*
	FROM COLEGIO.CURSOS_ALUMNOS_NOTAS can
	INNER JOIN	COLEGIO.CURSOS c ON can.idCurso=c.idCurso
	LEFT JOIN COLEGIO.CURSOS_PROFESORES cp ON c.idCurso=cp.idCurso
	LEFT JOIN COLEGIO.PROFESORES p ON p.idProfesor=cp.idProfesor
	WHERE can.idAlumno=@idAlumno
)
GO
--Funcion para devolver todas las notas de un curso de un alumno asignado por id
CREATE FUNCTION uf_NotasCursoAlumnoById(
@idAlumno INT,
@idCurso INT
)
RETURNS TABLE AS
RETURN(
	SELECT n.*,ca.idCurso,c.nombreCurso
	FROM COLEGIO.CURSOS_ALUMNOS_NOTAS ca 
	INNER JOIN COLEGIO.NOTAS n ON ca.idNota=n.idNota
	INNER JOIN COLEGIO.CURSOS c ON ca.idCurso=c.idCurso
	WHERE ca.idAlumno=@idAlumno AND ca.idCurso=@idCurso
)
GO
--Funcion que devuelve todas las notas del alumno de todos sus cursos
CREATE FUNCTION uf_NotasCursoAlumno(
@idAlumno INT 
)
RETURNS TABLE AS
RETURN(
	SELECT n.*,c.nombreCurso
	FROM COLEGIO.CURSOS_ALUMNOS_NOTAS ca 
	INNER JOIN COLEGIO.NOTAS n ON ca.idNota=n.idNota
	INNER JOIN COLEGIO.CURSOS c ON c.idCurso=ca.idCurso
	WHERE ca.idAlumno=@idAlumno 
)
GO
--Funcion que devuelve las aulas que estan disponibles mediante el grado , seccion y nivel seleccionado
CREATE FUNCTION uf_AulaDisponible(
@idGrado INT ,
@idNivel INT,
@idSeccion INT
)
RETURNS TABLE AS
RETURN(
	SELECT a.idAula,a.numeroAula,g.nombreGrado,n.nombreNivel,s.nombreSeccion,a.capacidadAlumnos
	FROM COLEGIO.AULAS a 
	INNER JOIN COLEGIO.GRADO g ON a.idGrado=g.idGrado
	INNER JOIN COLEGIO.NIVEL n ON a.idNivel=n.idNivel
	INNER JOIN COLEGIO.SECCIONES s ON a.idSeccion= s.idSeccion
	WHERE (a.idNivel=@idNivel AND a.idGrado=@idGrado AND a.idSeccion=@idSeccion) AND a.capacidadAlumnos>0
)
GO

--------------TIPO ESCALAR-----
--FUNCION QUE DEVUELVA EL PROMEDIO DE NOTAS DE UN CURSO DE UNA SECCION
-- Función de tipo escalar
-------------------------------------------------------------TRIGGERS--------------------------------------------
--TRIGGER PARA LLEVAR EL HISTORIAL DE NOTAS INSERCION DE NOTAS 
--TRIGGER QUE CREE UN USUARIO Y CONTRASEÑA CUANDO SE INSERTE UN NUEVO ALUMNO O PROFESOR
--alumno
CREATE TRIGGER tg_CrearUsuarioAlumno
ON COLEGIO.ALUMNOS
AFTER INSERT
AS
BEGIN
    -- Variables para almacenar datos del nuevo usuario
    DECLARE @nombreUsuario VARCHAR(30);
    DECLARE @passwordUsuario VARCHAR(30);

    -- Obtener los datos del alumno recién insertado
    SELECT @nombreUsuario = LEFT(nombreAlumno, 1) + LEFT(apellidos, 4), 
           @passwordUsuario = documentoIdentidad
    FROM inserted;

    -- Insertar un nuevo usuario en la tabla SISTEMA.USUARIOS
	print 'insertando nuevo usuario'
    INSERT INTO SISTEMA.USUARIOS (nombreUsuario, passwordUsuario, tipoUsuario)
    VALUES (@nombreUsuario, @passwordUsuario, 2);

    -- Obtener el id del usuario recién insertado
    DECLARE @idUsuario INT = SCOPE_IDENTITY();

    -- Asociar el usuario con el alumno en la tabla COLEGIO.ALUMNOS
    UPDATE COLEGIO.ALUMNOS
    SET idUsuario = @idUsuario
    WHERE idAlumno IN (SELECT idAlumno FROM inserted);
END
GO
--profesor
CREATE TRIGGER tg_CrearUsuarioProfesor
ON COLEGIO.PROFESORES
AFTER INSERT
AS
BEGIN
    -- Variables para almacenar datos del nuevo usuario
    DECLARE @nombreUsuario VARCHAR(30);
    DECLARE @passwordUsuario VARCHAR(30);

    -- Obtener los datos del profesor recién insertado
    SELECT @nombreUsuario = CONCAT(nombreProfesor, apellidos), 
           @passwordUsuario = documentoIdentidad
    FROM inserted;

    -- Insertar un nuevo usuario en la tabla SISTEMA.USUARIOS
	print 'insertando nuevo usuario'
    INSERT INTO SISTEMA.USUARIOS (nombreUsuario, passwordUsuario, tipoUsuario)
    VALUES (@nombreUsuario, @passwordUsuario, 1);

    -- Obtener el id del usuario recién insertado
    DECLARE @idUsuario INT = SCOPE_IDENTITY();

    -- Asociar el usuario con el profesor en la tabla COLEGIO.PROFESORES
    UPDATE COLEGIO.PROFESORES
    SET idUsuario = @idUsuario
    WHERE idProfesor IN (SELECT idProfesor FROM inserted);
END
GO

--TRIGGER PARA ASIGNAR LOS CURSOS DEL ALUMNO POR GRADO Y NIVEL
CREATE TRIGGER COLEGIO.tg_AsignarCurso
ON COLEGIO.ALUMNO_AULA
AFTER INSERT
AS
BEGIN
DECLARE @idAlumno INT
    -- Insertar automáticamente un curso para cada alumno en la tabla COLEGIO.CURSOS_ALUMNOS_NOTAS
    print 'insertando datos en cursos alumno'
	INSERT INTO COLEGIO.CURSOS_ALUMNOS_NOTAS (idAlumno, idCurso)
    SELECT i.idAlumno, C.idCurso
    FROM inserted I
    INNER JOIN COLEGIO.AULAS A ON I.idAula = A.idAula
    INNER JOIN COLEGIO.CURSOS C ON A.idGrado = C.idGrado AND A.idNivel = C.idNivel
	SET @idAlumno=(SELECT i.idAlumno FROM inserted i)
	
	PRINT 'Insertando notas del alumno'

	DECLARE cursorNotas CURSOR FORWARD_ONLY FOR
		SELECT can.idNota 
		FROM COLEGIO.CURSOS_ALUMNOS_NOTAS can
		WHERE can.idAlumno=@idAlumno
	OPEN cursorNotas
	DECLARE @idNota INT 
		FETCH NEXT FROM cursorNotas INTO @idNota
	WHILE @@FETCH_STATUS=0
	BEGIN
		INSERT INTO COLEGIO.NOTAS DEFAULT VALUES;
		SET @idNota=SCOPE_IDENTITY()

		UPDATE COLEGIO.CURSOS_ALUMNOS_NOTAS
		SET idNota=@idNota
		WHERE CURRENT OF cursorNotas
		FETCH NEXT FROM cursorNotas INTO @idNota
	END
	CLOSE cursorNotas
	DEALLOCATE cursorNotas
END
GO

---------------------------------------------------------------INDICES-------------------------------------------------------
---CLUSTERED
---NON CLUSTERED
CREATE NONCLUSTERED INDEX IX_USUARIO_NOMBRE
ON SISTEMA.USUARIOS(nombreUsuario,tipoUsuario)
GO
---------------------------------------------------------------CURSORES-------------------------------------------------------

--------------------------------------------------PROCEDIMIENTOS CON TRANSACCIONES--------------------------------------------------
---INTRODUCIR NOTAS DE UN ALUMNO
CREATE PROCEDURE usp_EnviarNotaCursoAlumno(
@idAlumno INT,
@idCurso INT,
@pc1 FLOAT,@pc2 FLOAT,@pc3 FLOAT,@exa1 FLOAT,@exa2 FLOAT,@exa3 FLOAT
)
AS
BEGIN
	BEGIN TRAN
	BEGIN TRY
		UPDATE n--Tabla Notas
		SET n.notaPc1=@pc1, n.notaPc2=@pc2,n.notaPc3=@pc3,n.notaExamen1=@exa1,n.notaExamen2=@exa2,n.notaExamen3=@exa3
		FROM COLEGIO.NOTAS n
		INNER JOIN COLEGIO.CURSOS_ALUMNOS_NOTAS cn ON n.idNota=cn.idNota
		WHERE cn.idAlumno=@idAlumno AND cn.idCurso=@idCurso
		COMMIT--CONFIRMAMOS LA TRANSACCION
	END TRY
	BEGIN CATCH
		ROLLBACK -- DESHACEMOS LOS CAMBIOS 
	END CATCH
END
GO
----ACTUALIZAR DATOS DE UN ALUMNO
CREATE PROCEDURE usp_ActualizarAlumno(
@idAlumno INT,
@nombreAlumno VARCHAR(150),
@apellidos VARCHAR(150),
@documentoIdentidad VARCHAR(10),
@telefonoEmergencia VARCHAR(11),
@direccion VARCHAR(150)
)
AS
BEGIN
DECLARE @confirmacion INT
    BEGIN TRY
        BEGIN TRANSACTION;
        UPDATE COLEGIO.ALUMNOS
        SET
            nombreAlumno = @nombreAlumno,apellidos = @apellidos,documentoIdentidad = @documentoIdentidad,
			telefonoEmergencia = @telefonoEmergencia,
            direccion = @direccion
        WHERE idAlumno = @idAlumno;
        COMMIT---Confirmamos la transaccion
		SET @confirmacion=1---se agrego correctamente
    END TRY
    BEGIN CATCH
        ROLLBACK
		SET @confirmacion=0--transaccion fallida
    END CATCH
	RETURN @confirmacion
END
GO
--- ACTUALIZAR DATOS DEL PROFESOR
CREATE PROCEDURE ActualizarProfesor
    @idProfesor INT,
    @nombreProfesor VARCHAR(150),
    @apellidos VARCHAR(150),
    @telefono VARCHAR(11),
    @direccion VARCHAR(150),
    @documentoIdentidad VARCHAR(10)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
		DECLARE @confirmacion INT
        UPDATE COLEGIO.PROFESORES
        SET
            nombreProfesor = @nombreProfesor,apellidos = @apellidos, telefono = @telefono,direccion = @direccion,
            documentoIdentidad = @documentoIdentidad
        WHERE idProfesor = @idProfesor;
        COMMIT
		SET @confirmacion=1---transaccion realizada
    END TRY
    BEGIN CATCH
        ROLLBACK
        SET @confirmacion=0 --transaccion fallida
    END CATCH
	RETURN @confirmacion
END
GO
--PROCEDIMIENTO PARA MATRICULAR UN ALUMNO
CREATE PROCEDURE usp_MatricularAlumno(
--Datos Alumno
@NombreAlumno VARCHAR(150),
@Apellidos VARCHAR(150),
@DocumentoIdentidad VARCHAR(10),
@TelefonoEmergencia VARCHAR(11),
@Direccion VARCHAR(150),
@Genero CHAR(1),
--Datos Matricula del Alumno
@costoMatricula FLOAT,
@metodoPago VARCHAR(50),
@periodoAcademico VARCHAR(50),
@idAula INT
)
AS
BEGIN
BEGIN TRAN
    BEGIN TRY
        -- insertamos los datos del alumno
		print 'insertando datos alumno nuevo'
        INSERT INTO COLEGIO.ALUMNOS (NombreAlumno,Apellidos,DocumentoIdentidad,TelefonoEmergencia,Direccion,Genero)
        VALUES (@NombreAlumno,@Apellidos,@DocumentoIdentidad,@TelefonoEmergencia,@Direccion,@Genero)
		
		--Guardamos el identificador del alumno recien agregado
		DECLARE @idAlumno INT = SCOPE_IDENTITY();
		print 'insertando datos alumno en matricula'
		--insertamos los datos de la matricula
		INSERT INTO COLEGIO.MATRICULA (idAlumno,costoMatricula,metodoPago,periodoAcademico,idAula)
		VALUES (@idAlumno,@costoMatricula,@metodoPago,@periodoAcademico,@idAula)
		print 'insertando alumno_aula'
		--insertamos los datos del aula a la que va ir el alumno
		INSERT INTO COLEGIO.ALUMNO_AULA(idAlumno,idAula) VALUES(@idAlumno,@idAula)
		
		COMMIT--CONFIRMAMOS LA INSERCION
	END TRY
	BEGIN CATCH
	print error_message()
		ROLLBACK--DESHACEMOS EN CASO DE CUALQUIER ERROR
	END CATCH
END
GO
--PROCEDIMIENTO QUE DEVUELVA LOS DATOS DE USUARIO DE UN ALUMNO MATRICULADO POR EL DNI
CREATE PROCEDURE usp_ObtenerUsuarioByDNI(
@dni VARCHAR(8)
)
AS
BEGIN
	SELECT *
	FROM SISTEMA.USUARIOS u
	WHERE u.passwordUsuario LIKE @dni
END
GO
--PROCEDIMIENTO QUE INVOCARA A UNA FUNCION PARA MOSTRAR LAS NOTAS Y EL CURSO DE UN ALUMNO
CREATE PROCEDURE usp_NotasCusosAlumno(
@idAlumno INT
)
AS
BEGIN
	SELECT * FROM uf_NotasCursoAlumno(@idAlumno)
END
GO
--PROCEDIMIENTO PARA MOSTRAR LA INFORMACION DE UN ALUMNO POR EL ID
CREATE PROCEDURE usp_InformacionAulumnoById(
@idAlumno INT
)
AS
BEGIN
	SELECT *
	FROM COLEGIO.ALUMNOS a
	INNER JOIN COLEGIO.ALUMNO_AULA aul ON a.idAlumno=aul.idAlumno
	INNER JOIN COLEGIO.AULAS au ON  aul.idAula=au.idAula
	INNER JOIN COLEGIO.NIVEL n ON n.idNivel=au.idNivel
	INNER JOIN COLEGIO.SECCIONES s ON s.idSeccion=au.idSeccion
	INNER JOIN COLEGIO.GRADO g ON g.idGrado=au.idGrado
	INNER JOIN COLEGIO.MATRICULA m ON m.idAlumno=a.idAlumno
	WHERE a.idAlumno=@idAlumno
END
GO
--PROCEDIMIENTO PARA ACTUALIZAR DATOS DE ALUMNO
CREATE PROCEDURE usp_ActualizarInformacionAlumna(
@idAlumno INT,
@nombreAlumno VARCHAR(50),
@apellidoAlumno VARCHAR(50),
@dni VARCHAR(8),
@telefono VARCHAR(9),
@direccion VARCHAR(150)
)
AS
BEGIN
	UPDATE a 
	SET a.nombreAlumno=@nombreAlumno,a.apellidos=@apellidoAlumno,a.documentoIdentidad=@dni,
	a.telefonoEmergencia=@telefono,a.direccion=@direccion
	FROM COLEGIO.ALUMNOS a
	WHERE a.idAlumno=@idAlumno
END
GO
--FUNCION PARA MOSTRAR ACTIVIDADES POR ID DE ALUMNO Y CURSO
CREATE PROCEDURE usp_ActividadesAlumnoCurso(
@idGrado INT,
@idNivel INT,
@idCurso INT
)
AS
BEGIN
	SELECT a.*,aca.idCurso,cur.nombreCurso
	FROM COLEGIO.ACTIVIDADES a
	INNER JOIN COLEGIO.ACTIVIDADES_CURSO_AULA aca ON a.idActividad=aca.idActividades
	INNER JOIN COLEGIO.CURSOS cur ON aca.idCurso=cur.idCurso
	INNER JOIN COLEGIO.AULAS au ON aca.idAula=au.idAula
	WHERE au.idGrado=@idGrado AND au.idNivel=@idNivel AND aca.idCurso=@idCurso
END
GO
--PROCEDIMIENTO PARA ELIMINAR UN ALUMNO
--PROCEDIMIENTO PARA ACTUALIZAR DATOS DE UN ALUMNO

/*
----------------ROLES DE SEGURIDAD----
CREATE ROLE Administrador;
CREATE ROLE Profesor;
CREATE ROLE Alumno;
----PERMISOS----
---ADMIN
GRANT SELECT, INSERT, UPDATE, DELETE ON SISTEMA.TIPO_USUARIOS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON SISTEMA.USUARIOS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.AULAS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.GRADO TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.NIVEL TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.CURSOS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.SECCIONES TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.ALUMNOS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.PROFESORES TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.NOTAS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.CURSOS_ALUMNOS_NOTAS TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.MATRICULA TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.CURSOS_PROFESORES TO Administrador;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.ALUMNO_GRADO_NIVEL TO Administrador;

-- PROFESOR
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.CURSOS TO Profesor;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.CURSOS_ALUMNOS_NOTAS TO Profesor;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.NOTAS TO Profesor;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.PROFESORES TO Profesor;
GRANT SELECT, INSERT, UPDATE, DELETE ON COLEGIO.ALUMNO_GRADO_NIVEL TO Profesor;

--ALUMNO
GRANT SELECT ON COLEGIO.CURSOS TO Alumno;
GRANT SELECT ON COLEGIO.ALUMNOS TO Alumno;
GRANT SELECT ON COLEGIO.NOTAS TO Alumno;
GRANT SELECT ON COLEGIO.CURSOS_ALUMNOS_NOTAS TO Alumno;
GRANT SELECT ON COLEGIO.MATRICULA TO Alumno;
*/