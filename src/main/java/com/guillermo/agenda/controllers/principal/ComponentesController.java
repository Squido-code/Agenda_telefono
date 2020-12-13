package com.guillermo.agenda.controllers.principal;

import com.guillermo.agenda.beans.Persona;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * @author Guillermo Suarez
 */
public class ComponentesController {
    public ListView<Persona> lvLista;
    public Label lbNombre;
    public Label lbApellidos;
    public Label lbDireccion;
    public Label lbTelefono1;
    public Label lbTelefono2;
    public Label lbNombreDescripcion;
    public Label lbApellidosDescripcion;
    public Label lbDireccionDescripcion;
    public Label lbTelefonoDescripcion;
    public Label lbTituloAgenda;
    public Label lbBusqueda;
    public Label lbNombreBusqueda;
    public Label lbApellidosBusqueda;


    public TextArea txNotas;
    public Button btEditarNotas;
    public Button btEditarContacto;
    public Button btAceptarEdit;
    public Button btCancelarEdit;
    public Button btEliminar;
    public Button btBuscar;

    public TextField txNNombre;
    public TextField txNApellido;
    public TextField txNdireccion;
    public TextField txNcp;
    public TextField txNpoblacion;
    public TextField txNtf1Nombre;
    public TextField txNtf1Numero;
    public TextField txNtf2Nombre;
    public TextField txNtf2Numero;

    public TextField txNombreEdit;
    public TextField txApellidosEdit;
    public TextField txDireEdit;
    public TextField txCpEdit;
    public TextField txPoblaEdit;
    public TextField txt1NonmbreEdit;
    public TextField txt1NumeroEdit;
    public TextField txt2NonmbreEdit;
    public TextField txt2NumeroEdit;

    public TextField txBuscarNombre;
    public TextField txBuscarApellidos;
    public Pane pEditar;
}
