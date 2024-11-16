package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.DefaultListModel;
/**
 * Clase que contiene la lista de usuarios conectados al servidor
 * @author Erick Arroyo
 * @version 1.0
 */
public class ListaUsuarios implements Serializable {

    private HashMap<String, ProxyPaquete> lista = new HashMap<>();

    public ListaUsuarios() {
        //Constructor de la clase
    }

    /**
     * Metodo que regresa un iterador de los paquetes contenidos en el hashmap
     * de paquetes
     *
     * @return Iterator
     */
    public Iterator<ProxyPaquete> crearIteradorPaquete() {
        return lista.values().iterator();
    }

    /**
     * Metodo que regresa un iterador de los nombres contenidos en el hashmap de
     * paquetes
     *
     * @return Iterator
     */
    public Iterator<String> crearIteradorNombre() {
        return lista.keySet().iterator();
    }

    /**
     * Metodo que regresa un true si se pudo actualziar lista de usuarios
     * correctamente
     *
     * @param paquete
     * @return Boolean
     */
    public Boolean agregarUsuario(ProxyPaquete paquete) {
        String nombre = paquete.getNickname();
        if (lista.containsKey(nombre)) {
            return false;
        }
        lista.put(nombre, paquete);
        return true;
    }

    /**
     * Metodo que elimina usuario y actualiza lista de usuarios
     *
     * @param nombre
     * @return Boolean
     */
    public Boolean eliminarUsuario(String nombre) {
        if (lista.containsKey(nombre)) {
            lista.remove(nombre);
            return true;
        }
        return false;
    }

    /**
     * Metodo que regresa paquete en indice especificado de la lista
     *
     * @param nombre
     * @return ProxyPaquete
     */
    public ProxyPaquete getPaquete(String nombre) {
        return lista.get(nombre);
    }

    /**
     * Metodo que indica si un paquete existe en la lista
     *
     * @param nombre
     * @return boolean
     */
    public boolean contains(String nombre) {
        return lista.containsKey(nombre);
    }

    /**
     * Metodo que regresa tamano de la lista
     *
     * @return int
     */
    public int size() {
        return lista.size();
    }

    /**
     * Metodo que regresa lista
     *
     * @return DefaultistModel
     */
    public DefaultListModel<String> getListModel() {
        DefaultListModel<String> listaModelo = new DefaultListModel<>();
        Iterator<String> it = crearIteradorNombre();
        while (it.hasNext()) {
            listaModelo.addElement(it.next());
        }
        return listaModelo;
    }

}
