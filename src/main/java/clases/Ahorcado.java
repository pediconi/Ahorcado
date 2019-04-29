package clases;

import dao.JugadorDAO;
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


    public Ahorcado(int cantJugadores, String palabra) {

        contenedor = new Contenedor();
        this.cantJugadores = cantJugadores;
        this.palabra = palabra;
    }


    public void cargarJugadores(){

        for(int i = 0; i < cantJugadores; i++) {

            System.out.println("Nombre jugador "+i+":");

            jugadores.add(new Jugador(contenedor, palabra, sc.nextLine()));
            hilos.add( new Thread(jugadores.get(i)));

        }

    }

    public void comenzar(){

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

        // CONSIDERO QUE SOLO GANA QUIEN COMPLETO LA PALABRA, SI ESTO NO SE CUMPLE NO HAY GANADOR
        for (Jugador j : jugadores){
            if (j.getPalabra().equals("")){
                jugadorDAO.create(j); // guardo al ganador
            }
        }

    }

}
