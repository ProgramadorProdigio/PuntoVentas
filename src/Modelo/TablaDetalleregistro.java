package Modelo;

/**
 *
 * @author Angel
 */
public class TablaDetalleregistro {

    String codigo;
    String descripcion;
    int cantidad;
    double precio;
    double total;
    double totaldetotal;
    double descuento;
    double cambio;
    double pagos;

    public double getPagos() {
        return pagos;
    }

    public void setPagos(double pagos) {
        this.pagos = pagos;
    }
    public double getTotaldetotal() {
        return totaldetotal;
    }

    public void setTotaldetotal(double totaldetotal) {
        this.totaldetotal = totaldetotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public double getTotal() {
        return precio * cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setTotal(double precio) {
        this.total = this.cantidad * this.precio;
    }

}
