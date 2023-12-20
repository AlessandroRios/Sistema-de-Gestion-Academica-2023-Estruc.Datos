package Interfaz;

/**
 *
 * @author aless
 * @param <T>
 */
public interface IPila<T> {
    void push(T dato);
    void pop();//sacar el ultimo ingresado
    boolean estaVacia();
    void limpiar();//borrar todos los datos
}
