/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.archivos;

import Modelo.Producto;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase que me permite controlar las operaciones básicas de los archivos en este caso de texto 
 * @author regin
 */
public class ArchivoTexto {
    private static final String nombreArchivo = "MiBaseDatos.txt";
    private static HashSet<String> codigosProductos = new HashSet<>();
    public static List<Producto> getProductos (){
        List<Producto> lista = new ArrayList<>(); //<> Genérico, una lista de puros productos
        
        File archivo = new File (nombreArchivo);
        
        try {
            FileReader fr = new FileReader (archivo);
            Scanner sc = new Scanner(fr);
            sc.useDelimiter("#");
            while (sc.hasNext()){
                String codigo = sc.next();
                if (codigo.trim() .isEmpty()) //trim-Todos los espacios en blanco antes o después, los limpia
                    break;
                String descripcion = sc.next();
                String costoStr = sc.next();
                double costo = Double.parseDouble (costoStr);
                
                Producto p = new Producto (codigo, descripcion, costo); //Crear constructor para hacer el objeto más rápidp
                lista.add(p);
                System.out.println(p); //Para ver que esté leyendo
            }
            
      } catch (IOException e) {
       //     System.out.println("Error " + e.getMessage());
         //   lista = new ArrayList<>();
        }
            
        
        return lista;
    
        
    }
    //Método que permite grabar en el archivo los datos de los productos
    //param p datos del prodcuto
    //returno verdadero si se grabó correctamente.
    //New para mandar llamar al método
    //Cuando no se quier poner el new es ponerlas como static, eje: nombreclase.objeto
    public static boolean agregarProducto (Producto p){
        String dato = p.toString();
        boolean resultado = false;
        File archivo = new File(nombreArchivo);
      
        List <Producto> productos = getProductos();
        for(Producto ProductoExiste : productos){
            if (ProductoExiste.getCodigo().equals(p.getCodigo())) {
                System.err.println("Ya esta el producto");
                return resultado = false;
            }
        }
        try {
     
            FileWriter fw = new FileWriter (archivo, true); //Va a gregar el archivo al que ya existe, false es que lo va a generar again
       PrintWriter pw =new PrintWriter (fw); //PW es el ejecutor, FW le dice donde lo va a guardar 
       pw.println(dato); //Lo va a escribir en el archivo, es como sout en la consola 
       pw.flush();
       pw.close();
       fw.close();
       
       // Actualizar el HashSet con el nuevo código de producto
        codigosProductos.add(p.getCodigo());
        
       resultado = true;
        } catch (IOException ex) {
            System.out.println("Algo pasó " + ex.getMessage());
            resultado = false; 
        }
        
        return resultado;
        
    }

    public static boolean regenerarElArchivo(List<Producto> listaDatos) {
       boolean resultado = false; 
       File archivo = new File(nombreArchivo);
       try{
           FileWriter fw = new FileWriter(archivo,false);
           PrintWriter pw = new PrintWriter(fw);
           for(Producto p: listaDatos){
               pw.println(p);
           }
           pw.flush();
           pw.close();
           fw.close();
           resultado = true;
       }catch(IOException e){
           System.out.println("No se pudo regenerar ");
           resultado = false;
       }
        return resultado;
    }
}
