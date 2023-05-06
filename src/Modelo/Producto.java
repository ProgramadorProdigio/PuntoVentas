
package Modelo;

import java.io.Serializable;

/**
 *clase para alamcenar datos de un producto en particuliar
 * @author Miguel ANgel LAra Hermosillo
 */
public class Producto implements Serializable{
    private String codigo;
    private String descripcion;
    private double costo;

    @Override
    public String toString() {
        return codigo + "#" + descripcion + "#" + costo + "#";
    }
    

    public Producto(String codigo, String descrpcion, double costo) {
        this.codigo = codigo;
        this.descripcion = descrpcion;
        this.costo = costo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescrpcion(String descrpcion) {
        this.descripcion = descrpcion;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
    
    
    
    
}
