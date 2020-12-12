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
public class PersonaDAO extends ConexionDAO implements interfazDAO<Persona> {


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
    public void modificar(Persona pAntiguo, Persona pNueva) throws SQLException {
        String sql = "UPDATE personas SET nombre = ?, apellidos = ?, direccion = ?,poblacion = ?,codigo postal = ?, notas = ? WHERE id_persona = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, pNueva.getNombre());
        sentencia.setString(2, pNueva.getApellidos());
        sentencia.setString(3,pNueva.getDireccion());
        sentencia.setString(4,pNueva.getCodigo_postal());
        sentencia.setString(5,pNueva.getPoblacion());
        sentencia.setString(6,pNueva.getNotas());
        sentencia.setInt(7,pAntiguo.getId_persona());
        sentencia.executeUpdate();
    }
    public void modificarTelefono(int numero,Persona p){
        String sql = "UPDATE telefono SET numero = ? WHERE id_telefono = ?";
    }

    @Override
    public void eliminar(Persona p) throws SQLException {

    }
    public int obtener_id(Persona p) throws SQLException {
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
}
