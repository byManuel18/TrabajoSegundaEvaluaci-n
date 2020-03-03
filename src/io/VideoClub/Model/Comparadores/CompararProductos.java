/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model.Comparadores;

import io.VideoClub.Model.Product;
import java.util.Comparator;

/**
 *
 * @author Manueh
 */
public class CompararProductos implements Comparator<Product>{
    public enum Criterio{
        AtoZ,
        ZtoA,
        PorKeyAtoZ,
        PorkeyZtoA,
        MenosAMallorPrecio,
        MallorAMenorPrecio
    }
    private Criterio criterio;
    public CompararProductos() {
        this(Criterio.AtoZ);
    }

    public CompararProductos(Criterio criterio) {
        this.criterio = criterio;
    }
    
    
    @Override
    public int compare(Product o1, Product o2) {
        int resultado=0;
        
        switch(this.criterio){
            case AtoZ:
                resultado=o1.getName().compareTo(o2.getName());
                break;
            case ZtoA:
                resultado=o2.getName().compareTo(o1.getName());
                break;
            case PorKeyAtoZ:
                resultado=o1.getKey().compareTo(o2.getKey());
                break;  
            case PorkeyZtoA:
                resultado=o2.getKey().compareTo(o1.getKey());
                break;
            case MenosAMallorPrecio:
                resultado=(int)(o1.getPrize()-o2.getPrize());
                break;
            case MallorAMenorPrecio:
                 resultado=(int)(o2.getPrize()-o1.getPrize());
                break;
                
        }
        
        return resultado;
        
    }
    
}
