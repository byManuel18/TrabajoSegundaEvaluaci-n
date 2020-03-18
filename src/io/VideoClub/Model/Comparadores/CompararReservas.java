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
        IdMayoraMenor,
        IdMenoraMayor,
        FechaInicioReserva,
        NombreCliente,
        MenosAMallorPrecio,
        MallorAMenorPrecio
    }
    private Criterio criterio;

    public CompararReservas() {
        this(Criterio.IdMayoraMenor);
    }

    public CompararReservas(Criterio criterio) {
        this.criterio = criterio;
    }
    
    
    @Override
    public int compare(Reservation o1, Reservation o2) {
        int resultado=0;
        switch(criterio){
            case IdMenoraMayor:
                resultado=o1.getId()-o2.getId();
                break;
            case IdMayoraMenor:
                resultado=o2.getId()-o1.getId();
                break;
            case FechaInicioReserva:
                resultado=o1.ini.compareTo(o2.ini);
                if(resultado==0){
                    resultado=o1.getId()-o2.getId();
                }
                break;
            case NombreCliente:
                resultado=o1.cli.getName().compareTo(o2.cli.getName());
                if(resultado==0){
                    resultado=o1.getId()-o2.getId();
                }
                break;
            case MenosAMallorPrecio:
                resultado=(int)(o1.pro.getPrize()-o2.pro.getPrize());
                if(resultado==0){
                    resultado=o1.getId()-o2.getId();
                }
                break;
            case MallorAMenorPrecio:
                resultado=(int)(o2.pro.getPrize()-o1.pro.getPrize());
                if(resultado==0){
                    resultado=o1.getId()-o2.getId();
                }
                break;
        }
        return resultado;
    }

    
    
}
