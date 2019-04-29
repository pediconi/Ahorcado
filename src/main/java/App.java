import clases.Ahorcado;
import util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class App {

    public static void main (String[] args){

        Connection connection = DBUtil.getConnection();

        String palabra = "";

        try {
            Statement myStatement = connection.createStatement();  // esto es para la query
            ResultSet rs = myStatement.executeQuery("SELECT palabra FROM palabras ORDER BY rand() limit 1 ");  // la sentencia que quiero ejecutar

            while (rs.next()){
                palabra = rs.getString("palabra").toUpperCase();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("PALABRA: " +palabra);
        Ahorcado ahorcado = new Ahorcado(3, palabra);

        ahorcado.cargarJugadores();
        ahorcado.comenzar();

    }
}
