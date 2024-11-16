package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import meta.Constantes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * Clase que representa la interfaz del usuario
 * @version 1.0 
 * @author Erick Arroyo
 */
public class InterfazUsuario extends javax.swing.JFrame {

    private final HashMap<String, JPanel> chats = new HashMap<>();
    private String ventanaActual;
    private final String myNick;

    // Componentes de interfaz
    private javax.swing.JList<String> Lusuarios;
    private javax.swing.JScrollPane baseChat;
    private javax.swing.JScrollPane baseDestinatarios;
    private javax.swing.JLabel contador;
    private javax.swing.JButton envio;
    private javax.swing.JLabel fondo;
    private javax.swing.JTextField mensaje;
    private javax.swing.JLabel nickname;
    private javax.swing.JLabel nombreAmigo;
    private javax.swing.JButton notificaciones;

    /**
     * Metodo constructor de objetos de clase InterfazUsuario
     *
     * @param nick String
     */
    public InterfazUsuario(String nick) {
        initComponents();
        this.addChat(Constantes.CHAT_NOTIFICATION_NAME);
        ventanaActual = Constantes.CHAT_NOTIFICATION_NAME;
        nickname.setText(nick);
        this.myNick = nick;
        setResizable(false);
        this.Lusuarios.setFixedCellHeight(12);

        // Establecer fondo negro para la interfaz
        getContentPane().setBackground(Color.BLACK);
    }

