/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.modelo.entities.Ambiente;
import com.framework.modelo.facades.AmbienteFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "registroAmbienteController")
@RequestScoped
public class RegistroAmbienteController {
    /**
     * Creates a new instance of RegistroController
     */
    @EJB
    private AmbienteFacadeLocal afl;
    private Ambiente nuevoambiente;
    private List<Ambiente> ambientes;
    
    public RegistroAmbienteController(){
    }
    
    @PostConstruct
    public void init(){
        ambientes = afl.findAll();
        nuevoambiente = new Ambiente();
    }

    public AmbienteFacadeLocal getAfl() {
        return afl;
    }

    public void setAfl(AmbienteFacadeLocal afl) {
        this.afl = afl;
    }

    public Ambiente getNuevoambiente() {
        return nuevoambiente;
    }

    public void setNuevoambiente(Ambiente nuevoambiente) {
        this.nuevoambiente = nuevoambiente;
    }
    
    public String registrar(){
        afl.create(nuevoambiente);
        System.out.println("Ambiente Creado");
        init();
        return "verambientes.xhtml?faces-redirect=true";
    }
    
    public void recargarAmbiente(){
        ambientes = afl.findAll();
    }
}
