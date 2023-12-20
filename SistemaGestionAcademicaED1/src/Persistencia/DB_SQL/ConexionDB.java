package Persistencia.DB_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @a uthor aless
 */
public class ConexionDB {
    Connection conn;

    public Connection conectarDB() {
        String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=DB_SisGesAcademica;user=piero;password=root;encrypt=false;trustServerCertificate=true;";

        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbUrl);
            System.out.println("conexion exitosa");
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
//        catch (ClassNotFoundException ex) {
//            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return conn;
    }
    
}
