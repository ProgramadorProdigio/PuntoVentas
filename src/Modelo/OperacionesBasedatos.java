
package Modelo;

/**
 *
 * Miguel Angel LAra Hermosillo
 */
public interface OperacionesBasedatos {
    public String [][] todosProducto();
     public String [][] todosProducto(String filtro);
     public boolean agregarproducto(Producto producto);
     public boolean actualizarProducto (Producto producto);
     public Producto buscarProtductos(String codgio);
   // public boolean existeProducto(String codigo);

}

