
import Entidad.Profesor;
import Entidad.Usuario;
import Lista.ListaEnlazadaCircularSimple;
import Lista.ListaEnlazadaDoble;
import Lista.ListaEnlazadaSimple;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Interfaz.IListaEnlazadaSimple;

/**
 *
 * @author aless
 */
public class pruebaControladorProfesor {

    Profesor profesor;
    IListaEnlazadaSimple<Profesor> listaProfesores;

    public pruebaControladorProfesor() {
        listaProfesores = new ListaEnlazadaCircularSimple();
    }

    public void obtenerDatosProfesor() {
        try (Connection db = new ConexionDB().conectarDB()) {
            String sql = "SELECT * FROM vistaDatosProfesor";
            PreparedStatement statement = db.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                profesor = new Profesor();
                profesor.setIdProfesor(resultSet.getInt(1));
                profesor.setNombre(resultSet.getString(2));
                profesor.setApellido(resultSet.getString(3));
                profesor.setTelefono(resultSet.getString(4));
                profesor.setDireccion(resultSet.getString(5));
                profesor.setDocumentoIdentidad(resultSet.getString(6));
//                profesor.setUsuario(new Usuario() {
//                    {
//                        setId(resultSet.getInt(7));
//                    }
//                });
                listaProfesores.agregar(profesor);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public IListaEnlazadaSimple<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(IListaEnlazadaSimple<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

}
