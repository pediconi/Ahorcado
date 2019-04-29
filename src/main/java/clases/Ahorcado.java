package clases;

import dao.JugadorDAO;
import util.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ahorcado {

    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private List<Thread> hilos = new ArrayList<Thread>();
    private int cantJugadores;
    private String palabra;
    private Scanner sc = new Scanner(System.in);
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private static Contenedor contenedor;


    public Ahorcado(int cantJugadores) {

        contenedor = new Contenedor();
        this.cantJugadores = cantJugadores;
        this.palabra = getPalabraAleatoriaFromDB();
    }

    /**
     * metodo para cargar los jugadores y crear los hilos
     */
    public void cargarJugadores(){

        for(int i = 0; i < cantJugadores; i++) {

            System.out.println("Nombre jugador "+i+":");

            jugadores.add(new Jugador(contenedor, palabra, sc.nextLine()));
            hilos.add( new Thread(jugadores.get(i)));
        }
    }

    /**
     * metodo para comenzar el juego y guardar al ganador en la bd
     */
    public void comenzar(){

        System.out.println("PALABRA: "+palabra);

        for(Thread h : hilos) {
            h.start(); // llamo al metodo run de jugador
        }

        for(Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        guardarGanadorToDB();
    }

    // CONSIDERO QUE SOLO GANA QUIEN COMPLETO LA PALABRA, SI ESTO NO SE CUMPLE NO HAY GANADOR
    /**
     * Guardo al ganador si lo hubo en la base de datos
     */
    private void guardarGanadorToDB(){

        for (Jugador j : jugadores){
            if (j.getPalabra().equals("")){

                jugadorDAO.create(j);
            }
        }
    }

    /**
     * metodo para obtener una palabra aleatoria de la bd
     * @return
     */
    private String getPalabraAleatoriaFromDB(){

        Connection connection = DBUtil.getConnection(); // aca quizas podria usar Singleton para obtener la instancia de la clase Connection si la hay y asi no volver a crear una nueva.
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

        return palabra;
    }

}
