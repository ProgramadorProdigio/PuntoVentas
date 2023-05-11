
package control.Basededatos;

import Modelo.OperacionesBasedatos;
import Modelo.Producto;
import Modelo.VentasModelo;

import control.archivos.ArchivoBinario;
import control.archivos.ArchivoXML;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import vista.ActualizarProductos;
/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class BasedatosVirtual  implements OperacionesBasedatos{
//private static final List<Producto> dbProductos = new ArrayList<Producto>();
    @Override
    public String[][] todosProducto() {
      
        return convertirArreglo(ArchivoBinario.getProductos());
    }
    
    private String [][]convertirArreglo(List<Producto> lis){
        String data [][] = new String [lis.size()][3];
        int i = 0;
        for (Producto producto : lis) {
           data[i][0] = producto.getCodigo().trim();
            data [i][1] = producto.getDescripcion().trim();
            data[i][2] = String.format("%.2f",producto.getCosto()).trim();
            i++;
        }
          return data;
    }
    
    
    
    

    @Override
    public String[][] todosProducto(String filtro) {
   String filtro2 = filtro.toLowerCase();
        List<Producto>productos = ArchivoBinario.getProductos();
       
      Stream<Producto> streamFiltrado = productos.stream().filter(x-> x.getDescripcion().toLowerCase().contains(filtro2) || x.getCodigo().toLowerCase().contains(filtro2));
        
        List<Producto> productosFiltrados = streamFiltrado.collect(Collectors.toList());
        return convertirArreglo(productosFiltrados);
    }

    @Override
    public boolean agregarproducto(Producto producto) {
       // BasedatosVirtual.dbProductos.add(producto);
       boolean res = ArchivoBinario.agregarProducto(producto); //Guarda nuestro archivo.
        return res;
    }
public boolean obtenerProductoPorCodigo(String codigoAntiguo){
    return false;
}    

    @Override
    public boolean actualizarProducto(Producto producto) {
    List<Producto> listaDProductos = ArchivoBinario.getProductos(); //Con esto me traigo todos los archivos que están en mi objeto
    boolean res;
    Producto original = listaDProductos.stream().filter(x -> x.getCodigo().trim()//Lo que hace es un recorrido, por cada elemento que pase lo voy a manejar con x=producto
            .equals(producto.getCodigo())).findFirst()//El filter hace lo mismo que el if 
            .orElse(null);
    if (original !=null){
        original.setDescrpcion(producto.getDescripcion());
        original.setCosto(producto.getCosto());
        System.out.println("Aquí voy a regenerar mi archivo");
        res = ArchivoBinario.regenerarElArchivo(listaDProductos);
    }else{ 
        System.out.println("No lo encontró");
        res = false;
    }
   return res;
    }

    @Override
    public Producto buscarProtductos(String codgio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public Producto BuscarProductoVenta (String codigo){
            List<Producto> listaDatos = ArchivoBinario.getProductos(); //Con esto me traigo todos los archivos que están en mi objeto
    boolean res;
    Producto original = listaDatos.stream().filter(x -> x.getCodigo().trim()//Lo que hace es un recorrido, por cada elemento que pase lo voy a manejar con x=producto
            .equals(codigo.trim())).findFirst()//El filter hace lo mismo que el if 
            .orElse(null);
    return original;
    }
 
 
    public boolean guardarventa(VentasModelo venta){
        boolean res = ArchivoXML.guardarVenta(venta);
        return res;
    }
 
 public  int numerodeventas(){
     return ArchivoXML.cantidadVentas();
 }
 
}
