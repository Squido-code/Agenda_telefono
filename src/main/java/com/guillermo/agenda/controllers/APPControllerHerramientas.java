package com.guillermo.agenda.controllers;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;

import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class APPControllerHerramientas extends ComponentesController {

    protected void modoEdicionContacto (Boolean activar){
        //ocultamos todos los labels
        lbNombre.setVisible(!activar);
        lbApellidos.setVisible(!activar);
        lbDireccion.setVisible(!activar);
        lbTelefono1.setVisible(!activar);
        lbTelefono2.setVisible(!activar);
        lbNombreDescripcion.setVisible(!activar);
        lbApellidosDescripcion.setVisible(!activar);
        lbDireccionDescripcion.setVisible(!activar);
        lbTelefonoDescripcion.setVisible(!activar);
        //desactivamos los botones y listas
        txNotas.setDisable(activar);
        lvLista.setDisable(activar);
        btEditarNotas.setDisable(activar);
        //mostramos la pantalla de edicion y sus botones
        pEditar.setVisible(activar);
        btAceptarEdit.setVisible(activar);
        btCancelarEdit.setVisible(activar);
    }
    protected void edicionTxField(){
        Persona pAntigua = lvLista.getSelectionModel().getSelectedItem();
        txNombreEdit.setText(pAntigua.getNombre());
        txApellidosEdit.setText(pAntigua.getApellidos());
        txDireEdit.setText(pAntigua.getDireccion());
        txCpEdit.setText(pAntigua.getCodigo_postal());
        txPoblaEdit.setText(pAntigua.getPoblacion());
        ArrayList<Telefono> listaTelefonos = new ArrayList<>();
        listaTelefonos=pAntigua.getTelefono();
        txt1NonmbreEdit.setText(listaTelefonos.get(0).getNombre());
        txt1NumeroEdit.setText(listaTelefonos.get(0).getNumero());
        txt2NonmbreEdit.setText(listaTelefonos.get(1).getNombre());
        txt2NumeroEdit.setText(listaTelefonos.get(1).getNumero());

    }
}
