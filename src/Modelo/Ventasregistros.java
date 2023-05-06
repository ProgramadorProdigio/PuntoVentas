
package Modelo;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Miguel Angel Lara Hermosillo
 * perimite la estrucutura primaria de mi XML 
 */@XmlRootElement
public class Ventasregistros {
 
    private List<VentasModelo> ventas;

    public List<VentasModelo> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentasModelo> ventas) {
        this.ventas = ventas;
    }

  
    
}
