package com.guillermo.agenda.DAO;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.interfaces.interfaz_DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author squid
 */
public class Persona_DAO implements interfaz_DAO<Persona> {

    @Override
    public ArrayList<Persona> listar() throws SQLException {
        return null;
    }

    @Override
    public void insertar(Persona p) throws SQLException {

    }

    @Override
    public void update(int id) throws SQLException {

    }

    @Override
    public void eliminar(int id) throws SQLException {

    }
}
