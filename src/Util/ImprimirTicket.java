package Util;

import Modelo.TablaDetalleregistro;
import Modelo.VentasModelo;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import vista.Principal;

/**
 * clase para hacer al imprecion de un ticket
 *
 * @author Laboratorio
 */
public class ImprimirTicket {

    public void pritnTicket(VentasModelo venta) {
        PrinterJob impresora = PrinterJob.getPrinterJob();
        impresora.setPrintable(new FormatoImprecion(venta));
        boolean quieroImprimir = impresora.printDialog();
        if (quieroImprimir) {
            try {
                impresora.print();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No puedo imprimir");
            }
        }

    }

    private class FormatoImprecion implements Printable {

        private VentasModelo venta;

        public FormatoImprecion(VentasModelo venta) {
            this.venta = venta;
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            //validacion encaso de eco}
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            Graphics2D print = (Graphics2D) graphics;
            print.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            int y = 20;
            print.setFont(new Font("Monospaced", Font.BOLD, 13) {
            });
            print.drawString("Farmcia santacruz", 60, y);
            y += 15;

            print.setFont(new Font("Monospaced", Font.PLAIN, 12) {
            });
            print.drawString("Ticket NO :" + venta.getFolio(), 80, y);
            y += 15;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            print.drawString(format.format(venta.getFecha()), 80, y);
            y += 15;
            String hora = Principal.getHoraActual();
            print.drawString("Hora " +hora, 80, y);
            y += 20;
            print.drawLine(0, y, 230, y);
            y += 20;
            print.setFont(new Font("Monospaced", Font.BOLD, 7) {
            });
            print.drawString("Concepto", 7, y);
            print.drawString("Cantidad", 75, y);
            print.drawString("" + "Precio", 140, y);
            print.drawString("" + "Total", 200, y);
            y += 5;
            print.drawLine(0, y, 230, y);

            y += 15;

            print.setFont(new Font("Monospaced", Font.PLAIN, 7) {
            });
            for (TablaDetalleregistro item : venta.getDetalle()) {
                print.drawString(item.getDescripcion(), 7, y);
                print.drawString("" + item.getCantidad(), 92, y);
                print.drawString("" + item.getPrecio(), 142, y);
                print.drawString("" + item.getTotal(), 202, y);
                y += 15;
            }

            print.drawLine(0, y, 230, y);
            print.setFont(new Font("Monospaced", Font.BOLD, 8) {
            });
            y += 15;
            double subtotal = venta.getSubtotal();
            double total = venta.getTotal();
            int descuento = venta.getDescuento();
            double cambio = venta.getCambio();
            double pago = venta.getPago();
            int xx = 100;
            int x = 200;
            print.drawString("" + subtotal + "$", x, y);
            print.drawString("SUBTOTAL", xx, y);
            y += 15;

            print.drawString("" +"("+ descuento +"%) "+venta.getPorcentaje()+"$", 190, y);
            print.drawString("Descuento", xx, y);
            y += 15;
            print.drawString("" + total + "$", x, y);
            print.drawString("Total a pagar ", xx, y);
            y += 15;
            print.drawString("" + pago + "$", x, y);
            print.drawString("Pago", xx, y);
            y += 15;
            print.drawString("" + cambio + "$", x, y);
            print.drawString("Cambio", xx, y);
            y += 15;

            print.drawString("Gracias por su compra", xx, y);

            return Printable.PAGE_EXISTS;

        }

        public void pritnTicket(VentasModelo venta) {
            PrinterJob impresora = PrinterJob.getPrinterJob();
            impresora.setPrintable(new FormatoImprecion(venta));
            boolean quieroImprimir = impresora.printDialog();
            if (quieroImprimir) {
                boolean imprimir = true;
                // Agregar diálogo de confirmación de cancelación
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la impresión?", "Cancelar impresión", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    imprimir = false;
                }
                if (imprimir) {
                    try {
                        impresora.print();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("No puedo imprimir");
                    }
                }
            }
        }

    }

}
