/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Suit;
import com.framework.modelo.facades.SuitFacadeLocal;
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
@Named(value = "registroSuitsController")
@RequestScoped
public class RegistroSuitsController {

    /**
     * Creates a new instance of RegistroController
     */
    @EJB
    private SuitFacadeLocal sfl;

    private Suit nuevasuit;

    private List<Suit> suits;

    public Date fechaSistema() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String strTimeStamp = dateFormat.format(date);
        System.out.println(strTimeStamp);
        Date fecha = dateFormat.parse(strTimeStamp);
        return fecha;
    }

    public RegistroSuitsController() {
    }

    @PostConstruct
    public void init() {
        suits = sfl.findAll();
        nuevasuit = new Suit();
    }

    public SuitFacadeLocal getSfl() {
        return sfl;
    }

    public void setSfl(SuitFacadeLocal sfl) {
        this.sfl = sfl;
    }

    public Suit getNuevasuit() {
        return nuevasuit;
    }

    public void setNuevasuit(Suit nuevasuit) {
        this.nuevasuit = nuevasuit;
    }

    public List<Suit> getSuits() {
        return suits;
    }

    public void setSuits(List<Suit> suits) {
        this.suits = suits;
    }

//    public boolean validarnombre() {
//        boolean paso = false;
//        for (Suits suit : suits) {
//            if (nuevasuit.getNombreSuit().equals(suit)) {
//                paso = true;
//            } else {
//                paso = false;
//            }
//        }
//        return paso;
//    }

    public String registrar() throws ParseException {
        nuevasuit.setFechaCreacion(fechaSistema());
        sfl.create(nuevasuit);
        System.out.println("Suit Creada");
        init();
        return "versuits.xhtml?faces-redirect=true";
    }

    public void recargarSuits() {
        suits = sfl.findAll();
    }
}
