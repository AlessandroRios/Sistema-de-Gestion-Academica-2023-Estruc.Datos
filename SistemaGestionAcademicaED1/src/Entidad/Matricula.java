package Entidad;

import java.util.Date;

/**
 *
 * @author aless
 */
public class Matricula {
    private int id;
    private int idAlumno;
    private Date fechaMatricula;
    private double costo;
    private String metodoPago;
    private String periodoAcademico;
    private String estadoMatricula;
    private double descuento;

    public Matricula() {
    }

    public Matricula(int id, int idAlumno, Date fechaMatricula, double costo, String metodoPago, String periodoAcademico, String estadoMatricula, double descuento) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.fechaMatricula = fechaMatricula;
        this.costo = costo;
        this.metodoPago = metodoPago;
        this.periodoAcademico = periodoAcademico;
        this.estadoMatricula = estadoMatricula;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    
    
    
    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
