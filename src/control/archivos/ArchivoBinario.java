package control.archivos;

import Modelo.Producto;
import static control.archivos.ArchivoTexto.getProductos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import vista.ListaProductos;

/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ArchivoBinario {

    private static final String nombreArchivo = "datos.dat";

    /**
     * Metodo que me permite recuperar la lista de los Prodictos almacenados
     *
     * @return
     */
    public static List<Producto> getProductos() {
        List<Producto> lista = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try {
                FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                lista = (ArrayList<Producto>) ois.readObject();
                ois.close();
                fis.close();

            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());

            } catch (ClassNotFoundException ex) {
                System.out.println("Error" + ex.getMessage());
            }
        }
        return lista;
    }

    /**
     * lee la lista que se tiene en el archivo y agrega un nuevo elemento, manda
     * a llama que regenere el archivo
     *
     * @param p
     * @return
     */
    public static boolean agregarProducto(Producto p) {
        boolean resultado = false;
        List<Producto> lista = getProductos();
        for (Producto ProductoExiste : lista) {
        if (ProductoExiste.getCodigo().equals(p.getCodigo())) {
            System.err.println("Ya está el producto");
            return false;
        }
    }
        lista.add(p);
        resultado = regenerarElArchivo(lista);

          return resultado;
    }
    public static boolean regenerarElArchivo(List<Producto> lista) {
        boolean resultado = false;
        File archivo = new File(nombreArchivo);

        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oss = new ObjectOutputStream(fos);
            oss.writeObject(lista);
            oss.close();
            fos.close();
            resultado = true;
            

        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return resultado;
    }
}
