package Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;

import meta.Constantes;

/**
 * Clase que implementa el paquete de baja
 * @author Erick Arroyo
 * @version 1.0
 */
public class PaqueteBaja implements Paquete, Serializable{
    private final String nick;
    private final String tipo = Constantes.TYPE_LOGOUT;

    /**
     * Metodo constructor del paqute baja
     * @param nickName
     */
    public PaqueteBaja(String nickName){
        this.nick= nickName;
    }

    /**
     * Metodo que regresa nickname del paquete
     * @return String
     */
    @Override
    public String getNickname() {
        return nick;
    }

    /**
     * Metodo que regresa ip del paquete
     * @return String
     */
    @Override
    public String getIP() {
    return "Ip no necesitada";    
    }

    /**
     * Metodo que regresa mensaje del paquete
     * @return String
     */
    @Override
    public String getMensaje() {
        return "Usuario: "+getNickname()+" dado de baja."; 
    }

    /**
     * Metodo que regresa puerto del paquete
     * @return int
     */
    @Override
    public int getPort() {
        return 0; 
    }

    /**
     * Metodo que regresa lista de usuarios del paquete
     * @return String
     */
    @Override
    public DefaultListModel getListaUsuarios() {
        return null; 
    }

    /**
     * Metodo que regresa tipo del paquete
     * @return String
     */
    @Override
    public String getTipo() {
        return tipo; 
    }

    /**
     * Metodo que actualiza mensaje del paquete
     */
    @Override
    public void setMensaje(String mensaje, String actualizado) {
    System.out.println("No se puede cargar el mensaje");
    }

    /**
     * Metodo que actualiza lista de usuarios del paquete
     * @return String
     */
    @Override
    public void upDate(DefaultListModel actualizada) {
         System.out.println("No se pueden cargar actualizaciones");
    }

    /**
     * Metodo que regresa destinatario del paquete
     * @return String
     */
    @Override
    public String getDestinatario() {
      return "SERVIDOR"; 
    }

    /**
     * Metodo que regresa lista en forma de string del paquete
     * @return String
     */
    @Override
    public String imprimirLista() {
      return "Lista nula";
    }

    /**
     * Metodo que imprime informacion del paquete
     */
    @Override
    public void print() {
       System.out.println(
                "{"
                + "\n\tTipo: "+ getTipo()
                + "\n\tNickName:"+ getNickname()
                + "\n\tIP:"+ getIP()
                + "\n\tmensaje:"+ getMensaje()
                + "\n\tLista:"+ imprimirLista()+"\n}\n"
                        );
    }

    /**
     * Metodo que regresa tipo interno del paquete
     * @return String
     */
    @Override
    public String getTipoInterno() {
       return "BAJA";
    }

    /**
     * Metodo que actualiza tipo interno del paquete
     */
    @Override
    public void setTipoInterno(String nuevo) {
                System.out.println("Este metodo no requiere tipo interno.");

    }


    
}
