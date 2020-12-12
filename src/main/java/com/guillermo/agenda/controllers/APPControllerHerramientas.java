package com.guillermo.agenda.controllers;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.controllers.APPController;
import com.guillermo.agenda.controllers.ComponentesController;

import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class APPControllerHerramientas extends ComponentesController {

    public void modoEdicionContacto (Boolean activar){
        //ocultamos todos los labels
        lbNombre.setVisible(!activar);
        lbApellidos.setVisible(!activar);
        lbDireccion.setVisible(!activar);
        lbTelefono1.setVisible(!activar);
        lbTelefono2.setVisible(!activar);
        txNotas.setDisable(activar);
        //hacemos visibles los textfields
        txNombreEdit.setVisible(activar);
        txApellidosEdit.setVisible(activar);
        txDireEdit.setVisible(activar);
        txCpEdit.setVisible(activar);
        txPoblaEdit.setVisible(activar);
        txt1NonmbreEdit.setVisible(activar);
        txt1NumeroEdit.setVisible(activar);
        txt2NonmbreEdit.setVisible(activar);
        txt2NumeroEdit.setVisible(activar);
        //hacemos visible los lables complementarios
        lbCP.setVisible(activar);
        lbPoblacion.setVisible(activar);
        lbnumero1edit.setVisible(activar);
        lbnumero2edit.setVisible(activar);
        lbt1nombreedit.setVisible(activar);
        lbt2nombreedit.setVisible(activar);
    }
    public void edicionTxField(){
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
