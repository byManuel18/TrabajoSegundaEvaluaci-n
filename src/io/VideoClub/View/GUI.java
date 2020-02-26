/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Product;
import java.time.LocalDateTime;

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
    } 
    
}
