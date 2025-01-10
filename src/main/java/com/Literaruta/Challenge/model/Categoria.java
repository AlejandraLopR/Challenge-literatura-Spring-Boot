package com.Literaruta.Challenge.model;

public enum Categoria {
    ACCION ("Action","Acción"),
    ROMANCE ("Romance", "Romance"),
    COMEDIA ("Comedy", "Comedia"),
    DRAMA ("Drama", "Drama"),
    CRIMEN ("Crime", "Crimen"),
    ARCHITECTURE ("Architecture","Arquitectura"),
    HISTORY ("History","Historia"),
    FICTION ("Fiction","Ficcion");

    private String categoriaGUTEN;
    private String categoriaEspanol;

    Categoria(String categoriaGUTEN,String categoriaEspanol){
        this.categoriaGUTEN= categoriaGUTEN;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString( String texto){
        for(Categoria categoria: Categoria.values()){
            if (categoria.categoriaGUTEN.equalsIgnoreCase(texto)){
                return  categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada" + texto);
    }

    public static Categoria fromEspanol( String texto){
        for(Categoria categoria: Categoria.values()){
            if (categoria.categoriaEspanol.equalsIgnoreCase(texto)){
                return  categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada" + texto);
    }


}
