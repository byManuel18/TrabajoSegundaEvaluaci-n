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
public class Juego extends Product{
    GameCategory category;

    public Juego(String name, String description, double prize, int edadminima, ProductsTypes tipo,GameCategory category) {
        super(name, description, prize, edadminima, tipo);
        this.category=category;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return super.toString()+" Categoria: "+category+" Edad mínima: "+getEdadmnima();
    }
    
    
    
    
}
