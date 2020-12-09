package com.guillermo.agenda;

import com.guillermo.agenda.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Guillermo Suarez
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //me creo el loader para poder cargar la pantalla
        FXMLLoader loader = new FXMLLoader();
        //loader carga la pantalla
        loader.setLocation(R.getUI("uiPrincipal.fxml"));
        //cargo la clase donde esta el codigo vincula a la ventana
        APPController controller = new APPController();
        loader.setController(controller);
        //cargamos todo el codigo dentro de un vbox
        VBox vbox = loader.load();
        //una vez cargada la pantalla cargamos los datos
        //controller.cargarDatos();
        //creamos una escena donde esta el vbox
        Scene scene = new Scene(vbox);
        //cargamos esa escena dentro del excenario que es la applicacion
        stage.setScene(scene);
        //mostramos el teatro
        stage.show();
    }
}

