
package vista;

import Modelo.Producto;
import java.awt.Frame;

/**
 *Vista para agregar nuevos productos...
 * @author regina
 */
//OverRay, es decir que existe este método en otro lado y lo estás modificando aquí,
//Ten cuidado porque la otra ya no sirve, es esta
public class AgregaProducto extends ActualizarProductos{

    public AgregaProducto(Frame parent) {
        super(parent, true);
        actualizarTitulo("Agregrar Productos ");
    }
    
    public boolean procesarTarea(Producto p){
    boolean res = db.agregarproducto(p);
    return res; 
}
}
