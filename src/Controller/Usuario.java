package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Model.*;
import View.InterfazUsuario;
import meta.Constantes;
import java.util.logging.Logger;
/**
 * Clase Usuario, que representa el controlador de la aplicacion, cuya funcion
 * es la de manejar la vista y el modelo del usuario
 * @author Erick Arroyo
 * @version 1.0
 */
public class Usuario implements Runnable {

    private final InterfazUsuario interfaz;
    private final String nick;
    private String ipServidor;
    private int puertoServidor;
    public Integer puerto;
    private PaqueteFachada paqueteFachada = new PaqueteFachada();
    private static final Logger LOGGER = Logger.getLogger(Usuario.class.getName());

    /**
     * Metodo constructor de objetos de clase Usuario, que asigna sus variables
     * internas la vista el y modelo del usuaio
     *
     * @param interfaz this.InterfazUsuario
     * @param usuario
     */
    public Usuario(InterfazUsuario interfaz, String usuario) {
        this.interfaz = interfaz;
        this.nick = usuario;
        this.puerto = 5001;
        GestorServidor.conectarServidor(this);
        if (Constantes.DEBUG) {
            String info = "Servidor encontrado: " + this.ipServidor + ":" + this.puertoServidor;
            LOGGER.info(String.format(Constantes.GREEN_FORTMAT, info));
        }
        initComponents();
    }

