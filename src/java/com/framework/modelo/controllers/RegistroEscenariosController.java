/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Escenario;
import com.framework.modelo.facades.EscenarioFacadeLocal;
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
@Named(value = "registroEscenariosController")
@RequestScoped
public class RegistroEscenariosController {

    @EJB
    private EscenarioFacadeLocal efl;

    private Escenario nuevoescenario;

    private List<Escenario> escenarios;

    public Date fechaSistema() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String strTimeStamp = dateFormat.format(date);
        System.out.println(strTimeStamp);
        Date fecha = dateFormat.parse(strTimeStamp);
        return fecha;
    }

    public RegistroEscenariosController() {
    }

    @PostConstruct
    public void init() {
        escenarios = efl.findAll();
        nuevoescenario = new Escenario();
    }

    public EscenarioFacadeLocal getEfl() {
        return efl;
    }

    public void setEfl(EscenarioFacadeLocal efl) {
        this.efl = efl;
    }

    public Escenario getNuevoescenario() {
        return nuevoescenario;
    }

    public void setNuevoescenario(Escenario nuevoescenario) {
        this.nuevoescenario = nuevoescenario;
    }
    
    public String registrar() throws ParseException{
        nuevoescenario.setFechaCreacion(fechaSistema());
        efl.create(nuevoescenario);
        System.out.println("Escenario Creado");
        init();
        return "verescenarios.xhtml?faces-rediretc=true";
    }
    
    public void recargarEscenarios(){
        escenarios = efl.findAll();
    }
}
