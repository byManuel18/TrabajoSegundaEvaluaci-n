/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.ProductsTypes;

/**
 *
 * @author Manueh
 */
public class Juego extends Product implements Cloneable{
    GameCategory category;
    private int edadmnima;
    public Juego(String name, String description, double prize, int edadminima, ProductsTypes tipo,GameCategory category) {
        super(name, description, prize, tipo);
        this.category=category;
        this.edadmnima=edadminima;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public int getEdadmnima() {
        return edadmnima;
    }

    public void setEdadmnima(int edadmnima) {
        this.edadmnima = edadmnima;
    }

    @Override
    public String toString() {
        return super.toString()+" Categoria: "+category+" Edad mínima: "+getEdadmnima();
    }
    
    
    
    
}
