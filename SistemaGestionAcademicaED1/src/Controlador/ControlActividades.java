package Controlador;

import Cola.Cola;
import Entidad.Actividad;
import Entidad.Curso;
import Interfaz.IListaEnlazadaDoble;
import Lista.ListaEnlazadaDoble;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aless
 */
public class ControlActividades {

    private Actividad actividad;
    private IListaEnlazadaDoble<Curso> cursos;
    private Cola<Actividad> colaActividades;

    public ControlActividades() {
        colaActividades = new Cola<>();
        cursos = new ListaEnlazadaDoble<>();
    }

    //actividades by data logica
    public void cargarActividades() {
        for (Curso o : cursos) {
            colaActividades.push(new Actividad(o.getIdCurso(), o, "TRAER INFORME TERMINADO"));
        }
    }

    public void eliminarActividad() {
        colaActividades.pop();
        
    }

    public void obtenerCursosAlumno(int idAlumno) {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_ObtenerCursosAlumno(?)";
            CallableStatement cs = db.prepareCall(sp);
            ResultSet rs;
            cs.setInt(1, idAlumno);
            rs = cs.executeQuery();
            while (rs.next()) {
                cursos.agregar(new Curso() {
                    {
                        setIdCurso(rs.getInt("idCurso"));
                        setNombreCurso(rs.getString("nombreCurso"));
                        setNombreProfesor(rs.getString("nombreProfesor"));
                    }
                });
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public IListaEnlazadaDoble<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(IListaEnlazadaDoble<Curso> cursos) {
        this.cursos = cursos;
    }

    public Cola<Actividad> getColaActividades() {
        return colaActividades;
    }

    public void setColaActividades(Cola<Actividad> colaActividades) {
        this.colaActividades = colaActividades;
    }

}
