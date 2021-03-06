package com.guillermo.agenda.DAO;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.interfaces.interfazDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Guillermo Suarez
 */
public class PersonaDao extends ConexionDao implements interfazDAO<Persona> {


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
            persona.setCodigo_postal(rs.getString("codigo_postal"));
            persona.setPoblacion(rs.getString("poblacion"));
            persona.setNotas(rs.getString("notas"));
            listaPersonas.add(persona);
        }
        return listaPersonas;
    }

    public HashMap<Integer,String> listarTelefonos(int id_persona) throws SQLException {
        String sql = "SELECT * FROM telefonos where id_persona = ?";
        ResultSet rs = null;
        HashMap<Integer,String> listaTelefonos = new HashMap<>();
        PreparedStatement st = conexion.prepareStatement(sql);
        st.setInt(1, id_persona);
        rs = st.executeQuery();
        while (rs.next()) {
            listaTelefonos.put(rs.getInt("id_telefono"),rs.getString("numero"));
        }
        return listaTelefonos;
    }

    @Override
    public void insertar(Persona p) throws SQLException {
        String sql = "INSERT INTO personas (nombre,apellidos,direccion,codigo_postal,poblacion) values (?,?,?,?,?)";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1,p.getNombre());
        sentencia.setString(2,p.getApellidos());
        sentencia.setString(3,p.getDireccion());
        sentencia.setString(4,p.getCodigo_postal());
        sentencia.setString(5,p.getPoblacion());
        sentencia.executeUpdate();
    }

    @Override
    public void modificar(Persona persona) throws SQLException {
        String sql = "UPDATE personas SET nombre = ?, apellidos = ?, direccion = ?,codigo_postal = ?,poblacion = ?, notas = ? WHERE id_persona = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, persona.getNombre());
        sentencia.setString(2, persona.getApellidos());
        sentencia.setString(3,persona.getDireccion());
        sentencia.setString(4,persona.getCodigo_postal());
        sentencia.setString(5,persona.getPoblacion());
        sentencia.setString(6,persona.getNotas());
        sentencia.setInt(7,persona.getIdpersona());
        sentencia.executeUpdate();
    }

    @Override
    public void eliminar(Persona persona) throws SQLException {
        String sql = "DELETE FROM personas WHERE id_persona = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, persona.getIdpersona());
        sentencia.executeUpdate();
    }


    @Override
    public int id(Persona p) throws SQLException {
        int id_persona=0;
        String sql = "SELECT id_persona from personas where nombre=? AND apellidos=?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, p.getNombre());
        sentencia.setString(2, p.getApellidos());
        ResultSet rs = null;
        rs = sentencia.executeQuery();
        while (rs.next()) {
            id_persona=rs.getInt("id_persona");
        }
        return id_persona;
    }

    public void editarNota(Persona p) throws SQLException {
        String sql = "UPDATE personas set notas = ? where id_persona=?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, p.getNotas());
        sentencia.setInt(2,p.getIdpersona());
        sentencia.executeUpdate();
    }
    public Persona buscarNombre(Persona persona) throws SQLException {
        String sql = "SELECT * FROM personas where nombre= ? AND apellidos = ?";
        ResultSet rs = null;
        Persona p = new Persona();
        PreparedStatement st = conexion.prepareStatement(sql);
        st.setString(1,persona.getNombre());
        st.setString(2,persona.getApellidos());
        rs = st.executeQuery();
        while (rs.next()) {
            p.setId_persona(rs.getInt("id_persona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellidos(rs.getString("apellidos"));
            p.setDireccion(rs.getString("direccion"));
            p.setCodigo_postal(rs.getString("codigo_postal"));
            p.setPoblacion(rs.getString("poblacion"));
            p.setNotas(rs.getString("notas"));
        }
        return p;
    }
}
