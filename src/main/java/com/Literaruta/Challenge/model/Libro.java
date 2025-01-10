package com.Literaruta.Challenge.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="libros")

public class Libro {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DatosAutor> autor;
    private String lenguage;
    private Double downloadCount;


    public  Libro(){

    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List <DatosAutor> getAutor() {
        return autor;
    }

    public void setAutor(List<DatosAutor> autor) {
        autor.forEach(e -> e.setLibro(this) );
        this.autor = autor;
    }

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }


    public  Libro(InfoLibro infoLibro){
        this.titulo = infoLibro.titulo();
        this.downloadCount = infoLibro.numeroDescargas();
        this.lenguage = infoLibro.idioma().get(0);

    }

    @Override
    public String toString(){
        String autores = autor.stream().map(a -> {
            String[] partes = a.getName().split(", ");
            return partes.length == 2 ? partes[1]+" "+partes[0] : a.getName();
        }).collect(Collectors.joining(", "));
        //String autores = autor.stream().map(DatosAutor::getName).collect(Collectors.joining());

        return "\n-----TITULO-----"+
                "\nTitulo= " + titulo+
                "\nLenguage= " + lenguage+
                "\nNumero de descargas= " + downloadCount+
                "\nAutor= " +  autores +"\n" +
                "-----------------\n";
    }
}
