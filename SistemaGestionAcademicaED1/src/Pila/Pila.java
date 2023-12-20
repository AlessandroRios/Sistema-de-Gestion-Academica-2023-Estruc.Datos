package Pila;

import Interfaz.IPila;
import Lista.NodoSimple;

/**
 *
 * @author aless
 */
public class Pila implements IPila<Object>{

    private NodoSimple cima;

    public Pila() {
        cima=null;
    }
    
    @Override
    public void push(Object dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        nuevo.setSiguiente(cima);
        cima=nuevo;
    }

    @Override
    public void pop() {
        if(estaVacia())
            System.out.println("Pila Vacia");
        else{
            cima=cima.getSiguiente();
        }
    }

    @Override
    public boolean estaVacia() {
        return cima==null;
    }

    @Override
    public void limpiar() {
        while(!estaVacia()){
            pop();
        }
    }

    public NodoSimple getCima() {
        return cima;
    }

    public void setCima(NodoSimple cima) {
        this.cima = cima;
    }

    @Override
    public String toString() {
        return "Pila{" + "cima=" + cima + '}';
    }

    
}
