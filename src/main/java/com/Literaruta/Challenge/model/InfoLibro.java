package com.Literaruta.Challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InfoLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<InfoAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count")  Double numeroDescargas
) {
}
