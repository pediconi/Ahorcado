# TP-Ahorcado
Diferencia entre runnable y thread:

- Cada hilo creado de extends Thread crea un objeto único para él y se asocia con ese objeto. Por otro lado, cada hilo creado al        implementar la interfaz runnable comparte la misma instancia runnable.

-Como cada hilo está asociado con un objeto único cuando se crea al extender de Thread, se requiere más memoria. Por otro lado, cada hilo creado al implementar Runnable comparte el mismo espacio, por lo tanto, requiere menos memoria.

-Si se extiende de Thread,luego no se podra heredar cualquier otra clase ya que Java no permite la herencia múltiple, mientras que la implementación de Runnable aún ofrece la posibilidad de que una clase herede cualquier otra clase.

-Se debe extender de Thread solo si se tiene que sobreescribir o utilizar algunos de los otros métodos de la clase Thread.Se debe implementar runnable si solo desea sobreescribir el método run.

-La extensión de la clase Thread introduce un acoplamiento estricto en el código, ya que el código y el trabajo del hilo están contenidos en la misma clase. Por otro lado, la interfaz de ejecución runable introduce un acoplamiento suelto en el código, ya que el código del hilo está separado del trabajo asignado al hilo.


Ciclo de vida de un hilo:

Cuando se instancia la clase Thread (o una subclase) se crea un nuevo Thread que está en en su estado inicial ('New Thread'). En este estado es simplemente un objeto más. No existe todavía el thread en ejecución. El único método que puede invocarse sobre él es el método start. 

Cuando se invoca el método start sobre el thread el sistema crea los recursos necesarios, lo planifica (le asigna prioridad) y llama al método run. En este momento el thread está corriendo. 

Si el método run invoca internamente el método sleep o wait o el thread tiene que esperar por una operación de entrada/salida, entonces el thread pasa al estado 'no runnable' (no ejecutable) hasta que la condición de espera finalice. Durante este tiempo el sistema puede ceder control a otros threads activos.

Por último cuando el método run finaliza el thread termina y pasa a la situación 'Dead' (Muerto).

Metodos:

-start(): El método start crea los recuros del sistema necesarios para que el hilo pase a estado de ejecucion y a continuación, llama al método run y ejecuta lo que este conlleve.

-yield() : detiene temporalmente el hilo que se está ejecutando actualmente para dar una oportunidad a los hilos en espera restantes de la misma prioridad para ejecutar. Si no hay un hilo en espera o todos los hilos en espera tienen una prioridad más baja, el mismo hilo continuará su ejecución. El programador de hilos cuyo comportamiento depende del proveedor decidirá cuando el hilo generado tendrá la posibilidad de ejecución.

-sleep(): este método hace que el hilo que se está ejecutando se duerma durante la cantidad especificada de milisegundos y luego continue su ejecucion.

-join(): el método join () de una instancia de Thread se usa para unir el inicio de la ejecución de un hilo al final de la ejecución de otro hilo, de manera que un hilo no comienza a ejecutarse hasta que otro hilo finaliza. Si se llama a join () en una instancia de Thread, el hilo actualmente en ejecución se bloqueará hasta que dicha instancia de Thread haya terminado de ejecutarse.
El método join () espera un máximo de milisegundos para que este hilo muera. Un tiempo de espera de 0 significa esperar por siempre


