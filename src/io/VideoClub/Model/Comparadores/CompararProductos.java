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
    private enum Criterio{
        AtoZ,
        ZtoA,
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
                
                break;
            default:
                
        }
        
        return resultado;
        
    }
    
}
