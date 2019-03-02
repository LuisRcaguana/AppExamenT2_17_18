package com.example.a21746033.appexament2_17_18.model;

import java.io.Serializable;

public class Alimento implements Serializable {

    private int id;
    private String nombre;
    private String tipo;
    private String origen;
    private String nutrientes;
    private String funcion;

    public Alimento(String nombre, String tipo, String origen, String nutrientes, String funcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.nutrientes = nutrientes;
        this.funcion = funcion;
    }

    public Alimento(int id, String nombre, String tipo, String origen, String nutrientes, String funcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.nutrientes = nutrientes;
        this.funcion = funcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public String getFuncion() {
        return funcion;
    }

    public int getId() {
        return id;
    }
}