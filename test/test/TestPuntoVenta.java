package test;

import Modelo.Producto;
import control.ControlProductos;
import control.archivos.ArchivoBinario;
import junit.framework.Assert;
import org.junit.Test;
import vista.EditarProducto;

/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class TestPuntoVenta {

    @Test

    public void pruebaAgregar() {

        String codigo = "0010";
        String descripcion = "hola mundo";
        double costo = 50;
        Producto p = new Producto(codigo, descripcion, costo);
        boolean resultado = ArchivoBinario.agregarProducto(p);
        Assert.assertEquals(true, resultado);
        System.out.println(resultado);
    }

    @Test

    public void pruebaEditarArchivo() {
        String codigo = "0005";
        String descripcion = "Golozinas";
        double costo = 20;

        Producto p = new Producto(codigo, descripcion, costo);

        EditarProducto mensajero = new EditarProducto(null, p);

        boolean res = mensajero.procesarTarea(p);
        Assert.assertEquals(true, res);

    }
    
      @Test
    public void purevaEliminar(){
         ControlProductos cal = new ControlProductos();
          boolean Resultado = cal.Eliminar(1);
          Assert.assertEquals(true, Resultado);
          if (Resultado) {
              System.out.println("Se elimino");
        }else{
              System.out.println("No se elimino");
          }
    }
}
