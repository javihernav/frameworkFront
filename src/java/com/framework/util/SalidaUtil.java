/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.util;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author SQA-PRUEBA
 */
@Named(value = "salida")
@Dependent
public class SalidaUtil {
    private String nombre;
    private String tipo;
    private String origen;
    private int idmetodo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getIdmetodo() {
        return idmetodo;
    }

    public void setIdmetodo(int idmetodo) {
        this.idmetodo = idmetodo;
    }

    @Override
    public String toString() {
        return "SalidaUtil{" + "nombre=" + nombre + ", tipo=" + tipo + ", origen=" + origen + ", idmetodo=" + idmetodo + '}';
    }
    
}
