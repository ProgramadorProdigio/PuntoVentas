package vista.reporte;

import Modelo.TablaDetalleregistro;
import Modelo.VentasModelo;
import Modelo.Ventasregistros;
import Util.ImprimirTicket;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import control.ReimprimeElTicket;
import control.archivos.ArchivoCSV;
import control.archivos.ArchivoXML;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.stream.Collectors;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Miguel Angel lara Hermosillo
 */
public class ReporteForm extends javax.swing.JDialog {

    String folio = null;

    private List<VentasModelo> ListaMostrada;

    public ReporteForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Ventasregistros objVenta = ArchivoXML.leerXML();
        //  acomodarGrid(objVenta.getVentas());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date inicialFecha = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date finalFecha = calendar.getTime();
        filtrarDatos(inicialFecha, finalFecha, folio);
        TxtBuscar.setText("Ingrese el folio a buscar");
        TxtBuscar.setForeground(Color.GRAY);
    }
    //  metodo que permite filtrar la tabla por medio del folio

    // Metodo que permite modificar de acuerdo al folio sobre el mismo dia y las fechas para buscar todas las ventas dentro de un rango
    private void filtrarDatos(Date inicialFecha, Date finalFecha, String folio) {
        Ventasregistros objVenta = ArchivoXML.leerXML();
        List<VentasModelo> listaFiltrada;

        if (folio != null && !folio.isEmpty() && !"0".equals(folio)) {

            listaFiltrada = objVenta.getVentas().stream()
                    .filter(v -> v.getFolio().equals(folio)).collect(Collectors.toList());

            acomodarGrid(listaFiltrada);
        } else {
            listaFiltrada = objVenta.getVentas().stream()
                    .filter(v -> v.getFecha().after(inicialFecha) && v.getFecha().before(finalFecha))
                    .collect(Collectors.toList());
        }

        acomodarGrid(listaFiltrada);
    }

    public void acomodarGrid(List< VentasModelo> listaDatos) {
        ListaMostrada = listaDatos;
        String[][] datos = extraerDatos(listaDatos);
        String[] columnas = {"Folio", "Fecha", "Hora Registro", "Total"};
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tbDatos.setModel(modelo);
        // Datos formateado
        DefaultCategoryDataset datosgraficos = extraerDatosGrafivos(listaDatos);
        // crear el grafico 
        crearGrafico(datosgraficos);

        //ponerlo en el panel
    }

    private DefaultCategoryDataset extraerDatosGrafivos(List<VentasModelo> lista) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HashMap<String, Double> mapa = new HashMap<>();
        for (VentasModelo venta : lista) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(venta.getFecha());
            int dia = calendar.get(Calendar.DAY_OF_WEEK);
            double total = venta.getTotal();
            String llevedia = numeroDia(dia);
            double loqueseLleva = 0;
            if (mapa.containsKey(llevedia)) {
                loqueseLleva = mapa.get(llevedia);

            }
            mapa.put(llevedia, total + loqueseLleva);
        }
        for (var entrada : mapa.entrySet()) {
            String llave = entrada.getKey();
            double valor = entrada.getValue();
            dataset.addValue(valor, "Ventas dias", llave);
        }
        return dataset;
    }

    private String numeroDia(int dia) {
        String dialetra = "";
        switch (dia) {
            case 1:
                dialetra
                        = "DOM";
                break;
            case 2:
                dialetra
                        = "LUN";
                break;
            case 3:
                dialetra
                        = "MAR";
                break;
            case 4:
                dialetra
                        = "MIE";
                break;
            case 5:
                dialetra
                        = "JUE";
                break;
            case 6:
                dialetra
                        = "VIE";
                break;

            case 7:
                dialetra
                        = "SAB";
                break;

        }

        return dialetra;
    }

    private void crearGrafico(DefaultCategoryDataset datos) {
        JFreeChart grafico = ChartFactory.createBarChart("ventas tienda", "Dias", "total", datos);
        ChartPanel panel = new ChartPanel(grafico);
        panel.setPreferredSize(jpanelGrafico.getSize());
        // Obtener el objeto CategoryPlot del gráfico
        CategoryPlot plot = grafico.getCategoryPlot();

        // Cambiar el color de las barras
        Color colorBarra = new Color(0, 128, 255); // Azul
        plot.getRenderer().setSeriesPaint(0, colorBarra);
// Personalizar otras características del gráfico
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setInsets(new RectangleInsets(10, 10, 10, 10));

        jpanelGrafico.removeAll();
        jpanelGrafico.add(panel);
        jpanelGrafico.updateUI();
        jpanelGrafico.repaint();
    }

    private String[][] extraerDatos(List<VentasModelo> lista) {
        String[][] arreglodatos = new String[lista.size()][4];
        int i = 0;
        for (VentasModelo venta : lista) {
            arreglodatos[i][0] = venta.getFolio();
            arreglodatos[i][1] = fomatarFecha(venta.getFecha());
            arreglodatos[i][2] = venta.getHora();
            arreglodatos[i][3] = String.format("$%.2f", venta.getTotal());
            i++;
        }

        return arreglodatos;

    }

    private String fomatarFecha(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lia = new javax.swing.JLabel();
        txtFinal = new javax.swing.JFormattedTextField();
        TxtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        jpanelGrafico = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        txtInicial = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 116, 217));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reporte de venta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lia.setText("Fecha Final");

        try {
            txtFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        TxtBuscar.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        TxtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TxtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TxtBuscarFocusLost(evt);
            }
        });
        TxtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtBuscarActionPerformed(evt);
            }
        });

        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbDatos);

        jButton2.setText("Reimprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        try {
            txtInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("Fecha Inicial");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(112, 112, 112)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addComponent(btnActualizar))))
                    .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jButton2)
                                .addGap(119, 119, 119)
                                .addComponent(btnExportar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpanelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addGap(53, 53, 53)
                        .addComponent(TxtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jpanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnExportar))
                        .addGap(63, 63, 63))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar archivo CSV");
        chooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV", "csv"));

        int respuesta = chooser.showSaveDialog(this);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().getPath();
            ruta = !ruta.toLowerCase().endsWith(".csv")
                    ? ruta + ".csv" : ruta;
            boolean res = ArchivoCSV.crear(ruta, ListaMostrada);
            if (res) {
                JOptionPane.showMessageDialog(this, "Se exporto");
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String fechaInicial = txtInicial.getText();
        String fechaFinal = txtFinal.getText();

        if (fechaInicial == null && fechaInicial.isEmpty() && fechaFinal == null && fechaFinal.isEmpty()) {
            System.out.println("NO se hizo nada ");
        } else {

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = null;
            Date date2 = null;
            Date fechaSumada = null;
            folio = null;
            try {
                date1 = dateFormat.parse(fechaInicial);
                date2 = dateFormat.parse(fechaFinal);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date2);
                calendar.add(Calendar.DAY_OF_MONTH, +1);
                fechaSumada = calendar.getTime();
                System.out.println("Hola");
                filtrarDatos(date1, fechaSumada, folio);
                txtInicial.setText("");
                txtFinal.setText("");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void TxtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtBuscarActionPerformed

        String folio = TxtBuscar.getText();
        this.folio = folio;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date inicialFecha = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date finalFecha = calendar.getTime();
        filtrarDatos(inicialFecha, finalFecha, folio);
        TxtBuscar.setText("");
        txtInicial.setText("");
        txtFinal.setText("");
    }//GEN-LAST:event_TxtBuscarActionPerformed

    private void TxtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtBuscarFocusLost
        // se borre cuando se pierda la ubicacion del puntero
        TxtBuscar.setText("");
        TxtBuscar.setText("Ingrese el folio a buscar");
        TxtBuscar.setForeground(Color.GRAY);

    }//GEN-LAST:event_TxtBuscarFocusLost

    private void TxtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TxtBuscarFocusGained
        if (TxtBuscar.getText().equals("Ingrese el folio a buscar")) {
            TxtBuscar.setText("");
            TxtBuscar.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_TxtBuscarFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        
        
       
        int filaSeleccionada = tbDatos.getSelectedRow();
        String folios = tbDatos.getValueAt(filaSeleccionada, 0).toString();
        
      ReimprimeElTicket envio = new ReimprimeElTicket();
      envio.reimprimeTicket(folio, folios, filaSeleccionada);

       

           
        
    }//GEN-LAST:event_jButton2ActionPerformed

// metodo que me permite imprimir el tikcet de nueva cuenta
    // sin ayuda de chatGPT xd
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtBuscar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanelGrafico;
    private javax.swing.JLabel lia;
    private javax.swing.JTable tbDatos;
    private javax.swing.JFormattedTextField txtFinal;
    private javax.swing.JFormattedTextField txtInicial;
    // End of variables declaration//GEN-END:variables

    private static class collector {

        public collector() {
        }
    }
}
