package ObDTO;

import Entidad.Alumno;
import Entidad.Aula;
import Entidad.Matricula;

/**
 *
 * @author aless
 */
public class InformacionAlumno {
    private Matricula matricula;
    private Alumno alumno;
    private Aula aula;

    public InformacionAlumno() {
    }

    
    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
    
    
    
    
}
