package vista;

import Modelo.OperacionesBasedatos;
import Modelo.Producto;
import Modelo.TablaDetalleregistro;
import Modelo.VentasModelo;
import Modelo.Ventasregistros;
import Util.ImprimirTicket;
import control.Basededatos.BasedatosVirtual;
import control.archivos.ArchivoBinario;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import vista.ventas.Ventas;

/**
 *
 * @author Miguel Angel Lara Hermosillo
 */
public class Principal extends javax.swing.JFrame {

    public List<TablaDetalleregistro> detalle;
    private BasedatosVirtual ab;
    private int folio;// se alamcena el folio o numero de ticket
    private double total; // alamacena el total de toda la venta
    private Clientes clientes; // variable miembro de la clase para mantener la instancia de la clase Clientes
    private JFrame frame; // variable miembro de la clase para mantener la instancia del JFrame
    public double sub; // almacena el subtotal de la venata
    private int descuento; // Almacena el descuento de compra
    private double pagos; // varaiable que almacena el monto ingresado
    private double cambio; // varaible que almacena el cambio
     public String codigo;

    /**
     * Creates new form Principal
     */
    public Principal() {

        initComponents();
        this.setLocationRelativeTo(null);
        clientes = new Clientes(); // crea una instancia de la clase Clientes
        frame = new JFrame(); // crea una nueva ventana JFrame
        frame.setContentPane(clientes); // agrega el panel Clientes al JFrame
        frame.setUndecorated(true); // hace que la ventana no tenga decoración (barra de título, botones de cerrar, minimizar, etc.)
        frame.pack(); // ajusta el tamaño del JFrame al tamaño del panel
        frame.setLocationRelativeTo(null); // centra la ventana en la pantalla
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // evita que se cierre la ventana al presionar el botón de cerrar
        frame.setResizable(false); // hace que la ventana no sea redimensionable
        detalle = new ArrayList<>();
        actualizarVenta();
        jLFecha.setText(getFecha());
        ab = new BasedatosVirtual();
        folio = ab.numerodeventas() + 1;
        jLTicket.setText("" + folio);
        String foli = String.format("%05d", this.folio);
        jLTicket.setText(foli);
        jtModificarCantidad.setText("Ingresa cantidad");
        jtModificarCantidad.setForeground(Color.GRAY);
        // Crea un nuevo CardLayout y un JPanel que servirá como contenedor

    }

    private TablaDetalleregistro buscarRegistro(String codigoProducto) {
        for (TablaDetalleregistro registro : detalle) {
            if (registro.getCodigo().equals(codigoProducto)) {
                return registro;
            }
        }
        return null;
    }

    public double actualizarVenta() {

        String[] colums = {"Cantidad", "Descripcion", "Precio", "Total"};
        String[][] datos = extraerDatos();

        DefaultTableModel model = new DefaultTableModel(datos, colums);
        jtablaVentas.setModel(model);

        // Calcular el precio total de la venta
        double subtotal = 0;

        for (TablaDetalleregistro registro : detalle) {
            subtotal += registro.getTotal();

        }

        jLSUB.setText(String.format("%.2f", subtotal));
        jLTotal.setText(String.format("%.2f", subtotal));

        return subtotal;
    }

    private String[][] extraerDatos() {
        String[][] datos = new String[detalle.size()][4]; // Inicializa una matriz con el tamaño de la lista detalle y con 4 columnas.
        int i = 0; // Inicializa el índice del arreglo en cero.
        double subtotal = 0; // Inicializa el total de la venta en cero.
        for (TablaDetalleregistro registro : detalle) { // Recorre la lista detalle.
            datos[i][0] = "" + registro.getCantidad(); // Agrega la cantidad a la columna 0 de la fila actual.
            datos[i][1] = registro.getDescripcion(); // Agrega la descripción a la columna 2 de la fila actual.
            datos[i][2] = String.format("%.2f", registro.getPrecio()); // Agrega el precio con formato a la columna 2 de la fila actual.
            datos[i][3] = String.format("%.2f", registro.getTotal()); // Agrega el total con formato a la columna 3 de la fila actual.

            i++; // Incrementa el índice del arreglo.
            subtotal += registro.getTotal(); // Agrega el total de la fila actual al total de la venta.

        }
        total = subtotal;
        jLSUB.setText(String.format("%.2f", subtotal)); // Actualiza el valor del subtotal en la etiqueta jLSUB.
        jLTotal.setText(String.format("%.2f", total));

        sub = subtotal;
        return datos; // Retorna la matriz con los datos extraídos.
    }

