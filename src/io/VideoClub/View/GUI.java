/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Comparadores.CompararProductos;
import io.VideoClub.Model.Comparadores.CompararaClientes;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Product;
import io.VideoClub.Utilities.Utilities;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Manueh
 */
public class GUI {

    private static AppController controlador = AppController.getInstance();

    public static void principal() {
        if (controlador.loadAllDDBB()) {
            Utilities.P("Base de datos cargada correctamente.");
        } else {
            Utilities.P("Base de datos no encontrada. Si es la primera vez que ejecuta el programa, este creará los archivos de guardado una vez cierre el programa.");
        }
        PrimerMenu();
    }

    private static void PrimerMenu() {
        int opcion = 0;

        do {
            opcion = Utilities.Menu();
            ControladorPrimerMenu(opcion);
        } while (opcion != 15);

    }

    private static void ControladorPrimerMenu(int op1) {
        int opcion2 = 0;
        String nombre = "";
        ProductsTypes tipo = null;

        switch (op1) {
            case 1:
                do {

                    opcion2 = Utilities.MenuListarProductor();
                    ControladorMenuProductos(opcion2);

                } while (opcion2 != 12);

                break;
            case 2:
                do {

                    opcion2 = Utilities.MenuListarClientes();
                    ControladorMenuListarClientes(opcion2);

                } while (opcion2 != 4);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                nombre = Utilities.getString("Introduce el nombre del producto");
                do {
                    int opcionTipo = 0;
                    opcionTipo = Utilities.MenuTipoProducto();
                    tipo = devolverTypoProducto(opcionTipo);
                } while (tipo == null);
                if(controlador.addProduct(nombre, tipo)){
                   Utilities.P("Se ha podido crear correctamente");
                   controlador.saveCatalogFromDDBB();
                }else{
                Utilities.P("No se ha podido crear correctamente");
                }
            case 8:
                do{ opcion2=Utilities.MenuBorrarProducto();
                    BorrarProductosControlador(opcion2);
                }while(opcion2!=3);
                   
                break;

            case 15:

                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuProductos(int op2) {
        Set<Product> pro;
        List<Product> lispro;
        Map<Product, Integer> mappro;
        String nombre;
        ProductsTypes tipo = null;
        Product.Status estadopro = null;
        int opListaPro = 0;
        switch (op2) {
            case 1:
                pro = controlador.listAllProducts();
                if (!pro.isEmpty()) {
                    ListarSetProductos(pro);
                } else {
                    Utilities.P("No existen Productos aún.");
                }
                break;

            case 2:

                do {
                    opListaPro = Utilities.MenuOrdenarProductos();
                    MostrarProductosOrdenados(opListaPro);
                } while (opListaPro != 7);

                break;
            case 3:
                nombre = Utilities.getString("Introduce el nombre del producto");
                pro = controlador.listAllByName(nombre);
                if (!pro.isEmpty()) {
                    for (Product p : pro) {
                        Utilities.P(p.toString());
                    }
                } else {
                    Utilities.P("No existen com esa especificación aún.");
                }
                break;
            case 4:
                nombre = Utilities.getString("Introduce el nombre del producto");
                do {
                    opListaPro = Utilities.MenuTipoProducto();
                    tipo = devolverTypoProducto(opListaPro);
                } while (tipo == null);

                pro = controlador.listAllByName(nombre, tipo);
                if (!pro.isEmpty()) {
                    ListarSetProductos(pro);
                } else {
                    Utilities.P("Lista vacía.");
                }

                break;
            case 5:
                do {
                    opListaPro = Utilities.MenuTipoProducto();
                    tipo = devolverTypoProducto(opListaPro);
                } while (tipo == null);
                pro = controlador.listAllByType(tipo);
                if (!pro.isEmpty()) {
                    ListarSetProductos(pro);
                } else {
                    Utilities.P("Lista vacía.");
                }
                break;
            case 6:
                do {
                    opListaPro = Utilities.MenuEstadoProducto();
                    estadopro = devolverEstadoProducto(opListaPro);
                } while (estadopro == null);
                pro = controlador.listAllByStatus(estadopro);
                if (!pro.isEmpty()) {
                    ListarSetProductos(pro);
                } else {
                    Utilities.P("Lista vacía.");
                }
                break;
            case 7:
                lispro = controlador.listAllDifferentMovies();
                if (!lispro.isEmpty()) {
                    ListarListProductos(lispro);
                } else {
                    Utilities.P("No hay películas");
                }
                break;
            case 8:
                lispro = controlador.listAllDifferentGames();
                if (!lispro.isEmpty()) {
                    ListarListProductos(lispro);
                } else {
                    Utilities.P("No hay Juegos");
                }
                break;
            case 9:
                lispro = controlador.listAllDifferentProducts();
                if (!lispro.isEmpty()) {
                    ListarListProductos(lispro);
                } else {
                    Utilities.P("No hay Productos");
                }
                break;
            case 10:
                nombre = Utilities.getString("Introduce el nombre del producto");
                mappro = controlador.listAllAmountOfProducts(nombre);
                if (!mappro.isEmpty()) {
                    ListarMapProductos(mappro);
                } else {
                    Utilities.P("No hay Productos");
                }
                break;
            case 11:
                nombre = Utilities.getString("Introduce el nombre del producto");
                do {
                    opListaPro = Utilities.MenuTipoProducto();
                    tipo = devolverTypoProducto(opListaPro);
                } while (tipo == null);
                mappro = controlador.listAllAmountOfProducts(tipo, nombre);
                if (!mappro.isEmpty()) {
                    ListarMapProductos(mappro);
                } else {
                    Utilities.P("No hay Productos");
                }

                break;
            case 12:
                Utilities.P("Volviendo al menú anterior.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void MostrarProductosOrdenados(int op) {
        CompararProductos.Criterio comp = null;
        switch (op) {
            case 1:
                comp = CompararProductos.Criterio.AtoZ;
                ListarProductosOrdenados(comp);
                break;
            case 2:
                comp = CompararProductos.Criterio.ZtoA;
                ListarProductosOrdenados(comp);
                break;
            case 3:
                comp = CompararProductos.Criterio.MallorAMenorPrecio;
                ListarProductosOrdenados(comp);
                break;
            case 4:
                comp = CompararProductos.Criterio.MenosAMallorPrecio;
                ListarProductosOrdenados(comp);
                break;
            case 5:
                comp = CompararProductos.Criterio.PorKeyAtoZ;
                ListarProductosOrdenados(comp);
                break;
            case 6:
                comp = CompararProductos.Criterio.PorkeyZtoA;
                ListarProductosOrdenados(comp);
                break;
            case 7:
                Utilities.P("Volviendo al menú anterior.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");

        }

    }
    private static void ControladorMenuListarClientes(int op){
        Set<IClient> clientes;
        switch(op){
            case 1:
                clientes=controlador.listAllClients();
                if(!clientes.isEmpty()){
                    for(IClient cli: clientes){
                        Utilities.P(cli.toString());
                    }
                }else{
                    Utilities.P("No hay clientes.");
                }
                break;
            case 2:
                int opc=0;
                do{
                    opc=Utilities.MenuListarClientesOrdenados();
                    MostrarClientessOrdenados(opc);
                }while(opc!=7);
                    
                break;
            case 3:
                clientes=controlador.listAllClientsWithReservationsNotFinished();
                clientes=controlador.listAllClients();
                if(!clientes.isEmpty()){
                    for(IClient cli: clientes){
                        Utilities.P(cli.toString());
                    }
                }else{
                    Utilities.P("No hay clientes.");
                }
                break;
            case 4:
                Utilities.P("Volviendo al menú anterior.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
        
    }
    private static void MostrarClientessOrdenados(int op) {
        CompararaClientes.Criterio comp = null;
        switch (op) {
            case 1:
                comp = CompararaClientes.Criterio.AtoZ;
                ListarClientesOrdenados(comp);
                break;
            case 2:
                comp = CompararaClientes.Criterio.ZtoA;
                ListarClientesOrdenados(comp);
                break;
            case 3:
                comp = CompararaClientes.Criterio.PorKeyAtoZ;
                ListarClientesOrdenados(comp);
                break;
            case 4:
                comp = CompararaClientes.Criterio.PorkeyZtoA;
                ListarClientesOrdenados(comp);
                break;
            case 5:
                comp = CompararaClientes.Criterio.FechaInscripciónMenorMayor;
                ListarClientesOrdenados(comp);
                break;
            case 6:
                comp = CompararaClientes.Criterio.FechaInscripciónMayorMenor;
                ListarClientesOrdenados(comp);
                break;
            case 7:
                Utilities.P("Volviendo al menú anterior.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");

        }

    }
    
    private static void BorrarProductosControlador(int op){
        
        switch(op){
            case 1:
                Utilities.p("Introduce la key del producto a eliminar: ");
                String key=Utilities.getStringSinModicar();
                if(controlador.productoExistentePorKey(key)){
                 if(controlador.removeProduct(key)){
                    Utilities.P("Producto con key: "+key+" borrado correctamente.");
                    controlador.saveCatalogFromDDBB();
                }else{
                     Utilities.P("No se ha podido eliminar el producto. El producto está en uso.");
                }   
                }else{
                    Utilities.P("No existe ningún producto con la key: "+key); 
                }

                break;
            case 2:
                String nombre=Utilities.getString("Inroduce el nombre del producto");
                ProductsTypes tipo=devolverTypoProducto(Utilities.MenuTipoProducto());
                if(controlador.productoExistente(nombre, tipo)){
                    if(controlador.removeProduct(nombre, tipo)){
                         Utilities.P("Productos con las cararterísticas indicadas borrados correctamente.");
                         controlador.saveCatalogFromDDBB();
                    }else{
                        Utilities.P("No se ha podido eliminar el producto. El producto está en uso.");
                    }
                }else{
                    Utilities.P("No existe ningún producto con las caracteeristicas indicadas."); 
                }
                break;
            case 3:
                 Utilities.P("Volviendo al menú anterior.");
                break;
                
            default:
              Utilities.P("Opción no válida, vuelve a intentarlo.");  
        }
    }

    private static void ListarProductosOrdenados(CompararProductos.Criterio c) {
        CompararProductos comparador = new CompararProductos(c);
        Set<Product> pro = controlador.listAllProducts(comparador);
        if (!pro.isEmpty()) {
            for (Product p : pro) {
                Utilities.P(p.toString());
            }
        } else {
            Utilities.P("No existen Productos aún.");
        }
    }
    private static void ListarClientesOrdenados(CompararaClientes.Criterio c) {
        CompararaClientes comparador = new CompararaClientes(c);
        Set<IClient> clien = controlador.listAllClients(comparador);
        if (!clien.isEmpty()) {
            for (IClient cl : clien) {
                Utilities.P(cl.toString());
            }
        } else {
            Utilities.P("No existen Productos aún.");
        }
    }

    private static ProductsTypes devolverTypoProducto(int op) {
        ProductsTypes tipo = null;
        switch (op) {
            case 1:
                tipo = ProductsTypes.Juegos;
                break;
            case 2:
                tipo = ProductsTypes.Otros;
                break;
            case 3:
                tipo = ProductsTypes.Peliculas;
                break;
            default:
                Utilities.P("Opción incorrecta. Prueba de nuevo.");
        }
        return tipo;
    }

    private static void ListarSetProductos(Set<Product> listap) {

        if (!listap.isEmpty()) {
            for (Product p : listap) {
                Utilities.P(p.toString());
            }
        } else {
            Utilities.P("Lista vacía.");
        }

    }

    private static void ListarListProductos(List<Product> listap) {

        if (!listap.isEmpty()) {
            for (Product p : listap) {
                Utilities.P(p.toString());
            }
        } else {
            Utilities.P("Lista vacía.");
        }

    }

    private static void ListarMapProductos(Map<Product, Integer> listap) {

        if (!listap.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : listap.entrySet()) {
                Utilities.P(entry.getKey().toString() + " Cantidad ---> " + entry.getValue());
            }
        } else {
            Utilities.P("Lista vacía.");
        }

    }

    private static Product.Status devolverEstadoProducto(int op) {
        Product.Status estado = null;
        switch (op) {
            case 1:
                estado = Product.Status.AVAILABLE;
                break;
            case 2:
                estado = Product.Status.RESERVED;
                break;
            default:
                Utilities.P("Opción incorrecta. Prueba de nuevo.");
        }
        return estado;
    }

}
