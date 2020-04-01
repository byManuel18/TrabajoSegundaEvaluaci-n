/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.View;

import io.VideoClub.Controller.AppController;
import io.VideoClub.Model.Client;
import io.VideoClub.Model.Comparadores.CompararProductos;
import io.VideoClub.Model.Comparadores.CompararReservas;
import io.VideoClub.Model.Comparadores.CompararaClientes;
import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.MovieCategory;
import io.VideoClub.Model.Enums.ProductsTypes;
import io.VideoClub.Model.IClient;
import io.VideoClub.Model.Juego;
import io.VideoClub.Model.Others;
import io.VideoClub.Model.Pelicula;
import io.VideoClub.Model.Product;
import io.VideoClub.Model.Reservation;
import io.VideoClub.Utilities.Utilities;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Manueh
 */
public class GUI {

    private static AppController controlador = AppController.getInstance();
    private static DecimalFormat decimal = new DecimalFormat("#.00");

    public static void principal() {
        if (controlador.loadAllDDBB()) {
            Utilities.P("Base de datos cargada correctamente.");
            controlador.UpgradearReservas();
            controlador.saveReservationsFromDDBB();
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
                do {
                    opcion2 = Utilities.MenuListarreservas();
                    ControladorMenuListarReservas(opcion2);
                } while (opcion2 != 4);
                break;
            case 4:
                String dnicliente,
                 nombreclienbte,
                 telefono;
                do {
                    do {
                        dnicliente = Utilities.getString("Introduce el Dni del cliente");
                        if (!Utilities.validarDNI(dnicliente)) {
                            Utilities.P("Formato de DNI no válido.");
                        }
                    } while (!Utilities.validarDNI(dnicliente));
                    if (controlador.existeCliente(dnicliente)) {
                        Utilities.P("Ya existe un contacto con ese dni.");
                    }
                } while (controlador.existeCliente(dnicliente));

                do {
                    nombreclienbte = Utilities.getString("Introduce el Nombre del cliente");
                    if (!Utilities.validarNombre(nombreclienbte)) {
                        Utilities.P("Formato de Nombre no válido.");
                    }
                } while (!Utilities.validarNombre(nombreclienbte));

                do {
                    telefono = Utilities.getString("Itroduce el número de telefono");
                    if (!Utilities.validarTelf(telefono)) {
                        Utilities.P("Formato de telefono no válido.");
                    }
                } while (!Utilities.validarTelf(telefono));
                if (controlador.createClient(dnicliente, nombreclienbte, telefono, LocalDateTime.now())) {
                    Utilities.P("Cliente creado correctamente.");
                    controlador.saveClientsFromDDBB();
                } else {
                    Utilities.P("No se ha podido crear el nuevo cliente");
                }
                break;
            case 5:
                do {
                    opcion2 = Utilities.MenuCrearProductos();
                    ControladorMenuCrearProductos(opcion2);

                } while (opcion2 != 4);
                break;
            case 6:
                Utilities.p("Introduce el Key del producto: ");
                nombre = Utilities.getStringSinModicar();
                Product prod = null;
                prod = controlador.devuelveUnProductoDisponible(nombre);
                if (prod != null) {
                    int exist = Utilities.getInt("Introduce el numero de copias");
                    for (int i = 0; i < exist; i++) {
                        controlador.addProduct(prod.getName(), prod.getTipo());
                    }
                    Utilities.P("Se ha añadido al existencia con exito");
                    controlador.saveCatalogFromDDBB();

                } else {
                    Utilities.P("No existe el producto");
                }
                break;
            case 7:
                Utilities.p("Introduce el ID del producto: ");
                String key = Utilities.getStringSinModicar();
                if (controlador.productoExistentePorKey(key)) {
                    if (controlador.isAvailableProduct(key)) {
                        nombre = Utilities.getString("Introduce el nuevo nombre del producto");
                        Utilities.p("Introduce el nuevo precio: ");
                        double precio = Utilities.getDouble();
                        String descripcion = Utilities.getString("Introduce una nueva descripcion");

                        do {
                            int opcionTipo = 0;
                            opcionTipo = Utilities.MenuTipoProducto();
                            tipo = devolverTypoProducto(opcionTipo);
                        } while (tipo == null);
                        Product p;
                        if (tipo == ProductsTypes.Otros) {
                            p = new Others(nombre, descripcion, precio);
                            p.setTipo(tipo);
                        } else if (tipo == ProductsTypes.Peliculas) {
                            MovieCategory categoriaPelicula;
                            do {
                                int opcioncate = Utilities.MenuDevolverTipoPeli();
                                categoriaPelicula = DevolverTipoPelicula(opcioncate);
                            } while (categoriaPelicula == null);
                            p = new Pelicula(nombre, descripcion, precio, Utilities.getInt("Introduce la edad minima"), tipo, categoriaPelicula);
                        } else {
                            GameCategory categoriajuego;
                            do {
                                int opcioncate = Utilities.MenuDevolverTipoJuego();
                                categoriajuego = DevolverTipoJuego(opcioncate);
                            } while (categoriajuego == null);
                            p = new Juego(nombre, descripcion, precio, Utilities.getInt("Introduce la edad minima"), tipo, categoriajuego);
                        }
                        p.setKey(key);
                        if (p != null && controlador.editProduct(key, p)) {
                            Utilities.P("Se ha editado correctamente");
                        } else {
                            Utilities.P("No se ha podido editar");
                        }
                    } else {

                        Utilities.P("El producto está reservado");
                    }
                } else {
                    Utilities.P("No existe el producto");
                }
                break;
            case 8:
                do {
                    opcion2 = Utilities.MenuBorrarProducto();
                    BorrarProductosControlador(opcion2);
                } while (opcion2 != 3);

                break;
            case 9:
                Product p = null;
                nombre = Utilities.getString("Introduce el nombre del producto");
                do {
                    int opcionTipo = 0;
                    opcionTipo = Utilities.MenuTipoProducto();
                    tipo = devolverTypoProducto(opcionTipo);
                } while (tipo == null);
                if (controlador.productoExistente(nombre, tipo)) {
                    p = controlador.isAvailableProduct(nombre, tipo);
                    if (p == null) {
                        Utilities.P("No esta disponible el producto introducido");
                    } else {
                        Utilities.P(p.toString());
                    }
                } else {
                    Utilities.P("No existe e producto en el catalogo");
                }

                break;

            case 10:
                do {
                    dnicliente = Utilities.getString("Introduce el dni del Cliente a eliminar");
                    if (!Utilities.validarDNI(dnicliente)) {
                        Utilities.P("Formato de Dni no válido.");
                    }
                } while (!Utilities.validarDNI(dnicliente));
                if (controlador.existeCliente(dnicliente)) {
                    if (controlador.removeClient(dnicliente)) {
                        Utilities.P("Cliente eliminado correctamente.");
                        controlador.saveClientsFromDDBB();
                    } else {
                        Utilities.P("No se ha podido borrar el cliente porque tiene reservas pendientes.");
                    }
                } else {
                    Utilities.P("No existe un cliente con ese DNI.");
                }

                break;
            case 11:
                String dni = Utilities.getString("Introduce el DNI del Cliente");
                if (controlador.existeCliente(dni)) {
                    do {
                        telefono = Utilities.getString("Introduce el nuevo telefono");
                        if (!Utilities.validarTelf(telefono)) {
                            Utilities.P("El formato del telefono no es correcto");
                        }
                    } while (!Utilities.validarTelf(telefono));
                    do {
                        nombre = Utilities.getString("Introduce el nuevo nombre");
                        if (!Utilities.validarNombre(nombre)) {
                            Utilities.P("El formato del nombre no es correcto");
                        }
                    } while (!Utilities.validarNombre(nombre));
                    IClient c = new Client(dni, nombre, LocalDateTime.now(), telefono);
                    if (controlador.editClient(c)) {
                        Utilities.P("Se ha editado correctamente");
                    } else {
                        Utilities.P("El cliente no se ha podido editar");
                    }
                } else {
                    Utilities.P("No existe el cliente");
                }

                break;
            case 12:
                IClient c = null;
                Product pro = null;
                do {
                    do {
                        dni = Utilities.getString("Introduzca el DNI del cliente");
                        if (!Utilities.validarDNI(dni)) {
                            Utilities.P("El DNI no es valido");
                        }
                    } while (!Utilities.validarDNI(dni));
                    c = controlador.devolverClienteExistente(dni);
                    if (c == null) {
                        Utilities.P("No existe el cliente");
                    }
                } while (c == null);
                do {
                    Utilities.p("Introduce el ID del producto: ");
                    key = Utilities.getStringSinModicar();
                    if (!controlador.productoExistentePorKey(key)) {
                        Utilities.P("El producto no existe");
                    } else if (controlador.isAvailableProduct(key)) {
                        pro = controlador.devuelveUnProductoDisponible(key);
                    } else {
                        Utilities.P("El producto no está disponible");
                    }
                } while (pro == null);
                if (controlador.reserveProduct(pro, c)) {
                    if (pro instanceof Others) {
                        Utilities.P("Importe de la compra: " + decimal.format(controlador.closeReservation(controlador.devolverReservaIdproduct(pro.getKey()))) + " €");
                        controlador.removeProduct(pro.getKey());
                        controlador.saveCatalogFromDDBB();
                    } else {
                        Utilities.P("Se ha hecho de forma correcta la reserva");
                    }

                } else {
                    Utilities.P("No se ha podido realizar la reserva");
                }
                break;
            case 13:
                Reservation r = null;
                int id = Utilities.getInt("Introduzca el ID de la reserva");
                r = controlador.devolverUnaReserva(id);
                if (r != null) {
                    float precio = (float) controlador.closeReservation(r);
                    Utilities.P("Importe de la reserva: " + decimal.format(precio) + " €");
                } else {
                    Utilities.P("No se ha podido realizar el cierre la reserva");
                }
                break;
            case 14:
                do {
                    opcion2 = Utilities.MenuGanancias();
                    controladorMenuGanancias(opcion2);
                } while (opcion2 != 5);
                break;
            case 15:
                controlador.saveAllDDBB();
                Utilities.P("Saliendo de la aplicación.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ControladorMenuCrearProductos(int op) {
        String name, descripcion = "";
        int edadmin, opcioncate = 0;
        double precio = 0;

        switch (op) {
            case 1:
                name = Utilities.getString("Introduce el nombre de la película");
                if (controlador.productoExistente(name, ProductsTypes.Peliculas)) {
                    Utilities.P("Ya existe un producto con ese nombre");
                    do {
                        opcioncate = Utilities.MenuSioNoAñadirExistencia();
                        if (Anadirexistencia(opcioncate, name, ProductsTypes.Peliculas)) {
                            Utilities.P("Se ha añadido una existencia al catálogo");
                        } else {
                            Utilities.P("No se ha creado existencia");
                        }
                    } while (opcioncate != 2);
                } else {
                    descripcion = Utilities.getString("Introduce la descripción de la Película");
                    edadmin = Utilities.getInt("Introduce la edad mínima");
                    Utilities.p("Introduce el precio del producto: ");
                    precio = Utilities.getDouble();
                    MovieCategory categoriapeli = null;
                    do {
                        opcioncate = Utilities.MenuDevolverTipoPeli();
                        categoriapeli = DevolverTipoPelicula(opcioncate);
                    } while (categoriapeli == null);
                    if (controlador.createMovie(ProductsTypes.Peliculas, name, descripcion, categoriapeli, edadmin, precio)) {
                        controlador.saveCatalogFromDDBB();
                        Utilities.P("Película creada correctamente.");
                    } else {
                        Utilities.P("No se ha podido crear el producto.");
                    }
                }

                break;
            case 2:
                name = Utilities.getString("Introduce el nombre del videojuego");
                if (controlador.productoExistente(name, ProductsTypes.Juegos)) {
                    Utilities.P("Ya existe un producto con ese nombre");
                    do {
                        opcioncate = Utilities.MenuSioNoAñadirExistencia();
                        if (Anadirexistencia(opcioncate, name, ProductsTypes.Juegos)) {
                            Utilities.P("Se ha añadido una existencia al catálogo");
                        } else {
                            Utilities.P("No se ha creado existencia");
                        }
                    } while (opcioncate != 2);
                } else {
                    descripcion = Utilities.getString("Introduce la descripción del videojuego");
                    edadmin = Utilities.getInt("Introduce la edad mínima");
                    Utilities.p("Introduce el precio del producto: ");
                    precio = Utilities.getDouble();
                    GameCategory categoriajuego = null;
                    do {
                        opcioncate = Utilities.MenuDevolverTipoJuego();
                        categoriajuego = DevolverTipoJuego(opcioncate);
                    } while (categoriajuego == null);
                    if (controlador.createGame(ProductsTypes.Juegos, name, descripcion, categoriajuego, edadmin, precio)) {
                        controlador.saveCatalogFromDDBB();
                        Utilities.P("Videojuego creado correctamente.");
                    } else {
                        Utilities.P("No se ha podido crear el producto.");
                    }
                }
                break;
            case 3:
                name = Utilities.getString("Introduce el nombre del producto");
                if (controlador.productoExistente(name, ProductsTypes.Otros)) {
                    Utilities.P("Ya existe un producto con ese nombre");
                    do {
                        opcioncate = Utilities.MenuSioNoAñadirExistencia();
                        if (Anadirexistencia(opcioncate, name, ProductsTypes.Otros)) {
                            Utilities.P("Se ha añadido una existencia al catálogo");
                        } else {
                            Utilities.P("No se ha creado existencia");
                        }
                    } while (opcioncate != 2);
                } else {
                    descripcion = Utilities.getString("Introduce la descripción del producto");
                    Utilities.p("Introduce el precio del producto: ");
                    precio = Utilities.getDouble();

                    if (controlador.createProduct(name, descripcion, precio)) {
                        controlador.saveCatalogFromDDBB();
                        Utilities.P("Producto creado correctamente.");
                    } else {
                        Utilities.P("No se ha podido crear el producto.");
                    }
                }
                break;
            case 4:
                Utilities.P("Volviendo al menú anterior.");
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

    private static void ControladorMenuListarClientes(int op) {
        Set<IClient> clientes;
        switch (op) {
            case 1:
                clientes = controlador.listAllClients();
                if (!clientes.isEmpty()) {
                    for (IClient cli : clientes) {
                        Utilities.P(cli.toString());
                    }
                } else {
                    Utilities.P("No hay clientes.");
                }
                break;
            case 2:
                int opc = 0;
                do {
                    opc = Utilities.MenuListarClientesOrdenados();
                    MostrarClientessOrdenados(opc);
                } while (opc != 7);

                break;
            case 3:
                clientes = controlador.listAllClientsWithReservationsNotFinished();
                if (!clientes.isEmpty()) {
                    for (IClient cli : clientes) {
                        Utilities.P(cli.toString());
                    }
                } else {
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

    private static void ControladorMenuListarReservas(int op) {
        Set<Reservation> reser;
        switch (op) {
            case 1:
                reser = controlador.listAllReservations();
                ListarReservas(reser);

                break;
            case 2:
                int opcioncomparadorreser = 0;
                while (opcioncomparadorreser != 7) {
                    do {
                        opcioncomparadorreser = Utilities.MenuOPcionOrdenarReserva();
                    } while (opcioncomparadorreser != 7 && devolverCompararReserva(opcioncomparadorreser) == null);
                    if (opcioncomparadorreser != 7) {
                        CompararReservas c = new CompararReservas(devolverCompararReserva(opcioncomparadorreser));
                        reser = controlador.listAllReservations(c);
                        ListarReservas(reser);
                    }

                }

                break;
            case 3:
                int opcionestadoreser = 0;
                while (opcionestadoreser != 4) {
                    do {
                        opcionestadoreser = Utilities.MenuOPcionEstadoReserva();
                    } while (devolverEstadoReserva(opcionestadoreser) == null && opcionestadoreser != 4);
                    if (opcionestadoreser != 4) {
                        reser = controlador.listAllReservations(devolverEstadoReserva(opcionestadoreser));
                        ListarReservas(reser);
                    }
                }

                break;
            case 4:
                Utilities.P("Volviendo al menú anterior.");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }

    private static void ListarReservas(Set<Reservation> reser) {
        if (!reser.isEmpty()) {
            for (Reservation r : reser) {
                Utilities.P(r.toString());
            }
        } else {
            Utilities.P("No hay reservas.");
        }
    }

    private static Reservation.StatusReserve devolverEstadoReserva(int op) {
        Reservation.StatusReserve status = null;
        switch (op) {
            case 1:
                status = Reservation.StatusReserve.ACTIVE;
                break;
            case 2:
                status = Reservation.StatusReserve.FINISHED;
                break;
            case 3:
                status = Reservation.StatusReserve.PENDING;
                break;
            case 4:
                Utilities.P("Volviendo al menú anterior");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
        return status;
    }

    private static CompararReservas.Criterio devolverCompararReserva(int op) {
        CompararReservas.Criterio criterio = null;
        switch (op) {
            case 1:
                criterio = CompararReservas.Criterio.IdMenoraMayor;
                break;
            case 2:
                criterio = CompararReservas.Criterio.IdMayoraMenor;
                break;
            case 3:
                criterio = CompararReservas.Criterio.FechaInicioReserva;
                break;
            case 4:
                criterio = CompararReservas.Criterio.NombreCliente;
                break;
            case 5:
                criterio = CompararReservas.Criterio.MenosAMallorPrecio;
                break;
            case 6:
                criterio = CompararReservas.Criterio.MallorAMenorPrecio;
                break;
            case 7:
                Utilities.P("Volviendo al menú anterior");
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
        return criterio;

    }

    private static void BorrarProductosControlador(int op) {

        switch (op) {
            case 1:
                Utilities.p("Introduce la key del producto a eliminar: ");
                String key = Utilities.getStringSinModicar();
                if (controlador.productoExistentePorKey(key)) {
                    if (controlador.removeProduct(key)) {
                        Utilities.P("Producto con key: " + key + " borrado correctamente.");
                        controlador.saveCatalogFromDDBB();
                    } else {
                        Utilities.P("No se ha podido eliminar el producto. El producto está en uso.");
                    }
                } else {
                    Utilities.P("No existe ningún producto con la key: " + key);
                }

                break;
            case 2:
                String nombre = Utilities.getString("Inroduce el nombre del producto");
                ProductsTypes tipo = devolverTypoProducto(Utilities.MenuTipoProducto());
                if (controlador.productoExistente(nombre, tipo)) {
                    if (controlador.removeProduct(nombre, tipo)) {
                        Utilities.P("Productos con las cararterísticas indicadas borrados correctamente.");
                        controlador.saveCatalogFromDDBB();
                    } else {
                        Utilities.P("No se ha podido eliminar el producto. El producto está en uso.");
                    }
                } else {
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

    private static MovieCategory DevolverTipoPelicula(int opcion) {
        MovieCategory categoria = null;
        switch (opcion) {
            case 1:
                categoria = MovieCategory.Horror;
                break;
            case 2:
                categoria = MovieCategory.Love;
                break;
            case 3:
                categoria = MovieCategory.Action;
                break;
            case 4:
                categoria = MovieCategory.SciFi;
                break;
            default:
                Utilities.P("Opción incorrecta. Prueba de nuevo.");
        }
        return categoria;
    }

    private static GameCategory DevolverTipoJuego(int opcion) {
        GameCategory categoria = null;
        switch (opcion) {
            case 1:
                categoria = GameCategory.Adeventures;
                break;
            case 2:
                categoria = GameCategory.Cars;
                break;
            case 3:
                categoria = GameCategory.Shooter;
                break;
            default:
                Utilities.P("Opción incorrecta. Prueba de nuevo.");
        }
        return categoria;
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
        Utilities.P("Productos disponibles:");
        if (!listap.isEmpty()) {
            for (Map.Entry<Product, Integer> entry : listap.entrySet()) {
                // Utilities.P(entry.getKey().toString() + " Cantidad ---> " + entry.getValue());
      Utilities.p("Nombre: " + entry.getKey().getName() + ", Descripción: " + entry.getKey().getDescription() + ", Precio:" + entry.getKey().getPrize() + ", Tipo:" + entry.getKey().getTipo() );
                if (entry.getKey() instanceof Juego) {
                  Juego j= (Juego)entry.getKey();
                   Utilities.p(", Categoria: "+j.getCategory()+", Edad minima: "+j.getEdadmnima());
                }else if(entry.getKey() instanceof Pelicula){
                Pelicula j= (Pelicula)entry.getKey();
                   Utilities.p(", Categoria: "+j.getCategory()+", Edad minima: "+j.getEdadmnima());
                }
                Utilities.p(" Cantidad ---> " + entry.getValue()+"\n");
                
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

    private static boolean Anadirexistencia(int op, String name, ProductsTypes tipo) {
        boolean creado = false;
        switch (op) {
            case 1:
                creado = controlador.addProduct(name, tipo);
                controlador.saveCatalogFromDDBB();
                break;
            case 2:
                break;
            default:
                Utilities.P("Opción incorrecta. Prueba de nuevo.");
        }
        return creado;
    }

    private static void controladorMenuGanancias(int op) {

        switch (op) {
            case 1:
                Utilities.P("" + decimal.format((float) controlador.getIncommings()));
                break;
            case 2:
                int año = Utilities.getInt("Introduzca el año");
                int mes = Utilities.getInt("Introduzca el mes");
                int dia = Utilities.getInt("Introduzca el dia");
                try {
                    LocalDate from = LocalDate.of(año, mes, dia);
                    Utilities.P("" + decimal.format((float) controlador.getIncommings(from)));
                } catch (Exception e) {
                    Utilities.P("Formato de Fecha incorrecto");
                }
                break;
            case 3:
                try {
                    LocalDate from = LocalDate.of(Utilities.getInt("Introduzca el año del principio"), Utilities.getInt("Introduzca el mes del principio"), Utilities.getInt("Introduzca el dia del principio"));
                    LocalDate to = LocalDate.of(Utilities.getInt("Introduzca el año del final"), Utilities.getInt("Introduzca el mes del final"), Utilities.getInt("Introduzca el dia del final"));
                    Utilities.P("" + decimal.format((float) controlador.getIncommings(from, to)));
                } catch (Exception e) {
                    Utilities.P("Formato de Fecha incorrecto");
                }
                break;
            case 4:

                Map<IClient, Double> list = controlador.resumeAllIncomingsByClient();
                if (!list.isEmpty()) {
                    for (Map.Entry<IClient, Double> entry : list.entrySet()) {
                        double cant = entry.getValue();
                        Utilities.P(entry.getKey().toString() + " Cantidad ---> " + decimal.format((float) cant) + "€");
                    }
                } else {
                    Utilities.P("Lista vacía.");
                }

                break;
            case 5:
                break;
            default:
                Utilities.P("Opción no válida, vuelve a intentarlo.");
        }
    }
}
