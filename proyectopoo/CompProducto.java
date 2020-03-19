package proyectopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat; // para formatear números decimales a nuestro gusto
import java.util.ArrayList;
import java.util.List;

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class CompProducto extends JFrame {
    // Atributos de la clase CompProducto
    private JTextField textCanPro;
    private JButton calcularPrecio;
    private JTextField textCosPro;
    private JButton compraNueva;
    private JButton finalizarCompra;
    private JPanel panel4;
    private JLabel lblNombre;

    // static nos permitira invocar nuestra lista creada que almacena
    // datos tipo objeto Productofactura desde otra clase
    static List<ProductoFactura> productoFacturas = new ArrayList<ProductoFactura>();

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana CompProducto
     */
    public CompProducto() {
        // Le damos un nombre a la ventana
        super("Compra Producto");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel4
        setContentPane(panel4);

        // El nombre que se encuentra guardado en la lista nombProduc de la clase Venta se mostrara como subtitulo
        // es la ventana de CompProducto
        lblNombre.setText(Venta.nombProduc.get(0));

        // el boton compraNueva no estara visible en la ventana hasta que se le indique
        compraNueva.setVisible(false);

        // Trabajamos con el primer boton que calculara el valor a pagar por la cantidad de productos ingresados
        calcularPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // especificamos la cantidad de decimales que queremos que aparezcan
                DecimalFormat decimal = new DecimalFormat("0.00");

                // Declaramos variables e inicializamos
                int i = 0;
                String nombre = lblNombre.getText(); // guardamos en la variable el nombre que pusimos como subtitulo


                // para recorrer toda la lista que fue declarada como static en la clase Stock y
                // poder trabajar con sus datos
                for (ProductoEntrada productoEntrada : Stock.productos) {

                    // para trabajar con los respectivos datos del producto,
                    // el cual se encuentra dentro de la lista Stock.productos
                    if (productoEntrada.getNombre().toLowerCase().contains(
                            nombre.toLowerCase())) {

                        // En el caso de que le den al boton de calcularPrecio sin haber ingresado
                        // la cantidad del producto a comprar
                        if (textCanPro.getText().equals("")) {
                            // El apartado que muestra el Precio por el producto mostrara un mensaje
                            textCosPro.setText("Su dato es cadena");

                            // El boton compraNueva mostrara otro tipo de mensaje por la ocación dada
                            compraNueva.setText("Ingresar Nuevamente");

                            // El boton se hara visible
                            compraNueva.setVisible(true);

                            // En otro caso si no hay ese problema se hara el
                            // calculo del precio por la cantidad del producto ingresado
                        } else if (Integer.parseInt(textCanPro.getText()) <= productoEntrada.getCantidad()) {

                            // variable que guardara la cantidad a comprar del producto
                            int cantidad = Integer.parseInt(textCanPro.getText());

                            // variable que guardara el precio que tiene el producto dentro de la lista productos
                            double preUnitario = productoEntrada.getPrecio();

                            // variable que guardara el precio a pagar por el producto luego de hacer el calculo
                            double preTot = preUnitario * cantidad;

                            // variable que guardara la nueva cantidad del producto que se va a encontrar
                            // dentro de la lista Stock.productos ya que estos ya no seran parte del punto de venta
                            int nuevaCantidad = productoEntrada.getCantidad() - cantidad;

                            // se mandara el costo que tiene la cantidad del producto en el apartado
                            // de Precio por el producto
                            textCosPro.setText(String.valueOf(decimal.format(preTot)));

                            // Creacion de objeto de la clase ProductoFactura
                            ProductoFactura productoFactura = new ProductoFactura(nombre, cantidad,
                                    preUnitario, preTot);

                            // Agregamos objeto ProductoFactura a la lista productofacturas
                            productoFacturas.add(productoFactura);

                            // Actualizamos la cantidad del producto en su respectiva posicion
                            // dentro de la lista Stock.producto con la nuevaCantidad
                            Stock.productos.set(i, new ProductoEntrada(nombre, nuevaCantidad, preUnitario));

                            // Volvemos visible el boton compraNueva
                            compraNueva.setVisible(true);

                        } else { // En el caso de que la cantidad a comprar del producto sobrepase la cantidad
                            // que hay dentro de la lista Stock.productos

                            // El apartado del Precio por el Producto mostrara un respectivo mensaje por la ocacion
                            textCosPro.setText("Sobrepasa el Limite");

                            // El boton de compraNueva mostrara un nuevo mensaje debido a la ocacion
                            compraNueva.setText("Pedir Nuevamente");

                            // El boton compraNueva se hara visible en la ventana
                            compraNueva.setVisible(true);
                        }
                    }
                    i++; // El contador que nos da la posicion que estamos en la lista aumentara en uno
                }
            }
        });

        // Trabajamos con el segundo boton, el cual tiene varias funciones
        compraNueva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // variable que contiene el subtitulo de la ventana
                String nombre = lblNombre.getText();

                // para recorrer toda la lista que fue declarada como static en la clase Stock y
                // poder trabajar con sus datos
                for (ProductoEntrada productoEntrada : Stock.productos) {

                    // para trabajar con los respectivos datos del producto,
                    // el cual se encuentra dentro de la lista Stock.productos
                    if (productoEntrada.getNombre().toLowerCase().contains(
                            nombre.toLowerCase())) {

                        // En el caso de que el apartado del Precio por el Producto muestre estos mensajes
                        if ((textCosPro.getText().equals("Sobrepasa el Limite")) ||
                                (textCosPro.getText().equals("Su dato es cadena"))) {

                            /*
                                Hacemos el llamado de la clase CompProducto con su respectiva ventana
                                con la finalidad de que el usuario vuelva a ingresar la cantidad
                            */
                            CompProducto compProducto = new CompProducto();
                            compProducto.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
                            compProducto.show(); // muestra en la pantalla la ventana
                        } else {
                            /*
                                Hacemos el llamado de la clase Venta con su respectiva ventana
                                en el caso de que el usuario quiera comprar otro producto
                            */
                            Venta venta = new Venta();
                            venta.pack();
                            venta.show();
                            // quitamos el boton de cancelar de la ventana Venta
                            venta.cancelar.setVisible(false);
                        }
                    }
                }
            }
        });

        // Trabajamos con el tercer boton el cual tiene la finalidad de llevarte a la ventana de DatosCliente
        finalizarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    Hacemos el llamado de la clase Venta con su respectiva ventana
                    en el caso de que el usuario quiera comprar otro producto
                */
                DatosCliente datosCliente = new DatosCliente();
                datosCliente.pack();
                datosCliente.show();
            }
        });
    }
}
