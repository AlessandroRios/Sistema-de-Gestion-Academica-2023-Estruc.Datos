
import Cola.Cola;
import Controlador.ControlSesionAlumno;
import Entidad.Curso;
import Entidad.Usuario;
import Interfaz.IListaEnlazadaDoble;
import Lista.ListaEnlazadaCircularDoble;
import Lista.ListaEnlazadaCircularSimple;
import Lista.ListaEnlazadaDoble;
import Lista.ListaEnlazadaSimple;
import Lista.NodoSimple;
import Pila.Pila;
import Interfaz.IListaEnlazadaSimple;
import java.sql.Connection;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.*;

public class prueba {

    public static void main(String[] args) {
        Cola cola = new Cola();
        cola.push(1);
        cola.push(2);
        cola.push(3);
        cola.push(5);
        cola.pop();
        for(var o:cola){
            System.out.println(o);
        }

    }
}
