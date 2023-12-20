package Interfaz;

/**
 *
 * @author aless
 * @param <T> es el objeto con el que trabajara, en este caso puede ser la entidad requerida
 */
public interface IListaEnlazadaSimple<T> extends Iterable<T>{
    void agregar(T obj);
    void eliminar(T obj);
    void mostrar();
    void actualizar(T objAntiguo ,T objNuevo);
    T buscar(T obj);
    boolean estaVacia();
}
