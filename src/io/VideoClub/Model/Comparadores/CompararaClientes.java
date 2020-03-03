/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model.Comparadores;

import io.VideoClub.Model.Client;
import java.util.Comparator;

/**
 *
 * @author Manueh
 */
public class CompararaClientes implements Comparator<Client>{
    public enum Criterio{
            AtoZ,
            ZtoA,
            PorKeyAtoZ,
            PorkeyZtoA,
            FechaInscripciónMenorMayor,
            FechaInscripciónMayorMenor
    }
    private Criterio criterio;
    public CompararaClientes(){
       this(Criterio.AtoZ);
    }

    public CompararaClientes(Criterio criterio) {
        this.criterio = criterio;
    }

    @Override
    public int compare(Client o1, Client o2) {
        int resultado=0;
        switch(criterio){
            case AtoZ:
                resultado=o1.getName().compareTo(o2.getName());
                break;
            case ZtoA:
                resultado=o2.getName().compareTo(o1.getName());
                break;
            case PorKeyAtoZ:
                resultado=o1.getID().compareTo(o2.getID());
                break;  
            case PorkeyZtoA:
                resultado=o2.getID().compareTo(o1.getID());
                break;
            case FechaInscripciónMenorMayor:
                resultado=o1.getTime().compareTo(o2.getTime());
                break;
            case FechaInscripciónMayorMenor:
                 resultado=o2.getTime().compareTo(o1.getTime());
                break;
        }
        return resultado;
    }
    
    
    
}
