package com.guillermo.agenda.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;


public interface interfazDAO<B> {
    ArrayList<B> listar() throws SQLException;
    void insertar(B object) throws SQLException;
    void modificar(B object, B object2) throws SQLException;
    void eliminar(B object) throws SQLException;
    int id(B object) throws  SQLException;


}
