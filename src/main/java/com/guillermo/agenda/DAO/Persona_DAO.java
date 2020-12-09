package com.guillermo.agenda.DAO;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.interfaces.interfaz_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author squid
 */
public class Persona_DAO extends Conexion_DAO implements interfaz_DAO<Persona> {


    @Override
    public ArrayList<Persona> listar() throws SQLException {
        String sql = "SELECT * FROM personas ";
        ResultSet rs = null;
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        PreparedStatement st = conexion.prepareStatement(sql);
        rs = st.executeQuery();
        while (rs.next()) {
            Persona persona = new Persona();
            persona.setId_persona(rs.getInt("id_persona"));
            persona.setNombre(rs.getString("nombre"));
            persona.setApellidos(rs.getString("apellidos"));
            persona.setDireccion(rs.getString("direccion"));
            persona.setCodigo_postal(rs.getString("codigo postal"));
            persona.setPoblacion(rs.getString("poblacion"));
            persona.setNotas(rs.getString("notas"));
            listaPersonas.add(persona);
        }
        return listaPersonas;
    }

    public ArrayList<String> listarTelefonos(int id_persona) throws SQLException {
        String sql = "SELECT * FROM telefonos where id_persona = ?";
        ResultSet rs = null;
        ArrayList<String> listaTelefonos = new ArrayList<>();
        PreparedStatement st = conexion.prepareStatement(sql);
        st.setInt(1, id_persona);
        rs = st.executeQuery();
        while (rs.next()) {
            listaTelefonos.add(rs.getString("telefono"));
        }
        return listaTelefonos;
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
