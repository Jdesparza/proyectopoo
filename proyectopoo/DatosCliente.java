package proyectopoo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat; // para trabajar con un formato de fecha
import java.text.DecimalFormat;
import java.text.SimpleDateFormat; // permite el formateo, analisis y normalizacion de las fechas
import java.util.Date; // para trabajar con un campo el cual tiene un dato tipo fecha

// hereda de JFrame debido a que crearemos una aplicación de escritorio
public class DatosCliente extends JFrame {
    // Atributos de la clase DatosCliente
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCed;
    private JButton facturaFinal;
    private JPanel panel6;

    /**
     * Método Constructor que nos permitira trabajar con las partes de nuestra ventana DatosCliente
     */
    public DatosCliente() {
        // le damos un nombre a la ventana
        super("Datos Cliente");

        // Para que no se pueda modificar el tamaño de la ventana cuando sea llamado en otra clase
        this.setResizable(false);

        // le añadimos al contenedor de Jframe el panel6
        setContentPane(panel6);

        // Trabajamos con el unico boton que hay, el cual nos permitira mandar a la factura los datos
        facturaFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // llamamos al método void que permitira mostrar todos los datos en la factura
                visualizar_productos_en_la_factura();
            }

            /**
             * Método que nos permitira visualizar los datos en la factura
             */
            public void visualizar_productos_en_la_factura() {
                // Hacemos el llamado a la clase Factura
                Factura factura = new Factura();

                // Declaramos e inicializamos variables
                int i = 0;
                double valor = 0;

                // Arreglos que tienen el mismo tamaño que la lista CompProducto.productoFacturas en la cual
                // almacenaremos datos
                String[] prodDesc = new String[CompProducto.productoFacturas.size()];
                String[] prodCantid = new String[CompProducto.productoFacturas.size()];
                String[] prodPreUni = new String[CompProducto.productoFacturas.size()];
                String[] prodPreTot = new String[CompProducto.productoFacturas.size()];

                // LLamado de Date para trabajar con fechas
                Date date = new Date();

                // Le damos el formato que queremos a la fecha
                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

                // especificamos la cantidad de decimales que queremos que aparezcan
                DecimalFormat decimal = new DecimalFormat("0.00");

                // almacenamos en una variable los nombres y apellidos ingresados
                String cliente = textNombre.getText()+" "+textApellido.getText();

                // almacenamos en otra variable el numero de cedula
                String id = textCed.getText();

                // recorremos todos los datos de la lista declarada como static en la clase CompProducto
                for (ProductoFactura productoFactura : CompProducto.productoFacturas) {
                    // Guarda el nombre del producto que se encuentra en la lista en mayuscula
                    prodDesc[i] = productoFactura.getNombre().toUpperCase();

                    // Guarda la cantidad del producto que compro el cliente, el cual tambien se encuentra en la lista
                    prodCantid[i] = ""+productoFactura.getCantidad();

                    // Guarda el precio unitario del producto
                    prodPreUni[i] = "$ "+productoFactura.getPrecio();

                    // Guarda el precio total por la compra de una cierta cantidad del producto
                    prodPreTot[i] = "$ "+decimal.format(productoFactura.getPrecioTotal());

                    // variable que se encarga de ir sumando cada vez que entra en el for los precios totales
                    // por la compra de cierta cantidad del producto
                    valor += productoFactura.getPrecioTotal();

                    // El contador aumenta en uno, es el encargado de posicionar los datos en el arreglo
                    i++;
                }

                // LLamado del objeto ProductoFactura y le enviamos el valor de la suma de todos los costos
                ProductoFactura productoFactura = new ProductoFactura(valor);

                // Calculamos Iva y ValorTotal dentro de ProductoFactura
                productoFactura.calcularIva();
                productoFactura.calcularValorTotal();

                // Mandamos a las listas de la factura los arreglos
                factura.listDes.setListData(prodDesc);
                factura.listCan.setListData(prodCantid);
                factura.listPUni.setListData(prodPreUni);
                factura.listPTo.setListData(prodPreTot);

                // Mandamos al apartado del Valor de la factura el subtotal de la compra
                factura.textValor.setText("$ "+decimal.format(productoFactura.getValor()));

                // Mandamos al apartado del 12% IVA, el precio del iva calculado
                factura.textIva.setText("$ "+decimal.format(productoFactura.getIva()));

                // Mandamos al apartado de TOTAL el valor a pagar
                factura.textTotal.setText("$ "+decimal.format(productoFactura.getValorTotal()));

                // Mandamos al apartado de CLIENTE la variable cliente, la cual
                // contiene los nombres y apellidos del cliente
                factura.textCliente.setText(cliente.toUpperCase());

                // Mandamos al apartado de CED/RUC el id del cliente
                factura.textCed.setText(id);

                // Mandamos al apartado de FECHA la fecha actual de la compra
                factura.textFecha.setText(fecha.format(date));

                factura.pack(); // le da el tamaño mas pequeño que se puede dar a la ventana
                factura.show(); // muestra en la pantalla la ventana
            }
        });
    }
}
