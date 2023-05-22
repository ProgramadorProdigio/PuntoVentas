
package control;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import vista.Principal;

/**
 *
 * @author Laboratorio
 */
public class PuntoVenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         System.out.println("Hola");
        // TODO code application logic here
   /* Create and display the form */
  
   File archivo = new File ("MiBase.txt");
     
   try {
             boolean create = archivo.createNewFile();
             if (create){
                System.out.println("No ha sido detectado ningún archivo ");
             }else{
                 System.out.println("El archivo ha sido creado" );
                     
             }        
        } catch (Exception e) {
        }
      
       
       
   
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal p = new Principal();
                //p.setLocationRelativeTo(null);
                p.setVisible(true);
             //   new Principal().setVisible(true);
            }
        });
    }
    
}
