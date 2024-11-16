package main;

import Controller.Servidor;
import Model.ListaUsuarios;
import View.InterfazServidor;
import meta.Constantes;
/**
 * Clase principal que inicia el servidor
 * @version 1.0
 * @author Erick Arroyo
 */
public class ClienteServidor {
    public static void main(String[] args) {
        Servidor server = Servidor.getInstancia(Constantes.SERVER_NAME, Constantes.SERVER_IP, Constantes.SERVER_PORT, new InterfazServidor(), new ListaUsuarios());
        Thread proceso = new Thread(server);
        proceso.start();
    }
}
