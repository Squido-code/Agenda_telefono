package com.guillermo.agenda.controllers;

import com.guillermo.agenda.DAO.PersonaDao;
import com.guillermo.agenda.DAO.TelefonoDao;
import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.util.Herramientas;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

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
        pintarInicioPersona(p);
    }

    /**
     * Método que inserta un nuevo contacto en la base de datos
     */
    @FXML
    public void nuevoContacto() {
        PersonaDao pdao = new PersonaDao();
        TelefonoDao telefonoDao = new TelefonoDao();
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

                telefonoDao.conectar();
                t1.setIdPersona(idPersona);
                telefonoDao.insertar(t1);
                telefonoDao.desconectar();

            telefonoDao.conectar();
            t2.setIdPersona(idPersona);
            telefonoDao.insertar(t2);
            telefonoDao.desconectar();



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
    public void borrarRegistro() {
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
    public void editarNotas(){
        PersonaDao personaDAO = new PersonaDao();
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
    public void entrarModoEdicion(){
        modoEdicionContacto(true);
        edicionTxField();
    }
    @FXML
    public void editarContacto(){
        PersonaDao personaDao = new PersonaDao();
        TelefonoDao telefonoDao =new TelefonoDao();
        Persona persona = personaEditada();
        try {
            personaDao.conectar();
            personaDao.modificar(persona);
            personaDao.desconectar();
            //editamos sus telefonos
            telefonoDao.conectar();
            int id;
            String nombre,numero;
            //telefono 1
            Telefono telefono = new Telefono();

           id = persona.getTelefono().get(0).getIdTelefono();
            //comprobamos si el telefono existia antes y lo actualizamos, o creamos una entrada nueva
           if(id==-1){
               telefono.setIdPersona(persona.getIdpersona());
               telefono.setNombre(txt1NonmbreEdit.getText());
               telefono.setNumero(txt1NumeroEdit.getText());
               telefonoDao.insertar(telefono);
           }else{
               telefono.setIdTelefono(id);
               nombre =persona.getTelefono().get(0).getNombre();
               numero = persona.getTelefono().get(0).getNumero();
               telefono.setNombre(nombre);
               telefono.setNumero(numero);
               telefonoDao.modificar(telefono);
           }

            //telefono 2
            if(persona.getTelefono().size()==2){
                id= persona.getTelefono().get(1).getIdTelefono();
                //comprobamos si el telefono existia antes y lo actualizamos, o creamos una entrada nueva
                if(id!=-1 && persona.getIdpersona()!=-1){
                    telefono.setIdPersona(persona.getIdpersona());
                    telefono.setNombre(txt2NonmbreEdit.getText());
                    telefono.setNumero(txt2NumeroEdit.getText());
                    telefonoDao.insertar(telefono);
                }else{
                    nombre = persona.getTelefono().get(1).getNombre();
                    numero = persona.getTelefono().get(1).getNumero();
                    telefono.setIdTelefono(id);
                    telefono.setNombre(nombre);
                    telefono.setNumero(numero);
                    telefonoDao.modificar(telefono);
                }
            }
            telefonoDao.desconectar();
            cargarDatos();
            limpiarPrincipal();
            limpiarEdicion();
            tool.alertaInfo("El contacto se ha actualizado correctamente");
            modoEdicionContacto(false);
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error crítico");
        } catch (SQLException throwables) {
            tool.alertaError("Error al conectar con la base de datos");
        }

    }
    @FXML
    public void cancelarEdicion(){
        modoEdicionContacto(false);
    }
    @FXML
    public void eliminarContacto(){
        PersonaDao personaDao = new PersonaDao();
        Persona persona = lvLista.getSelectionModel().getSelectedItem();
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar contacto");
        confirmacion.setContentText("¿Estás seguro?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            return;
        }
        try {
            personaDao.conectar();
            personaDao.eliminar(persona);
            personaDao.desconectar();
            cargarDatos();
            limpiarPrincipal();
            btEditarContacto.setDisable(true);//al seleccionar un contacto para eliminar se habilitaba
        } catch (SQLException throwables) {
            tool.alertaError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error crítico");
        }
    }
    @FXML
    public void buscarContacto(){
        Boolean nombreIsEmpty = txBuscarNombre.getText().isEmpty();
        Boolean apellidosIsEmpty = txBuscarApellidos.getText().isEmpty();
        if(nombreIsEmpty || apellidosIsEmpty){
            tool.alertaError("El nombre y los apellidos es obligatorio");
            return;
        }
        String nombre = txBuscarNombre.getText();
        String apellidos = txBuscarApellidos.getText();
        PersonaDao personaDao = new PersonaDao();
        Persona persona = new Persona(nombre,apellidos);
        try {
            personaDao.conectar();
            Persona personaBuscada = new Persona();
            personaBuscada = personaDao.buscarNombre(persona);
            if(personaBuscada.idIsEmpty()){
                tool.alertaInfo("La persona buscada no existe");
                return;
            }
            Persona personaCompleta = tool.personaCompleta(personaBuscada);
            tool.alertaInfo("Busqueda con éxito");
            pintarInicioPersona(personaCompleta);
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error al conectar con la base de datos");
        } catch (SQLException throwables) {
            tool.alertaError("Error crítico");
        }


    }

}
