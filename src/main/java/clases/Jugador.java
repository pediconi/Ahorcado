package clases;


public class Jugador implements Runnable
    {
        private static boolean ejcutando = true;

        private Juego juego;

        private int puntos = 5;
        private String palabra;
        private StringBuilder palabraToArmar;
        private String nombre;
        private Boolean jugando = Boolean.FALSE;

        /**
         * Constructor de la clase
         * @param juego Juego común a los consumidores y el productor

         */
        public Jugador(Juego juego, String palabra, String nombre)
        {
            this.juego = juego;
            this.palabra = palabra;
            this.nombre = nombre;
            this.palabraToArmar = juego.codificarPalabra(new StringBuilder(palabra), palabra.length());
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

        public Boolean getJugando() {
            return jugando;
        }

        public static void setEjcutando(boolean ejcutando) {
            Jugador.ejcutando = ejcutando;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setJugando(Boolean jugando) {
            this.jugando = jugando;
        }

        public StringBuilder getPalabraToArmar() {
            return palabraToArmar;
        }

        public void setPalabraToArmar(StringBuilder palabraToArmar) {
            this.palabraToArmar = palabraToArmar;
        }

        @Override
        /**
         * Implementación del run
         */
        public void run()
        {

            while(this.getPuntos()>0 && ejcutando && !juego.getLista().isEmpty())
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                juego.jugar(this);
            }

            if(juego.getLista().isEmpty()) {
                System.out.println("ABCEDARIO TERMINADO");
            }
        }

    }
