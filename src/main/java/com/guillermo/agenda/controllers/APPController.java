package com.guillermo.agenda.controllers;

import com.guillermo.agenda.DAO.PersonaDAO;
import com.guillermo.agenda.DAO.TelefonoDAO;
import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.util.Herramientas;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;


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
    public Button btNuevoContacto;
    public Button BorrarRegistro;
    public Button btEditar;
    public Button btEliminar;
    public Button btBuscar;
    private Herramientas tool;
    private PersonaDAO persona_dao;
    private TelefonoDAO telefonoDao;
    private TextField txNNombre;
    private TextField txNApellido;
    private TextField txNdireccion;
    private TextField txNcp;
    private TextField txNpoblacion;
    private TextField txNtf1Nombre;
    private TextField txNtf1Numero;
    private TextField txNtf2Nombre;
    private TextField txNtf2Numero;


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
        PersonaDAO pdao = new PersonaDAO();
        Persona p = new Persona();
        Telefono t1 = new Telefono();
        Telefono t2 = new Telefono();
        ArrayList<Telefono> listaT = new ArrayList<>();
        p.setNombre(txNNombre.getText());
        p.setApellidos(txNApellido.getText());
        p.setDireccion(txNdireccion.getText());
        p.setCodigo_postal(txNcp.getText());
        p.setPoblacion(txNpoblacion.getText());
        t1.setNombre(txNtf1Nombre.getText());
        t1.setNumero(txNtf1Numero.getText());
        t2.setNombre(txNtf2Nombre.getText());
        t2.setNumero(txNtf2Numero.getText());
        listaT.add(t1);
        listaT.add(t2);
        p.setTelefono(listaT);
        try {
            pdao.insertar(p);
        } catch (SQLException throwables) {
            tool.alertaError("Error al conectar con la base de datos");
        }
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Contacto agregado correctamente");
        cargarDatos();
        borrarRegistro();
    }

    private void borrarRegistro() {
        txNNombre.setText("");
        txNApellido.setText("");
        txNdireccion.setText("");
        txNcp.setText("");
        txNpoblacion.setText("");
        txNtf1Nombre.setText("");
        txNtf1Numero.setText("");
        txNtf2Nombre.setText("");
        txNtf2Numero.setText("");
    }

}
