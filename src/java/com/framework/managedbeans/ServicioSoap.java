/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.managedbeans;

import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author SQA-PRUEBA
 */
@Named(value = "urlServicio")
@Dependent
public class ServicioSoap {

    /**
     * Creates a new instance of UrlServicio
     */
    public ServicioSoap() {
        url="";
        datos=new ArrayList();
    }
    private String url;
    ArrayList datos;

    public String getUrl() {
        return url;
    }

    public ArrayList getDatos() {
        return datos;
    }

    public void setDatos(ArrayList datos) {
        this.datos = datos;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
