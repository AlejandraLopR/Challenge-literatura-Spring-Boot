package com.Literaruta.Challenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InfoAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento,
        @JsonAlias("death_year") String fechaDifusion
) {
}
