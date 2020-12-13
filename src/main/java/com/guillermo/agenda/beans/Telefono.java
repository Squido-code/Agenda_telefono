package com.guillermo.agenda.beans;

/**
 * @author Guillermo Suarez
 */
public class Telefono {
    private  int idTelefono=-1;
    private int idPersona;
    private String nombre;
    private String numero;

    public Telefono() {
    }

    public Telefono(int id,String nombre, String numero) {
        this.idTelefono = id;
        this.nombre = nombre;
        this.numero = numero;
    }
    public Boolean idIsEmpty(){
        if(idTelefono==-1){
            return true;
        }else{
            return false;
        }
    }
    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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
