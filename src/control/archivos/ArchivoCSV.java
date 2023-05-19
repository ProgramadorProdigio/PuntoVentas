package control.archivos;

import Modelo.VentasModelo;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ArchivoCSV {

    public static boolean crear(String ruta, List<VentasModelo> datos) {
        boolean res = false;

        try {
            FileWriter write = new FileWriter(ruta);
            write.append("Reporte ventas ,,\n");
            write.append("folio,fecha,Total ,,\n");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (VentasModelo venta : datos) {
                write.append(venta.getFolio())
                        .append(" ,")
                        .append(sdf.format(venta.getFecha()))
                        .append(" ,")
                        .append(String.format("$%.2f", venta.getTotal()))
                        .append("\n");

            }
            write.flush();
            write.close();
            res = true;
        } catch (Exception e) {
      e.printStackTrace();
      res = false;
        }
        return res;
      
    }
}
