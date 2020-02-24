/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.Client;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Item;
import io.VideoClub.Model.Juego;
import io.VideoClub.Model.Pelicula;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Manueh
 */
public class AppController implements IAppController {

    private static AppController instancia = null;

    private Set<Product> productos;
    private Set<Client> clientes;
    private Set<Reservation> reservas;

    private AppController() {
        productos = new HashSet<>();
        clientes = new HashSet<>();
        reservas = new HashSet<>();

    }

    public static AppController getInstance() {
        instancia = new AppController();
        return instancia;
    }

    @Override
    public Set<Product> listAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllProducts(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByType(ProductsTypes type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByName(String name, ProductsTypes type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Product> listAllByStatus(Product.Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAllDifferentProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAllDifferentMovies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> listAllDifferentGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Product, Integer> listAllAmountOfProducts(ProductsTypes type, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClients(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IClient> listAllClientsWithReservationsNotFinished() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations(Comparator c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Reservation> listAllReservations(Reservation.StatusReserve status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getIncommings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getIncommings(LocalDate from) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getIncommings(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<IClient, Double> resumeAllIncomingsByClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createProduct(String name, String description, double prize) {
        boolean creado = false;
        Item nuevo = new Item(name, description, prize);

        for (Item ite : productos) {
            if (ite.equals(nuevo)) {
                creado = true;
                break;
            }
        }

        return creado;
    }

    @Override
    public boolean createMovie(ProductsTypes type, String name, String description, MovieCategory cat, int minAge) {
        boolean result = false, flag = false;

        Pelicula p = new Pelicula(name, description, minAge, minAge, type, cat);

        for (Product peli : productos) {
            if (peli.equals(p)) {
                result = true;
                break;
            }
        }

        if (!result) {
            productos.add(p);
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean createGame(ProductsTypes type, String name, String description, GameCategory cat, int minAge) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createClient(String id, String name, String phone, LocalDateTime time) {
        Client c = new Client(id, name, time, phone);
        boolean flag = false, añadido = false;
        for (Client client : clientes) {
            if (client.equals(c)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            clientes.add(c);
            añadido = true;
        }

        return añadido;
    }

    @Override
    public boolean removeClient(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editClient(IClient e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editProduct(String key, Product newP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product isAvailableProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reserveProduct(Product prod, IClient client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double closeReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadCatalogFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadClientsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadReservationsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadAllDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveCatalogFromDDBB() {
        boolean guardado = false;
        try {

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element raiz = doc.createElement("Catalogo");

            for (Product c : productos) {
                Element e = null;
                if (c instanceof Pelicula) {
                    e = doc.createElement("Pelicula");
                    Element cat = doc.createElement("Categoria");
                    cat.appendChild(doc.createTextNode(String.valueOf(((Pelicula) c).getCategory())));
                    e.appendChild(cat);
                } else if (c instanceof Juego) {
                    e = doc.createElement("Juego");
                    Element cat = doc.createElement("Categoria");
                    cat.appendChild(doc.createTextNode(String.valueOf(((Juego) c).getCategory())));
                    e.appendChild(cat);
                } else {
                    e = doc.createElement("Otro");
                }

                Element k = doc.createElement("Key");
                k.appendChild(doc.createTextNode(c.getKey()));
                Element name = doc.createElement("Nombre");
                name.appendChild(doc.createTextNode(c.getName()));
                Element des = doc.createElement("Descripcion");
                des.appendChild(doc.createTextNode(c.getDescription()));
                Element prec = doc.createElement("Precio");
                prec.appendChild(doc.createTextNode(String.valueOf(c.getPrize())));
                Element edad = doc.createElement("EdadMinima");
                edad.appendChild(doc.createTextNode(String.valueOf(c.getPrize())));
                Element tipo = doc.createElement("Tipo");
                tipo.appendChild(doc.createTextNode(String.valueOf(c.getTipo())));
                Element estado = doc.createElement("Estado");
                estado.appendChild(doc.createTextNode(String.valueOf(c.getStatus())));

                e.appendChild(k);
                e.appendChild(name);
                e.appendChild(des);
                e.appendChild(prec);
                e.appendChild(edad);
                e.appendChild(tipo);
                e.appendChild(estado);
                raiz.appendChild(e);

            }

            //Guardar el xml en el disco duro
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();
            //<-- OPCIONES DEL ARCHIVO
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(catalogDDBB));

            trans.transform(source, result);
            guardado = true;

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);

        }
        return guardado;
    }

    @Override
    public boolean saveClientsFromDDBB() {
        boolean guardado = false;
        try {

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element raiz = doc.createElement("Clientes");

            for (Client c : clientes) {
                Element e = doc.createElement("Cliente");

                Element k = doc.createElement("ID");
                k.appendChild(doc.createTextNode(c.getID()));
                Element name = doc.createElement("Nombre");
                name.appendChild(doc.createTextNode(c.getName()));
                Element tel = doc.createElement("Telefono");
                tel.appendChild(doc.createTextNode(c.getPhone()));
                Element prec = doc.createElement("Fecha");
                prec.appendChild(doc.createTextNode(String.valueOf(c.getTime())));

                e.appendChild(k);
                e.appendChild(name);
                e.appendChild(tel);
                e.appendChild(prec);

                raiz.appendChild(e);

            }

            //Guardar el xml en el disco duro
            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();
            //<-- OPCIONES DEL ARCHIVO
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xlst}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(catalogDDBB));

            trans.transform(source, result);
            guardado = true;

        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex);
        } catch (TransformerException ex) {
            System.out.println(ex);

        }
        return guardado;
    }

    @Override
    public boolean saveReservationsFromDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveAllDDBB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
