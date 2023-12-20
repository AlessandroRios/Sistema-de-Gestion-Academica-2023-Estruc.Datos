package Lista;

import java.util.Iterator;
import Interfaz.IListaEnlazadaSimple;

/**
 *
 * @author aless
 * @param <T>
 */
public class ListaEnlazadaSimple<T> implements IListaEnlazadaSimple<Object> {

    private NodoSimple inicio;
    private NodoSimple fin;

    public ListaEnlazadaSimple() {
        inicio = fin = null;
    }

    //agregar al final
    @Override
    public void agregar(Object obj) {
        NodoSimple nuevo = new NodoSimple(obj);
        if (estaVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public void agregarAlInicio(Object obj) {
        NodoSimple nuevo = new NodoSimple(obj);
        nuevo.setSiguiente(this.inicio);
        inicio = nuevo;
    }

    @Override
    public void eliminar(Object obj) {
        NodoSimple aux = inicio;
        NodoSimple eliminado = new NodoSimple();
        eliminado.setDato(obj);

        if (eliminado.getDato().equals(inicio.getDato())) {
            eliminarInicio();
            System.out.println("Se elimino al principio de la lista");
        } else {
            while (aux != null && aux.getSiguiente() != null) {
                if (aux.getSiguiente().getDato().equals(eliminado.getDato())) {
                    // Elimina el nodo siguiente al nodo actual
                    aux.setSiguiente(aux.getSiguiente().getSiguiente());
                }
                aux = aux.getSiguiente();
            }
        }
    }

    //eliminar al inicio
    public void eliminarInicio() {

        if (estaVacia()) {
            System.out.println("Lista Vacia");
        } else {
            NodoSimple aux;
            aux = inicio.getSiguiente();
            inicio.setSiguiente(null);
            inicio = aux;
        }
    }

    @Override
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("lista vacia");
        } else {
            NodoSimple aux = inicio;
            while (aux != null) {
                System.out.println(aux.toString());
                aux = aux.getSiguiente();
            }
        }
    }

    @Override
    public void actualizar(Object objViejo, Object objNuevo) {
        NodoSimple aux = (NodoSimple) buscar(objViejo);

        if (aux != null) {
            aux.setDato(objNuevo);
        } else {
            System.out.println("El dato no existe");
        }
    }

    //Busca dentro de los nodos
    @Override
    public Object buscar(Object obj) {
        NodoSimple aux = inicio;
        //Va tomar el nodo el cual tenga dentro el dato correspondiente
        NodoSimple encontrado = null;

        while (aux != null) {
            if (aux.getDato().equals(obj)) {
                encontrado = aux;
            }
            aux = aux.getSiguiente();
        }
        return encontrado;
    }

    public String mensajeByBuscar(Object obj) {
        NodoSimple encontrado = (NodoSimple) buscar(obj);
        String mensaje;

        if (encontrado != null) {
            mensaje = "Se elimino:" + encontrado.getDato().toString();
        } else {
            mensaje = "No existe en la lista";
        }

        return mensaje;
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

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No hay más elementos en la lista");
                }
                Object dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }

}
