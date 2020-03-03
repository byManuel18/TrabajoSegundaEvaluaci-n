/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;

/**
 *
 * @author alberto
 */
public class Pelicula extends Product implements Cloneable{

    private MovieCategory category;
    private int edadmnima;
    public Pelicula(String name, String description, double prize, int edadminima, ProductsTypes tipo, MovieCategory category) {
         super(name, description, prize, tipo);
        this.category = category;
        this.edadmnima=edadminima;
    }

    public MovieCategory getCategory() {
        return category;
    }

    public void setCategory(MovieCategory category) {
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
        return super.toString() + " Categoria: " + category + " Edad mínima: " + getEdadmnima();
    }


}
