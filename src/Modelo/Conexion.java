package Modelo;
import static java.lang.Class.forName;
import static java.lang.System.err;
import java.sql.*;
public class Conexion {
    private Connection conn;
    private String url ="jdbc:mysql://127.0.0.1:3306/Inventario";
    private String user ="root";
    private String pass="root";
    
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public Connection getConnection(){
        return conn;
    }
}
