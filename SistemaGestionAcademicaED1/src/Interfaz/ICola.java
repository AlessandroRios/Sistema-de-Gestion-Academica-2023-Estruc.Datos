package Interfaz;

/**
 *
 * @author aless
 */
public interface ICola<T> extends Iterable<T>{
    void push(T dato);
    void pop();
    boolean estaVacia();
    void limpiar();
}