    /**
     * Metodo que se encarga de enviar un PaqueteMensaje al servidor Toma la
     * informacion de destinatario y el mensaje desde la vista
     */
    private void mandarMensaje() {
        String destinatario = this.interfaz.getLista().getSelectedValue();
        String mensaje = this.interfaz.getMensaje().getText();
        ProxyPaquete paquete = new ProxyPaquete(new PaqueteMensaje(nick, mensaje, destinatario));
        this.paqueteFachada.enviarPaquete(this.ipServidor, this.puertoServidor, paquete);
        this.interfaz.agregarMensaje(destinatario, "Tu:\n" + mensaje);
        this.interfaz.setChat(destinatario);
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Mensaje enviado:"));
            paquete.print();
        }
    }

    /**
     * Metodo que notifica al servidor que se ha dado de baja un usuario
     */
    private void mandarBaja() {
        ProxyPaquete paquete = new ProxyPaquete(new PaqueteBaja(nick));
        this.paqueteFachada.enviarPaquete(this.ipServidor, this.puertoServidor, paquete);
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Baja enviada:\n"));
            paquete.print();
        }
    }

    /**
     * Metodo que se encarga de enviarle al servidor el alta de usuario
     * 
     * @param puerto puerto del usuario
     */
    private void mandarAlta() {
        ProxyPaquete paquete = new ProxyPaquete(new PaqueteAlta(nick, this.ipServidor, this.puerto));
        this.paqueteFachada.enviarPaquete(this.ipServidor, this.puertoServidor, paquete);
        if (Constantes.DEBUG) {
            LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Alta enviada:"));
            paquete.print();
        }
    }

    /**
     * Metodo que representa el serverSocket del usuario, cuya funcion es
     * recibir todos los paquetes enviador por el servidor
     *
     * @param server ServerSocket
     */
    private void leerServidor(ServerSocket server) {
        ProxyPaquete paqueteRecibido = this.paqueteFachada.recibirPaquete(server);
        manejarPaquete(paqueteRecibido);
    }

    /**
     * Metodo que se encarga de manejar los paquetes recibidos por el servidor
     * 
     * @param pp ProxyPaquete
     */
    private void manejarPaquete(ProxyPaquete pp) {
        String tipo = pp.getTipo();
        if (tipo.equalsIgnoreCase(Constantes.TYPE_MESSAGE)) {
            String nickE = pp.getNickname();
            this.interfaz.agregarMensaje(nickE, nickE + "\n" + pp.getMensaje());
            this.interfaz.setChat(nickE);

        } else if (tipo.equalsIgnoreCase(Constantes.TYPE_USER_DATA)) {
            if (Constantes.DEBUG) {
                LOGGER.info(String.format(Constantes.BLUE_FORTMAT, "Datos de usuario recibidos"));
                pp.print();
            }

            DefaultListModel<String> listaSinMi = this.interfaz.eliminarme(pp.getListaUsuarios());
            this.actualizarLista(listaSinMi);

            if (pp.getTipoInterno().equalsIgnoreCase(Constantes.TYPE_LOGOUT)) {
                this.interfaz.removeChat(pp.getDestinatario());
                this.interfaz.setChat(Constantes.CHAT_NOTIFICATION_NAME);
                this.interfaz.agregarMensaje(Constantes.CHAT_NOTIFICATION_NAME, pp.getMensaje());
            } else if (pp.getTipoInterno().equalsIgnoreCase(Constantes.TYPE_LOGIN)) {
                this.interfaz.cargarChats(listaSinMi);
            }
            // En caso que el servidor envie un mensaje de notificacion al usuario
            if (pp.getMensaje().contains(nick)) {
            this.interfaz.agregarMensaje(Constantes.CHAT_NOTIFICATION_NAME, pp.getMensaje());
            this.interfaz.setChat(Constantes.CHAT_NOTIFICATION_NAME);
            }

            if (this.interfaz.getVentanaActual().equalsIgnoreCase(Constantes.CHAT_NOTIFICATION_NAME)) {
                this.interfaz.reiniciarContador();
            } else {
                this.interfaz.aumentarContador();
                this.interfaz.getContador().setVisible(true);
               
            }
            // En caso que el servidor no permita la conexion del usuario por un nickname ya
            // utilizado se cierra la aplicacion
        } else if (tipo.equalsIgnoreCase(Constantes.TYPE_DENIED)) {
            JOptionPane.showMessageDialog(null, "Nickname ya utilizado, intenta de nuevo");
            System.exit(0);
        }

    }

    private void actualizarLista(DefaultListModel<String> lista) {
        this.interfaz.getLista().removeAll();
        this.interfaz.setLista(lista);
    }

    /**
     * Metodo que se encarga de validar y establecer la conexion con el servidor
     *
     * @return int
     */
    private int crearConexion() {
        int puertoAProbar = 5001;
        while (true) {
            try {

                ServerSocket serverS = new ServerSocket(puertoAProbar);
                if (Constantes.DEBUG) {
                    LOGGER.info(
                            String.format(Constantes.GREEN_FORTMAT, "CONEXION USUARIO en puerto: " + puertoAProbar));
                }
                this.puerto = puertoAProbar;
                mandarAlta();
                while (true) {
                    leerServidor(serverS);
                }

            } catch (IOException ex) {
                if (Constantes.DEBUG) {
                    LOGGER.info(String.format(Constantes.RED_FORTMAT,
                            "Error en conexion USUARIO, puerto: " + puertoAProbar));
                }
                if (++puertoAProbar == 5011) {
                    return -1;
                }
            }

        }

    }

    /**
     * Metodo run() de interface Runnable, cuya funcion es mantener le ejecucion
     * simultanea con el servidor
     */
    @Override
    public void run() {
        crearConexion();
    }

    /**
     * Metodo que asigna la IP del servidor
     * 
     * @param ipServidor ip del servidor
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    /**
     * Metodo que asigna el puerto del servidor
     * 
     * @param puertoServidor puerto del servidor
     */
    public void setPuertoServidor(int puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    /**
     * Metodo que inicializa las funciones del atributo this.Interfaz
     */
    public void initComponents() {
        this.interfaz.getMensaje().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * Metodo que permite al usuario enviar un mensaje presionando la
             * tecla enter de su teclado
             *
             * @param e KeyEvent
             */
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    if (!(interfaz.getLista().isSelectionEmpty())) {
                        if (!(interfaz.getMensaje().getText().trim().equalsIgnoreCase(""))) {
                            mandarMensaje();
                            interfaz.getMensaje().setText("");
                        } else {
                            if (Constantes.DEBUG)
                                LOGGER.info(String.format(Constantes.YELLOW_FORTMAT, "Tu mensaje esta vacio\n"));
                        }

                    } else {
                        if (Constantes.DEBUG)
                            LOGGER.info(String.format(Constantes.YELLOW_FORTMAT,
                                    "Falta la información del destinatario\n"));
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.interfaz.addWindowListener(new WindowAdapter() {
            /**
             * Metodo que se encarga de reconocer cuando la ejecucion es
             * finalizada y notifica al servidor por medio del metodo
             * mandarBaja()
             *
             * @param evt WindowAdapter
             */
            @Override
            public void windowClosing(WindowEvent evt) {
                mandarBaja();
                System.exit(0);
            }
        });

        this.interfaz.getBotonNotificaciones().addActionListener(new ActionListener() {
            /**
             * Metodo que se encarga de mostrar el chat de notificacion del
             * servidor al usuario
             *
             * @param e ActionListener
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                interfaz.setChat(Constantes.CHAT_NOTIFICATION_NAME);
                interfaz.setNombre(Constantes.CHAT_NOTIFICATION_NAME);
                interfaz.reiniciarContador();

                interfaz.getContador().setVisible(false);
            }
        });

        this.interfaz.getBotonEnvio().addActionListener(new ActionListener() {
            /**
             * Metodo que se encarga de mandar al servidor la informacion de un
             * nuevo mensaje
             *
             * @param e ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(interfaz.getLista().isSelectionEmpty())) {
                    if (!(interfaz.getMensaje().getText().trim().equalsIgnoreCase(""))) {
                        mandarMensaje();
                        interfaz.getMensaje().setText("");
                    } else {
                        LOGGER.info(String.format(Constantes.YELLOW_FORTMAT, "Tu mensaje esta vacio\n"));
                    }

                } else {
                    LOGGER.info(String.format(Constantes.YELLOW_FORTMAT, "Falta la información del destinatario\n"));
                }

            }
        });

        this.interfaz.setVisible(true);
        this.interfaz.getLista().addListSelectionListener(new ListSelectionListener() {
            /**
             * Metodo que se encarga de detectar el cambio de destinatario a
             * partir de la this.interfaz
             *
             * @param arg0 ListSelectionEvent
             */
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!interfaz.getLista().isSelectionEmpty()) {
                    String seleccionado = interfaz.getLista().getSelectedValue();
                    interfaz.setChat(seleccionado);
                    interfaz.setForeground(Color.blue);
                    interfaz.setNombre(seleccionado);

                }

            }
        });
        LOGGER.info(String.format(Constantes.GREEN_FORTMAT, "Componentes iniciados"));
    }   
}
