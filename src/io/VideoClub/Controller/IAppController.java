/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.Reservation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Manueh
 */
public interface IAppController {
    public final static String catalogDDBB="catalog.xml";
    public final static String clientsDDBB="clients.xml";
    public final static String reservationsDDBB="reservations.xml";
    
    /**
     * Lista todos los productos
     * @return devuelve una lista con todos los productos
     */
    Set<Product> listAllProducts();
    
    /**
     * Lista todos los productos pasandole un comparador
     * @param c Comparator
     * @return devuelve la lista
     */
    Set<Product> listAllProducts(Comparator c);
    
    /**
     * Lista todos los productos según el tipo que le pasemos
     * @param type ProductsTypes
     * @return devuelve la lista de los productos
     */
    Set<Product> listAllByType(ProductsTypes type);
    
    /**
     * Lista todos los productos de la lista pasandole un nombre
     * @param name String
     * @return devuelve la lista de los productos
     */
    Set<Product> listAllByName(String name);
    
    /**
     * Lista todos los productos segun el nombre y el tipo pasado
     * @param name String
     * @param type ProductTypes
     * @return devuelve una lista Set de los productos
     */
    Set<Product> listAllByName(String name,ProductsTypes type);
    /**
     * Lista todos los productos segun el estatus pasado
     * @param status Product.Status
     * @return devuelve una lista Set de los productos 
     */
    Set<Product> listAllByStatus(Product.Status status);
    
    /**
     * Lista todos los productos diferentes del catalogo
     * @return devuelve una lista List de los productos
     */
    List<Product> listAllDifferentProducts();
     /**
     * Lista todos las peliculas diferentes del catalogo
     * @return devuelve una lista List de las peliculas
     */
    List<Product> listAllDifferentMovies();
    /**
     * Lista todos los juegos diferentes del catalogo
     * @return devuelve una lista List de los juegos
     */
    List<Product> listAllDifferentGames();
    
    /**
     * Muestra todos los productos que hay en la lista segun el nombre pasado
     * @param name String
     * @return devuelve una lista tipo Map 
     */
    Map<Product,Integer> listAllAmountOfProducts(String name); 
    /**
     * Muestra todos los productos que hay en la lista segun el nombre y el tipo de producto pasado
     * @param type ProductsTypes
     * @param name String
     * @return devuelve una lista tipo Map 
     */
    Map<Product,Integer> listAllAmountOfProducts(ProductsTypes type,String name);
    
    /**
     * Lista todos los clientes que se encuentran en la lista
     * @return devuelve una lista de tipo set
     */
    Set<IClient> listAllClients();
    /**
     * Lista todos los clientes que se encuentran en la lista
     * @param c recibe un Comparator
     * @return devuelve una lista de tipo Set
     */
    Set<IClient> listAllClients(Comparator c);
    /**
     * Lista todos los clientes que se encuentran en la lista, con reservas pendientes de finalizar
     * @return devuelve una lista de tipo Set
     */
    Set<IClient> listAllClientsWithReservationsNotFinished();
    
    /**
     * Lista todas las reservas que se encuentren en la lista
     * @return devuelve una lista de tipo Set
     */
    Set<Reservation> listAllReservations();
    /**
     * Lista todas la reservas ordenandolas con un comparador
     * @param c recibe un Comparator
     * @return devuelve  una lista tipo set
     */
    Set<Reservation> listAllReservations(Comparator c);
    /**
     * Lista todas las reservas, segun el estado de la reserva pasado 
     * @param status recibe Reservation.StatusReserve
     * @return devuelve  una lista tipo set 
     */
    Set<Reservation> listAllReservations(Reservation.StatusReserve status);
    
    /**
     * Obtener todoas las ganacias del videoclub
     * @return devuelve la cantidad (double)
     */
    double getIncommings();
    /**
     * Obteniene las ganacias del videoclub desde una fecha indicada hasta el presente
     * @param from fecha (LocalDate)
     * @return devuelve la cantidad (double)
     */
    double getIncommings(LocalDate from);
    /**
     * Obteniene las ganacias del videoclub entre dos fechas
     * @param from fecha inicio (LocalDate)
     * @param to fecha final (LocalDate)
     * @return devuelve la cantidad (double)
     */
    double getIncommings(LocalDate from, LocalDate to);
    /**
     * Ganancias obtenidad por cada cliente
     * @return devuelve un Map con la key clinete y el valor total de las reservas cerradas del cliente
     */
    Map<IClient,Double> resumeAllIncomingsByClient();
    
