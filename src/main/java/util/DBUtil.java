package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String url ="jdbc:mysql://localhost:3306/ahorcado?useTimezone=true&serverTimezone=UTC"; // luego del 3306 (puerto) va el nombre de la base de datos
    private static final String user="root";
    private static final String password ="";
    private static Connection connection = null;

    public static Connection getConnection(){

        try {
            connection = DriverManager.getConnection( url, user, password);
            System.out.println("CONEXION ESTABLECIDA");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR AL CONECTARSE");
        }

        return connection;
    }
}
