package Controlador;

import Entidad.Usuario;
import Persistencia.DB_SQL.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author aless
 */
public class ControlLogin {

    private Usuario user;
    
    public ControlLogin(String user, String password) {
        this.user = new Usuario();
        this.user.setNombreUsuario(user);
        this.user.setPassword(password);
    }

    public void ValidarLogin() {
        try (Connection db = (Connection) new ConexionDB().conectarDB()) {
            //Creamos la peticion al store procedure
            //El segundo y tercer "?" recibiran los parametros de entrada y el primero sera la salida
            String sp = "SELECT * FROM uf_LogearUsuario(?,?)";
            CallableStatement cs = db.prepareCall(sp);
            ResultSet rs;
            //Enviamos los Parametros al procedimiento almacenado
            cs.setString(1, user.getNombreUsuario());
            cs.setString(2, user.getPassword());
            rs = cs.executeQuery();
            
            while (rs.next()) {
                this.user.setId(rs.getInt("idUsuario"));
                this.user.setNombreUsuario(rs.getString("nombreUsuario"));
                this.user.setPassword(rs.getString("passwordUsuario"));
                this.user.setTipoUsuario(rs.getInt("tipoUsuario"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

}
