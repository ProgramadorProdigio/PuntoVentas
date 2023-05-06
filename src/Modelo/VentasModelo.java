
package Modelo;

import java.util.Date;
import java.util.List;

/**
 * ventasModelo
 * @author Miguel Angel Lara Hermosillo
 */
public class VentasModelo{
    private String folio;
    private Date fecha;
    private double subtotal;
    private double descuento;
    private double total;
private List <TablaDetalleregistro> detalle;

    public List<TablaDetalleregistro> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<TablaDetalleregistro> detalle) {
        this.detalle = detalle;
    }
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
