/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Product;
import io.VideoClub.Utilities.Utilities;

/**
 *
 * @author Manueh
 */
public class GUI {

    public static void principal(){
        AppController ni=AppController.getInstance();
        //System.out.println(ni.createClient("rqwe", "fasef", "ad", LocalDateTime.now()));
        ni.createProduct("P1", "Producto1", 5.5);
        ni.createProduct("P2", "Producto2", 6.5);
        ni.createProduct("P3", "Producto3", 7.5);
        ni.saveCatalogFromDDBB();
        System.out.println(ni.loadClientsFromDDBB());
        System.out.println(ni.clientes.isEmpty());
        System.out.println(ni.clientes);
        System.out.println(ni.listAllByStatus(Product.Status.RESERVED));
        PrimerMenu();
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
