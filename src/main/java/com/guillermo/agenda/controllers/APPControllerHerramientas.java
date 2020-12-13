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
        btEditarContacto.setDisable(activar);
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
        if(!listaTelefonos.isEmpty()){
            Boolean nombre1isEmpty =listaTelefonos.get(0).getNombre().isEmpty();
            Boolean numero1isEmpty = listaTelefonos.get(0).getNumero().isEmpty();
            if(!nombre1isEmpty){
                txt1NonmbreEdit.setText(listaTelefonos.get(0).getNombre());

            }
            if (!numero1isEmpty){
                txt1NumeroEdit.setText(listaTelefonos.get(0).getNumero());
            }
        }
        if(listaTelefonos.size()==2){
            Boolean nombre2isEmpty = listaTelefonos.get(1).getNombre().isEmpty();
            Boolean numero2isEmpty = listaTelefonos.get(1).getNumero().isEmpty();
            if (!nombre2isEmpty){
                txt2NonmbreEdit.setText(listaTelefonos.get(1).getNombre());
            }
            if(!numero2isEmpty){
                txt2NumeroEdit.setText(listaTelefonos.get(1).getNumero());
            }
        }



    }
    protected Persona personaEditada(){
        Persona personaEdit = new Persona();
        Persona personaOriginal;
        personaOriginal = lvLista.getSelectionModel().getSelectedItem();
        personaEdit.setId_persona(personaOriginal.getId_persona());
        personaEdit.setNombre(txNombreEdit.getText());
        personaEdit.setApellidos(txApellidosEdit.getText());
        personaEdit.setDireccion(txDireEdit.getText());
        personaEdit.setCodigo_postal(txCpEdit.getText());
        personaEdit.setPoblacion(txPoblaEdit.getText());
        //obtenemos telefonos y controlamos errores de acceso al array
        ArrayList<Telefono> listaTelefono = new ArrayList<>();
        if(!personaOriginal.getTelefono().isEmpty()){
            Boolean id1IsEmpty = personaOriginal.getTelefono().get(0).idIsEmpty();
            if(!id1IsEmpty){
                int id1 = personaOriginal.getTelefono().get(0).getIdTelefono();
                String teleNombre1 = txt1NonmbreEdit.getText();
                String teleNumero1 = txt1NumeroEdit.getText();
                Telefono telefono1 = new Telefono(id1,teleNombre1,teleNumero1);
                listaTelefono.add(telefono1);
            }
        }
        if(personaOriginal.getTelefono().size()==2){
            Boolean id2ISEmpty = personaOriginal.getTelefono().get(1).idIsEmpty();
            if(!id2ISEmpty){
                int id2 = personaOriginal.getTelefono().get(1).getIdTelefono();
                String teleNombre2 = txt2NonmbreEdit.getText();
                String teleNumero2 = txt2NumeroEdit.getText();
                Telefono telefono2 = new Telefono(id2,teleNombre2,teleNumero2);
                listaTelefono.add(telefono2);
            }


        }
        personaEdit.setTelefono(listaTelefono);
        return personaEdit;
    }
    public void limpiarPrincipal(){
        lbNombre.setText("");
        lbApellidos.setText("");
        lbDireccion.setText("");
        lbTelefono1.setText("");
        lbTelefono2.setText("");
    }
    public void limpiarEdicion(){
        txNombreEdit.setText("");
        txApellidosEdit.setText("");
        txDireEdit.setText("");
        txCpEdit.setText("");
        txPoblaEdit.setText("");
        txt1NonmbreEdit.setText("");
        txt1NumeroEdit.setText("");
        txt2NonmbreEdit.setText("");
        txt2NumeroEdit.setText("");
    }
}
