package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Juego {

    private List<String> lista;
    private final Random aleatorio = new Random();
    private boolean ocupado = Boolean.FALSE;


    public Juego() {
        this.lista = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
    }

    public List<String> getLista() {
        return lista;
    }

    /**
     * Metodo que ejecuta el turno sincronizado de cada jugador (hilo) y setea su resultado
     */

    public synchronized void jugar(Jugador jugador)   // sincronized esta indicando que en ese método tenemos una sección crítica del código y por lo tanto los hilos que accedan a dicho método deberán hacerlo de forma síncrona
    {
        while(jugador.getJugando()) {   // si esta jugando entra aca y se pone en wait, si no sigue
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        jugador.setJugando(true);

        String letra = getLetra();

        jugador.setPalabraToArmar(comprobarPalabra(jugador,letra));

        System.out.println("-------------------------------");
        System.out.println("Jugador: "+jugador.getNombre()+ "\nLetra obtenida: " + letra + "\nPalabra: " + jugador.getPalabraToArmar() + "\nPuntos: " + jugador.getPuntos());
        System.out.println("-------------------------------");


        if (jugador.getPalabraToArmar().toString().equals(jugador.getPalabra())){
            System.out.println("GANADOR " +jugador.getNombre()+" PALABRA: " +jugador.getPalabra());
            Jugador.setEjcutando(false);


        } else if(jugador.getPuntos()==0 ) {
            System.out.println(jugador.getNombre()+" AHORCADO");
        }

        jugador.setJugando(false);
        notify();
    }

    /**
     * Metodo para obtener una letra random de la lista con el abcdario
     * @return
     */
    public synchronized String getLetra()
    {
        while(ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ocupado = true;

        String letra = (!lista.isEmpty()) ? this.lista.remove(aleatorio.nextInt(this.lista.size())) : "";

        ocupado = false;
        notify();
        return letra;
    }

    /**
     * comienza a armar la palabra si encontro una letra o resta puntos en caso contrario
     * @param jugador
     * @param letra
     * @return
     */

    public StringBuilder comprobarPalabra(Jugador jugador, String letra){

        int pos = jugador.getPalabra().indexOf(letra);

        if (pos<0)
            jugador.setPuntos(jugador.getPuntos()-1);

        else{
            while (pos >= 0) {

                jugador.setPalabraToArmar(jugador.getPalabraToArmar().replace(pos,pos+1, letra));

                pos = jugador.getPalabra().indexOf(letra, pos + 1);

            }
        }
        return jugador.getPalabraToArmar();

    }

    /**
     * Cambia cada letra de la palabra por "-"
     * @param palabra
     * @param lenght
     * @return
     */
    public StringBuilder codificarPalabra(StringBuilder palabra, int lenght){

        for (int i=0 ; i<lenght ;i++){

             palabra.replace(i, i+1 ,"-");
        }

        return palabra;
    }



}
