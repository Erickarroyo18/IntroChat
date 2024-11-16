package Model;


import javax.swing.DefaultListModel;

/**
 * Interfaz que define los metodos que debe tener un paquete
 * para ser enviado por la red
 * @author Erick Arroyo
 * @version 1.0
 */
public interface Paquete {

    /**
     * Metodo que regresa nickname del paquete
     * @return String
     */
    public String getNickname();

    /**
     * Metodo que regresa ip del paquete
     * @return String
     */
    public String getIP();

    /**
     * Metodo que regresa mensaje del paquete
     * @return
     */
    public String getMensaje();

    /**
     * Metodo que regresa puerto del paquete
     * @return int
     */
    public int getPort();

    /**
     * Metodo que regresa lista de usuarios del paquete
     * @return DefaultListModel
     */
    public DefaultListModel getListaUsuarios();

    /**
     * Metodo que regresa tipo del paquete
     * @return String
     */
    public String getTipo();

    /**
     * Metodo que actualiza lista del paquete
     * @param actualizada
     */
    public void upDate(DefaultListModel actualizada);

    /**
     * Metodo que regresa destinatario del paquete
     * @return String
     */
    public String getDestinatario();

    /**
     * Metodo que regresa lista en forma de string del paquete
     * @return String
     */
    public String imprimirLista();

    /**
     * Metodo que imprime informacion del paquete
     */
    public void print();

    /**
     * Metodo que actualiza mensaje del paquete
     * @param mensaje String
     * @param actualizado String
     */
    public void setMensaje(String mensaje, String actualizado);

    /**
     * Metodo que regresa tipo interno del paquete
     * @return Strings
     */
    public String getTipoInterno();

    /**
     * Metodo que actualiza tipo interno del paquete
     * @param nuevo
     */
    public void setTipoInterno(String nuevo);
    
}
