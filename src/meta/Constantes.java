package meta;

import java.awt.Color;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * Clase que contiene las constantes del programa
 * @author Erick Arroyo
 * @version 1.0
 */
public class Constantes {
    public static final boolean DEBUG = false;
    public static final String DIR = "src" + File.separator + "meta";
    public static final String CHAT_SERVER_NAME = "SERVIDOR";
    public static final String CHAT_NOTIFICATION_NAME = "NOTIFICACION";
    public static final String TYPE_LOGIN = "ALTA";
    public static final String TYPE_LOGOUT = "BAJA";    
    public static final String TYPE_DENIED = "BAJA";  
    public static final String TYPE_MESSAGE = "MENSAJE";
    public static final String TYPE_USER_DATA = "DATOS USUARIO";
    public static final String RED_FORTMAT = "\033[31m \n %s \033[0m";
    public static final String GREEN_FORTMAT = "\033[32m \n %s \033[0m";
    public static final String YELLOW_FORTMAT = "\033[33m \n %s \033[0m";
    public static final String BLUE_FORTMAT = "\033[34m \n %s \033[0m";
    public static final String PURPLE_FORTMAT = "\033[35m \n %s \033[0m";
    public static final String SERVER_NAME = "SERVIDOR";
    public static final String SERVER_MESSAGE = "Estas conectado al servidor";
    public static final String SERVER_MESSAGE_LOGOUT = " se ha desconectado del servidor.";
    public static final int SERVER_PORT = 9999;
    public static final String SERVER_IP;
    static {
        String ip = "unknown";
        try {
            ip = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        SERVER_IP = ip;
    }

    public static final Color COLOR_ACTUALIZACION = Color.CYAN;
    public static final Color COLOR_MENSAJE = Color.GREEN;
    public static final Color COLOR_ERROR = Color.RED;
    public static final Color COLOR_ALTA = Color.BLACK;
    public static final Color COLOR_BAJA = Color.ORANGE;
    
    
}
