package App.Models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ConexionToDB{

    /*Variables de configuracion*/
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/Contoso";
    private static String USER = "root";
    private static String PASS = "gf222473";

    /*Declaro los elementos que pueden heredad las clases DAO Data Access Object*/

    protected Connection conn; //Conexion a base de datos
    protected Statement st; //Para generar el uso de una consola sql //Consultas no preparas // Texto Plano
    protected PreparedStatement pst; //Para generar el uso de una consola con consultas preparas //Evitar vulnerabilidad SQL- INJECCTION
    protected ResultSet rs; //Guardar resultados provenientes

    public ConexionToDB(){
        try{
            Class.forName(JDBC_DRIVER);
        }catch (Exception e){
            Logger.getLogger(ConexionToDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void conectar() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
    }

    public void desconectar() throws SQLException {
        if(conn != null){
            conn.close();
            conn = null;
        }
    }


}
