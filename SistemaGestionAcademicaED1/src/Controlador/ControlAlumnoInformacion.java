package Controlador;

import Entidad.Alumno;
import Entidad.Aula;
import Entidad.Curso;
import Entidad.Matricula;
import ObDTO.InformacionAlumno;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aless
 */
public class ControlAlumnoInformacion {

    private Alumno alumno;
    private InformacionAlumno informacionAlumno;

    public ControlAlumnoInformacion() {
        informacionAlumno = new InformacionAlumno();
    }

    //
    public void actualizarDatosAlumnos(String nombre, String apellido, String dni, String telefono, String direccion) {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_ObtenerDatosAlumnoByIdUsuario(?,?,?,?,?,?)";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, alumno.getIdAlumno());
            cs.setString(2, nombre);
            cs.setString(3, apellido);
            cs.setString(4, dni);
            cs.setString(5, telefono);
            cs.setString(6, direccion);
            cs.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void obtenerDatosAlumno() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "{call usp_InformacionAulumnoById(?)}";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, alumno.getIdAlumno());
            ResultSet rs;
            rs = cs.executeQuery();
            while (rs.next()) {
                informacionAlumno.setAlumno(new Alumno() {
                    {
                        setNombre(rs.getString("nombreAlumno"));
                        setApellido(rs.getString("apellidos"));
                        setDocumentoIdentidad(rs.getString("documentoIdentidad"));
                        setDireccion(rs.getString("direccion"));
                        setTelefono(rs.getString("telefonoEmergencia"));
                        setGenero(rs.getString("genero").charAt(0));
                    }
                });
                informacionAlumno.setAula(new Aula() {
                    {
                        setNivel(rs.getString("nombreNivel"));
                        setGrado(rs.getString("nombreGrado"));
                        setSeccion(rs.getString("nombreSeccion"));
                    }
                });
                informacionAlumno.setMatricula(new Matricula() {
                    {
                        setEstadoMatricula(rs.getString("estadoMatricula"));
                        setPeriodoAcademico(rs.getString("periodoAcademico"));
                    }
                });
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public InformacionAlumno getInformacionAlumno() {
        return informacionAlumno;
    }

    public void setInformacionAlumno(InformacionAlumno informacionAlumno) {
        this.informacionAlumno = informacionAlumno;
    }

}
