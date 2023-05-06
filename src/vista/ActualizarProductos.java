/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import Modelo.OperacionesBasedatos;
import Modelo.Producto;
import control.Basededatos.BasedatosVirtual;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Laboratorio
 */
public abstract class ActualizarProductos extends javax.swing.JDialog {

    OperacionesBasedatos db;

    /**
     * Creates new form ActualizarProductos
     */
    public ActualizarProductos(java.awt.Frame parent, boolean modal) { //Estos métodos son constructores, este permite crear el objeto
        super(parent, modal); //Se necesita un parent o un modal, son obligatorios sino no hay ventanas
        initComponents(); //necesito que la que hereda le de los datos, con eso usamos el super = manda a llamar al padre
        db = new BasedatosVirtual();
    }

    public void cargarDatos(Producto p) {
        jtNombre.setText(p.getCodigo());
        jtdescripcion.setText(p.getDescripcion());
        jtCosto.setText("" + p.getCosto()); //"" porque este valor es número, esto hace que el costo se convierta en string
    }

    public void actualizarTitulo(String titulo) {
        jlTitular.setText(titulo);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTitulo = new javax.swing.JPanel();
        jlTitular = new javax.swing.JLabel();
        jtcodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jtdescripcion = new javax.swing.JTextField();
        jtCosto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlTitulo.setBackground(new java.awt.Color(0, 64, 204));
        jlTitulo.setForeground(new java.awt.Color(0, 0, 204));

        jlTitular.setBackground(new java.awt.Color(255, 255, 255));
        jlTitular.setFont(new java.awt.Font("Perpetua", 1, 24)); // NOI18N
        jlTitular.setForeground(new java.awt.Color(255, 255, 255));
        jlTitular.setText("Operacion de productos");

        javax.swing.GroupLayout jlTituloLayout = new javax.swing.GroupLayout(jlTitulo);
        jlTitulo.setLayout(jlTituloLayout);
        jlTituloLayout.setHorizontalGroup(
            jlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlTituloLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jlTitular)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        jlTituloLayout.setVerticalGroup(
            jlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jlTituloLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jlTitular)
                .addGap(22, 22, 22))
        );

        jtcodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtcodigo.setText("Código Barras:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Descripcion:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Costo:");

        jtNombre.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jtdescripcion.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtdescripcionActionPerformed(evt);
            }
        });

        jtCosto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(46, 204, 64));
        btnGuardar.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guaradar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtcodigo)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(244, 244, 244)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jtcodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //  boolean codigoExiste = false;

        try {
            //int lineaSeleccionada = tbProductos.get

            String codigo = jtNombre.getText();
            String descripcion = jtdescripcion.getText();
            double costo = Double.parseDouble(jtCosto.getText().trim());
            //  if (db.existeProducto(codigo)){
            //    JOptionPane.showMessageDialog(this, "El producto ya existe");
            //}return;

            if (codigo.isEmpty() || descripcion.isEmpty() || jtCosto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Llene los campos vacíos para guardar");
            } else {
                BasedatosVirtual db = new BasedatosVirtual();

                Producto p = new Producto(codigo, descripcion, costo);
                boolean res = procesarTarea(p); //Este método es el que guarda el producto

                if (res) {
                    String msg = res ? "Se grabó correctamente" : "Ocurrió un error";
                    JOptionPane.showMessageDialog(this, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(p);
                

                if (res) {
                    this.dispose();
                }
            }else{
                    JOptionPane.showMessageDialog(null, "Este codigo ya esta en uso");
                            
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Ingrese los datos correctos ");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed
//abstract es que puede realizar varias tareas, aunque no se sabe qué es
    //una clase abstracta es que existen ambas clases que sí dicen qué hacer y qué no.

    public abstract boolean procesarTarea(Producto p);

    public void habilitarCodigo(boolean habilita) { //Método para habilitar y deshabilitar código, es como una puertita.
        jtNombre.setEnabled(habilita);
        jtcodigo.setEnabled(habilita);
    }
    private void jtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtdescripcionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlTitular;
    private javax.swing.JPanel jlTitulo;
    private javax.swing.JTextField jtCosto;
    private javax.swing.JTextField jtNombre;
    private javax.swing.JLabel jtcodigo;
    private javax.swing.JTextField jtdescripcion;
    // End of variables declaration//GEN-END:variables
}
