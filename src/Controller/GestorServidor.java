package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.logging.Logger;
import meta.Constantes;

/**
 * Clase que se encarga de gestionar la informacion del servidor
 * 
 * @version 1.0
 * @author Erick Arroyo
 */
public class GestorServidor {

    private static final Logger LOGGER = Logger.getLogger(GestorServidor.class.getName());

    /**
     * Metodo que se encarga de escribir en un archivo la informacion del servidor
     * 
     * @param nombre nombre del archivo
     * @param ip     ip del servidor
     * @param puerto puerto del servidor
     * @return true si se pudo escribir en el archivo, false en caso contrario
     */

    public static boolean escribirServidor(String nombre, String ip, int puerto) {
        // Uso de File.separator para asegurar compatibilidad con el sistema operativo
        String rutaArchivo = Constantes.DIR + File.separator + nombre + ".txt";

        // Asegura que el directorio exista
        File archivo = new File(rutaArchivo);
        File directorio = archivo.getParentFile();
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea el directorio si no existe
        }

        // Escribe en el archivo usando try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(String.format("%s,%s,%d", nombre, ip, puerto));
            if (Constantes.DEBUG) {
                String logMessage = String.format("Contenido escrito en archivo: %s,%s,%d", nombre, ip, puerto);
                LOGGER.info(logMessage);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que se encarga de buscar un servidor en la carpeta meta
     * 
     * @return informacion del servidor si se encontro, null en caso contrario
     */
    public static String buscarServidor() {
        Deque<String> nombres = obtenerNombresArchivosTXT(Constantes.DIR);
        String act;
        while (!nombres.isEmpty()) {
            act = nombres.pop();
            act = validarServidor(act);
            if (act != null) {
                return act;
            }
        }
        return null;
    }

    /**
     * Metodo que se encarga de cargar la informacion del servidor
     * 
     * @param linea linea con la informacion del servidor
     */
    public static String getIP(String linea) {
        String[] datos = linea.strip().split(",");
        return datos[1];

    }

    /**
     * Metodo que se encarga de obtener el puerto del servidor
     * 
     * @param linea linea con la informacion del servidor
     * @return el puerto del servidor
     */
    public static int getPuerto(String linea) {
        String[] datos = linea.strip().split(",");
        return Integer.parseInt(datos[2]);
    }

    /**
     * Metodo que se encarga de cargar la informacion del servidor
     * 
     * @param nombre nombre del archivo
     * @return los datos del servidor
     */
    public static String validarServidor(String nombre) {
        String linea;
        // Construye la ruta con File.separator
        String rutaArchivo = Constantes.DIR + File.separator + nombre;
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            linea = reader.readLine();
        } catch (IOException e) {
            LOGGER.info("ERROR [Lectura Servidor]: No se pudo leer la información del servidor.");
            return null;
        }
        return linea;
    }

    /**
     * Metodo que se encarga de conectar al servidor
     * 
     * @param usuario usuario que se conectara al servidor
     */
    public static void conectarServidor(Usuario usuario) {
        String servidor = buscarServidor();
        if (servidor == null) {
            LOGGER.info("ERROR [Conexión Servidor]: No se encontró información del servidor.");
            System.exit(0);
        }
        String ip = getIP(servidor);
        int puerto = getPuerto(servidor);
        usuario.setIpServidor(ip);
        usuario.setPuertoServidor(puerto);
        if (Constantes.DEBUG) {
            LOGGER.info("Conectando al servidor...");
        }
    }

    /**
     * Metodo que se encarga de obtener los nombres de los archivos .txt en una ruta
     * 
     * @param ruta ruta donde se buscaran los archivos
     * @return nombres de los archivos .txt
     */
    private static Deque<String> obtenerNombresArchivosTXT(String ruta) {
        Deque<String> nombres = new ArrayDeque<>();
        File directorio = new File(ruta);
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    nombres.push(archivo.getName());

                    LOGGER.info("Archivo encontrado: " + archivo.getName());
                }
            }
        }
        return nombres;
    }

}
