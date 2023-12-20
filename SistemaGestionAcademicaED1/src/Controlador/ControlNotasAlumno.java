package Controlador;

import Entidad.Alumno;
import Entidad.Curso;
import Entidad.Nota;
import Entidad.Profesor;
import Interfaz.IListaEnlazadaDoble;
import Persistencia.DB_SQL.ConexionDB;
import Interfaz.IListaEnlazadaSimple;
import Lista.ListaEnlazadaCircularSimple;
import Lista.ListaEnlazadaDoble;
import ObDTO.Notas_Cursos_Alumno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aless
 */
public class ControlNotasAlumno {

    private IListaEnlazadaSimple<Nota> notas;
    private Nota nota;
    private Alumno alumno;
    private Profesor profesor;
    private Curso curso;
    private IListaEnlazadaDoble<Curso> cursosAlumno;
    private Notas_Cursos_Alumno nota_curso_alumno;
    private IListaEnlazadaDoble<Notas_Cursos_Alumno> vistaNotaCursos;

    public ControlNotasAlumno() {
        notas = new ListaEnlazadaCircularSimple();
        cursosAlumno = new ListaEnlazadaDoble();
        nota = new Nota();
        curso = new Curso();
        nota_curso_alumno = new Notas_Cursos_Alumno();
        vistaNotaCursos = new ListaEnlazadaDoble();
    }

    public void obtenerCursosAlumno() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_ObtenerCursosAlumno(?)";
            CallableStatement cs = db.prepareCall(sp);
            ResultSet rs;
            cs.setInt(1, alumno.getIdAlumno());
            rs = cs.executeQuery();
            while (rs.next()) {
                curso = new Curso();
                curso.setIdCurso(rs.getInt("idCurso"));
                curso.setNombreCurso(rs.getString("nombreCurso"));
                curso.setNombreProfesor(rs.getString("nombreProfesor"));
                cursosAlumno.agregar(curso);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    //Traer todas las notas que tiene el alumno por el id del curso
    public void obtenerNotasAlumnoById() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            String sp = "SELECT * FROM uf_NotasCursoAlumnoById(?,?)";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, alumno.getIdAlumno());
            cs.setInt(2, curso.getIdCurso());
            ResultSet rs = cs.executeQuery();
            notas = new ListaEnlazadaCircularSimple();
            while (rs.next()) {
                nota.setId(rs.getInt("idNota"));
                nota.setPc1(rs.getDouble("notaPc1"));
                nota.setPc2(rs.getDouble("notaPc2"));
                nota.setPc3(rs.getDouble("notaPc3"));
                nota.setExa1(rs.getDouble("notaExamen1"));
                nota.setExa2(rs.getDouble("notaExamen2"));
                nota.setExa3(rs.getDouble("notaExamen3"));
                notas.agregar(nota);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    //Listar todos las notas del alumno de todos los cursos
    public void obtenerAllNotasCursos() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {

            String sp = "SELECT * FROM uf_NotasCursoAlumno(?)";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, alumno.getIdAlumno());
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                nota_curso_alumno= new Notas_Cursos_Alumno();
                nota_curso_alumno.setNota(new Nota() {
                    {
                        setId(rs.getInt("idNota"));
                        setPc1(rs.getFloat("notaPc1"));
                        setPc2(rs.getFloat("notaPc2"));
                        setPc3(rs.getFloat("notaPc3"));
                        setExa1(rs.getFloat("notaExamen1"));
                        setExa2(rs.getFloat("notaExamen2"));
                        setExa3(rs.getFloat("notaExamen3"));
                    }
                });
                nota_curso_alumno.setCurso(new Curso() {
                    {
                        setNombreCurso(rs.getString("nombreCurso"));
                    }
                });
                vistaNotaCursos.agregar(nota_curso_alumno);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    //borrar
    public void listarNotasAlumno() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            notas = new ListaEnlazadaCircularSimple();
            cursosAlumno = new ListaEnlazadaDoble();
            String sp = "{ call usp_NotasCusosAlumno(?)}";
            CallableStatement cs = db.prepareCall(sp);
            cs.setInt(1, alumno.getIdAlumno());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                nota.setId(rs.getInt("idNota"));
                nota.setPc1(rs.getDouble("notaPc1"));
                nota.setPc2(rs.getDouble("notaPc2"));
                nota.setPc3(rs.getDouble("notaPc3"));
                nota.setExa1(rs.getDouble("notaExamen1"));
                nota.setExa2(rs.getDouble("notaExamen2"));
                nota.setExa3(rs.getDouble("notaExamen3"));
                curso.setNombreCurso(rs.getString("nombreCurso"));
                notas.agregar(nota);
                cursosAlumno.agregar(curso);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public double calcularPromedioPractica() {
        Nota aux= new Nota();
        for(Nota o: notas){
            aux.setPc1(o.getPc1());
            aux.setPc2(o.getPc2());
            aux.setPc3(o.getPc3());
        }
        return aux.calcularPromedioPracticas();
    }

    public double calcularPromedioExamen() {
        Nota aux= new Nota();
        for(Nota o: notas){
            aux.setExa1(o.getExa1());
            aux.setExa2(o.getExa2());
            aux.setExa3(o.getExa3());
        }
        return aux.calcularPromedioExamenes();
    }

    public IListaEnlazadaSimple<Nota> getNotas() {
        return notas;
    }
    
    public void setNotas(IListaEnlazadaSimple<Nota> notas) {
        this.notas = notas;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public IListaEnlazadaDoble<Curso> getCursosAlumno() {
        return cursosAlumno;
    }

    public void setCursosAlumno(IListaEnlazadaDoble<Curso> cursosAlumno) {
        this.cursosAlumno = cursosAlumno;
    }

    public Notas_Cursos_Alumno getNota_curso_alumno() {
        return nota_curso_alumno;
    }

    public void setNota_curso_alumno(Notas_Cursos_Alumno nota_curso_alumno) {
        this.nota_curso_alumno = nota_curso_alumno;
    }

    public IListaEnlazadaDoble<Notas_Cursos_Alumno> getVistaNotaCursos() {
        return vistaNotaCursos;
    }

    public void setVistaNotaCursos(IListaEnlazadaDoble<Notas_Cursos_Alumno> vistaNotaCursos) {
        this.vistaNotaCursos = vistaNotaCursos;
    }

}
