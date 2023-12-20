package Lista;


import Interfaz.IListaEnlazadaDoble;
import java.util.Iterator;

/**
 *
 * @author aless
 * @param <Object>
 */
public class ListaEnlazadaDoble<Object> implements IListaEnlazadaDoble<Object> {

    private NodoDoble inicio;
    private NodoDoble fin;

    public ListaEnlazadaDoble() {
        inicio = fin = null;
    }

    //agregar al final
    @Override
    public void agregar(Object obj) {
        NodoDoble nuevo = new NodoDoble();
        nuevo.setDato(obj);

        if (estaVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    //eliminar en cualquier parte
    @Override
    public void eliminar(Object obj) {
        NodoDoble aux = new NodoDoble(obj);
        NodoDoble eliminado = (NodoDoble) buscar((Object) aux);

        if (!estaVacia() && eliminado != null) {
            //Si el objeto a eliminar es el primero 
            if (eliminado.getDato().equals(inicio.getDato())) {
                eliminarInicio();
                return;
            }
            //Si el dato a eliminar es el primero
            if (eliminado.getDato().equals(fin.getDato())) {
                eliminarFinal();
                return;
            }

            /*
            Tomamos el nodo anterior del nodo que queremos eliminar y le seteamos
            el siguiente nodo al cual apunta el nodo que queremos eliminar
             */
            eliminado.getAnterior().setSiguiente(eliminado.getSiguiente());
            /*
            hacemos que su siguiente nodo apunte a null y asi quede liberado
             */
            eliminado.setSiguiente(null);
            /*
            hacemos que su anterior nodo apunte a null y asi quede liberado
             */
            eliminado.setAnterior(null);
            /*
            Se veria asi
            null<-NODO_ELIMNADO->null
             */
        }
    }

    public void eliminarInicio() {
        NodoDoble aux = inicio.getSiguiente();
        inicio.setSiguiente(null);
        inicio = aux;
        inicio.setAnterior(null);
    }

    public void eliminarFinal() {
        NodoDoble aux = fin.getAnterior();
        fin.setAnterior(null);
        fin = aux;
        fin.setSiguiente(null);
    }

    //mostrar de principio a fin
    @Override
    public void mostrar() {
        NodoDoble aux = inicio;
        if (estaVacia()) {
            System.out.println("Lista Vacia");
        }
        while (aux != null) {
            System.out.println(aux.toString());
            aux = aux.getSiguiente();
        }
    }

    @Override
    public void actualizar(Object objAntiguo, Object objNuevo) {
        NodoDoble aux = (NodoDoble) buscar(objAntiguo);

        if (aux != null) {
            aux.setDato(objNuevo);
        } else {
            System.out.println("El dato no existe");
        }
    }

    @Override
    public Object buscar(Object obj) {
        NodoDoble aux = inicio;
        NodoDoble encontrado = null;

        while (aux != null) {
            if (aux.getDato().equals(obj)) {
                encontrado = aux;
            }
            aux = aux.getSiguiente();
        }

        return (Object) encontrado;
    }

    public String mensajeByBuscar(Object obj) {
        NodoDoble encontrado = (NodoDoble) buscar(obj);
        String mensaje;

        if (encontrado != null) {
            mensaje = "Se encontro:" + encontrado.toString();
        } else {
            mensaje = "No existe en la lista";
        }

        return mensaje;
    }

    @Override
    public boolean estaVacia() {
        return inicio == null;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            @Override
            public boolean hasNext() {
                return inicio!=null;
            }

            @Override
            public Object next() {
                Object aux = (Object) inicio.getDato();
                inicio=inicio.getSiguiente();
                return aux;
            }
        };
    }

    @Override
    public Iterator<Object> iterarReversa() {
        return new Iterator<Object>() {
            private NodoDoble actual=fin;
            @Override
            public boolean hasNext() {
                return inicio!=null;
            }

            @Override
            public Object next() {
                Object aux = (Object) actual.getDato();
                actual=actual.getAnterior();
                return aux;
            }
        };
    }

}
