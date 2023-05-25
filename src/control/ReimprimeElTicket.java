
package control;

import Modelo.VentasModelo;
import Modelo.Ventasregistros;
import Util.ImprimirTicket;
import control.archivos.ArchivoXML;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ReimprimeElTicket {
    public void reimprimeTicket(String folio , String folios, int filaSeleccionada){
           try {
            
                
                Ventasregistros objVenta = ArchivoXML.leerXML();
        List<VentasModelo> listaFiltrada = null;

       

            listaFiltrada = objVenta.getVentas().stream()
                    .filter(v -> v.getFolio().equals(folio)).collect(Collectors.toList());
           
            VentasModelo cal = new VentasModelo();
            VentasModelo venta = listaFiltrada.get(filaSeleccionada);

            cal.setFolio(venta.getFolio());
            cal.setFecha(venta.getFecha());
            cal.setCambio(venta.getCambio());

            cal.setFecha(Calendar.getInstance().getTime());
            cal.setSubtotal(venta.getSubtotal());
            cal.setTotal(venta.getTotal());
            cal.setDetalle(venta.getDetalle());
            cal.setDescuento(venta.getDescuento());

            cal.setPago(venta.getPago());
            cal.setPorcentaje(venta.getPorcentaje());
            cal.setHora(venta.getHora());
            ImprimirTicket envio = new ImprimirTicket();
            envio.pritnTicket(venta);
        } catch (Exception e) {
             System.out.println("Sin folio para imprimir");
        }
    }
}
