/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.modelo.entities.Caso;
import com.framework.modelo.facades.CasoFacadeLocal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "registroCasosController")
@RequestScoped
public class RegistroCasosController {

    @EJB

    private CasoFacadeLocal cfl;

    private List<Caso> casos;

    private Caso nuevocaso;

    public Date fechaSistema() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String strTimeStamp = dateFormat.format(date);
        System.out.println(strTimeStamp);
        Date fecha = dateFormat.parse(strTimeStamp);
        return fecha;
    }

    public RegistroCasosController() {
    }
    
    @PostConstruct
    public void init() {
        casos = cfl.findAll();
        nuevocaso = new Caso();
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public Caso getNuevocaso() {
        return nuevocaso;
    }

    public void setNuevocaso(Caso nuevocaso) {
        this.nuevocaso = nuevocaso;
    }
    
    public String registrar() throws ParseException {
        nuevocaso.setFechaCreacion(fechaSistema());
        cfl.create(nuevocaso);
        System.out.println("Caso Creado");
        init();
        return "vercasos.xhtml?face-redirect=true";
    }
    
    public void recargarCasos(){
        casos = cfl.findAll();
    }
}
