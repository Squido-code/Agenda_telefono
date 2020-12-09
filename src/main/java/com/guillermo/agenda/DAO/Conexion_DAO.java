package com.guillermo.agenda.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Guillermo Suarez
 */
public class Conexion_DAO {
    protected Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_telefonos?serverTimezone=UTC","guillermo","1234");
    }
    public void desconectar() throws SQLException {
        conexion.close();
    }
}
