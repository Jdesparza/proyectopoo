package proyectopoo;

import javax.swing.*; // Es una biblioteca gráfica
import java.awt.*; // para hacer interfaces graficas
import java.awt.event.ActionEvent; // permitira programar lo que realizara el botón
import java.awt.event.ActionListener; // detecta una accion para poder despues dar una accion
import javax.swing.ImageIcon; // para crear iconos de imagenes

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class Tienda extends JFrame {

    // Atributos de la clase Tienda
    private JButton stoct_registro;
    private JPanel panel1;
    private JButton ventaProducto;
    private JButton salirPrograma;

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana Tienda
     */
    public Tienda() {
        // Me premite ponerle un título a la ventana
        super("Menú");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel1
        setContentPane(panel1);

        // Trabajamos con el primer botón de la ventana que llamara a la clase Stock para trabajar con ella
        // esto se realizara despues de que se genere un evento de accion sobre el botón
        stoct_registro.addActionListener(new ActionListener() {
            @Override
            // Isertamos que hara el botón despues del evento de accion
            public void actionPerformed(ActionEvent e) {
                /*
                    Hacemos el llamado de la clase Stock con su respectiva ventana
                 */
                Stock stock = new Stock();
                stock.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
                stock.show(); // muestra en la pantalla la ventana
            }
        });

        // Trabajamos con el segundo botón de la ventana que llamara a la clase Venta para trabajar con ella
        ventaProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    Hacemos el llamado de la clase Venta con su respectiva ventana
                 */
                Venta venta = new Venta();
                venta.pack();
                venta.show();
            }
        });

        // Trabajamos con el tercer botón que nos permitira salir del programa
        salirPrograma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // le damos la ubicación de la imagen que queremos transformar en icono
                ImageIcon imagen = new ImageIcon("src/proyectopoo/img/adios1.jpg");

                // le damos un tamaño al icono
                // .SCALE_DEFAULT para trabajar con la escala de imagen predeterminada
                Icon fondo = new ImageIcon(imagen.getImage().getScaledInstance(100, 90,
                        Image.SCALE_DEFAULT));

                dispose(); // Hace que la ventana sea destruida y limpiada por el sistema operativo

                // JOptionPane es un cuadro de dialogo que nos permitira mostrar información
                // showMessageDialog permitira madar un mensaje de dialogo
                JOptionPane.showMessageDialog(null, "SALIDA DEL PROGRAMA", "EXIT",
                        0, fondo);

                // nos permite salir del programa por completo
                System.exit(0);
            }
        });
    }

    /**
     * Método que nos permitira ejecutar el programa
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú"); // llamamos a la biblioteca grafica JFrame
        frame.setContentPane(new Tienda().panel1); // le añadimos al contenedor de Jframe el panel1
        frame.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
        frame.setVisible(true); // para que la ventana sea visible
        frame.setResizable(false); // para que no se pueda modificar el tamaño de la ventana
    }
}
