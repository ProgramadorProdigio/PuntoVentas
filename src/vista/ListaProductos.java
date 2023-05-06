package vista;

import javax.swing.JOptionPane;
import Modelo.OperacionesBasedatos;
import Modelo.Producto;
import java.awt.Frame;
import javax.swing.table.DefaultTableModel;
import control.Basededatos.BasedatosVirtual;
import control.ControlProductos;
import control.archivos.ArchivoBinario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Para no hacer dos ventanas, solo se hará 2 agregaProducto
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class ListaProductos extends javax.swing.JDialog {

//Frame parent;
    OperacionesBasedatos bd;

    public ListaProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //this.parent = parent;
        initComponents();
        bd = new BasedatosVirtual();
        cargaGrid(bd.todosProducto());
    }

    private void cargaGrid(String[][] datos) {
        String[] columnas = {"codigo", "Descripcion", "costo"};
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tbProductos.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTituloVentana = new javax.swing.JPanel();
        jLMostrarProductos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnagregar = new javax.swing.JButton();
        bteditar = new javax.swing.JButton();
        jtFiltro = new javax.swing.JTextField();
        btnfiltro = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlTituloVentana.setBackground(new java.awt.Color(0, 64, 204));

        jLMostrarProductos.setFont(new java.awt.Font("Felix Titling", 1, 18)); // NOI18N
        jLMostrarProductos.setForeground(new java.awt.Color(255, 255, 255));
        jLMostrarProductos.setText("Productos en venta");

        javax.swing.GroupLayout jlTituloVentanaLayout = new javax.swing.GroupLayout(jlTituloVentana);
        jlTituloVentana.setLayout(jlTituloVentanaLayout);
        jlTituloVentanaLayout.setHorizontalGroup(
            jlTituloVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jlTituloVentanaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLMostrarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        jlTituloVentanaLayout.setVerticalGroup(
            jlTituloVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlTituloVentanaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLMostrarProductos)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbProductos);

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        bteditar.setText("Editar");
        bteditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditarActionPerformed(evt);
            }
        });

        jtFiltro.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jtFiltro.setCaretColor(new java.awt.Color(255, 102, 0));
        jtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtFiltroActionPerformed(evt);
            }
        });

        btnfiltro.setText("Buscar");
        btnfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfiltroActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlTituloVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfiltro)
                    .addComponent(btnagregar)
                    .addComponent(btnEliminar)
                    .addComponent(bteditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTituloVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnfiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnagregar)
                        .addGap(40, 40, 40)
                        .addComponent(bteditar)
                        .addGap(35, 35, 35)
                        .addComponent(btnEliminar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        ActualizarProductos act = new AgregaProducto((Frame) this.getParent());
        act.setLocationRelativeTo(this);
        act.setVisible(true);
        // this.dispose();// cerrar el panel
        cargaGrid(bd.todosProducto());
    }//GEN-LAST:event_btnagregarActionPerformed

    private void bteditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditarActionPerformed
        // Obtener código de producto seleccionado 
        //  int codigoProducto = tbProductos;
        //JOptionPane.showMessageDialog(this, "Código del producto seleccionado: " + codigoProducto);
        int filaSeleccionada = tbProductos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar ");
        } else {
            String codigoProducto = tbProductos.getValueAt(filaSeleccionada, 0).toString();
            String descripcion = tbProductos.getValueAt(filaSeleccionada, 1).toString();
            String costo = tbProductos.getValueAt(filaSeleccionada, 2).toString();

            //JOptionPane.showMessageDialog(this,"Código: " +  codigoProducto + "\nDescripción: "
            //      + descripcion + "\nCosto: $" + costo);  //\n para hacer salto de línea
            double costoNumerico = Double.parseDouble(costo);
            Producto p = new Producto(codigoProducto, descripcion, costoNumerico);
            EditarProducto ed = new EditarProducto((Frame) getParent(), p);
            ed.setLocationRelativeTo(this); //Hacer que se posicione al centro
            ed.setVisible(true);
            cargaGrid(bd.todosProducto());

        }
        //if (tbProductos.getSelectionModel().isSelectionEmpty()) {
        //JOptionPane.showMessageDialog(null, "Por favor seleccione un producto de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
//} else {

        //try{
        //  String editar = bteditar.getText();
        // String mensaje = "Seleccione un producto";
        // if (editar == null){
        //     JOptionPane.showMessageDialog(this, mensaje, JOptionPane.INFORMATION_MESSAGE);
        // }
        // }catch(){
        //}
    }//GEN-LAST:event_bteditarActionPerformed

    private void jtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtFiltroActionPerformed

    }//GEN-LAST:event_jtFiltroActionPerformed

    private void btnfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfiltroActionPerformed
        String filto = jtFiltro.getText();
        cargaGrid(bd.todosProducto(filto));

    }//GEN-LAST:event_btnfiltroActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ControlProductos cal = new ControlProductos();
        int filaSeleccionada = tbProductos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para Eliminar ");
        } else {

            int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esto?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                // Código para eliminar el elemento

                // con esto se puede acceder ala lista
                List<Producto> listaProductos = ArchivoBinario.getProductos();
                /**
                 * Con la siguiente condicion se actualiza la tabla
                 */

                boolean eliminado = cal.Eliminar(filaSeleccionada);
                if (eliminado) {
                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("Codigo");
                    modelo.addColumn("Descripcion");
                    modelo.addColumn("Precio");

                    for (Producto producto : listaProductos) {
                        Object[] fila = new Object[3];
                        fila[0] = producto.getCodigo();
                        fila[1] = producto.getDescripcion();
                        fila[2] = producto.getCosto();
                        modelo.addRow(fila);
                    }

                    tbProductos.setModel(modelo);
                    JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
                }

            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bteditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnfiltro;
    private javax.swing.JLabel jLMostrarProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jlTituloVentana;
    private javax.swing.JTextField jtFiltro;
    public javax.swing.JTable tbProductos;
    // End of variables declaration//GEN-END:variables
}
