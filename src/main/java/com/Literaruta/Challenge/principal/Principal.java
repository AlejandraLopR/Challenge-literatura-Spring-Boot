package com.Literaruta.Challenge.principal;

import com.Literaruta.Challenge.model.*;
import com.Literaruta.Challenge.repository.LibroRepository;
import com.Literaruta.Challenge.service.ConsumoApi;
import com.Literaruta.Challenge.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado =new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository repositorio ;
    private List<Libro> libros;


    public Principal(LibroRepository repository){this.repositorio = repository;}



    public void muestraElMenu(){
        var json = consumoApi.ObtenerDatos(URL_BASE);
        System.out.println(json);
        var datosLibros = convierteDatos.obtenerDatos(json, DatosLibros.class);
        System.out.println(datosLibros);

        //MENU PRINCIPAL
        var opcion =-1;
        while(opcion!= 0){

            System.out.println("====================================");
            System.out.println("Eliga la opción a través de su número");
            var menu = """
                1- buscar libro por titúlo
                2- listar libros registrados
                3- lista de autores registrados
                4- listar autores vivos en un determinado año
                5- listar libros por idioma
                0- salir
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();


            switch (opcion){
                case 1:
                    buscarLibroPorWeb();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivosPorFecha();
                    break;
                case 5:
                    listarLibrosIdioma();
                    break;
                default:
                    System.out.println("Opción Invalida");
            }
        }
    }

    private void buscarLibroPorWeb(){
        //BUSQUEDA DE LIBRO POR NOMBRE
        System.out.println("Ingrede el libro que desee buscar: ");
        var tituloLibro = teclado.nextLine();
        var json =consumoApi.ObtenerDatos(URL_BASE + "?search="+tituloLibro.replace(" ","+"));
        var datosBusqueda = convierteDatos.obtenerDatos(json, DatosLibros.class);

        Optional<InfoLibro> libroEncontrado= datosBusqueda.libros().stream()
                .filter( l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();


        if(libroEncontrado.isPresent()){

            var libroFind = libroEncontrado.get();
            Libro libro = new Libro(libroFind);

            List<DatosAutor> autores = libroEncontrado.stream()
                    .flatMap(d -> d.autor().stream().map(e -> new DatosAutor(e)))
                    .collect(Collectors.toList());

            libro.setAutor(autores);
            repositorio.save(libro);
            System.out.println(libro.toString());

        }else{
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibrosBuscados() {
        libros =repositorio.findAll();
        libros.forEach(System.out::println);

    }

    public void listarAutoresRegistrados(){
        //System.out.println(repositorio.listarAutores());
        List<AutorPorLibros> autoresPorLibros =repositorio.listarAutores().stream()
                .map(e -> new AutorPorLibros((String) e[0],(String)  e[1],(String)  e[2],List.of((String) e[3]) )).collect(Collectors.toList());

        autoresPorLibros.forEach(System.out::println);


    }

    private void autoresVivosPorFecha() {
        System.out.println("Que escritores buscas en un año determinado? ");
        var fecha = teclado.nextLine();
        List<DatosAutor> autoresPorFecha = repositorio.autoresPorPeriodo(fecha);
        if(autoresPorFecha.isEmpty()){
            System.out.println("\nLo siento ... no hay algun autor en nuestra base de datos con esa fecha.");
        }
        else{
            //autoresPorFecha.forEach(s -> System.out.println(s.getName() + s.getFechaNacimiento() + s.getFechaDifunsion()));
            autoresPorFecha = autoresPorFecha.stream().collect(Collectors.toMap(DatosAutor::getName, Function.identity(), (autor1, autor2) ->autor1)).values().stream().toList();
            autoresPorFecha.forEach(System.out::println);
        }
    }

    private void listarLibrosIdioma(){
        List<Libro> librosPorIdioma;
        System.out.println("Idioamas de  libros que puedes conocer.");
        System.out.println("1.- ESPAÑOL - ES");
        System.out.println("2.- INGLES - EN");
        System.out.println("Escribe la abreviacion del idioma.");
        var lenguaje = teclado.nextLine();
        switch (lenguaje){
            case "ES":
                librosPorIdioma = repositorio.librosPorIdioma("es");
                librosPorIdioma.forEach(System.out::println);
                break;
            case  "EN":
                librosPorIdioma = repositorio.librosPorIdioma("en");
                librosPorIdioma.forEach(System.out::println);
                break;
            default:
                System.out.println("Ese idioma no se ecuntra disponible :(");

        }
    }


}
