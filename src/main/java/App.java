import clases.Ahorcado;
import clases.Jugador;

public class App {

    public static void main (String[] args){

        Ahorcado ahorcado = new Ahorcado(3);
        ahorcado.cargarJugadores();
        Jugador ganador = ahorcado.comenzar();

        String msj = (ganador!=null) ? "Ganador: "+ganador.getNombre()+ "\nPalabra "+ganador.getPalabraCompleta() : "No hay ganador";
        System.out.println(msj);

    }
}
