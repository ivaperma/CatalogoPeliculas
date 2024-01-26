package org.example.servicio;

import org.example.dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas {

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Si existe el archivo no se vuelve a crear
            if (archivo.exists()) {
                System.out.println("El archivo ya existe");
            } else {
                //Si no existe, se crea
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            System.out.println("Listado de películas");
            // Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            // Leemos línea a línea el archivo
            String linea;
            linea = entrada.readLine();
            // Leemos todas las líneas
            while (linea != null) {
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                // volvemos a leer la siguiente linea
                linea = entrada.readLine();
            }
            // Cerramos archivo
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            // Revisamos si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            // Agregar la película (toString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agregó al archivo: " + pelicula);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al añadir película: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            // Abrimos archivo para lectura, recorriendo línea a línea
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null) {
                // Buscamos sin importar mayusculas/minusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)) {
                    encontrada = true;
                    break;
                }
                // Leemos siguiente linea antes de seguir iterando
                lineaTexto = entrada.readLine();
                indice++;
            }
            // Imprimimos los resultados
            if (encontrada)
                System.out.println("Pelicula" + lineaTexto + " encontrada - línea: " + indice);
            else
                System.out.println("No se encontró la película: " + pelicula.getNombre());
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar película: " + e.getMessage());
        }
    }
}
