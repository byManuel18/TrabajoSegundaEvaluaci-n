/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model.Comparadores;

import io.VideoClub.Model.Reservation;
import java.util.Comparator;

/**
 *
 * @author Manueh
 */
public class CompararReservas implements Comparator<Reservation>{
    public enum Criterio{
        AtoZ,
        ZtoA,
        PorKeyAtoZ,
        PorkeyZtoA,
        MenosAMallorPrecio,
        MallorAMenorPrecio
    }
    private Criterio criterio;

    public CompararReservas() {
        this(Criterio.AtoZ);
    }

    public CompararReservas(Criterio criterio) {
        this.criterio = criterio;
    }
    
    
    @Override
    public int compare(Reservation o1, Reservation o2) {
        int resultado=0;
        switch(criterio){
            
        }
        return resultado;
    }

    
    
}
