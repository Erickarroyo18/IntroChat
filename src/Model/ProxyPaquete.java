package Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;

/**
 * Clase que crea un paquete proxy
 * 
 * @autor Erick Arroyo
 * @version 1.0
 */
public class ProxyPaquete implements Paquete, Serializable {

    private final Paquete paquete;

    /**
     * Metodo constructor de proxypaqute
     * 
     * @param paquete
     */
    public ProxyPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * Metodo que regresa nickname de paquete
     * 
     * @return String
     */
    @Override
    public String getNickname() {
        return paquete.getNickname();
    }

    /**
     * Metodo que regresa ip de paquete
     * 
     * @return String
     */
    @Override
    public String getIP() {
        return paquete.getIP();
    }

    /**
     * Metodo que regresa mensaje paquete
     * 
     * @return String
     */
    @Override
    public String getMensaje() {
        return paquete.getMensaje();
    }

    /**
     * Metodo que regresa puerto del paquete
     * 
     * @return int
     */
    @Override
    public int getPort() {
        return paquete.getPort();
    }

    /**
     * Metodo que regresa lista de usuarios del paquete
     * 
     * @return int
     */
    @Override
    public DefaultListModel getListaUsuarios() {
        return paquete.getListaUsuarios();
    }

    /**
     * Metodo que regresa tipo del paquete
     * 
     * @return int
     */
    @Override
    public String getTipo() {
        return paquete.getTipo();
    }

    /**
     * Metodo que actualiza lista de usuarios paquete
     */
    @Override
    public void upDate(DefaultListModel actualizada) {
        this.paquete.upDate(actualizada);
    }

    /**
     * Metodo que actualiza mensaje del paquete
     */
    @Override
    public void setMensaje(String mensaje, String actualizado) {
        this.paquete.setMensaje(mensaje, actualizado);
    }

    /**
     * Metodo que regresa destinatario del paquete
     * 
     * @return String
     */
    @Override
    public String getDestinatario() {
        return this.paquete.getDestinatario();
    }

    /**
     * Metodo que regresa lista en forma de string del paquete
     * 
     * @return String
     */
    @Override
    public String imprimirLista() {
        return paquete.imprimirLista();
    }

    /**
     * Metodo que imprime el paquete
     * 
     * @return int
     */
    @Override
    public void print() {
        paquete.print();

    }

    /**
     * Metodo que regresa tipo interno del paquete
     * 
     * @return String
     */
    @Override
    public String getTipoInterno() {
        return this.paquete.getTipoInterno();
    }

    /**
     * Metodo que actualiza tipo interno del paquete
     */
    @Override
    public void setTipoInterno(String nuevo) {
        this.paquete.setTipoInterno(nuevo);
    }

}
