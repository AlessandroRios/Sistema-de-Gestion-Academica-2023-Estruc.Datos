package Cola;

import Interfaz.ICola;
import Lista.NodoSimple;
import java.util.Iterator;

/**
 *
 * @author aless
 */
public class Cola<T> implements ICola<T> {

    private NodoSimple inicio, fin;

    public Cola() {
        inicio = fin = null;
    }

    @Override
    public void push(T dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public void pop() {
        if (estaVacia()) {
            System.out.println("Cola vacía");
        } else {
            if (inicio == fin) {
                // Si solo hay un elemento, la cola quedará vacía
                inicio = fin = null;
            } else {
                // Mueve el puntero inicio al siguiente nodo
                inicio = inicio.getSiguiente();
            }
        }

    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public void limpiar() {
        while (!estaVacia()) {
            pop();
            //fin=null;
        }
    }

    public NodoSimple getInicio() {
        return inicio;
    }

    public void setInicio(NodoSimple inicio) {
        this.inicio = inicio;
    }

    public NodoSimple getFin() {
        return fin;
    }

    public void setFin(NodoSimple fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Cola{" + "inicio=" + inicio + ", fin=" + fin + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return inicio != null;
            }

            @Override
            public T next() {
                var aux = inicio.getDato();
                inicio = inicio.getSiguiente();
                return (T) aux;
            }
        };
    }

}
