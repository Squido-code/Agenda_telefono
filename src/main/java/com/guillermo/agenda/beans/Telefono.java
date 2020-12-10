package com.guillermo.agenda.beans;

/**
 * @author Guillermo Suarez
 */
public class Telefono {
    private int id_telefono;
    private String nombre;
    private String numero;

    public int getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(int id_telefono) {
        this.id_telefono = id_telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String toString(){
        return getNombre()+": "+getNumero();
    }
}
