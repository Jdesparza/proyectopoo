package proyectopoo;

public class ProductoEntrada extends Producto {
    /**
     * Método constructor de la clase ProductoEntrada que
     * recibe tres parametros
     * @param nombre
     * @param cantidad
     * @param precio
     */
    public ProductoEntrada(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
