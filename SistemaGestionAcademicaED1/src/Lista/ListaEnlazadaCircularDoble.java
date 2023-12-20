package Lista;

import Interfaz.IListaEnlazadaDoble;
import java.util.Iterator;
import Interfaz.IListaEnlazadaSimple;
import java.util.function.Consumer;

/**
 *
 * @author aless
 * @param <T>
 */
public class ListaEnlazadaCircularDoble<T> implements IListaEnlazadaDoble<Object> {

    private NodoDoble inicio, fin;

    public ListaEnlazadaCircularDoble() {
        inicio = fin = null;
    }

    @Override
    public void agregar(Object obj) {
        NodoDoble nuevo = new NodoDoble(obj);
        if (estaVacia()) {
            inicio = fin = nuevo;
            inicio.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
        } else {
            nuevo.setAnterior(fin);
            nuevo.setSiguiente(inicio);
            fin.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public void eliminar(Object obj) {
        if (estaVacia()) {
            System.out.println("Lista Vacia");
            return;
        }

        if (inicio.getDato().equals(obj)) {
            eliminarInicio();
            return;
        }

        if (fin.getDato().equals(obj)) {
            eliminarFinal();
            return;
        }

        NodoDoble aux = inicio.getSiguiente();
        while (aux != inicio) {
            if (aux.getDato().equals(obj)) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                return;
            }
            aux = aux.getSiguiente();
        }
    }

    public void eliminarInicio() {
        if (estaVacia()) {
            System.out.println("Lista Vacia");
        } else {
            inicio.getSiguiente().setAnterior(fin);
            fin.setSiguiente(inicio.getSiguiente());
            inicio = inicio.getSiguiente();
        }
    }

    public void eliminarFinal() {
        if (estaVacia()) {
            System.out.println("Lista Vacia");
        } else {
            fin.getAnterior().setSiguiente(inicio);
            inicio.setAnterior(fin.getAnterior());
            fin = fin.getAnterior();
        }
    }

    @Override
    public void mostrar() {
        NodoDoble aux = inicio;
        do {
            System.out.println(aux.toString());
            aux = aux.getSiguiente();
        } while (aux != inicio && aux != null);
    }

    @Override
    public void actualizar(Object objAntiguo, Object objNuevo) {
        NodoDoble aux = inicio;
        do {
            if (aux.getDato().equals(objAntiguo)) {
                aux.setDato(objNuevo);
            }
            aux = aux.getSiguiente();
        } while (aux != inicio);
    }

    @Override
    public Object buscar(Object obj) {
        NodoDoble aux = inicio;
        do {
            if (aux.getDato().equals(obj)) {
                return aux;
            }
            aux = aux.getSiguiente();
        } while (aux != inicio);
        return null;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private NodoDoble actual = inicio;
            private boolean primeroVisitado = false;

            @Override
            public boolean hasNext() {
                return inicio != null && (!primeroVisitado || actual != inicio);
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new Error("No hay más elementos en la lista");
                }
                Object dato = inicio.getDato();
                inicio = inicio.getSiguiente();

                // Marcar el primer elemento como visitado después de la primera iteración
                primeroVisitado = true;
                return dato;
            }
        };
    }

    @Override
    public Iterator<Object> iterarReversa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
