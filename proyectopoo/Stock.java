package proyectopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // permitira almacenar datos en memoria de forma dinamica(sin que le demos un tamaño)
import java.util.List; // nos permitira agrupar en forma de lista, es decir uno detras de otro
import javax.swing.ImageIcon;

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class Stock extends JFrame {

    // atributos de la clase Stock
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JButton guardar;
    private JButton cancelar;
    private JPanel panel2;
    private JButton menu;

    // static nos permitira invocar nuestra lista creada que almacena datos tipo objeto desde otra clase
    static List<ProductoEntrada> productos = new ArrayList<ProductoEntrada>();

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana Stock
     */
    public Stock() {
        // super permite ponerle un t´tulo a la ventana
        super("Strok de Productos");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel2
        setContentPane(panel2);

        // iniciamos la ejecución de esta ventana sin que aparezca el boton de menu
        menu.setVisible(false);

        // Trabajamos con el primer boton de esta ventana que guardara datos
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // variables a usar para guardar los datos ingresados en la ventana cuando el botón sea accionado
                // .getText() permite obtener el texto ingresado
                String nombre = txtNombre.getText(); // se guarda el texto del apartado Nombre producto en la variable
                int cantidad;
                double precio;

                if (txtCantidad.getText().equals("")) {
                    // se guardara una cantidad de 0 en entero cuando no ingresen un valor
                    // en el apartado de Cantidad de producto
                    cantidad = 0;
                } else {
                    // se guarda el texto transformado en entero del apartado Cantidad de producto en una variable en
                    // el caso de que el usuario si ingrese un dato aqui
                    cantidad = Integer.parseInt(txtCantidad.getText());
                }

                if (txtPrecio.getText().equals("")) {
                    // se guardara una cantidad de 0 en dato real
                    // cuando no ingresen un valor en el apartado del Precio del producto
                    precio = 0;
                } else {
                    // se guarda el texto transformado en dato real del apartado de Precio del
                    // producto en una variable en el caso de que el usuario si ingrese un dato aqui
                    precio = Double.parseDouble(txtPrecio.getText());
                }

                // Creación de objeto de la clase ProductoEntrada
                ProductoEntrada productoEntrada = new ProductoEntrada(nombre, cantidad, precio);

                // Agregamos objeto ProductoEntrada a la lista productos
                productos.add(productoEntrada);

                // llamamos al método void que permitira mostrar los datos de la lista
                visualizar_productos();

                // llamamos al método void que nos permitira limpiar los apartados de entrada de cada text
                limpiar();
            }

            /**
             * Método para limpiar los apartados de text de la ventana
             */
            public void limpiar() {
                txtNombre.setText("");
                txtCantidad.setText("");
                txtPrecio.setText("");
            }

            /**
             * Método que nos permitira visualizar los datos de la lista productos
             */
            public void visualizar_productos() {
                // le damos la ubicación de la imagen que queremos transformar en icono
                ImageIcon imagen1 = new ImageIcon("src/proyectopoo/img/store1.jpg");

                // le damos un tamaño al icono
                // .SCALE_DEFAULT para trabajar con la escala de imagen predeterminada
                Icon fondo1 = new ImageIcon(imagen1.getImage().getScaledInstance(100, 90,
                        Image.SCALE_DEFAULT));

                // declaración de variables e inicialización
                int i = 0;
                String signo = " = $ ";
                String cad;
                String[] prod = new String[productos.size()]; // arreglo que tiene el mismo tamaño que la lista

                // para recorrer toda la lista  y poder trabajar con sus datos
                for (ProductoEntrada productoEntrada : productos) {
                    // cad son los datos que vamos a guardar dentro del arreglo en cada posicion
                    cad = String.format("%s%s%s\n", productoEntrada.getNombre(), signo, productoEntrada.getPrecio());

                    // guardamos cad dentro del arreglo en cada una de sus posiciones
                    prod[i] = cad;

                    // variable i aumenta en 1
                    i++;
                }

                // JOptionPane es un cuadro de dialogo que nos permitira mostrar información
                // showMessageDialog permitira madar un mensaje de dialogo
                JOptionPane.showMessageDialog(null, prod, "PRODUCTOS",
                        0, fondo1);

                // hacemos aparecer el boton de menu en la ventana
                menu.setVisible(true);
            }
        });

        // Trabajamos con el segundo boton de esta ventana que llamara a la clase Tienda para trabajar con ella
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    Hacemos el llamado de la clase Tienda con su respectiva ventana
                 */
                Tienda tienda = new Tienda();
                tienda.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
                tienda.show(); // muestra en la pantalla la ventana
            }
        });

        // Trabajamos con el tercer botón que tambien llamara a la clase tienda pero borrara los datos de la lista
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    Hacemos el llamado de la clase Tienda con su respectiva ventana
                 */
                Tienda tienda = new Tienda();

                // .clar() borrara los datos de la lista productos
                productos.clear();

                tienda.pack();
                tienda.show();
            }
        });
    }
}
