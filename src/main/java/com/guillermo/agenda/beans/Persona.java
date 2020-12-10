package com.guillermo.agenda.beans;

import java.util.ArrayList;

/**
 * @author squid
 */
public class Persona {
    private int id_persona;
    private String nombre;
    private String apellidos;
    private ArrayList<Telefono> telefono;
    private String direccion;
    private String codigo_postal;
    private String poblacion;
    private String notas;

    public int getId_persona() {
        return id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public ArrayList<Telefono> getTelefono() {
        return telefono;
    }

    public void setTelefono(ArrayList<Telefono> telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString(){
        return "nombre: "+getNombre()+" "+"Apellidos:"+getApellidos();
    }
}
