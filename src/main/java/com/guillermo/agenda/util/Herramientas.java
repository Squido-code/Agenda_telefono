package com.guillermo.agenda.util;

import com.guillermo.agenda.DAO.Persona_DAO;
import com.guillermo.agenda.beans.Persona;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class Herramientas {
    /**
     * metodo para mostrar por pantalla excepciones
     *
     * @param texto
     */
    public void alertaError(String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(texto);
        alerta.show();
    }

    /**
     * Método que rellena el atributo telefono de la clase persona.
     *
     * @return String
     * @throws SQLException
     */
    public ArrayList<Persona> persona_con_telefono() throws SQLException {
        Persona_DAO personaDAO = new Persona_DAO();
        //creamos lista de personas sin telefonos
        ArrayList<Persona> listaPersonas;
        //obtenemos todas las personas sin telefonos
        listaPersonas = personaDAO.listar();
        //creamos lista para añadir las personas con su telefono
        ArrayList<Persona> listaCompleta = new ArrayList<>();
        //desgranamos cada persona
        for (Persona p : listaPersonas
        ) {
            //vamos llenando el array del atributo telefono
           p.setTelefono(personaDAO.listarTelefonos(p.getId_persona()));

            //añadimos la persona completa a una nueva lista
            listaCompleta.add(p);
        }
        return listaCompleta;
    }

    /**
     * Método que combina los campos direccion, codigo postal y poblacion para crear una única String.
     * @param dire
     * @param codP
     * @param pob
     * @return String
     */
    public String direccionCompleta(String dire,String codP,String pob){
        String todo = dire+" CP: "+codP+" "+pob;
        return todo;
    }
}
