package com.Literaruta.Challenge.model;

import java.util.List;

public class AutorPorLibros {
    private String name;
    private String fechaDeNacimiento;
    private String fechaDifusion;
    private List<String> books;



    public AutorPorLibros(String name, String fechaDeNacimiento, String fechaDifusion, List<String> books){
        this.name=name;
        this.fechaDeNacimiento= fechaDeNacimiento;
        this.fechaDifusion= fechaDifusion;
        this.books = books;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDifusion() {
        return fechaDifusion;
    }

    public void setFechaDifusion(String fechaDifusion) {
        this.fechaDifusion = fechaDifusion;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "-----AUTORES-----\n"+
                "Name: "+name+
                "\nFecha de nacimiento:  "+fechaDeNacimiento+
                "\nFecha de difunsion: "+fechaDifusion+
                "\nLibros del autor: "+ books+
                "\n-----------------\n";
    }
}
