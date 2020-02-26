/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
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
        System.out.println(ni.clientes.isEmpty());
        System.out.println(ni.clientes);
    } 
    
}
