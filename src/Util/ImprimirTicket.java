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
import javax.swing.JLabel;
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

        double subtotal;
        double total;
        int decuento;

        VentasModelo cal = new VentasModelo();

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
            print.drawString("Punto de venta Tec", 60, y);
            y += 15;

            print.setFont(new Font("Monospaced", Font.PLAIN, 12) {
            });
            print.drawString("Ticket NO :" + venta.getFolio(), 80, y);
            y += 15;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            print.drawString(format.format(venta.getFecha()), 80, y);
            y += 20;

            print.drawLine(0, y, 300, y);
            y += 20;
            print.setFont(new Font("Monospaced", Font.BOLD, 10) {
            });
            print.drawString("Concepto", 7, y);
            print.drawString("Cantidad", 120, y);
            print.drawString("" + "Precio", 200, y);
            print.drawString("" + "Total", 260, y);
            y += 5;
            print.drawLine(0, y, 300, y);

            y += 20;
            print.setFont(new Font("Monospaced", Font.PLAIN, 10) {
            });
            for (TablaDetalleregistro item : venta.getDetalle()) {
                print.drawString(item.getDescripcion(), 7, y);
                print.drawString("" + item.getCantidad(), 140, y);
                print.drawString("" + item.getPrecio(), 205, y);
                print.drawString("" + item.getTotal(), 263, y);
                y += 15;
            }
            y += 5;
            print.drawLine(0, y, 300, y);
            print.setFont(new Font("Monospaced", Font.BOLD, 12) {
            });
            y += 15;
            cal.setSubtotal(subtotal);
            cal.setTotal(total);
            cal.setDescuento(decuento);
            String totales = Double.toString(total);
            String subtototales = Double.toString(subtotal);
            String descuentos = Double.toString(decuento);
            print.drawString("" + totales, 7, y);
            y += 15;
            print.drawString("" + subtototales, 7, y);
            y += 15;
            print.drawString("" + descuentos, 7, y);
            y += 15;
            print.drawString("    Vuelva Pronto  ", 7, y);

            return Printable.PAGE_EXISTS;

        }

    }
}
