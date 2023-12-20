package Lista;

/**
 *
 * @author aless
 */
public class NodoDoble {
    private Object dato;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(Object dato) {
        this.dato=dato;
        siguiente=null;
        anterior=null;
    }

    public NodoDoble() {
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    @Override
    public String toString() {
        return "NodoDoble{" + "dato=" + dato + ", siguiente=" + ((siguiente != null) ? siguiente.getDato() : "null") 
                + ", anterior=" + ((anterior != null) ? anterior.getDato() : "null") + '}';
    }


    
    
}
