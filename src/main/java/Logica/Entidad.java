package Logica;

import javax.swing.JTable;

public abstract class Entidad {

    protected int id;
    protected String nombre;

    public Entidad() {
    }

    public Entidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void listar(JTable tabla);

    public abstract void agregar();

    public abstract void actualizar();

    public abstract void eliminar();
}
