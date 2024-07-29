package App.Models;

public class InsumosMedicos{
    /*
    CREATE DATABASE Contoso;
    USE Contoso;

    CREATE TABLE insumos_medicos (
        PK_insumo INT PRIMARY KEY AUTO_INCREMENT,
        nombre_insumo VARCHAR(255) NOT NULL,
        descripcion VARCHAR(255) NOT NULL,
        precio DECIMAL(10, 2) NOT NULL
    );

    */
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
