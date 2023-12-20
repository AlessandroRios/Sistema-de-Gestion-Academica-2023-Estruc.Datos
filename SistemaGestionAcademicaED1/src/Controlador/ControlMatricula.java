package Controlador;

import Entidad.Alumno;
import Entidad.Aula;
import Entidad.Matricula;
import Entidad.Usuario;
import Interfaz.IListaEnlazadaSimple;
import Lista.ListaEnlazadaSimple;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ControlMatricula {

    private Aula aula;
    private Alumno alumno;
    private Matricula matricula;
    private Usuario usuario;
    private IListaEnlazadaSimple<Aula> aulas;

    public ControlMatricula() {
        aulas = new ListaEnlazadaSimple();
    }

    public void matricularAlumno() {
        try (var db = new ConexionDB().conectarDB()) {
            String sp = "{Call usp_MatricularAlumno(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = db.prepareCall(sp);
            cs.setString(1, alumno.getNombre());
            cs.setString(2, alumno.getApellido());
            cs.setString(3, alumno.getDocumentoIdentidad());
            cs.setString(4, alumno.getTelefono());
            cs.setString(5, alumno.getDireccion());
            cs.setString(6, String.valueOf(alumno.getGenero()));
            cs.setFloat(7, (float) matricula.getCosto());
            cs.setString(8, matricula.getMetodoPago());
            cs.setString(9, matricula.getPeriodoAcademico());
            cs.setInt(10, aula.getId());
            cs.execute();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void aulasDisponibles(int idGrado, int idNivel, int idSeccion) {
        try (var db = new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_AulaDisponible(?,?,?)";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, idGrado);
            cs.setInt(2, idNivel);
            cs.setInt(3, idSeccion);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                aula = new Aula();
                aula.setId(rs.getInt("idAula"));
                aula.setNumeroAula(rs.getString("numeroAula"));
                aula.setGrado(rs.getString("nombreGrado"));
                aula.setNivel(rs.getString("nombreNivel"));
                aula.setSeccion(rs.getString("nombreSeccion"));
                aula.setCapacidadAlumnos(rs.getInt("capacidadAlumnos"));
                aulas.agregar(aula);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void usuarioCreado() {
        try (var db = new ConexionDB().conectarDB()) {
            String sp = "{Call usp_ObtenerUsuarioByDNI(?)}";
            CallableStatement cs = db.prepareCall(sp);
            cs.setString(1,alumno.getDocumentoIdentidad());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                usuario= new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setPassword(rs.getString("passwordUsuario"));
                usuario.setTipoUsuario(rs.getInt("tipoUsuario"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public IListaEnlazadaSimple<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(IListaEnlazadaSimple<Aula> aulas) {
        this.aulas = aulas;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

}
