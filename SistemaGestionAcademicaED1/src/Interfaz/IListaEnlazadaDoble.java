package Interfaz;

import java.util.Iterator;

/**
 *
 * @author aless
 * @param <T>
 */
public interface IListaEnlazadaDoble<T> extends Iterable<T> {

    void agregar(T obj);

    void eliminar(T obj);

    void mostrar();

    void actualizar(T objAntiguo, T objNuevo);

    T buscar(T obj);

    boolean estaVacia();

    Iterator<T> iterarReversa();
}
