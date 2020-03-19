package proyectopoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class Venta extends JFrame {

    // Atributos de la clase Venta
    private JTextField txtVerificar;
    private JTextField lblCantidad;
    private JPanel panel3;
    private JButton buscar;
    private JTextField lblNombre;
    private JTextField lblPrecio;
    private JButton realizaCompra;
    protected JButton cancelar; // declarado como protected debido a que tambien trabajaremos con el desde otra clase
    private JLabel mensajeOtroCaso;

    // static nos permitira invocar nuestra lista creada que almacena
    // datos tipo String desde otra clase
    static List<String> nombProduc = new ArrayList<String>();

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana Venta
     */
    public Venta() {
        // le damos un nombre a la ventana
        super("Venta");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel3
        setContentPane(panel3);

        // el JLabel mensajeOtroCaso no estara visible en la ventana hasta que se le indique
        mensajeOtroCaso.setVisible(false);

        // el boton realizaCompra no estara visible en la ventana hasta que se le indique
        realizaCompra.setVisible(false);

        // .clear() limpiara la lista cada vez que vuelva a aparecer la ventana Venta
        nombProduc.clear();

        // Trabajamos con el primer botón que buscara si existe
        // el nombre o especificacion de el en la lista productos de la clase Stock
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // variable que guardara el nombre del producto para verificar si existe en el Stock
                String nombre_a_buscar = txtVerificar.getText();

                // mientras se verifica si existe el producto el apartado
                // que muestra el nombre del producto mantendra un mensaje hasta que se demuestre lo contrario
                lblNombre.setText("No existe producto");

                // para recorrer toda la lista que fue declarada como static en la clase Stock y
                // poder trabajar con sus datos
                for (ProductoEntrada productoEntrada : Stock.productos) {
                    // Verificamos que el nombre a buscar este contenido dentro de un
                    // producto de la lista
                    if (productoEntrada.getNombre().toLowerCase().contains(
                            nombre_a_buscar.toLowerCase())) {
                        // guardamos en la lista creada los nombres de los productos que contienen el mismo
                        // nombre ingresado para buscar para despues trabajar con ellos
                        nombProduc.add(productoEntrada.getNombre());

                        // En caso de que la lista creada tenga mas de un dato
                        if (nombProduc.size() > 1) {
                            // El boton de realizaCompra tendra un nuevo mensaje
                            realizaCompra.setText("Especificar Nuevamente");

                            // el apartado que muestra el nombre del producto tendra
                            // otro tipo de mensaje por la ocación
                            lblNombre.setText("Vuelva a especificar");

                            // el apartado que muestra la cantidad del producto que hay estara vacio
                            lblCantidad.setText("");

                            // el apartado que muestra el precio del producto que hay estara vacio
                            lblPrecio.setText("");

                            // el boton realizaCompra se volvera visible y mostrara su respectivo mensaje
                            realizaCompra.setVisible(true);

                        } else { // En el caso de que la lista creada tenga solo un dato
                            // Actualizara los apartados que muestran el nombre del producto, su catidad y precio
                            // con los respectivos datos que se encuentran en el Stock de acuerdo al nombre verificado
                            lblNombre.setText(productoEntrada.getNombre());
                            lblCantidad.setText(String.valueOf(productoEntrada.getCantidad()));
                            lblPrecio.setText(String.valueOf(productoEntrada.getPrecio()));

                            // En el caso de que la cantidad del producto este en cero
                            if (productoEntrada.getCantidad() == 0) {
                                // el boton realizaCompra tendra otro tipo de mensaje
                                realizaCompra.setText("Realizar Otra Compra");

                                // El Jlabel mensajeOtroCaso tendra un mensaje por la ocación
                                mensajeOtroCaso.setText("No Hay Productos");

                                // El Jlabel mensajeOtroCaso se hara visible en la ventana
                                mensajeOtroCaso.setVisible(true);

                                // El boton de realiza compra se hara visible y mostrara su respectivo mensaje
                                realizaCompra.setVisible(true);

                            } else { // En el caso de que la cantidad del producto sea mayor a cero
                                // el boton realizaCompra tendra otro tipo de mensaje
                                realizaCompra.setText("Realizar compra de producto");

                                // El boton de realiza compra se hara visible y mostrara su respectivo mensaje
                                realizaCompra.setVisible(true);
                            }
                        }
                    }

                    // En el caso de que el Jlabel que muestra el nombre del producto tenga un mensaje
                    // de no existe producto o en el caso de que el usuario no haya ingresado el nombre a verificar
                    if ((lblNombre.getText().equals("No existe producto")) ||
                            (nombre_a_buscar.equals(""))) {

                        // El jlabel del nombre mantendra el mensaje de no existe el producto
                        lblNombre.setText("No existe producto");

                        // El Jlabel de la cantidad mostrara un mensaje "Null"
                        lblCantidad.setText("Null");

                        // El Jlabel del precio mostrara un mensaje "Null"
                        lblPrecio.setText("Null");

                        // El boton realizaCompra mostrara otro tipo de mensaje
                        realizaCompra.setText("Ingresar Nuevamente");

                        // el Jlabel mensajeOtroCaso desaparece de la ventana
                        mensajeOtroCaso.setVisible(false);

                        // El boton realizaCompra se hara visible
                        realizaCompra.setVisible(true);
                    }
                }

            }
        });

        // Trabajamos con el segundo boton que tiene distintas funciones
        realizaCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // el boton volvera a desaparecer despues de que haga su función anterior
                realizaCompra.setVisible(false);

                // variable que guardara el nombre del producto que fue verificado para ver si existe en el Stock
                String nombre_a_buscar = txtVerificar.getText();

                // para recorrer toda la lista que fue declarada como static en la clase Stock y
                // poder trabajar con sus datos
                for (Producto producto : Stock.productos) {

                    // la función que realizara el boton realizaCompra cuando existan estos mensajes
                    if ((lblNombre.getText().equals("No existe producto")) || (nombre_a_buscar.equals(""))) {

                        /*
                            Hacemos el llamado de la clase Venta con su respectiva ventana
                            con la finalidad de que el usuario vuelva a pedir un producto que si exista
                        */
                        Venta venta = new Venta();
                        venta.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
                        venta.show(); // muestra en la pantalla la ventana

                    } else {
                        // En caso de que el nombre buscado si exista en el Stock realizara lo siguiente
                        if (producto.getNombre().toLowerCase().contains(
                                nombre_a_buscar.toLowerCase())) {

                            // En el caso de que el Jlabel del nombre del producto tenga este mensaje
                            if (lblNombre.getText().equals("Vuelva a especificar")) {

                                /*
                                    Hacemos el llamado de la clase Venta con su respectiva ventana
                                    con la finalidad de que el usuario vuelva a pedir un producto, especificando mejor
                                */
                                Venta venta = new Venta();
                                venta.pack();
                                venta.show();

                            } else {

                                // En caso de que la cantidad del producto que esta en el Stock sea cero
                                if (producto.getCantidad() == 0) {
                                    /*
                                        Hacemos el llamado de la clase Venta con su respectiva ventana
                                        con la finalidad de que el usuario pida otro producto
                                    */
                                    Venta venta = new Venta();
                                    venta.pack();
                                    venta.show();

                                } else {

                                    /*
                                        Hacemos el llamado de la clase CompProducto con su respectiva ventana
                                        para que el usuario ingrese la cantidad del producto que desea comprar
                                    */
                                    CompProducto compProducto = new CompProducto();
                                    compProducto.pack();
                                    compProducto.show();
                                }
                            }
                        }
                    }
                }
            }
        });

        // Trabajamos con el tercer boton que solo te llevara a la ventana principal es decir a la de la clase Tienda
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*
                    Hacemos el llamado de la clase Tienda con su respectiva ventana
                    para que puedan volver a realizar cualquiera de las funciones que muestra
                */
                Tienda tienda = new Tienda();
                tienda.pack();
                tienda.show();
            }
        });
    }
}
