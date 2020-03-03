/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import java.util.Objects;

/**
 *
 * @author Manueh
 */
public  abstract class Item {
   
    protected String name;
    protected String description;
    protected double prize;
    
    public Item(){}
    public Item(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    @Override
    public boolean equals(Object obj) {
        boolean igual=false;
        if(obj!=null){
            if(this==obj){
                igual=true;
            }else{
                if(obj instanceof Item){
                    Item n=(Item) obj;
                    igual=name.equals(n.name);
                }
            }
        }
        return igual;
    }
    
    
    
    
    
}
