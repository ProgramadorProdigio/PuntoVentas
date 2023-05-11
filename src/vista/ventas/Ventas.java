package vista.ventas;

import Modelo.OperacionesBasedatos;
import Modelo.Producto;
import Modelo.TablaDetalleregistro;

import control.Basededatos.BasedatosVirtual;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import vista.Principal;

/**
 *
 * @author Angel
 */
public class Ventas extends javax.swing.JDialog {

   
private String codigo;
    OperacionesBasedatos bd;
    private BasedatosVirtual ab;

    /**
     * Creates new form Ventas
     *
     * @param parent
     * @param modal
     */
    public Ventas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        bd = new BasedatosVirtual();
        datosentrnates(bd.todosProducto());
        ab = new BasedatosVirtual();

    
    }

    private void datosentrnates(String[][] datos) {
        String[] columnas = {"codigo", "Descripcion", "costo"};
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        jTablaBusqueda.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaBusqueda = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        ErrorFila = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(40, 116, 166));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingresa el nombre del producto");

        jTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTNombreActionPerformed(evt);
            }
        });
        jTNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTNombreKeyReleased(evt);
            }
        });

        jTablaBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablaBusqueda);

        jButton1.setText("Agregar a tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ErrorFila.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(ErrorFila, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(ErrorFila)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTNombreActionPerformed

    }//GEN-LAST:event_jTNombreActionPerformed

    private void jTNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTNombreKeyReleased
        String nombre = jTNombre.getText();
        datosentrnates(bd.todosProducto(nombre));
    }//GEN-LAST:event_jTNombreKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int filaSeleccionada = jTablaBusqueda.getSelectedRow();
        String codigoProducto = jTablaBusqueda.getValueAt(filaSeleccionada, 0).toString();
       codigo = codigoProducto;
       
        System.out.println(codigoProducto);
        this.dispose();
        
   
    }//GEN-LAST:event_jButton1ActionPerformed

    
        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorFila;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTNombre;
    private javax.swing.JTable jTablaBusqueda;
    // End of variables declaration//GEN-END:variables

    public String getCodigo() {
        return codigo;
    }
}
