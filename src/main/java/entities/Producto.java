package entities;

public class Producto {
    private int idProducto;
    private String nombre;
    private Double valor;

    public Producto(int idProducto, String nombre, Double valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getValor() {
        return valor;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
