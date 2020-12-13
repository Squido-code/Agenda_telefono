package com.guillermo.agenda.controllers.login;

import com.guillermo.agenda.DAO.UsuarioDao;
import com.guillermo.agenda.beans.Usuario;
import com.guillermo.agenda.util.Herramientas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

import static com.guillermo.agenda.App.autorizado;

/**
 * @author Guillermo Suarez
 */
public class LoginController {
    public TextField txUser;
    public TextField txPass;
    public Button btAceptar;
    public Button btSalir;
    Herramientas tool;
    public LoginController() {
        tool= new Herramientas();
    }

    @FXML
    public void salirApp(){
        System.exit(0);
    }
    @FXML
    public void aceptarLogin(){
        UsuarioDao usuarioDao = new UsuarioDao();
        String user =txUser.getText();
        String pass = txPass.getText();
        Usuario usuario = new Usuario(user,pass);
        try {
            usuarioDao.conectar();
            Boolean isOk = usuarioDao.comprobarUsuario(usuario);
            usuarioDao.desconectar();
            if (!isOk) {
                tool.alertaInfo("Usuario o contraseña incorrectos");
                return;
            }
            Stage ventana = (Stage) this.btAceptar.getScene().getWindow();
            autorizado= true;
            ventana.close();
            } catch (SQLException throwables) {
            tool.alertaError("No se pudo conectar con la base de datos");
        } catch (ClassNotFoundException e) {
            tool.alertaError("Error crítico");
        }

    }
    @FXML
    private void cerrarVentana(ActionEvent event) {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
