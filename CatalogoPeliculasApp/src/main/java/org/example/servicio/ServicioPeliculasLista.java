package org.example.servicio;

import org.example.dominio.Pelicula;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas {

    private final List<Pelicula> peliculas;

    public ServicioPeliculasLista() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas() {
        System.out.println("Listado de peliculas");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        System.out.println("Se añadió la pelicula: " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var indice = peliculas.indexOf(pelicula);
        System.out.println("Pelicula encontrada en el índice: " + indice);
    }
}
