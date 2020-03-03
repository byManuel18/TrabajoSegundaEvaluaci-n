/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Utilities;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manueh
 */
public class Utilities {
    private static Scanner keyboard = new Scanner(System.in);
    private final static String EXPRESION_NOMBRE="^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+"
            + "[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
    
    /**
     * Método que valida un nombre
     * @param nombre el nombre a validar
     * @return devulve true si es un nombre válido y false si no lo es
     */
    private static boolean validarNombre(String nombre){
        boolean correcto=false;
        Pattern pat=Pattern.compile(EXPRESION_NOMBRE);
        Matcher mat=pat.matcher(nombre);

        if(mat.matches()){
            correcto=true;
        }

        return correcto;
    }
    /**
     * Escribe un texto en consola sin retorno de carro
     * @param f texto a imprimir
     */
    public static void p(String f) {
        System.out.print(f);
    }
    /**
     * Escribe un texto en consola con retorno de carro
     * @param f texto a imprimir
     */
    public static void P(String f) {
        Utilities.p(f + "\n");
    }
    /**
     * Lee un entero de teclado
     * @return devuelve el entero leído
     */
    public static int getInt() {
        int result = 0;
        boolean valid = false;
        do {
            try {
                result = Integer.parseInt(keyboard.nextLine());
                valid = true;

            } catch (IllegalStateException ex) {
                keyboard = new Scanner(System.in);
                Utilities.P("Error in keyboard. Please, try it again: ");
            } catch (NumberFormatException ex) {
                Utilities.P("Error reading integer type. Please, try it again: ");
            }catch (Exception ex) {
                ex.printStackTrace();
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }
    /**
     * Lee un entero de teclado
     * @param f Mensaje a imprimir al usuario antes de solicitar el entero
     * @return devuelve el entero leído
     */
    public static int getInt(String f) {
        Utilities.p(f + " : ");
        return Utilities.getInt();
    }
    /**
     * Lee un float de teclado
     * @return devuelve el float leído
     */
    public static double getDouble() {
        double result = 0;
        boolean valid = false;
        do {
            try {
                result = Double.parseDouble(keyboard.nextLine());
                valid = true;

            } catch (IllegalStateException ex) {
                keyboard = new Scanner(System.in);
                Utilities.P("Error in keyboard. Please, try it again: ");
            } catch (NumberFormatException ex) {
                Utilities.P("Error reading decimal number. Please, try it again: ");
            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            } 
        } while (!valid);
        return result;
    }
    /**
     * Lee un float del teclado, imprimiendo previamente un mensaje al usuario
     * @param f mensaje a imprimir antes de solicitar el float
     * @return float insertado por el usuario
     */
    public static double getFloat(String f) {
        Utilities.p(f + " : ");
        return Utilities.getDouble();
    }
    /**
     * Lee un string de teclado
     * @return strint insertado por el usuario
     */
    public static String getString() {
        String result = "";
        boolean valid = false;
        do {
            try {
                result = keyboard.nextLine();
                valid = true;

            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }
    /**
     * Lee un string de teclado, imprimiendo previamente un mensaje
     * @param f mensaje a mostrar antes de solicitar el string
     * @return string insertado por el usuario
     */
    public static String getString(String f) {
        Utilities.p(f + " : ");
        return Utilities.getString();
    }
   public static int Menu(){
        int opcion;
        
        P("-----Bienvenido al videoclub-----");
        P("1)Listar productos");
        P("2)Listar productos por tipo");
        P("3)Listar los productos ordenados");
        P("4)Listar productos por nombre");
        P("5)Listar productos por nombre y tipo");
        P("6)Listar los productos por estado");
        P("7)Listar diferentes productos");
        P("8)Listar peliculas");
        P("9)Listar juegos");
        P("10)Listar catidad de productos por nombre");
        P("11)Listar catidad de productos por nombre y tipo");
        P("12)Mostrar clientes");
        P("13)Mostrar todos los clientes(comparados)");
        P("14)Mostrar clientes cuya reserva no ha acabado");
        P("15)Mostrar todas las reservas ");
        P("16)Listar las reservas (ordenadas)");
        P("17)Listar reservas por estado");
        P("18)Crear Producto");
        P("19)Añadir cliente");
        P("20)Borrar cliente");
        P("21)Añadir producto");
        P("22)Borrar producto");
        P("23)Editar producto");
        P("24)Consultar productos disponibles");
        P("25)Cerrar reserva");
        P("26)Salir");
        P("-------------------------------------");
        
        opcion=getInt(); //Llama a la funcion LeerNUmeros para darle un valor por teclado a opción
        
        return opcion;
    }
}
