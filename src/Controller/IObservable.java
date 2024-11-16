package Controller;

import Model.ProxyPaquete;

/**
 * Interfaz que define los metodos que se deben implementar en la clase
 * Observador
 * 
 * @author Erick Arroyo
 * @version 2.0
 */
public interface IObservable {

    /**
     * Metodo que almacena en la base de datos interna la informacion de un
     * nuevo usuario
     *
     * @param paquete ProxyPaquete
     * @return boolean
     */
    public boolean altaUsuario(ProxyPaquete paquete);

    /**
     * Metodo que elimina la informacion de la base de datos
     *
     * @param paquete ProxyPaquete
     */
    public void bajaUsuario(ProxyPaquete paquete);

    /**
     * Metodo que notifica al usuario de que se ha dado de baja del servidor
     *
     * @param paquete ProxyPaquete
     */
    public void mandarBaja(ProxyPaquete paquete);

    /**
     * Metodo que se encarga de redirigir la informacion de un mensaje a su
     * destinatario
     *
     * @param paquete ProxyPaquete
     */
    public void reenvioMensaje(ProxyPaquete paquete);

    /**
     * Metodo que se encarga de notificar el alta de un usuario a todos lo
     * PaquetesDatos que se encuentren en la base de datos interna, aplicandoles
     * su metodo propio update();
     *
     * @param nick String
     */
    public void notificarAll(String nick);

    /**
     * Metodo que se encarga de notificar la baja de un usuario a todos lo
     * PaquetesDatos que se encuentren en la base de datos interna, aplicandoles
     * su metodo propio update();
     *
     * @param nick String
     */
    public void notificarBaja(String nick);

    /**
     * Metodo auxiliar a notificarAll y a notificarBaja, que se encarga de
     * mandar la actualización el serverSocket de los usuarios
     *
     * @param paquete ProxyPaquete
     */
    public void enviarNotificacion(ProxyPaquete paquete);

}
