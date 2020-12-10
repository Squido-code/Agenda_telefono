package com.guillermo.agenda.controllers;

import com.guillermo.agenda.DAO.Conexion_DAO;
import com.guillermo.agenda.DAO.Persona_DAO;
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
    public Label lbTelefono_1;
    public Label lbTelefono_2;
    public TextArea txNotas;
    public Button btNuevo;
    public Button btEditar;
    public Button btEliminar;
    public Button btBuscar;
    private final Herramientas tool;
    private final Persona_DAO persona_dao;

    public APPController() {
        Conexion_DAO baseDatos = new Conexion_DAO();
        persona_dao = new Persona_DAO();
        tool = new Herramientas();
        try {
            persona_dao.conectar();
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error critico, programa dañado");
        } catch (SQLException throwables) {
            tool.alertaError("Error al conectar con la base de datos");
        }
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
            lbTelefono_1.setText(String.valueOf(telefono.get(0)));
        }else{
            lbTelefono_1.setText("");
        }

        if (telefono.size() == 2){
            lbTelefono_2.setText(String.valueOf(telefono.get(1)));
        }else{
            lbTelefono_2.setText("");
        }
        txNotas.setText(p.getNotas());
    }
    @FXML
    public void nuevoContacto(){

    }

}