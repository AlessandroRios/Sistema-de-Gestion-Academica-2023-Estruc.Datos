package Entidad;
/**
 *
 * @author aless
 */
public class Actividad {
    private int id;
    private Curso curso;
    private String descripcion;

    public Actividad() {
    }

    
    public Actividad(int id, Curso curso, String descripcion) {
        this.id = id;
        this.curso = curso;
        this.descripcion = descripcion;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
