package proyectopoo;

public class ProductoFactura extends Producto {
    // Atributos propios de la clase ProductoFactura
    private double precioTotal;
    private double valor;
    private double iva;
    private double valorTotal;

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getValor() {
        return valor;
    }

    public double getIva() {
        return iva;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Método constructor de la clase ProductoFactura
     * que recibe cuatro parámetros
     * @param nombre
     * @param cantidad
     * @param precio
     * @param precioTotal
     */
    public ProductoFactura(String nombre, int cantidad, double precio, double precioTotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = precioTotal;
    }

    /**
     * Método constructor de la clase ProductoFactura
     * que recibe un parámetro
     * @param valor
     */
    public ProductoFactura(double valor) {
        this.valor = valor;
    }

    /**
     * Método para calcular el iva del costo subtotal
     */
    public void calcularIva() {
        iva = valor * 0.12;
    }

    /**
     * Método para calcular el total a pagar
     */
    public void calcularValorTotal() {
        valorTotal = valor + iva;
    }
}
