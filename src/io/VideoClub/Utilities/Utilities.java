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
    private final static String EXPRESION_NOMBRE = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+"
            + "[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";

    /**
     * Método que valida un nombre
     *
     * @param nombre el nombre a validar
     * @return devulve true si es un nombre válido y false si no lo es
     */
    private static boolean validarNombre(String nombre) {
        boolean correcto = false;
        Pattern pat = Pattern.compile(EXPRESION_NOMBRE);
        Matcher mat = pat.matcher(nombre);

        if (mat.matches()) {
            correcto = true;
        }

        return correcto;
    }

    /**
     * Escribe un texto en consola sin retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void p(String f) {
        System.out.print(f);
    }

    /**
     * Escribe un texto en consola con retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void P(String f) {
        Utilities.p(f + "\n");
    }

    /**
     * Lee un entero de teclado
     *
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
            } catch (Exception ex) {
                ex.printStackTrace();
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un entero de teclado
     *
     * @param f Mensaje a imprimir al usuario antes de solicitar el entero
     * @return devuelve el entero leído
     */
    public static int getInt(String f) {
        Utilities.p(f + " : ");
        return Utilities.getInt();
    }

    /**
     * Lee un float de teclado
     *
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
     *
     * @param f mensaje a imprimir antes de solicitar el float
     * @return float insertado por el usuario
     */
    public static double getFloat(String f) {
        Utilities.p(f + " : ");
        return Utilities.getDouble();
    }

    /**
     * Lee un string de teclado
     *
     * @return strint insertado por el usuario
     */
    public static String getString() {
        String result = "";
        boolean valid = false;
        do {
            try {
                result = keyboard.nextLine().toUpperCase();
                valid = true;

            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un string de teclado, imprimiendo previamente un mensaje
     *
     * @param f mensaje a mostrar antes de solicitar el string
     * @return string insertado por el usuario
     */
    public static String getString(String f) {
        Utilities.p(f + " : ");
        return Utilities.getString();
    }

    public static int Menu() {
        int opcion;

        P("-----Bienvenido al videoclub-----");
        P("1)Listar productos");
        P("2)Mostrar clientes");
        P("3)Listar reservas");
        P("4)Añadir cliente");
        P("5)Crear producto");
        P("6)Añadir existencia de un producto");
        P("7)Editar producto");
        P("8)Borrar producto");
        P("9)Consultar productos disponibles");
        P("10)Añadir cliente");
        P("11)Borrar cliente");
        P("12)Editar cliente");
        P("13)Hacer reserva");
        P("14)Cerrar reserva");
        P("15)Salir");
        P("-------------------------------------");
        p("> ");

        opcion = getInt(); //Llama a la funcion LeerNUmeros para darle un valor por teclado a opción

        return opcion;
    }

    public static int MenuListarProductor() {
        int resultado = 0;

        P("1)Listar todos");
        P("2)Listar Productos ordenados");
        P("3)Listar por nombre");
        P("4)Listar pot tipo y nombre");
        P("5)Listar por tipo");
        P("6)Listar por estado");
        P("7)Listar películas");
        P("8)Listar juegos");
        P("9)Listar otros");
        P("10)Listar catidad de productos por nombre");
        P("11)Listar catidad de productos por nombre y tipo");
        P("12)Volver al menú anterior");
        p("> ");
        resultado = getInt();

        return resultado;
    }

    public static int MenuListarClientes() {
        int resultado = 0;
        
        P("1)Listar todos los clientes");
        P("2)Mostrar los clientes ordenados");
        P("3)Mostrar los clientes cuya reserva no ha finalizado");
        p("> ");
        resultado = getInt();

        return resultado;

    }
    
    public static int MenuListarreservas() {
        int resultado = 0;
        
        P("1)Listar todas las reservas");
        P("2)Mostrar las reservas ordenadas");
        P("3)Mostrar las reservas por estado");
        p("> ");
        resultado = getInt();

        return resultado;

    }
    
    public static int MenuOrdenarProductos(){
        int resultado = 0;
        
        P("1)De la A a la Z");
        P("2)De la Z a la A");
        P("3)De Mayor a Menor precio");
        P("4)De Menor a Meyor precio");
        P("5)Por key de la A a la Z");
        P("6)Por key de la Z a la A");
        P("7)Volver al menú anterior");
        p("> ");
        resultado = getInt();

        return resultado;
    }
    
    public static int MenuTipoProducto(){
        int resultado=0;
        P("Introduce el tipo de Producto:");
        P("1)Juego");
        P("2)Otro");
        P("3)Película");
        p("> ");
        resultado=getInt();
        return resultado;
    }
    public static int MenuEstadoProducto(){
        int resultado=0;
        P("Introduce el estado de Producto:");
        P("1)Disponible");
        P("2)No disponible");
        p("> ");
        resultado=getInt();
        return resultado;
    }

}
