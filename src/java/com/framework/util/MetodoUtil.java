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
public class MetodoUtil {
    private String nombre;
    private String targetNamespace;
    private String input;
    private String output;
    private ArrayList<ParametroUtil> parametros;
    private SalidaUtil salida;
private String valorEsperado;
private int idpaso;
private String contenttype;
    
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

    public ArrayList<ParametroUtil> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<ParametroUtil> parametros) {
        this.parametros = parametros;
    }

    public MetodoUtil() {
        this.parametros=new ArrayList();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getValorEsperado() {
        return valorEsperado;
    }

    public void setValorEsperado(String valorEsperado) {
        this.valorEsperado = valorEsperado;
    }

    public int getIdpaso() {
        return idpaso;
    }

    public void setIdpaso(int idpaso) {
        this.idpaso = idpaso;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public SalidaUtil getSalida() {
        return salida;
    }

    public void setSalida(SalidaUtil salida) {
        this.salida = salida;
    }

    @Override
    public String toString() {
        return "MetodoUtil{" + "nombre=" + nombre + ", targetNamespace=" + targetNamespace + ", input=" + input + ", output=" + output + ", parametros=" + parametros + ", salida=" + salida + ", valorEsperado=" + valorEsperado + ", idpaso=" + idpaso + ", contenttype=" + contenttype + '}';
    }
    
}
