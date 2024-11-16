package View;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import Controller.Usuario;

/**
 * Clase que crea la interfaz de inicio
 * @author Erick Arroyo
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        // Crear los componentes
        fondo = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JLabel();
        usuarioTXT = new javax.swing.JTextField();
        contraLabel = new javax.swing.JLabel();
        contraTXT = new javax.swing.JPasswordField();
        entrarB = new javax.swing.JButton();

        // Configurar la ventana principal
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setResizable(false);
        getContentPane().setLayout(null); // Usar layout nulo para posicionamiento absoluto

        // Configurar el fondo
        fondo.setBounds(0, 0, 400, 400);
        fondo.setLayout(null); // Usar layout nulo en el fondo para posicionamiento absoluto
        fondo.setOpaque(true);
        fondo.setBackground(Color.BLACK);
        getContentPane().add(fondo);

        // Variables para centrar componentes
        int componenteAncho = 200;
        int componenteAlto = 30;
        int labelAncho = 120;
        int labelAlto = 25;
        int botonAncho = 100;
        int botonAlto = 30;

        int centroX = (400 - componenteAncho) / 2;

        int posY = 100; // Posición Y inicial
        int espacioVertical = 40; // Espacio entre componentes

        // Configurar el label y campo de texto de usuario
        usuarioLabel.setFont(new java.awt.Font("DialogInput", 0, 19));
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setText("Usuario");
        usuarioLabel.setBounds((400 - labelAncho) / 2, posY, labelAncho, labelAlto);
        fondo.add(usuarioLabel);

        posY += labelAlto + 5;

        usuarioTXT.setBackground(Color.DARK_GRAY);
        usuarioTXT.setForeground(Color.WHITE);
        usuarioTXT.setBounds(centroX, posY, componenteAncho, componenteAlto);
        fondo.add(usuarioTXT);

        posY += componenteAlto + espacioVertical;

        // Configurar el label y campo de texto de contraseña
        contraLabel.setFont(new java.awt.Font("DialogInput", 0, 19));
        contraLabel.setForeground(Color.WHITE);
        contraLabel.setText("Contraseña");
        contraLabel.setBounds((400 - labelAncho) / 2, posY, labelAncho, labelAlto);
        fondo.add(contraLabel);

        posY += labelAlto + 5;

        contraTXT.setBackground(Color.DARK_GRAY);
        contraTXT.setForeground(Color.WHITE);
        contraTXT.setBounds(centroX, posY, componenteAncho, componenteAlto);
        fondo.add(contraTXT);

        posY += componenteAlto + espacioVertical;

        // Configurar el botón de entrar
        entrarB.setFont(new java.awt.Font("DialogInput", 0, 14));
        entrarB.setText("Entrar");
        entrarB.setForeground(Color.WHITE);
        entrarB.setBackground(Color.GRAY);
        entrarB.setBounds((400 - botonAncho) / 2, posY, botonAncho, botonAlto);
        entrarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarBActionPerformed(evt);
            }
        });
        fondo.add(entrarB);

        pack();
    }
    // </editor-fold>

    private void entrarBActionPerformed(java.awt.event.ActionEvent evt) {
        // Eliminar espacios iniciales y finales
    String usuario = usuarioTXT.getText().trim();
    String contraseña = new String(contraTXT.getPassword()).trim();

    // Verificar que ambos campos estén llenos
    if (usuario.isEmpty() || contraseña.isEmpty()) {
        // Mostrar un JOptionPane indicando el error
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Salir del método sin continuar
    }

    // Si los campos están llenos, proceder con la lógica original
    this.setVisible(false);
    System.out.println("Se inició el usuario: " + usuario);
    InterfazUsuario interfaz = new InterfazUsuario(usuario);
    Usuario intUsuario = new Usuario(interfaz, usuario);
    Thread hilo = new Thread(intUsuario);
    hilo.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Establecer el look and feel Nimbus */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Crear y mostrar la interfaz */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Declaración de variables
    private javax.swing.JLabel contraLabel;
    private javax.swing.JPasswordField contraTXT;
    private javax.swing.JButton entrarB;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JTextField usuarioTXT;
}