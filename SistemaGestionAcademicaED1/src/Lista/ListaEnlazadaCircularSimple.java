package Lista;

import java.util.Iterator;
import Interfaz.IListaEnlazadaSimple;

/**
 *
 * @author aless
 * @param <T>
 */
public class ListaEnlazadaCircularSimple<T> implements IListaEnlazadaSimple<Object> {

    private NodoSimple inicio, fin;

    
    public ListaEnlazadaCircularSimple() {
        inicio = fin = null;
    }

    @Override
    public void agregar(Object obj) {
        NodoSimple nuevo = new NodoSimple(obj);
        if (estaVacia()) {
            inicio = fin = nuevo;
            inicio.setSiguiente(nuevo);
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
            fin.setSiguiente(inicio);
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

        NodoSimple aux = inicio;
        do {
            if (aux.getSiguiente().getDato().equals(obj)) {
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                return;
            }
            aux = aux.getSiguiente();
        } while (aux != inicio);

    }

    public void eliminarInicio() {
        if (estaVacia()) {
            System.out.println("Lista Vacia");
        } else {
            NodoSimple aux = inicio.getSiguiente();
            inicio.setSiguiente(null);
            inicio = aux;
            fin.setSiguiente(inicio);
        }
    }

    public void eliminarFinal() {
        if (estaVacia()) {
            System.out.println("Lista Vacia");
        } else {
            NodoSimple aux = inicio;
            do {
                if (aux.getSiguiente().equals(fin)) {
                    fin.setSiguiente(null);
                    fin = aux;
                    fin.setSiguiente(inicio);
                }
                aux = aux.getSiguiente();
            } while (aux != inicio);
        }
    }

    @Override
    public void mostrar() {
        NodoSimple aux = inicio;

        do {
            System.out.println(aux.toString());
            aux = aux.getSiguiente();
        } while (aux != inicio && aux != null);
    }

    @Override
    public void actualizar(Object objAntiguo, Object objNuevo) {
        NodoSimple aux = inicio;
        do {
            if (aux.getDato().equals(objAntiguo)) {
                aux.setDato(objNuevo);
            }
            aux = aux.getSiguiente();
        } while (aux != inicio);
    }

    //Busca el dato dentro de los nodos y devuelve el nodo
    @Override
    public Object buscar(Object obj) {
        NodoSimple aux = inicio;
        NodoSimple encontrado = null;
        while (aux != null) {
            if (aux.getDato().equals(obj)) {
                encontrado = aux;
            }
            aux = aux.getSiguiente();
        }
        return encontrado;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
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
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private NodoSimple actual = inicio;
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

}
