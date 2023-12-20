package Entidad;

/**
 *
 * @author aless
 */
public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int idProfesor;
    private String nombreProfesor;

    public Curso() {
    }

    public Curso(int id, String nombreCurso) {
        this.idCurso = id;
        this.nombreCurso = nombreCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    
    @Override
    public String toString() {
        return "Curso{" + "idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", idProfesor=" + idProfesor + ", nombreProfesor=" + nombreProfesor + '}';
    }

    

    
}
