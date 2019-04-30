package dao;

import clases.Jugador;
import interfaces.Crud;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class JugadorDAO implements Crud {


    public void create(Object object) {

        Jugador jugador = (Jugador) object;

        String SQL = "INSERT INTO ganadores (id_ganador, nombre ,fecha, palabra )VALUES(?,?,?,?)";
        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1,0 );
            statement.setString(2, jugador.getNombre());
            statement.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            statement.setString(4, jugador.getPalabra());

            int executeUpdate = statement.executeUpdate();

            if (executeUpdate == 1 )
                System.out.println("Cargado en la BD");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Object readByID(int id) {

        Jugador jugador = null;

        String SQL = "SELECT * FROM ganadores WHERE id_ganador =?)";
        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String nombre = rs.getString("nombre");
                String palabra = rs.getString("palabra");

                //jugador = new Jugador(aca pasar los campos q voy leyendo q son atributos de esta clase)
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugador;
    }

    public void update(int id, Object object) {

    }

    public void delete(int id) {

    }
}
