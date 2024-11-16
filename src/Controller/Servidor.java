package Controller;

import Model.ListaUsuarios;
import Model.PaqueteBaja;
import Model.PaqueteDatos;
import Model.PaqueteFachada;
import Model.ProxyPaquete;
import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.logging.Logger;
import View.InterfazServidor;
import meta.Constantes;
/**
 * Clase que se encarga de gestionar el servidor, almacenando la informacion de
 * los usuarios y gestionando los paquetes que llegan al servidor
 * @author Erick Arroyo
 * @version 2.0
 */
public class Servidor implements Runnable, IObservable {

    private static Servidor instancia;
    private ListaUsuarios lista;
    private InterfazServidor interfaz;
    private String ip;
    private String nombre;
    private int puerto;
    private PaqueteFachada paqueteFachada;
    private final static Logger LOGGER = Logger.getLogger(Servidor.class.getName());

    /**
     * Metodo constructor que se encarga de asignar a las variables internas el
     * modelo y la visa del servidor
     *
     * @param interfaz InterfazServidor
     * @param lista    ListaUsuarios
     */
    private Servidor(String nombre, String ip, int puerto, InterfazServidor interfaz, ListaUsuarios lista) {
        this.interfaz = interfaz;
        this.lista = lista;
        this.paqueteFachada = new PaqueteFachada();
        this.ip = ip;
        this.puerto = puerto;
        this.nombre = nombre;
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.GREEN_FORTMAT, "Se inicio el servidor: "));
        }
        // GestorServidor.escribirServidor(this.nombre, this.ip, this.puerto);
        interfaz.setVisible(true);
    }

    /**
     * Metodo que se encarga de crear una instancia del servidor
     * 
     * @param nombre   nombre del servidor
     * @param ip       ip del servidor
     * @param puerto   puerto del servidor
     * @param interfaz interfaz del servidor
     * @param lista    lista de usuarios
     * @return Servidor
     */
    public static Servidor getInstancia(String nombre, String ip, int puerto, InterfazServidor interfaz,
            ListaUsuarios lista) {
        if (instancia == null) {
            instancia = new Servidor(nombre, ip, puerto, interfaz, lista);
            if (Constantes.DEBUG) {
                LOGGER.info(String.format(Constantes.GREEN_FORTMAT, "Se creo una instancia del servidor"));
            }
        }
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.GREEN_FORTMAT, "Se devolvio la instancia del servidor"));
        }
        return instancia;
    }

    /**
     * Metodo run de interfaz Runnable Se encarga de ejecutar en un hilo el
     * serverSocket del servidor, para permitirle estar a la escucha de todas
     * las acciones de los usuarios
     */
    public void run() {
        try {
            // this.paqueteFachada.iniciarServerSocket(this.puerto);
            ServerSocket serverSocket = new ServerSocket(this.puerto);
            while (true) {
                ProxyPaquete paquete = this.paqueteFachada.recibirPaquete(serverSocket);

                if (Constantes.DEBUG) {
                    LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Paquete recibido:\n"));
                    paquete.print();
                }
                this.manejarPaquete(paquete);
                // this.paqueteFachada.cerrarServerSocket();
            }
        } catch (IOException e) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Error al recibir paquete: " + e.getMessage()));
        }
    }

    /**
     * Metodo que se encarga de manejar los paquetes que llegan al servidor
     * 
     * @param paquete ProxyPaquete
     */
    public void manejarPaquete(ProxyPaquete paquete) {
        if (paquete.getTipo().equalsIgnoreCase(Constantes.TYPE_MESSAGE)) {
            reenvioMensaje(paquete);
        } else if (paquete.getTipo().equalsIgnoreCase(Constantes.TYPE_LOGIN)) {
            if (!altaUsuario(paquete)) {
                bajaUsuario(paquete);
            }

        } else if (paquete.getTipo().equalsIgnoreCase(Constantes.TYPE_LOGOUT)) {
            bajaUsuario(paquete);
        }
    }

    /**
     * Metodo que almacena en la base de datos interna la informacion de un
     * nuevo usuario
     *
     * @param paquete ProxyPaquete
     * @return boolean
     */
    public boolean altaUsuario(ProxyPaquete paquete) {
        String nick = paquete.getNickname();
        ProxyPaquete nuevo = new ProxyPaquete(new PaqueteDatos(paquete));
        boolean aceptado = lista.agregarUsuario(nuevo);
        if (aceptado) {
            notificarAll(nick);
            String mensaje = String.format("[%s] Usuario: %s conectad@ [%s, %d]%n", Constantes.TYPE_LOGIN,nick, paquete.getIP(), paquete.getPort());
            this.agregarMensaje(mensaje, Constantes.COLOR_ALTA);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina la informacion de la base de datos
     *
     * @param paquete ProxyPaquete
     */
    public void bajaUsuario(ProxyPaquete paquete) {
        String nick = paquete.getNickname();
        lista.eliminarUsuario(nick);
        notificarBaja(nick);
        String mensaje = String.format("[%s] Usuario: %s desconectad@%n", Constantes.TYPE_LOGOUT, nick);
        this.agregarMensaje(mensaje, Constantes.COLOR_BAJA);
    }

    /**
     * Metodo que notifica al usuario de que se ha dado de baja del servidor
     *
     * @param paquete ProxyPaquete
     */
    public void mandarBaja(ProxyPaquete paquete) {
        paquete = new ProxyPaquete(new PaqueteBaja(paquete.getNickname()));
        this.paqueteFachada.enviarPaquete(paquete.getIP(), paquete.getPort(), paquete);
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Baja enviada:\n"));
            paquete.print();
        }
    }

    /**
     * Metodo que se encarga de redirigir la informacion de un mensaje a su
     * destinatario
     *
     * @param paquete ProxyPaquete
     */
    public void reenvioMensaje(ProxyPaquete paquete) {
        String nick = paquete.getDestinatario();
        ProxyPaquete paqueteUsuario = lista.getPaquete(nick);
        String mensaje = String.format("[%s] Mensaje:[ %s -> %s =>%s ]%n", Constantes.TYPE_MESSAGE,paquete.getNickname(), paquete.getMensaje(), nick);
        this.agregarMensaje(mensaje, Constantes.COLOR_MENSAJE);
        this.paqueteFachada.enviarPaquete(paqueteUsuario.getIP(), paqueteUsuario.getPort(), paquete);
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Paquete enviado:\n"));
            paquete.print();
        }
    }

    /**
     * Metodo que se encarga de agregar un mensaje a la interfaz con un color
     * especifico
     *
     * @param mensaje String
     * @param color   String
     */
    public void agregarMensaje(String mensaje, Color color) {
        this.interfaz.agregarMensaje(mensaje, color);
    }

    /**
     * Metodo que se encarga de notificar el alta de un usuario a todos lo
     * PaquetesDatos que se encuentren en la base de datos interna, aplicandoles
     * su metodo propio update();
     *
     * @param nick String
     */
    public void notificarAll(String nick) {
        Iterator<ProxyPaquete> it = lista.crearIteradorPaquete();
        while (it.hasNext()) {
            ProxyPaquete auxiliar = it.next();
            auxiliar.setTipoInterno(Constantes.TYPE_LOGIN);
            auxiliar.upDate(lista.getListModel());
            auxiliar.setMensaje(Constantes.SERVER_MESSAGE, nick);
            enviarNotificacion(auxiliar);
        }
    }

    /**
     * Metodo que se encarga de notificar la baja de un usuario a todos lo
     * PaquetesDatos que se encuentren en la base de datos interna, aplicandoles
     * su metodo propio update();
     *
     * @param nick String
     */
    public void notificarBaja(String nick) {
        Iterator<ProxyPaquete> it = lista.crearIteradorPaquete();
        while (it.hasNext()) {
            ProxyPaquete auxiliar = it.next();
            auxiliar.setMensaje(Constantes.SERVER_MESSAGE_LOGOUT, nick);
            auxiliar.setTipoInterno(Constantes.TYPE_LOGOUT);
            auxiliar.upDate(lista.getListModel());
            enviarNotificacion(auxiliar);
        }

    }

    /**
     * Metodo auxiliar a notificarAll y a notificarBaja, que se encarga de
     * mandar la actualización el serverSocket de los usuarios
     *
     * @param paquete ProxyPaquete
     */
    public void enviarNotificacion(ProxyPaquete paquete) {
        this.paqueteFachada.enviarPaquete(this.ip, paquete.getPort(), paquete);
    }

    @Override
    public String toString() {
        return "Servidor{" +
                "nombre='" + nombre + '\'' +
                ", ip='" + ip + '\'' +
                ", puerto=" + puerto +
                '}';
    }

}
