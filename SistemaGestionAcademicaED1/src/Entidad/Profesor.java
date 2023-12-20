package Entidad;

/**
 *
 * @author aless
 */
public class Profesor extends Persona{
    private int idProfesor;
    private int idUsuario;
    
    public Profesor() {
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Profesor{" + "idProfesor=" + idProfesor + ", idUsuario=" + idUsuario + '}';
    }
    
    
    
}
