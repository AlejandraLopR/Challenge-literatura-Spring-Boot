package com.Literaruta.Challenge.repository;

import com.Literaruta.Challenge.model.AutorPorLibros;
import com.Literaruta.Challenge.model.DatosAutor;
import com.Literaruta.Challenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query(value = "SELECT a.name, a.fecha_nacimiento, a.fecha_difunsion, STRING_AGG(l.titulo, ', ') FROM autores a  JOIN libros l ON a.libro_id = l.id GROUP BY a.name, a.fecha_nacimiento, a.fecha_difunsion" , nativeQuery = true)
    List<Object[]> listarAutores();

    @Query("SELECT s FROM DatosAutor s WHERE :fecha  BETWEEN s.fechaNacimiento AND s.fechaDifunsion ")
    List<DatosAutor> autoresPorPeriodo(String fecha);

    @Query("SELECT l FROM Libro  l WHERE lenguage = :leng")
    List<Libro> librosPorIdioma(String leng);
}
