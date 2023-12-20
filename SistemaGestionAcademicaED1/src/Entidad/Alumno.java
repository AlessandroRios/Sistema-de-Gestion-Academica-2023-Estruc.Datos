package Entidad;

/**
 *
 * @author aless
 */
public class Alumno extends Persona {

    private int idAlumno;
    private int grado;
    private String nivel;
    private int idUsuario;
    
    public Alumno() {
    }

    public Alumno(int idAlumno, int grado, String nivel, int idUsuario) {
        this.idAlumno = idAlumno;
        this.grado = grado;
        this.nivel = nivel;
        this.idUsuario = idUsuario;
    }

    public Alumno(int idAlumno, int grado, String nivel,int idUsuario, String nombre, String apellido, String direccion, String documentoIdentidad, String telefono, char genero) {
        super(nombre, apellido, direccion, documentoIdentidad, telefono, genero);
        this.idAlumno = idAlumno;
        this.grado = grado;
        this.nivel = nivel;
        this.idUsuario = idUsuario;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", grado=" + grado + ", nivel=" + nivel + ", idUsuario=" + idUsuario + '}';
    }
    

    
    

}
