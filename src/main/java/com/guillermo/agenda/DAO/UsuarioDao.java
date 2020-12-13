package com.guillermo.agenda.DAO;

import com.guillermo.agenda.beans.Persona;
import com.guillermo.agenda.beans.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Guillermo Suarez
 */
public class UsuarioDao extends ConexionDao{
    public Boolean comprobarUsuario(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios where nombre= ? AND contraseña = ?";
        ResultSet rs = null;

        PreparedStatement st = conexion.prepareStatement(sql);
        st.setString(1, usuario.getUsuario());
        st.setString(2, usuario.getContraseña());
        rs = st.executeQuery();
        if(rs==null){
            return false;
        }else{
            return true;
        }
    }
}
