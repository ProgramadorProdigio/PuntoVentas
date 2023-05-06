
package vista;

import Modelo.Producto;
import java.awt.Frame;

/**
 * extends es para heredar de una clase a otra.
 *Ventana para editar los productos
 * @author regin
 */
public class EditarProducto extends ActualizarProductos {

    public EditarProducto(Frame parent, Producto p) { //Constructor
        super(parent, true);
        actualizarTitulo ("Editar Producto");
        habilitarCodigo (false); //Este es para deshabilitar código
        cargarDatos(p);
    }

    @Override
    public boolean procesarTarea(Producto p) {
        System.out.println(p);
        boolean res = db.actualizarProducto(p);
        return res;
    }
    
}
