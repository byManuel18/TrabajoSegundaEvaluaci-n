/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.time.LocalDateTime;

/**
 *
 * @author Manueh
 */
public class Client implements IClient {

    //Atributos
    private String ID;
    private String name;
    private LocalDateTime time;
    private String phone;

    //Constructor FULL
    public Client(String ID, String name, LocalDateTime time, String phone) {
        this.ID = ID;
        this.name = name;
        this.time = time;
        this.phone = phone;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String n) {
        this.name = name;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public void setTime(LocalDateTime t) {
        this.time = time;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String p) {
        this.phone = phone;
    }

    //Método toString
    @Override
    public String toString() {
        return "Client{" + "ID=" + ID + ", name=" + name + ", time=" + time + ", phone=" + phone + '}';
    }

    //Método equals (Dos clientes son iguales si tienen el mismo ID)
    public boolean equals(Object o) {
        boolean result = true;

        if (o != null) {
            if (this == o) {
                result = true;
            } else if (o instanceof Client) {
                Client other = (Client) o;
                result = this.ID.equals(other.getID());
            }
        }

        return result;
    }

}
