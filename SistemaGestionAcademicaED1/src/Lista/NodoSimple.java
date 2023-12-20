package Lista;

/**
 *
 * @author aless
 */
public class NodoSimple {
    Object dato;
    NodoSimple siguiente;

    public NodoSimple(Object o) {
        this.dato=o;
        siguiente=null;
    }

    public NodoSimple() {
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoSimple{" + "dato=" + dato + ", siguiente=" + 
                ((siguiente != null) ? siguiente.getDato() : "null")+'}';
    }
    
    
}
