package com.guillermo.agenda.controllers;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.util.Herramientas;

import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class APPControllerHerramientas extends ComponentesController {

    private Herramientas tool = new Herramientas();

    protected void modoEdicionContacto (Boolean activar){
        //ocultamos todos los labels y la busqueda
        lbNombre.setVisible(!activar);
        lbApellidos.setVisible(!activar);
        lbDireccion.setVisible(!activar);
        lbTelefono1.setVisible(!activar);
        lbTelefono2.setVisible(!activar);
        lbNombreDescripcion.setVisible(!activar);
        lbApellidosDescripcion.setVisible(!activar);
        lbDireccionDescripcion.setVisible(!activar);
        lbTelefonoDescripcion.setVisible(!activar);
        txBuscarNombre.setVisible(!activar);
        txBuscarApellidos.setVisible(!activar);
        btBuscar.setVisible(!activar);
        lbTituloAgenda.setVisible(!activar);
        lbBusqueda.setVisible(!activar);
        lbNombreBusqueda.setVisible(!activar);
        lbApellidosBusqueda.setVisible(!activar);

        //desactivamos los botones y listas
        btEliminar.setDisable(activar);
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
        personaEdit.setId_persona(personaOriginal.getIdpersona());
        personaEdit.setNombre(txNombreEdit.getText());
        personaEdit.setApellidos(txApellidosEdit.getText());
        personaEdit.setDireccion(txDireEdit.getText());
        personaEdit.setCodigo_postal(txCpEdit.getText());
        personaEdit.setPoblacion(txPoblaEdit.getText());
        //obtenemos telefonos y controlamos errores de acceso al array
        ArrayList<Telefono> listaTelefono = new ArrayList<>();
        //si tiene ya telefonos almacenados
        String teleNombre;
        String teleNumero;
        if(!personaOriginal.getTelefono().isEmpty()){
            Boolean id1IsEmpty = personaOriginal.getTelefono().get(0).idIsEmpty();
            if(!id1IsEmpty){
                int id1 = personaOriginal.getTelefono().get(0).getIdTelefono();
                teleNombre = txt1NonmbreEdit.getText();
                teleNumero = txt1NumeroEdit.getText();
                Telefono telefono = new Telefono(id1,teleNombre,teleNumero);
                listaTelefono.add(telefono);
            }
        }else{
            teleNombre = txt1NonmbreEdit.getText();
            teleNumero = txt1NumeroEdit.getText();
            Telefono telefono = new Telefono(teleNombre,teleNumero);
            listaTelefono.add(telefono);
        }
        if(personaOriginal.getTelefono().size()==2){
            Boolean id2ISEmpty = personaOriginal.getTelefono().get(1).idIsEmpty();
            if(!id2ISEmpty){
                int id2 = personaOriginal.getTelefono().get(1).getIdTelefono();
                teleNombre = txt2NonmbreEdit.getText();
                teleNumero = txt2NumeroEdit.getText();
                Telefono telefono2 = new Telefono(id2,teleNombre,teleNumero);
                listaTelefono.add(telefono2);
            }
        }else{
            teleNombre = txt2NonmbreEdit.getText();
            teleNumero = txt2NumeroEdit.getText();
            Telefono telefono = new Telefono(teleNombre,teleNumero);
            listaTelefono.add(telefono);
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
    public void pintarInicioPersona(Persona p){
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

        if (telefono.size() == 2) {
            lbTelefono2.setText(String.valueOf(telefono.get(1)));
        } else {
            lbTelefono2.setText("");
        }
        txNotas.setText(p.getNotas());
    }
}
