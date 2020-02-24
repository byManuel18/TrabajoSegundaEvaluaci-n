/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Model;
import io.VideoClub.Model.Enums.ProductsTypes;
import java.util.UUID;
/**
 *
 * @author Manueh
 */
public abstract class Product extends Item implements Cloneable{
   public enum Status{
        AVAILABLE,
        RESERVED
    }
    private String key;
    private Status status;
    private int edadmnima;
    private ProductsTypes tipo;
    
    
    public Product(){}
    public Product(String name, String description,double prize){
        super(name,description,prize);
        this.key=generateRandom16Chars();
        this.status=Status.AVAILABLE;
    }
    
    public Product(String name, String description,double prize,int edadminima, ProductsTypes tipo){
        super(name,description,prize);
        this.key=generateRandom16Chars();
        this.tipo=tipo;
        this.edadmnima=edadminima;
    }
    public Product(String name, String description,double prize,ProductsTypes tipo){
        super(name,description,prize);
        this.key=generateRandom16Chars();
        this.tipo=tipo;
    }
    
    private String generateRandom16Chars(){
        return(String)UUID.randomUUID().toString().subSequence(0, 16);
    }

    public int getEdadmnima() {
        return edadmnima;
    }

    public void setEdadmnima(int edadmnima) {
        this.edadmnima = edadmnima;
    }

    public ProductsTypes getTipo() {
        return tipo;
    }

    public void setTipo(ProductsTypes tipo) {
        this.tipo = tipo;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public boolean equals(Object o){
        boolean result=false;
        if(o!=null){
            if(o instanceof Product){
                Product other=(Product)o;
                if(other.key.equals(other.key)){
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Product clone=(Product)super.clone(); //To change body of generated methods, choose Tools | Templates.
        clone.key=generateRandom16Chars();
        return (Object)clone;
    } 

    @Override
    public String toString() {
        return ">"+ "Key: "+ key +" Tipo: "+tipo +" Nombre: " +super.name+" Descripción: "+super.description+" Precio: "+super.prize+ " Estado: "+status;
    }
    
    
}
