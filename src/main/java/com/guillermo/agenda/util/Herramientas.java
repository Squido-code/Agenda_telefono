package com.guillermo.agenda.util;

import javafx.scene.control.Alert;

/**
 * @author squid
 */
public class Herramientas {
    public void alertaError (String texto){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(texto);
        alerta.show();
    }
}
