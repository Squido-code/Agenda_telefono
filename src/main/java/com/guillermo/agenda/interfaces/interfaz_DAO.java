package com.guillermo.agenda.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;


public interface interfaz_DAO<B> {
    ArrayList<B> listar() throws SQLException;
    void insertar(B object) throws SQLException;
    void update(int id) throws SQLException;
    void eliminar(int id) throws SQLException;


}
