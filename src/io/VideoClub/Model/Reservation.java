/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author Manueh
 */
public class Reservation {

    public enum StatusReserve {
        ACTIVE, //ini on, finished off
        FINISHED, //ini on finised on == end
        PENDING  //ini on , finished off and end past
    }
    private static int IDRESEREVA=1;
    private int id;
    public Product pro;
    public IClient cli;
    public LocalDate ini;
    public LocalDate end;
    public LocalDate finished;
    public StatusReserve status;

    private Reservation() {
    }

    ;
       
    public Reservation(Product pro, IClient cli) {
        this.pro = pro;
        this.cli = cli;
        ini = LocalDate.now();
        end = LocalDate.now().plusDays(8);
        if (end.getDayOfWeek() == DayOfWeek.SUNDAY) {
            end = end.plusDays(1);
        }
        status = StatusReserve.ACTIVE;
        id=IDRESEREVA;
        IDRESEREVA++;
    }

    public boolean equals(Object o) {
        boolean result = false;
        if (o != null) {
            if (o instanceof Reservation) {
                Reservation other = (Reservation) o;
                if (this.pro.equals(other.pro)
                        && this.cli.equals(other.cli)
                        && this.ini.equals(other.cli)
                        && this.end.equals(other.end)
                        && this.status == other.status
                        && this.id==other.id) {
                    result = true;
                }
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Reservation --> ID:"+ id + " {Producto=" + pro + ", Cliente=" + cli + ", Inicio=" + ini + ", Fin=" + end + ", Finished=" + finished + ", Estado=" + status + '}';
    }

   
}