    /**
     * Metodo que inicializa y personaliza los componentes de la interfaz.
     */
    private void initComponents() {

        nombreAmigo = new javax.swing.JLabel();
        mensaje = new javax.swing.JTextField();
        baseChat = new javax.swing.JScrollPane();
        envio = new javax.swing.JButton();
        baseDestinatarios = new javax.swing.JScrollPane();
        Lusuarios = new javax.swing.JList<>();
        contador = new javax.swing.JLabel();
        notificaciones = new javax.swing.JButton();
        nickname = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Configurar el nombre del amigo (nick del chat seleccionado)
        nombreAmigo.setBackground(Color.BLACK);
        nombreAmigo.setFont(new java.awt.Font("Al Nile", 1, 24)); // NOI18N
        nombreAmigo.setForeground(Color.GREEN);
        nombreAmigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nombreAmigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        // Configurar el campo de mensaje
        mensaje.setBackground(Color.DARK_GRAY);
        mensaje.setForeground(Color.WHITE);
        mensaje.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY));
        getContentPane().add(mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 320, 29));

        // Configurar el área de chat (JScrollPane)
        baseChat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        baseChat.getViewport().setBackground(Color.BLACK);
        getContentPane().add(baseChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 320, 460));

        // Configurar el botón de envío
        envio.setText("Enviar");
        envio.setForeground(Color.WHITE);
        envio.setBackground(Color.GRAY);
        envio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envioActionPerformed(evt);
            }
        });
        getContentPane().add(envio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, 100, 29));

        // Configurar la lista de usuarios (JList)
        Lusuarios.setBackground(Color.BLACK);
        Lusuarios.setBorder(javax.swing.BorderFactory.createLineBorder(Color.GRAY));
        Lusuarios.setForeground(Color.GREEN);
        Lusuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LusuariosValueChanged(evt);
            }
        });
        baseDestinatarios.setViewportView(Lusuarios);

        // Agregar la lista de usuarios y ajustar su tamaño dinámico
        getContentPane().add(baseDestinatarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 100, 460));

        // Configurar el contador de mensajes
        contador.setBackground(Color.WHITE);
        contador.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        contador.setForeground(new java.awt.Color(255, 51, 51));
        contador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contador.setOpaque(false);
        getContentPane().add(contador, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 20, 20));

        // Configurar el botón de notificaciones
        notificaciones.setText("Notificaciones");
        notificaciones.setForeground(Color.WHITE);
        notificaciones.setBackground(Color.GRAY);
        notificaciones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        notificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notificacionesActionPerformed(evt);
            }
        });
        getContentPane().add(notificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, 30));

        // Configurar el nombre del usuario (nickname)
        nickname.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nickname.setForeground(new java.awt.Color(255, 0, 255));
        getContentPane().add(nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        // Configurar el fondo
        fondo.setOpaque(true);
        fondo.setBackground(Color.BLACK);
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 570));

        pack();
    }

    private void notificacionesActionPerformed(java.awt.event.ActionEvent evt) {
        this.setChat(Constantes.CHAT_NOTIFICATION_NAME);
        Lusuarios.clearSelection();
    }

    private void envioActionPerformed(java.awt.event.ActionEvent evt) {
        String mensajeTexto = mensaje.getText();
        if (!mensajeTexto.isEmpty()) {
            agregarMensaje(ventanaActual, mensajeTexto);
            mensaje.setText("");
        }
        // } else {
        // JOptionPane.showMessageDialog(null, "No puedes enviar mensajes vacíos",
        // "Error", JOptionPane.ERROR_MESSAGE);
        // }
    }

    private void LusuariosValueChanged(javax.swing.event.ListSelectionEvent evt) {
        // Cambiar de chat al seleccionar un usuario de la lista
        String selectedUser = Lusuarios.getSelectedValue();
        if (selectedUser != null && !selectedUser.equals(ventanaActual)) {
            setChat(selectedUser);
        }
    }

    /**
     * Metodo que retorna el JPanel que representa el chat entre dos usuarios
     *
     * @param nick String
     * @return JPanel
     */
    public JPanel getChat(String nick) {
        return chats.get(nick);
    }

    /**
     * Metodo que crea y agrega un nuevo JPanel de chat a la interfaz
     *
     * @param nick String
     */
    public void addChat(String nick) {
        JPanel nueva = new JPanel();
        nueva.setLayout(new BoxLayout(nueva, BoxLayout.Y_AXIS));
        nueva.setBackground(Color.BLACK);
        nueva.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chats.put(nick, nueva);
    }

    /**
     * Metodo que elimina de la interfaz el chat de un usuario dado de baja
     *
     * @param nick String
     */
    public void removeChat(String nick) {
        this.chats.remove(nick);
    }

    /**
     * Metodo que se encarga de hacer visible el JPanel del chat seleccionado
     *
     * @param nick String
     */
    public void setChat(String nick) {
        JPanel nueva = getChat(nick);
        ventanaActual = nick;
        baseChat.setViewportView(nueva);
        nombreAmigo.setText(nick);
    }

    /**
     * Metodo que asigna una nueva lista de usuarios
     *
     * @param listaActualizada DefaultListModel
     */
    public void setLista(DefaultListModel<String> listaActualizada) {
        this.Lusuarios.setModel(listaActualizada);
        this.Lusuarios.setFixedCellHeight(12);
    }

    /**
     * Metodo que carga los chats de una lista de usuarios
     *
     * @param lista DefaultListModel
     */
    public void cargarChats(DefaultListModel<String> lista) {

        for (int i = 0; i < lista.getSize(); i++) {
            String name = lista.getElementAt(i);
            if (!chats.containsKey(name)) {
                addChat(name);
            }
        }
    }

    /**
     * Metodo que elimina el nick del usuario de la lista de usuarios
     *
     * @param listaActualizada DefaultListModel
     * @return DefaultListModel
     */
    public DefaultListModel<String> eliminarme(DefaultListModel<String> listaActualizada) {
        listaActualizada.removeElement(myNick);
        return listaActualizada;
    }

    /**
     * Metodo que agrega un mensaje al chat en un recuadro de distinto color
     *
     * @param chatId  String
     * @param mensaje String
     */
    public void agregarMensaje(String chatId, String mensaje) {
        JPanel chatPanel = getChat(chatId);
        if (chatPanel != null) {
            // Obtener el ancho máximo del panel
            int anchoMaximo = chatPanel.getWidth() - 25;
            Font fuente = new Font("default", Font.PLAIN, 12); // Define aquí la fuente que estás usando

            // Formatear el mensaje con saltos de línea
            String mensajeFormateado = formatearMensaje(mensaje, anchoMaximo, fuente);

            // Crear panel para el mensaje
            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BorderLayout());
            messagePanel.setBackground(new Color(50, 50, 50));
            messagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // Crear área de texto para el mensaje
            JTextArea messageText = new JTextArea(mensajeFormateado);
            messageText.setEditable(false);
            messageText.setLineWrap(true);
            messageText.setWrapStyleWord(true);
            messageText.setBackground(new Color(50, 50, 50));
            messageText.setForeground(Color.GREEN);
            messageText.setBorder(null);

            messagePanel.add(messageText, BorderLayout.CENTER);
            chatPanel.add(messagePanel);
            chatPanel.add(Box.createVerticalStrut(10));

            chatPanel.revalidate();
            chatPanel.repaint();

            JScrollBar verticalScrollBar = baseChat.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    }

    /**
     * Metodo que formatea un mensaje para que se ajuste al ancho máximo del panel
     * 
     * @param mensaje     mensaje a formatear
     * @param anchoMaximo ancho máximo del panel
     * @param fuente      fuente del mensaje
     * @return String
     */
    public String formatearMensaje(String mensaje, int anchoMaximo, Font fuente) {
        // Crear un FontMetrics para medir el ancho de los caracteres
        FontMetrics metrics = new JPanel().getFontMetrics(fuente);
        StringBuilder formateado = new StringBuilder();

        int anchoActual = 0;
        for (String palabra : mensaje.split(" ")) {
            int anchoPalabra = metrics.stringWidth(palabra + " ");

            // Si la palabra sobrepasa el ancho máximo, agrega un salto de línea
            if (anchoActual + anchoPalabra > anchoMaximo) {
                formateado.append("\n");
                anchoActual = 0;
            }

            formateado.append(palabra).append(" ");
            anchoActual += anchoPalabra;
        }

        return formateado.toString().trim();
    }

    /**
     * Metodo que retorna el atributo mensaje de clase
     * 
     * @return JTextField
     */
    public javax.swing.JTextField getMensaje() {
        return this.mensaje;
    }

    /**
     * Metodo que retorna el atributo envio
     * 
     * @return JButton
     */
    public javax.swing.JButton getBotonEnvio() {
        return this.envio;
    }

    /**
     * Metodo que retorna el atributo lusuarios
     * 
     * @return JList<String>
     */
    public javax.swing.JList<String> getLista() {
        return this.Lusuarios;
    }

    /**
     * Metodo que retorna el atributo nombreAmigo
     * 
     * @return JLabel
     */
    public JLabel getNombre() {
        return this.nombreAmigo;
    }

    /**
     * Metodo que asigna a la iterfaz el nombre del usuaio seleccionado
     * 
     * @param nick String
     */
    public void setNombre(String nick) {
        this.nombreAmigo.setText(nick);
    }

    /**
     * Metodo que retorna el atributo notificaciones
     * 
     * @return JButton
     */
    public javax.swing.JButton getBotonNotificaciones() {
        return this.notificaciones;
    }

    /**
     * Metodo que retorna el atributo chats
     * 
     * @return HashMap<String, JTextArea>
     */
    public HashMap<String, JPanel> getChats() {
        return this.chats;
    }

    /**
     * Metodo que retorna el atributo contador
     * 
     * @return JLabel
     */
    public javax.swing.JLabel getContador() {
        return this.contador;
    }

    /**
     * Metodo que aumenta en uno al atributo contador
     */
    public void aumentarContador() {

        int valor = Integer.parseInt(contador.getText());
        valor++;
        contador.setText(String.valueOf(valor));
        this.contador.setVisible(true);
    }

    /**
     * Metodo que carga a la interfaz el valor del atributo contador
     */
    public void setContador() {
        contador.setText("0");
    }

    /**
     * Metodo que reinicia al atributo contador a cero
     */
    public void reiniciarContador() {
        this.setContador();
        this.contador.setVisible(false);
    }

    /**
     * Metodo que retorna el atributo ventanaActual
     * 
     * @return String
     */
    public String getVentanaActual() {
        return this.ventanaActual;
    }

}
