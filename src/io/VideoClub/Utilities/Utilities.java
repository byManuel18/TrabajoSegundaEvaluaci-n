/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manueh
 */
public class Utilities {
    private final static String EXPRESION_NOMBRE="^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+"
            + "[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
    private static boolean validarNombre(String nombre){
        boolean correcto=false;
        Pattern pat=Pattern.compile(EXPRESION_NOMBRE);
        Matcher mat=pat.matcher(nombre);

        if(mat.matches()){
            correcto=true;
        }

        return correcto;
    }
}
