package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import meta.Constantes;
/**
 * Clase que implementa la interfaz Paquete y que se encarga de manejar los
 * paquetes de tipo fachada
 * @author Erick Arroyo
 * @version 1.0
 */
public class PaqueteFachada {

    private ServerSocket serverSocket;
    private static final Logger LOGGER = Logger.getLogger(PaqueteFachada.class.getName());

    /**
     * Constructor vacío de PaqueteFachada
     */
    public PaqueteFachada() {
        /*
         * Dado que servidor y usuario unen sus funcionalidades en esta clase, no es
         * necesario tener un constructor que reciba parámetros.
         * Sería innecesario tener un constructor que reciba una interfaz, un puerto y
         * una lista de usuarios, ya que esta clase no tiene relación con la interfaz
         * ni con la lista de usuarios.
         */
    }

    /**
     * Método para enviar un paquete a una dirección IP y puerto específicos.
     *
     * @param ip      Dirección IP del destinatario
     * @param puerto  Puerto del destinatario
     * @param paquete El paquete a enviar
     */
    public void enviarPaquete(String ip, int puerto, ProxyPaquete paquete) {
        try (Socket socket = new Socket(ip, puerto);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.writeObject(paquete);
            if (Constantes.DEBUG) {
                LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Paquete enviado:\n"));
                paquete.print();
            }
        } catch (IOException e) {
            LOGGER.info(String.format(Constantes.RED_FORTMAT, "Error al enviar paquete: " + e.getMessage()));
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al enviar paquete: " + e.getMessage() + "\nPor favor, verifique que el servidor esté funcionando.", 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para iniciar un ServerSocket en un puerto específico.
     *
     * @param puerto El puerto en el que se debe iniciar el ServerSocket
     * @throws IOException Si no se puede abrir el ServerSocket
     */
    public void iniciarServerSocket(int puerto) throws IOException {
        if (serverSocket == null || serverSocket.isClosed()) {
            serverSocket = new ServerSocket(puerto);
        }
    }

    /**
     * Método para recibir un paquete en un ServerSocket.
     *
     * @param serverSocket El ServerSocket en el que se debe recibir el paquete
     * @return El paquete recibido
     */
    public ProxyPaquete recibirPaquete(ServerSocket serverSocket) {
        try {
            Socket socket = serverSocket.accept();
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                ProxyPaquete paquete = (ProxyPaquete) in.readObject();
                if (Constantes.DEBUG) {
                    LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Paquete recibido:\n"));
                    paquete.print();
                }
                return paquete;
            }
        } catch (IOException e) {
            LOGGER.info(String.format(Constantes.RED_FORTMAT, "Error al recibir paquete: " + e.getMessage()));
            return null;
        } catch (ClassNotFoundException e) {
            LOGGER.info(String.format(Constantes.RED_FORTMAT, "Error al recibir paquete: " + e.getMessage()));
            return null;
        }
    }

    /**
     * Método para cerrar el ServerSocket.
     */
    public void cerrarServerSocket() {
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
                if (Constantes.DEBUG) {
                    LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "ServerSocket cerrado."));
                }
            } catch (IOException e) {
                LOGGER.info(String.format(Constantes.RED_FORTMAT, "Error al cerrar ServerSocket: " + e.getMessage()));
            }
        }
    }

    /**
     * Metodo que retorna el puerto del servidor
     * 
     * @return ServerSocket
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
