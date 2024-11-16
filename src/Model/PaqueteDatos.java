package Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;
import meta.Constantes;

/**
 * Clase que implementa la interfaz Paquete y que se encarga de manejar los
 * paquetes de tipo datos
 * 
 * @version 1.0
 * @author Erick Arroyo
 */
public class PaqueteDatos implements Paquete, Serializable {

    private String mensaje;
    private final String tipo = Constantes.TYPE_USER_DATA;
    private String tipoInterno;
    private String actualizado;
    private final String nick;
    private final String ip;
    private final int puerto;
    private DefaultListModel usuariosOnline;

    public PaqueteDatos(ProxyPaquete paqueteAlta) {
        this.nick = paqueteAlta.getNickname();
        this.ip = paqueteAlta.getIP();
        this.puerto = paqueteAlta.getPort();
        usuariosOnline = new DefaultListModel();

    }

    /**
     * Metodo que regresa nickname del paquete
     * 
     * @return String
     */
    @Override
    public String getNickname() {
        return nick;
    }

    /**
     * Metodo que regresa ip del paquete
     * 
     * @return String
     */
    @Override
    public String getIP() {
        return ip;
    }

    /**
     * Metodo que regresa mensaje del paquete
     * 
     * @return String
     */
    @Override
    public String getMensaje() {
        return this.mensaje;
    }

    /**
     * Metodo que actualzia mensjae del paquete
     */
    @Override
    public void setMensaje(String mensaje, String actualizado) {
        this.mensaje = actualizado + " " + mensaje;
        this.actualizado = actualizado;
    }

    /**
     * Metodo que regresa puerto del paquete
     * 
     * @return int
     */
    @Override
    public int getPort() {
        return puerto;
    }

    /**
     * Metodo que regresa lsitad usuarios del paquete
     * 
     * @return DefaultListModel
     */
    @Override
    public DefaultListModel getListaUsuarios() {
        return usuariosOnline;
    }

    /**
     * Metodo que regresa tipo del paquete
     * 
     * @return String
     */
    @Override
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo que actualiza lista de usuarios del paquete
     */
    @Override
    public void upDate(DefaultListModel actualizada) {
        this.usuariosOnline = actualizada;
    }

    /**
     * Metodo que regresa lista de usuarios en forma de string del paquete
     * 
     * @return String
     */
    @Override
    public String imprimirLista() {
        String listaImpresa = "[";
        for (int i = 0; i < usuariosOnline.size(); i++) {
            listaImpresa += " " + usuariosOnline.get(i);
        }
        listaImpresa += "]";
        return listaImpresa;
    }

    /**
     * Metodo que imprime informacion del paquete
     */
    @Override
    public void print() {
        System.out.println(
                """
                        {
                        \tTipo: """ + getTipo()
                        + "\n\tNickName:" + getNickname()
                        + "\n\tIP:" + getIP()
                        + "\n\tmensaje:" + getMensaje()
                        + "\n\tLista:" + imprimirLista() + "\n}\n");
    }

    /**
     * Metodo que regresa destinatario del paquete
     * 
     * @return String
     */
    @Override
    public String getDestinatario() {
        return actualizado;
    }

    /**
     * Metodo que regresa tipo interno del paquete
     * 
     * @return String
     */
    @Override
    public String getTipoInterno() {
        return this.tipoInterno;
    }

    /**
     * Metodo que actualiza tipo interno del paquete
     * 
     * @param nuevo String
     */
    @Override
    public void setTipoInterno(String nuevo) {
        this.tipoInterno = nuevo;
    }
}
