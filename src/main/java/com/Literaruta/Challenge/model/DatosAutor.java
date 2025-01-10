package com.Literaruta.Challenge.model;

import jakarta.persistence.*;

@Entity

@Table(name = "autores")
public class DatosAutor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private String fechaNacimiento;
    private String fechaDifunsion;
    @ManyToOne
    private Libro libro;

    public DatosAutor(){

    }
    public DatosAutor(InfoAutor infoAutor){
        this.name= infoAutor.nombre();
        this.fechaNacimiento = infoAutor.fechaNacimiento();
        this.fechaDifunsion = infoAutor.fechaDifusion();
    }

    public DatosAutor(String nombre, String s, String s1) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDifunsion() {
        return fechaDifunsion;
    }

    public void setFechaDifunsion(String fechaDifunsion) {
        this.fechaDifunsion = fechaDifunsion;
    }

    public void setLibro(Libro libro) {
        this.libro =libro;
    }

    public  Libro getLibro(){
        return libro;
    }

    @Override
    public String toString() {
        return "\n-----AUTOR-----"+
                "\nNombre: " + name+
                "\nFecha de Nacimiento: " + fechaNacimiento+
                "\nFecha de Difunsion: " + fechaDifunsion+
                "\n-----------------\n";
    }
}