    private String getFecha() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        return sdf.format(calendar.getTime());
    }

    // Metofo que permite saber la hora actual
    public static String getHoraActual() {
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        String horaFormateada = horaActual.format(formatter);

        return horaFormateada;

    }

    OperacionesBasedatos bd;

    /**
     *
     * @param parent
     * @param modal
     */
    /**
     *
     * @param parent
     * @param modal
     *
     */
    private void cargaGrid(String[][] datos) {
        String[] columnas = {"codigo", "Descripcion", "costo"};
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        jtablaVentas.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navegacionPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jtbusqueda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtablaVentas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        vtrProductos = new javax.swing.JButton();
        btnRegistroVentas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnGuardarcompra = new javax.swing.JButton();
        btnVacairLista = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jldecuento = new javax.swing.JLabel();
        jlTotal = new javax.swing.JLabel();
        lp = new javax.swing.JLabel();
        cam = new javax.swing.JLabel();
        jLSUB = new javax.swing.JLabel();
        jLTotal = new javax.swing.JLabel();
        jLCambio = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLTicket = new javax.swing.JLabel();
        jLFecha = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        jLDescuento = new javax.swing.JTextField();
        JLPago = new javax.swing.JTextField();
        Error1 = new javax.swing.JLabel();
        Error2 = new javax.swing.JLabel();
        Error4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jtModificarCantidad = new javax.swing.JTextField();
        ErrorCantidad = new javax.swing.JLabel();
        btnBuscarnombre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Centrado");
        setMaximumSize(new java.awt.Dimension(2000, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navegacionPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel3.setText("Productos");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jtbusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(105, 105, 105)
                .addComponent(btnBuscar)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar)
                            .addComponent(jtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jtablaVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtablaVentas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 116, 217));
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Punto de venta");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de Lugar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(392, 392, 392)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 116, 217));

        vtrProductos.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        vtrProductos.setText("Agregar Productos");
        vtrProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vtrProductosActionPerformed(evt);
            }
        });

        btnRegistroVentas.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnRegistroVentas.setText("Registros");
        btnRegistroVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroVentasActionPerformed(evt);
            }
        });

        btnClientes.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vtrProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnClientes)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnRegistroVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(vtrProductos)
                .addGap(61, 61, 61)
                .addComponent(btnRegistroVentas)
                .addGap(68, 68, 68)
                .addComponent(btnClientes)
                .addContainerGap(558, Short.MAX_VALUE))
        );

        btnGuardarcompra.setBackground(new java.awt.Color(46, 204, 64));
        btnGuardarcompra.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnGuardarcompra.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarcompra.setText("Guardar Venta");
        btnGuardarcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarcompraActionPerformed(evt);
            }
        });

        btnVacairLista.setBackground(new java.awt.Color(255, 51, 51));
        btnVacairLista.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnVacairLista.setForeground(new java.awt.Color(255, 255, 255));
        btnVacairLista.setText("Vaciar lisita");
        btnVacairLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVacairListaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel5.setText("Subtotal:");

        jldecuento.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jldecuento.setText("Descuentos %");

        jlTotal.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jlTotal.setText("Total:");

        lp.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        lp.setText("Pagó:");

        cam.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        cam.setText("Cambio:");

        jLSUB.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        jLTotal.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        jLCambio.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(0, 116, 217));

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cobrar venta");

        jLTicket.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLTicket.setForeground(new java.awt.Color(255, 255, 255));
        jLTicket.setText("Tictet #001");

        jLFecha.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLFecha.setForeground(new java.awt.Color(255, 255, 255));
        jLFecha.setText("01-06-2333");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(121, 121, 121))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLFecha)
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLTicket)
                    .addComponent(jLFecha))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnCobrar.setBackground(new java.awt.Color(46, 204, 64));
        btnCobrar.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        btnCobrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        jLDescuento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jLDescuentoFocusLost(evt);
            }
        });
        jLDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLDescuentoActionPerformed(evt);
            }
        });
        jLDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLDescuentoKeyReleased(evt);
            }
        });

        JLPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JLPagoFocusLost(evt);
            }
        });
        JLPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JLPagoActionPerformed(evt);
            }
        });
        JLPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JLPagoKeyReleased(evt);
            }
        });

        Error1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        Error1.setForeground(new java.awt.Color(255, 51, 51));

        Error2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        Error2.setForeground(new java.awt.Color(255, 51, 51));

        Error4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Error4.setText("s");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jldecuento)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lp)
                                .addComponent(jlTotal)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLPago, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLSUB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(Error1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(Error2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Error4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLSUB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jldecuento)
                    .addComponent(jLDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Error1)
                .addGap(61, 61, 61)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlTotal)
                    .addComponent(jLTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp)
                    .addComponent(JLPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Error2)
                .addGap(37, 37, 37)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Error4)
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cam)
                    .addComponent(jLCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imag/icons8-trash-can-48.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel12.setText("Borrar Producto");

        jtModificarCantidad.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        jtModificarCantidad.setForeground(new java.awt.Color(204, 204, 204));
        jtModificarCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtModificarCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtModificarCantidadFocusLost(evt);
            }
        });
        jtModificarCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtModificarCantidadActionPerformed(evt);
            }
        });

        ErrorCantidad.setFont(new java.awt.Font("Serif", 0, 10)); // NOI18N
        ErrorCantidad.setForeground(new java.awt.Color(255, 0, 0));
        ErrorCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ErrorCantidadFocusLost(evt);
            }
        });

        btnBuscarnombre.setText("Buscar por nombre");
        btnBuscarnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarnombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navegacionPanelLayout = new javax.swing.GroupLayout(navegacionPanel);
        navegacionPanel.setLayout(navegacionPanelLayout);
        navegacionPanelLayout.setHorizontalGroup(
            navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navegacionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navegacionPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(78, 78, 78))
                            .addGroup(navegacionPanelLayout.createSequentialGroup()
                                .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, navegacionPanelLayout.createSequentialGroup()
                                        .addGap(190, 190, 190)
                                        .addComponent(btnGuardarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVacairLista, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(118, 118, 118))
                                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, navegacionPanelLayout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(navegacionPanelLayout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                                                        .addComponent(jtModificarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(159, 159, 159)
                                                        .addComponent(btnBuscarnombre))
                                                    .addComponent(ErrorCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnEliminar)
                                                .addGap(80, 80, 80))
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        navegacionPanelLayout.setVerticalGroup(
            navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navegacionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addGroup(navegacionPanelLayout.createSequentialGroup()
                                .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtModificarCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarnombre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ErrorCantidad)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(100, 100, 100)
                        .addGroup(navegacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVacairLista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(navegacionPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(927, Short.MAX_VALUE))
        );

        getContentPane().add(navegacionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, 1880));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVacairListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVacairListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVacairListaActionPerformed

    private void btnGuardarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarcompraActionPerformed

        /**
         * System.out.println("Boton funciona "); if (!frame.isVisible()) { //
         * verifica si el JFrame no está visible frame.setVisible(true); // hace
         * visible el JFrame }
         */
    }//GEN-LAST:event_btnGuardarcompraActionPerformed

    private void btnRegistroVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroVentasActionPerformed

    }//GEN-LAST:event_btnRegistroVentasActionPerformed

    private void vtrProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vtrProductosActionPerformed
        System.out.println("sI SIRVE");
        ListaProductos vlista = new ListaProductos(this, true);
        vlista.setLocationRelativeTo(this);
        vlista.setVisible(true);
    }//GEN-LAST:event_vtrProductosActionPerformed

    private void jtbusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbusquedaActionPerformed
        hacerBusqueda();
    }//GEN-LAST:event_jtbusquedaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        hacerBusqueda();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        // Obtener la fila seleccionada en la tabla
        int filaSeleccionada = jtablaVentas.getSelectedRow();
        if (filaSeleccionada == -1) { // Si no se ha seleccionado ninguna fila, mostrar un mensaje de error.
            JOptionPane.showMessageDialog(this, "Seleccione un producto para Eliminar ");
        } else {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar esto?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) { // Si el usuario confirma que quiere eliminar el producto
                // Buscar el registro correspondiente y eliminarlo de la lista detalle
                String codigoProducto = (String) jtablaVentas.getValueAt(filaSeleccionada, 2); // Obtener el código de producto de la fila seleccionada
                TablaDetalleregistro registroAEliminar = buscarRegistro(codigoProducto); // Buscar el registro correspondiente a ese código de producto
                detalle.remove(registroAEliminar);
                detalle.remove(filaSeleccionada); // Eliminar el registro de la lista detalle
                // Actualizar la tabla de ventas
                jLDescuento.setText(" ");
                JLPago.setText(" ");
                actualizarVenta();
                System.out.println("Producto eliminado exitosamente");
            } else { // Si el usuario cancela la operación
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
            }
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jLDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLDescuentoActionPerformed

        hacerDescuento();

    }//GEN-LAST:event_jLDescuentoActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        if (jtablaVentas.getRowCount() == 0) {
            System.out.println("La tabla está vacía");
            JOptionPane.showMessageDialog(null, "Lo sentimos no se puede realizar el cobro intente de nuevo");
        } else {
            if (pagos < total) {
                Error4.setText("pago insuficiente");
                Error4.setVisible(true);
            }else{
            VentasModelo venta = new VentasModelo();
            venta.setFolio("" + folio);
            venta.setFecha(Calendar.getInstance().getTime());
            venta.setSubtotal(sub);
            venta.setTotal(total);
            venta.setDetalle(detalle);
            venta.setDescuento(descuento);
            venta.setCambio(cambio);
            venta.setPago(pagos);

            if (ab.guardarventa(venta)) {
                System.out.println("Imprimiendo ");
                ImprimirTicket imprimir = new ImprimirTicket();
                imprimir.pritnTicket(venta);

                folio++;
                jLTicket.setText("" + folio);
                detalle = new ArrayList<>();
                String folio = String.format("%05d", this.folio);
                jLTicket.setText(folio);

                actualizarVenta();

            } else {
                JOptionPane.showMessageDialog(this, "Ocurrio un error");
            }

        }
        }

    }//GEN-LAST:event_btnCobrarActionPerformed
