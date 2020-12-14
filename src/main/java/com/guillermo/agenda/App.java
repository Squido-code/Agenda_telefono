package com.guillermo.agenda;

import com.guillermo.agenda.controllers.login.LoginController;
import com.guillermo.agenda.controllers.principal.APPController;
import com.guillermo.agenda.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Guillermo Suarez
 */
public class App extends Application {
    //atributo para controlar que no se inicia la aplicacion sin el login
    public static boolean isAutorizado = false;
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginUi();
        if(!isAutorizado){
            System.exit(0);
        }
        //me creo el loader para poder cargar la pantalla
        FXMLLoader loader = new FXMLLoader();
        //loader carga la pantalla
        loader.setLocation(R.getUI("uiPrincipal2_1.fxml"));
        //cargo la clase donde esta el codigo vincula a la ventana
        APPController controller = new APPController();
        loader.setController(controller);
        //cargamos todo el codigo dentro de un vbox
        VBox vbox = loader.load();
        //una vez cargada la pantalla cargamos los datos
        controller.cargarDatos();
        //creamos una escena donde esta el vbox
        Scene scene = new Scene(vbox);
        //cargamos esa escena dentro del excenario que es la applicacion
        stage.setScene(scene);
        //mostramos el teatro
        stage.show();
    }
    public void loginUi() throws IOException {
        //me creo el loader para poder cargar la pantalla
        FXMLLoader loader = new FXMLLoader();
        //loader carga la pantalla
        loader.setLocation(R.getUI("uiLogin.fxml"));
        //cargo la clase donde esta el codigo vincula a la ventana
        LoginController controller = new LoginController();
        loader.setController(controller);
        Pane pane = loader.load();
        Stage ventana = new Stage();
        ventana.setTitle("Login");
        Scene scene = new Scene(pane);
        ventana.setScene(scene);
        ventana.initStyle(StageStyle.UNDECORATED);
        ventana.showAndWait();
    }



}

