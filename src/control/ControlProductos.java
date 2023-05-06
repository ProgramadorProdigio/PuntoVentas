package control;

import Modelo.Producto;
import control.archivos.ArchivoBinario;
import java.util.List;


/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ControlProductos {

    public  boolean Eliminar(int x) {
        
           int filaSeleccionada = x;
      
           List<Producto> listaProductos = ArchivoBinario.getProductos();
        Producto productoSeleccionado = listaProductos.get(filaSeleccionada);
        listaProductos.remove(productoSeleccionado);
      
        boolean res =  ArchivoBinario.regenerarElArchivo(listaProductos);
        
        return res;
    }
}
