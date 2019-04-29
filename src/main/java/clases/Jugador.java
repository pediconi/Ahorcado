package clases;


public class Jugador implements Runnable
    {
        private static boolean enJuego = true;

        private final Contenedor contenedor;
        private final String palabraCompleta;

        private int puntos = 15;
        private String palabra;
        private String nombre;
        private Boolean jugando = Boolean.FALSE;

        /**
         * Constructor de la clase
         * @param contenedor Contenedor común a los consumidores y el productor

         */
        public Jugador(Contenedor contenedor, String palabra, String nombre)
        {
            this.contenedor = contenedor;
            this.palabra = palabra;
            this.nombre = nombre;
            this.palabraCompleta = palabra;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntos() {
            return puntos;
        }

        public String getPalabra() {
            return palabra;
        }

        public String getPalabraCompleta() {
            return palabraCompleta;
        }

        @Override
        /**
         * Implementación de la hebra
         */
        public void run()
        {

            while(enJuego && !contenedor.getLista().isEmpty())
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.jugar();
            }

            if(contenedor.getLista().isEmpty()) {
                System.out.println("ABCEDARIO TERMINADO");
            }
        }

        private synchronized void jugar()   // sincronized esta dicando que en ese método tenemos una sección crítica del código y por lo tanto los hilos que accedan a dicho método deberán hacerlo de forma síncrona
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String letra ="";

            while(jugando) {   // si esta jugando entra aca y se pone en wait, si no sigue
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.jugando = true;  //pone jugando = true

            letra = contenedor.getLetra();

            if (this.palabra.contains(letra)){
                this.palabra = this.palabra.replace(letra,"");

            }else{
                this.puntos--;
            }

            if (this.palabra.equals("")){  // osea si GANO

                System.out.println("GANADOR " +this.getNombre()+" PALABRA: " +palabraCompleta);
                enJuego = false;

            } else if(this.puntos == 0 ) {

                System.out.println(this.nombre+" AHORCADO");
                Thread.currentThread().interrupt();
            }

            System.out.println("JUGADOR "+ this.nombre + " Letra: " + letra + " -----> " + this.palabra);

            this.jugando = false;
            notify();
        }

    }
