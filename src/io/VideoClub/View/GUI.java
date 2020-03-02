/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Comparadores.CompararaClientes;
import io.VideoClub.Model.Product;
import io.VideoClub.Utilities.Utilities;
import java.time.LocalDateTime;

/**
 *
 * @author Manueh
 */
public class GUI {

    public static void principal(){
        AppController ni=AppController.getInstance();
        //System.out.println(ni.createClient("rqwe", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.loadClientsFromDDBB());
        System.out.println(ni.createClient("12", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("13", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("14", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("15", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("16", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("17", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.createClient("18", "fasef", "ad", LocalDateTime.now()));
        System.out.println(ni.saveClientsFromDDBB());
        CompararaClientes c=new CompararaClientes(CompararaClientes.Criterio.PorkeyZtoA);
        System.out.println(ni.listAllClients(c));
       // PrimerMenu();
    } 
    private static void PrimerMenu(){
        int opcion=0;
        
        do{
            opcion=Utilities.Menu();
            ControladorPrimerMenu(opcion);
        }while(opcion!=26);
        
    }
    private static void ControladorPrimerMenu(int op1){
        switch(op1){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }
    
}
