/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.util;

import java.util.ArrayList;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author SQA-PRUEBA
 */
@Named(value = "metodo")
@Dependent
public class Metodo {
    private String nombre;
    private String targetNamespace;
    private ArrayList<Parametro> parametros;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    public ArrayList<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Parametro> parametros) {
        this.parametros = parametros;
    }

    public Metodo() {
        this.parametros=new ArrayList();
    }
    
}
