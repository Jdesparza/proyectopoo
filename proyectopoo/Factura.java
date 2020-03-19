package proyectopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class Factura extends JFrame {
    // Atributos de la clase Factura
    // Algunos fueron declarados como protected ya que seran utilizados en otras clases
    private JPanel panel5;
    protected JList listDes;
    protected JList listCan;
    protected JList listPUni;
    protected JList listPTo;
    protected JLabel textValor;
    protected JLabel textIva;
    protected JLabel textTotal;
    protected JLabel textCliente;
    protected JLabel textCed;
    protected JLabel textFecha;
    private JButton salir;

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana Factura
     */
    public Factura() {
        // Le ponemos un título a la ventana
        super("Factura");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel5
        setContentPane(panel5);

        // Trabajamos con el unico boton que tiene el cual cumple la funcion de cerrar el programa luego de mostrar
        // un pequeño mensaje
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // le damos la ubicación de la imagen que queremos transformar en icono
                ImageIcon imagen2 = new ImageIcon("src/proyectopoo/img/producto1.png");

                // le damos un tamaño al icono
                // .SCALE_DEFAULT para trabajar con la escala de imagen predeterminada
                Icon fondo2 = new ImageIcon(imagen2.getImage().getScaledInstance(100, 90,
                        Image.SCALE_DEFAULT));

                dispose(); // Hace que la ventana sea destruida y limpiada por el sistema operativo

                // JOptionPane es un cuadro de dialogo que nos permitira mostrar información
                // showMessageDialog permitira madar un mensaje de dialogo
                JOptionPane.showMessageDialog(null, "VUELVA PRONTO", "GRACIAS",
                        0, fondo2);

                // nos permite salir del programa por completo
                System.exit(0);
            }
        });
    }
}
