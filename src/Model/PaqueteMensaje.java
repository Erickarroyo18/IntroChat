package Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;
import meta.Constantes;

/**
 * Clase que crea un paquete mensaje
 * 
 * @autor Erick Arroyo
 * @version 1.0
 */
public class PaqueteMensaje implements Paquete, Serializable {

    private final String nickname;
    private final String nickNameDestinatario;
    private final String ip;
    private final String mensaje;
    private final int port;
    private final String tipo = Constantes.TYPE_MESSAGE;

    /**
     * Metodo constructor que crea paquete mensaje
     * 
     * @param nick
     * @param mensaje
     * @param nickNameDestinatario
     */
    public PaqueteMensaje(String nick, String mensaje, String nickNameDestinatario) {
        this.nickname = nick;
        this.mensaje = mensaje;
        this.nickNameDestinatario = nickNameDestinatario;
        this.ip = null;
        this.port = 0;
    }

    /**
     * Metodo que regresa nickname de quien envia mensaje
     * 
     * @return String
     */
    @Override
    public String getNickname() {
        return nickname;
    }

    /**
     * Metodo que regresa nickname de persona a quien se envia el mensaje
     * 
     * @return String
     */
    @Override
    public String getDestinatario() {
        return nickNameDestinatario;
    }

    /**
     * Metodo que regresa la ip de quien envia mensaje
     * 
     * @return String
     */
    @Override
    public String getIP() {
        return ip;
    }

    /**
     * Metodo que regresa el mensaje del paquete
     * 
     * @return String
     */
    @Override
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Metodo que regresa el puerto del paquete
     * 
     * @return String
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * Metodo que regresa la lista de usuarios del paquete
     * 
     * @return String
     */
    @Override
    public DefaultListModel getListaUsuarios() {
        return null;
    }

    /**
     * Metodo que regresa el tipo del paquete
     * 
     * @return String
     */
    @Override
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo que actualiza la lista del paquete
     * 
     * @param actualizada
     */
    @Override
    public void upDate(DefaultListModel actualizada) {
        System.out.println("Este paquete no actualiza la lista de usuarios.");
    }

    /**
     * Metodo que actualiza el mensaje del paquete
     * 
     * @param mensaje
     * @param actualizado
     */
    @Override
    public void setMensaje(String mensaje, String actualizado) {
        System.out.println("No se puede cargar el mensaje");
    }

    /**
     * Metodo que regresa en string la lista del paquete
     * 
     * @return String
     */
    @Override
    public String imprimirLista() {
        return "Sin lista";
    }

    /**
     * Metodo que imprime la informacion del paquete
     * 
     */
    public void print() {
        System.out.println(
                "{"
                        + "\n\tTipo: " + getTipo()
                        + "\n\tNickName:" + getNickname()
                        + "\n\tIP:" + getIP()
                        + "\n\tmensaje:" + getMensaje() + "\n}\n");
    }

    /**
     * Metodo que regresa el tipo interno del paquete
     * 
     * @return String
     */
    @Override
    public String getTipoInterno() {
        return "MENSAJE";
    }

    /**
     * Metodo que actualiza el tipo interno del paquete
     * 
     * @return String
     */
    @Override
    public void setTipoInterno(String nuevo) {
        System.out.println("Este paquete no requiere tipo interno.");

    }

}
