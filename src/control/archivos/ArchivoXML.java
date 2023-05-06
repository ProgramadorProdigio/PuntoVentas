package control.archivos;

import Modelo.VentasModelo;
import Modelo.Ventasregistros;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * clase para las operaciones con el archivo XML
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ArchivoXML {

    private static final String archivo = "ventas.xml";

    public static Ventasregistros leerXML() {

        File file = new File(archivo);
        Ventasregistros ventas = null;
        if (file.exists()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Ventasregistros.class);
                Unmarshaller um = jaxbContext.createUnmarshaller();
                ventas = (Ventasregistros) um.unmarshal(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ventas;
    }

    public static boolean guardarVenta(VentasModelo venta) {
        Ventasregistros ventasXml = leerXML();
        if (ventasXml == null) { // en caso de que no exista
            ventasXml = new Ventasregistros();// se crea un nuevo nodo
            ventasXml.setVentas(new ArrayList<>());

        }

        ventasXml.getVentas().add(venta);
        //Guardar XML
        File archivito = new File(archivo);
        boolean res = false;
        try {
            JAXBContext context = JAXBContext.newInstance(Ventasregistros.class);
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     marshal.marshal(ventasXml, archivito);
        
        res = true;
        
        } catch (Exception e) {
        e.printStackTrace();
        }
        return res;
    }
public static int cantidadVentas(){
    Ventasregistros venta = leerXML();
    int cantidad = venta == null?0:venta.getVentas().size();
    return cantidad;
}
}
