package com.guillermo.agenda.util;

import com.guillermo.agenda.DAO.PersonaDao;
import com.guillermo.agenda.DAO.TelefonoDao;
import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Guillermo Suarez
 */
public class Herramientas {
    /**
     * metodo para mostrar por pantalla excepciones
     *
     * @param texto
     */
    public void alertaError(String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(texto);
        alerta.show();
    }

    /**
     * Método que añade los telefonos asignados al id de la persona.
     *
     * @param p
     * @return Persona
     * @throws SQLException
     */
    public Persona personaCompleta(Persona p) throws SQLException, ClassNotFoundException {
        PersonaDao personaDAO = new PersonaDao();
        TelefonoDao telefonoDAO = new TelefonoDao();
        telefonoDAO.conectar();
        ArrayList<Telefono> listaTelefonos;
        listaTelefonos = telefonoDAO.listarTelefonos(p);
        p.setTelefono(listaTelefonos);
        telefonoDAO.desconectar();
        return p;
    }


    public ArrayList<Persona> Listas_personas_completa() throws SQLException, ClassNotFoundException {
        Herramientas tool = new Herramientas();
        PersonaDao personaDAO = new PersonaDao();
        personaDAO.conectar();
        ArrayList<Persona> listaPersonas;
        //obtenemos todas las personas sin telefonos
        listaPersonas = personaDAO.listar();
        //creamos lista para añadir las personas con su telefono
        ArrayList<Persona> listaCompleta = new ArrayList<>();
        //desgranamos cada persona
        for (Persona p : listaPersonas
        ) {
            Persona pCompleta = new Persona();
            //completamos cada persona con sus telefonos
            pCompleta = tool.personaCompleta(p);
            //añadimos la persona completa a una nueva lista
            listaCompleta.add(pCompleta);
        }
        personaDAO.desconectar();
        return listaCompleta;
    }

    /**
     * Método que combina los campos direccion, codigo postal y poblacion para crear una única String.
     *
     * @param dire
     * @param codP
     * @param pob
     * @return String
     */
    public String direccionCompleta(String dire, String codP, String pob) {
        String todo = dire +"\n"+ "CP: " + codP +"\n"+ pob;
        return todo;
    }

    /**
     * Método para mostar alertas
     * @param string
     */
    public void alertaInfo(String string) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(string);
        alerta.show();
    }
}