// cada ves que se ingresa un caracter desde el teclado automaticamente se ejecuta
    private void jLDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLDescuentoKeyReleased
        hacerDescuento();
    }//GEN-LAST:event_jLDescuentoKeyReleased
// cada ves que se ingresa un caracter desde el teclado automaticamente se ejecuta
    private void JLPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JLPagoKeyReleased
        pago();
    }//GEN-LAST:event_JLPagoKeyReleased
//FocusGained es un evento importante en Java que permite a las aplicaciones detectar cuándo un componente ha ganado el enfoque del usuario y tomar medidas en consecuencia.
    private void jtModificarCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtModificarCantidadFocusGained

        if (jtModificarCantidad.getText().equals("Ingresa cantidad")) {
            jtModificarCantidad.setText("");
            jtModificarCantidad.setForeground(Color.BLACK);
        }


    }//GEN-LAST:event_jtModificarCantidadFocusGained


    private void jtModificarCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtModificarCantidadActionPerformed
        cantidad();

    }//GEN-LAST:event_jtModificarCantidadActionPerformed
// cada ves que se seleccione el apartado y despues se seleccione aprarecera los mensajes de abajo
    private void jtModificarCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtModificarCantidadFocusLost
        jtModificarCantidad.setText("Ingresa cantidad");
        jtModificarCantidad.setForeground(Color.GRAY);
        ErrorCantidad.setText(" ");
        ErrorCantidad.setVisible(true);
    }//GEN-LAST:event_jtModificarCantidadFocusLost

    private void ErrorCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ErrorCantidadFocusLost

    }//GEN-LAST:event_ErrorCantidadFocusLost

    private void JLPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JLPagoActionPerformed

    }//GEN-LAST:event_JLPagoActionPerformed

    private void jLDescuentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jLDescuentoFocusLost
        Error1.setText("");
        Error1.setVisible(true);
    }//GEN-LAST:event_jLDescuentoFocusLost

    private void JLPagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JLPagoFocusLost
        Error2.setText("");
        Error2.setVisible(true);
    }//GEN-LAST:event_JLPagoFocusLost

    private void btnBuscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarnombreActionPerformed
        System.out.println("FUNCIONA");
        Ventas ventanas = new Ventas(this, true);
        ventanas.setLocationRelativeTo(this);
        ventanas.setResizable(false);
        ventanas.setVisible(true);
        
   //recuperar codigo
   
   String codigo = ventanas.getCodigo();
        if (codigo != null) {
            System.out.println("Si llego"+codigo);
   hacerBusqueda2(codigo);
        }
        System.out.println("no paso nada");

    }//GEN-LAST:event_btnBuscarnombreActionPerformed

  
    
    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClientesActionPerformed

    // metodo para realizar el decuento
    private double hacerDescuento() {

        double menosdecuento = 0;

// obetener lo del txtfield
        String txtfiel = jLDescuento.getText();
        try {

            if (txtfiel.isEmpty()) {
                Error1.setText("El valor del descuento no puede estar vacío");
                Error1.setVisible(true);
            } else {
                //comvertimos 

                int descuento = (int) Double.parseDouble(txtfiel);
                this.descuento = descuento;

                double subtotal = 0;

                for (TablaDetalleregistro registro : detalle) {
                    subtotal += registro.getTotal();

                }
// Calcular el descuento
                double porcentajeDescuento = descuento;
                double descuento1 = subtotal * porcentajeDescuento / 100.0;
                menosdecuento = subtotal - descuento1;
                total = menosdecuento;
                jLTotal.setText(String.format("%.2f", menosdecuento));
                System.out.println("Funciona el decuento");
                Error1.setText("");
                Error1.setVisible(true);
            }

        } catch (Exception e) {
            Error1.setText("solo ingrese numeros pocitivos");
            Error1.setVisible(true);

        }
        return menosdecuento;
    }

    // codido donde se hace el pago y se regresa el cambio
    public double pago() {

        double cambio = 0;
// obetener lo del txtfield
        String importeapagar = JLPago.getText();
        String totalfull = jLTotal.getText();
        try {
            // verifica que no este vacio el pago
            if (importeapagar.isEmpty()) {
                Error2.setText("El valor del pago no puede estar vacío");
                Error1.setVisible(true);
            } else {

                double total = Double.parseDouble(totalfull);
                double pago = Double.parseDouble(importeapagar);
                pagos = pago;

// verifica si el total es meno que el pago
                if (pago < total) {
                    Error2.setText("Insuficiente");
                    Error2.setVisible(true);
                } else {
                    cambio = pago - total;
                    this.cambio = cambio;
                    System.out.println("cambio " + cambio);
                    Error2.setText(" ");
                    Error2.setVisible(true);

                }

            }

            jLCambio.setText(String.format("%.2f", cambio));

        } catch (Exception e) {
            Error2.setText("Solo ingrese numeros");
            Error2.setVisible(true);

        }
        return cambio;
    }

    //Metodo que modifica la cantida de productos de un producto
    private int cantidad() {

        int aumentacantidad = 0;
        try {
            String cantidadmas = jtModificarCantidad.getText();

            int filaSeleccionada = jtablaVentas.getSelectedRow(); // Obtiene el índice de la fila seleccionada

            if (filaSeleccionada == -1) {
                ErrorCantidad.setText("Seleccione un producto");
                ErrorCantidad.setVisible(true);
            } else {
                aumentacantidad = Integer.parseInt(cantidadmas);
                TablaDetalleregistro producto = detalle.get(filaSeleccionada);
                producto.setCantidad(aumentacantidad);
                actualizarVenta();
                ErrorCantidad.setText("");
                ErrorCantidad.setVisible(true);

            }
        } catch (Exception e) {
            ErrorCantidad.setText("Ingrese solo numeros");
            ErrorCantidad.setVisible(true);
        }

        return aumentacantidad;

    }

   public void hacerBusqueda2(String codigos) {
        String codigo = codigos;
        Producto productoDato = ab.BuscarProductoVenta(codigo);
        if (productoDato != null) {
            // Verificar si ya existe una fila con el mismo código
            boolean encontrado = false;
            for (int i = 0; i < detalle.size(); i++) {
                TablaDetalleregistro registro = detalle.get(i);
                if (registro.getCodigo().equals(codigo)) {
                    // Ya existe una fila con el mismo código, incrementar la cantidad y actualizar el precio total
                    registro.setCantidad(registro.getCantidad() + 1);
                    registro.setTotal(registro.getCantidad() * registro.getPrecio());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                // No existe una fila con el mismo código, agregar una nueva fila
                TablaDetalleregistro producto = new TablaDetalleregistro();
                producto.setCodigo(productoDato.getCodigo());
                producto.setCantidad(1);
                producto.setPrecio(productoDato.getCosto());
                producto.setDescripcion(productoDato.getDescripcion());
                producto.setTotal(producto.getPrecio());
                detalle.add(producto);
            }
            // Actualizar el precio total de la venta
            actualizarVenta();
        } else {
            JOptionPane.showMessageDialog(this, "Producto no existente");
        }
        jtbusqueda.setText("");
    }

    private void hacerBusqueda() {
        String codigo = jtbusqueda.getText();
        
        Producto productoDato = ab.BuscarProductoVenta(codigo);
        if (productoDato != null) {
            // Verificar si ya existe una fila con el mismo código
            boolean encontrado = false;
            for (int i = 0; i < detalle.size(); i++) {
                TablaDetalleregistro registro = detalle.get(i);
                if (registro.getCodigo().equals(codigo)) {
                    // Ya existe una fila con el mismo código, incrementar la cantidad y actualizar el precio total
                    registro.setCantidad(registro.getCantidad() + 1);
                    registro.setTotal(registro.getCantidad() * registro.getPrecio());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                // No existe una fila con el mismo código, agregar una nueva fila
                TablaDetalleregistro producto = new TablaDetalleregistro();
                producto.setCodigo(productoDato.getCodigo());
                producto.setCantidad(1);
                producto.setPrecio(productoDato.getCosto());
                producto.setDescripcion(productoDato.getDescripcion());
                producto.setTotal(producto.getPrecio());
                detalle.add(producto);
            }
            // Actualizar el precio total de la venta
            actualizarVenta();
        } else {
            JOptionPane.showMessageDialog(this, "Producto no existente");
        }
        jtbusqueda.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Error1;
    private javax.swing.JLabel Error2;
    private javax.swing.JLabel Error4;
    private javax.swing.JLabel ErrorCantidad;
    public javax.swing.JTextField JLPago;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarnombre;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarcompra;
    private javax.swing.JButton btnRegistroVentas;
    private javax.swing.JButton btnVacairLista;
    private javax.swing.JLabel cam;
    public javax.swing.JLabel jLCambio;
    public javax.swing.JTextField jLDescuento;
    private javax.swing.JLabel jLFecha;
    public javax.swing.JLabel jLSUB;
    private javax.swing.JLabel jLTicket;
    public javax.swing.JLabel jLTotal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JLabel jldecuento;
    private javax.swing.JTextField jtModificarCantidad;
    private javax.swing.JTable jtablaVentas;
    private javax.swing.JTextField jtbusqueda;
    private javax.swing.JLabel lp;
    private javax.swing.JPanel navegacionPanel;
    private javax.swing.JButton vtrProductos;
    // End of variables declaration//GEN-END:variables
}
