package ObDTO;

import Entidad.Curso;
import Entidad.Nota;

/**
 *
 * @author aless
 */
public class Notas_Cursos_Alumno {
    private Nota nota;
    private Curso curso;

    public Notas_Cursos_Alumno() {
    }

    public Notas_Cursos_Alumno(Nota nota, Curso curso) {
        this.nota = nota;
        this.curso = curso;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
