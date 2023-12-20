package Entidad;
/**
 *
 * @author aless
 */
public class Aula {
    private int id;
    private String numeroAula;
    private String grado;
    private String nivel;
    private String seccion;
    private int capacidadAlumnos;

    public Aula() {
    }

    public Aula(int id, String numeroAula, String grado, String nivel, String seccion, int capacidadAlumnos) {
        this.id = id;
        this.numeroAula = numeroAula;
        this.grado = grado;
        this.nivel = nivel;
        this.seccion = seccion;
        this.capacidadAlumnos = capacidadAlumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = numeroAula;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public void setCapacidadAlumnos(int capacidadAlumnos) {
        this.capacidadAlumnos = capacidadAlumnos;
    }

    @Override
    public String toString() {
        return "Aula{" + "id=" + id + ", numeroAula=" + numeroAula + ", grado=" + grado + ", nivel=" + nivel + ", seccion=" + seccion + ", capacidadAlumnos=" + capacidadAlumnos + '}';
    }
    
    
    
    
}
