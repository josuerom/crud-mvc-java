import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion  {
    private static Connection con = null;

    public Conexion() {}

    public Connection conectarDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/test", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void desconectarDB() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}