package Controlador;

import Entidad.Alumno;
import Entidad.Matricula;
import Entidad.Profesor;
import Entidad.Usuario;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aless
 */
public class ControlSesionAlumno{

    private Alumno alumno;
    private Usuario usuario;
    private Matricula matricula;
    private Profesor profesor;


    public ControlSesionAlumno() {
        alumno= new Alumno();
        matricula= new Matricula();
        profesor= new Profesor();
    }
    
    //obtiene los datos del alumno mediante su idUsuario
    public void obtenerDatosAlumnoByIdUsuario() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_ObtenerDatosAlumnoByIdUsuario(?)";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, usuario.getId());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) { 
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombreAlumno"));
                alumno.setApellido(rs.getString("apellidos"));
                alumno.setDocumentoIdentidad(rs.getString("documentoIdentidad"));
                alumno.setTelefono(rs.getString("telefonoEmergencia"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setGenero(rs.getString("genero").charAt(0));
                alumno.setGrado(rs.getInt("idGrado"));
                alumno.setNivel(rs.getString("nombreNivel"));
                matricula.setId(rs.getInt("idMatricula"));
                matricula.setPeriodoAcademico(rs.getString("periodoAcademico"));
                matricula.setEstadoMatricula(rs.getString("estadoMatricula"));
                matricula.setFechaMatricula(rs.getDate("fechaMatricula"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void obtenerDatosProfesorById() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "{ call uf_datosProfesorById (?)}";
            CallableStatement cs = db.prepareCall(sp);
            ResultSet rs;
            cs.setInt(1, profesor.getIdProfesor());
            rs=cs.executeQuery();
            while(rs.next()){
                profesor.setIdProfesor(rs.getInt("idProfesor"));
                profesor.setNombre(rs.getString("nombreProfesor"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

}
