import clases.Ahorcado;

public class App {

    public static void main (String[] args){

        Ahorcado ahorcado = new Ahorcado(2);
        ahorcado.cargarJugadores();
        ahorcado.comenzar();
    }
}
