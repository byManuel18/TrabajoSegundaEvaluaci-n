/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

/**
 *
 * @author Manueh
 */
public class Item {
   
    protected String name;
    protected String description;
    protected double prize;
    
    public Item(){}
    public Item(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }
    
    
    
}