    /**
     * Crea el producto segun los parametros pasados
     * @param name String
     * @param description String
     * @param prize double
     * @return devuelve un booleano
     */
    boolean createProduct(String name, String description,double prize);
    /**
     *  Crea la pelicula segun los parametros pasados
     * @param type ProductsTypes
     * @param name String
     * @param description String
     * @param cat MovieCategory
     * @param minAge int
     * @param prize double
     * @return devuelve un booleano
     */
    boolean createMovie(ProductsTypes type,String name, String description, MovieCategory cat,int minAge, double prize);
    /**
     *  Crea el juego segun los parametros pasados
     * @param type ProductsTypes
     * @param name String
     * @param description String
     * @param cat MovieCategory
     * @param minAge int
     * @param prize double
     * @return devuelve un booleano
     */
    boolean createGame(ProductsTypes type, String name, String description, GameCategory cat,int minAge, double prize);
    
    /**
     * Crea un cliente
     * @param id String
     * @param name String
     * @param phone String
     * @param time LocalDateTime (Fecha de creación)
     * @return devuelve un boolean
     * Si es true crea el cliente, y si es false no
     */
    boolean createClient(String id,String name,String phone,LocalDateTime time);
    /**
     * Elimina un cliente pasandole su id(DNI)
     * Si tiene reservas no puede ser borrado
     * @param id String
     * @return devuelve un boolean
     * Si es true borra el cliente, y si es false no
     */
    boolean removeClient(String id);  
    /**
     * Edita un cliente, pasandole el cliente a editar
     * @param e IClient (Cliente)
     * @return devuelve un boolean
     * Si es true edita el cliente, y si es false no
     */
    boolean editClient(IClient e);
    
    /**
     * Añade la existencia de un producto existente
     * Recibe el nombre y tipo del producto
     * @param name String
     * @param type ProductTypes
     * @return devuelve un boolean
     * Si es true añade la existencia, y si es false no
     */
    boolean addProduct(String name, ProductsTypes type);
    /**
     * Borra todas la existencias del producto indicado
     * Recibe el nombre y tipo del producto
     * @param name String
     * @param ty ProductTypes
     * @return devuelve un boolean
     * Si es true borra las existencias, y si es false no
     */
    boolean removeProduct(String name,ProductsTypes ty);
    
    /**
     * Edita un producto, segun la Key y el nuevo producto pasado
     * @param key recibe un String
     * @param newP recibe un Product
     * @return devuelve un boolean
     */
    boolean editProduct(String key, Product newP);
    /**
     * Devuelve un producto si esta disponible
     * @param name recibe el nombre del producto String
     * @param tipo recibe el tipo del producto ProductTypes
     * @return devuelve el prodcuto
     */
    Product isAvailableProduct(String name,ProductsTypes tipo);  //get product if yes
    /**
     * Hace una reserva
     * Recibe el producto y el cliente
     * @param prod Product
     * @param client IClient
     * @return devuelve un boolean
     * Si es true hace la reserva, y si es false no 
     */
    boolean reserveProduct(Product prod,IClient client);
    /**
     * Cierra una reserva pasandole una reserva
     * @param r Reservation (Reserva)
     * @return devuelve el valor de la reserva
     */
    double closeReservation(Reservation r);  //-->> status finished  --> get prizetopay
    
    /**
     * Carga de un archivo xml los productos del catologo
     * @return devuelve un boolean
     */
    boolean loadCatalogFromDDBB();  //XML or JSON
    /**
     * Carga de un archivo xml los clientes registrados
     * @return devuelve un boolean
     */
    boolean loadClientsFromDDBB();
    /**
     * Carga de un archivo xml las reservas realizadas por los clientes
     * @return devuelve un boolean
     */
    boolean loadReservationsFromDDBB();
    /**
     * Llama a los tres metodos anteriores para cargar todo en el programa
     * @return devuelve un boolean
     */
    boolean loadAllDDBB();
    
    /**
     * Guarda en un archivo xml los productos del catologo
     * @return devuelve un boolean
     */
    boolean saveCatalogFromDDBB();  //XML or JSON
     /**
     * Guarda en un archivo xml los clientes registrados
     * @return devuelve un boolean
     */
    boolean saveClientsFromDDBB();
    /**
     * Guarda en un archivo xml las reservas realizadas por los clientes
     * @return devuelve un boolean
     */
    boolean saveReservationsFromDDBB();
    /**
     * Llama a los tres metodos anteriores para guardarlo todo 
     * @return devuelve un boolean
     */
    boolean saveAllDDBB();
}
