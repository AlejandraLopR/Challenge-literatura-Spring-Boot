package com.Literaruta.Challenge.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
