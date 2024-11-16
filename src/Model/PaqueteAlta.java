package Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;
import meta.Constantes;
/**
 * Clase que implementa el paquete de alta
 * 
 * @version 1.0
 * @autor Erick Arroyo
 */
public class PaqueteAlta implements Paquete, Serializable{
    private final String nickname;
    private final String ip;
    private final String mensaje;
    private final int port;
    public final String tipo = Constantes.TYPE_LOGIN;
    
    /**
     * Metodo constructor del paqute alta
     * @param nickname
     * @param ip
     * @param port
     */
    public PaqueteAlta(String nickname, String ip, int port){
        this.nickname = nickname;
        this.ip = ip;
        this.port = port;
        this.mensaje = "Nuevo usuario: " + nickname;
    }

    /**
     * Metodo que regresa nickname del paquete
     * @return String
     */
    @Override
    public String getNickname(){
        return nickname; 
    }
   
    /**
     * Metodo que regresa ip del paquete
     * @return String
     */
    @Override
    public String getIP(){
        return ip;
    }
    
    /**
     * Metodo que regresa mensaje del paquete
     * @return String
     */
    @Override
    public String getMensaje(){
        return mensaje;
    }  
    
    /**
     * Metodo que regresa puerto del paquete
     * @return int
     */
    @Override
    public int getPort(){
        return port;
    }

    /**
     * Metodo que regresa lista de usuarios del paquete
     * @return DefaultListModel
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
    public String getTipo(){
        return tipo;
    }

    /**
     * Metodo que actualiza lista de usuarios del paquete
     */
    @Override
    public void upDate(DefaultListModel actualizada) {    
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
     * Metodo que imprime informacion del paquete
     */
    public void print(){
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
     * Metodo que imprime lista de usuarios del paquete
     * @return String
     */
    @Override
    public String imprimirLista() {
        return "Sin lista";
    }

    /**
     * Metodo que actualiza mensaje del paquete
     */
    @Override
    public void setMensaje(String mensaje, String actualizado) {
    System.out.println("No se puede actualizar el mensaje");
    }

    /**
     * Metodo que regresa tipo interno del paquete
     * @return String
     */
    @Override
    public String getTipoInterno() {
       return tipo;
    }

    /**
     * Metodo que actualiza tipo interno del paquete
     */
    @Override
    public void setTipoInterno(String nuevo) {
     System.out.println("Este paquete no requiere un tipo interno.");  
    }

}
