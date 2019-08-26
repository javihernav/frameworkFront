/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.modelo.entities.Accion;
import com.framework.modelo.facades.AccionFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "registroAccionesController")
@RequestScoped
public class RegistroAccionesController {
    
    @EJB
    private AccionFacadeLocal afl;
    
    private Accion nuevaAccion;
    
    private List<Accion> acciones;
    
    public RegistroAccionesController(){
        
    }
    
    @PostConstruct
    public void init(){
        acciones = afl.findAll();
        nuevaAccion = new Accion();
    }

    public AccionFacadeLocal getAfl() {
        return afl;
    }

    public void setAfl(AccionFacadeLocal afl) {
        this.afl = afl;
    }

    public Accion getNuevaAccion() {
        return nuevaAccion;
    }

    public void setNuevaAccion(Accion nuevaAccion) {
        this.nuevaAccion = nuevaAccion;
    }
    
    public String registrar(){
        afl.create(nuevaAccion);
        System.out.println("Accion Creada");
        return "veracciones.xhtml?faces-redirect=true";
    }
    
    public void recargarAcciones(){
        acciones = afl.findAll();
    }
}
