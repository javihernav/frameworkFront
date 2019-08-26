/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.login.controllers.SessionController;
import com.framework.modelo.entities.Caso;
import com.framework.modelo.facades.CasoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.event.ReorderEvent;

/**
 *
 * @author SQA
 */
@ManagedBean(name = "casosController2")
public class CasosController2 implements Serializable {

    @EJB
    private CasoFacadeLocal cfl;

    private List<Caso> casos;
    
    @ManagedProperty("#{casos}")
    private Caso caso;
    
    static int tm;
    static SessionController sc;

    public CasosController2() {
        sc = new SessionController();
    }

    @PostConstruct
    public void init() {
       casos = cfl.findAll();
        for (Caso caso1 : casos) {
            System.out.println("CASOS: " + caso1.getNombreCaso());
        }
    }

    public CasoFacadeLocal getCfl() {
        return cfl;
    }

    public void setCfl(CasoFacadeLocal cfl) {
        this.cfl = cfl;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row Moved", "From: " + event.getFromIndex() + ", To:" + event.getToIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
