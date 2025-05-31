package org.angelo.webappcookie.uti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBaseDatos {
    //Declaramos e inicializamos las variables de conexion
    private  static  String url= "jdbc:mysql://localhost:3306/compraventa?serverTimezone=UTC";
    private  static  String username = "root";
    private  static  String password = "";

    //Implementamos un metodo de tipo Connetion
   public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(url, username, password);
   }

}
