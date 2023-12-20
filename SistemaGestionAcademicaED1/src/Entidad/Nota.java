package Entidad;

/**
 *
 * @author aless
 */
public class Nota {

    private int id;
    private double pc1;
    private double pc2;
    private double pc3;
    private double exa1;
    private double exa2;
    private double exa3;

    public Nota() {
    }

    public Nota(int id, double pc1, double pc2, double pc3, double exa1, double exa2, double exa3) {
        this.id = id;
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.pc3 = pc3;
        this.exa1 = exa1;
        this.exa2 = exa2;
        this.exa3 = exa3;
    }
    
    public double calcularPromedioPracticas(){
        return (pc1+pc2+pc3)/3;
    }
    
    public double calcularPromedioExamenes(){
        return (pc1+pc2+pc3)/3;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPc1() {
        return pc1;
    }

    public void setPc1(double pc1) {
        this.pc1 = pc1;
    }

    public double getPc2() {
        return pc2;
    }

    public void setPc2(double pc2) {
        this.pc2 = pc2;
    }

    public double getPc3() {
        return pc3;
    }

    public void setPc3(double pc3) {
        this.pc3 = pc3;
    }

    public double getExa1() {
        return exa1;
    }

    public void setExa1(double exa1) {
        this.exa1 = exa1;
    }

    public double getExa2() {
        return exa2;
    }

    public void setExa2(double exa2) {
        this.exa2 = exa2;
    }

    public double getExa3() {
        return exa3;
    }

    public void setExa3(double exa3) {
        this.exa3 = exa3;
    }
    
    
}
