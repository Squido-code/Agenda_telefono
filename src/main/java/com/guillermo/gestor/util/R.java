
package com.guillermo.gestor.util;

import java.io.File;
import java.net.URL;
/**
 * @author Guillermo Suarez
 *
 */
public class R {
    //metodo para recuperar recursos de la carpeta ui
    //usamos file separator para pintar /
    public static URL getUI(String name){
        return Thread.currentThread().getContextClassLoader().getResource("ui"+ File.separator+name);
    }
}
