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
     * @return
     * @throws SQLException
     */
    public ArrayList<Persona> persona_con_telefono() throws SQLException {
        Persona_DAO personaDAO = new Persona_DAO();
        ArrayList<Persona> listaPersonas;
        //obtenemos todas las personas sin telefonos
        listaPersonas = personaDAO.listar();
        ArrayList<Persona> listaCompleta = new ArrayList<>();

        for (Persona p : listaPersonas
        ) {
            //vamos llenando el array del atributo telefono
            p.setTelefono(personaDAO.listarTelefonos(p.getId_persona()));
            //añadimos la persona completa a una nueva lista
            listaCompleta.add(p);
        }
        return listaCompleta;
    }
}
