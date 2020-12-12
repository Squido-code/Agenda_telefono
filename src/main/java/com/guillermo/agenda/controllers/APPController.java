package com.guillermo.agenda.controllers;

import com.guillermo.agenda.DAO.PersonaDAO;
import com.guillermo.agenda.DAO.TelefonoDAO;
import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.util.Herramientas;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class APPController {
    public ListView<Persona> lvLista;
    public Label lbNombre;
    public Label lbApellidos;
    public Label lbDireccion;
    public Label lbTelefono1;
    public Label lbTelefono2;
    public TextArea txNotas;
    public Button btNuevo;
    public Button btEditar;
    public Button btEliminar;
    public Button btBuscar;
    private Herramientas tool;
    private PersonaDAO persona_dao;
    private TelefonoDAO telefonoDao;

    public APPController() {
        tool = new Herramientas();
    }

    /**
     * Método que carga en el ListView todos los datos de las personas al inicio d ela aplicación.
     */
    public void cargarDatos() {
        ArrayList<Persona> lista = new ArrayList<>();
        try {
            lista = tool.Listas_personas_completa();
        } catch (SQLException throwables) {
            tool.alertaError("Error al cargar los datos");
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error critico interno");
        }
        lvLista.setItems(FXCollections.observableArrayList(lista));


    }

    /**
     * Método que muestra en la pantalla principal toda la informacion de la clase persona
     * @param event
     */
    @FXML
    public void seleccionarPersona(Event event){
        Persona p= lvLista.getSelectionModel().getSelectedItem();
        ArrayList<Telefono> telefono = p.getTelefono();
        lbNombre.setText(p.getNombre());
        lbApellidos.setText(p.getApellidos());
        lbDireccion.setText(tool.direccionCompleta(p.getDireccion(),p.getCodigo_postal(),p.getPoblacion()));
        //controlamos erroes al acceder en el Array si este no contiene todos los campos
        if (!telefono.isEmpty()){
            lbTelefono1.setText(String.valueOf(telefono.get(0)));
        }else{
            lbTelefono1.setText("");
        }

        if (telefono.size() == 2){
            lbTelefono2.setText(String.valueOf(telefono.get(1)));
        }else{
            lbTelefono2.setText("");
        }
        txNotas.setText(p.getNotas());
    }
    @FXML
    public void nuevoContacto(){

    }

}
