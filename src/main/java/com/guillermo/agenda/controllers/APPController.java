package com.guillermo.agenda.controllers;

import com.guillermo.agenda.DAO.PersonaDAO;
import com.guillermo.agenda.DAO.TelefonoDAO;
import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.util.Herramientas;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class APPController extends APPControllerHerramientas{

    private Herramientas tool;



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
     *
     * @param event
     */
    @FXML
    public void seleccionarPersona(Event event) {
        Persona p = lvLista.getSelectionModel().getSelectedItem();
        ArrayList<Telefono> telefono = p.getTelefono();
        lbNombre.setText(p.getNombre());
        lbApellidos.setText(p.getApellidos());
        lbDireccion.setText(tool.direccionCompleta(p.getDireccion(), p.getCodigo_postal(), p.getPoblacion()));
        //controlamos erroes al acceder en el Array si este no contiene todos los campos
        if (!telefono.isEmpty()) {
            lbTelefono1.setText(String.valueOf(telefono.get(0)));
        } else {
            lbTelefono1.setText("");
        }

        if (telefono.size() == 2) {
            lbTelefono2.setText(String.valueOf(telefono.get(1)));
        } else {
            lbTelefono2.setText("");
        }
        txNotas.setText(p.getNotas());
        btEditarContacto.setDisable(false);
    }

    /**
     * Método que inserta un nuevo contacto en la base de datos
     */
    @FXML
    public void nuevoContacto() {
        PersonaDAO pdao = new PersonaDAO();
        TelefonoDAO telefonoDao = new TelefonoDAO();
        Persona p = new Persona();
        Telefono t1 = new Telefono();
        Telefono t2 = new Telefono();

        p.setNombre(txNNombre.getText());
        p.setApellidos(txNApellido.getText());
        p.setDireccion(txNdireccion.getText());
        p.setCodigo_postal(txNcp.getText());
        p.setPoblacion(txNpoblacion.getText());

        t1.setNombre(txNtf1Nombre.getText());
        t1.setNumero(txNtf1Numero.getText());

        t2.setNombre(txNtf2Nombre.getText());
        t2.setNumero(txNtf2Numero.getText());
        /*controlamos que se metan el nombre y el apellido,
         *estos son necesarios para recuperar el id de la persona.
         */
        if (p.getNombre().isEmpty() || p.getApellidos().isEmpty()){
            tool.alertaError("El nombre y los apellidos son obligatorios");
            return;
        }
        try {
            pdao.conectar();
            pdao.insertar(p);
            int idPersona=pdao.id(p);
            pdao.desconectar();
            /*
            Controlamos que haya telefonos antes de hacer el insert
             */
            if (t1.getNombre().isEmpty() || t1.getNumero().isEmpty()) {
                tool.alertaError("El nombre en telefono 1 es obligatorio");
                return;
            } else {
                telefonoDao.conectar();
                t1.setIdPersona(idPersona);
                telefonoDao.insertar(t1);
                telefonoDao.desconectar();
            }
            if (t2.getNombre().isEmpty() || t2.getNumero().isEmpty()) {
                tool.alertaError("El nombre en telefono 1 es obligatorio");
                return;
            } else {
            telefonoDao.conectar();
            t2.setIdPersona(idPersona);
            telefonoDao.insertar(t2);
            telefonoDao.desconectar();
            }


        } catch (SQLException throwables) {
            tool.alertaError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error crítico de aplicacion");
        }
        tool.alertaInfo("El contacto se ha registrado correctamente");
        cargarDatos();
        borrarRegistro();
    }

    /**
     * Método que borra los textfields de la zona de nuevo contacto.
     */
    @FXML
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
    @FXML
    private void editarNotas(){
        PersonaDAO personaDAO = new PersonaDAO();
        Persona p = lvLista.getSelectionModel().getSelectedItem();
        p.setNotas(txNotas.getText());
        try {
            personaDAO.conectar();
            personaDAO.editarNota(p);
            personaDAO.desconectar();
            tool.alertaInfo("Notas actualizadas correctamente");
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error crítico");
        } catch (SQLException throwables) {
            tool.alertaError("No se ha podido conectar a la base de datos");
        }
    }
    @FXML
    private void editarContacto(){
        modoEdicionContacto(true);
        edicionTxField();



    }
}
