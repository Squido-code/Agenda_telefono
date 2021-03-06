package com.guillermo.agenda.DAO;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Telefono;
import com.guillermo.agenda.interfaces.interfazDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author Guillermo Suarez
 */
public class TelefonoDao extends ConexionDao implements interfazDAO<Telefono> {


    public ArrayList<Telefono> listarTelefonos (Persona p) throws SQLException {
        String sql = "SELECT * FROM telefonos where id_persona = ?";
        ResultSet rs = null;
        ArrayList<Telefono> listaTelefonos = new ArrayList<>();
        PreparedStatement st = conexion.prepareStatement(sql);
        st.setInt(1, p.getIdpersona());
        rs = st.executeQuery();

        while (rs.next()) {
            Telefono t = new Telefono();
            t.setIdTelefono(rs.getInt("id_telefono"));
            t.setNombre(rs.getString("nombre"));
            t.setNumero(rs.getString("numero"));
            listaTelefonos.add(t);
        }
        return listaTelefonos;

    }

    @Override
    public ArrayList<Telefono> listar() throws SQLException {
        return null;
    }

    @Override
    public void insertar(Telefono t) throws SQLException {
        String sql = "INSERT INTO telefonos (id_persona,nombre,numero) values (?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, t.getIdPersona());
        sentencia.setString(2, t.getNombre());
        sentencia.setString(3, t.getNumero());
        sentencia.executeUpdate();
    }

    @Override
    public void modificar(Telefono telefono) throws SQLException {
        String sql = "UPDATE telefonos set nombre = ?,numero = ? where id_telefono=?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, telefono.getNombre());
        sentencia.setString(2,telefono.getNumero());
        sentencia.setInt(3,telefono.getIdTelefono());
        sentencia.executeUpdate();
    }

    @Override
    public void eliminar(Telefono object) throws SQLException {

    }

    @Override
    public int id(Telefono object) throws SQLException {
        return 0;
    }
}
