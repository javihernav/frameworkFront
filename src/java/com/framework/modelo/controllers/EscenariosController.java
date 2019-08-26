/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.login.controllers.SessionController;
import com.framework.modelo.entities.Escenario;
import com.framework.modelo.entities.Suit;
import com.framework.modelo.facades.EscenarioFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "escenariosController")
@SessionScoped
public class EscenariosController implements Serializable {

    @EJB
    private EscenarioFacadeLocal efl;
    
    static private List<Escenario> escenarios;
    private Escenario escenario;
    static int tm;
    static SessionController sc;

    public EscenariosController() {
        sc = new  SessionController();
    }

    @PostConstruct
    public void init() {
        sc = new  SessionController();
        RecargarlistaRole();
    }


    public EscenarioFacadeLocal getEfl() {
        return efl;
    }

    public void setEfl(EscenarioFacadeLocal efl) {
        this.efl = efl;
    }

    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public String verEscenarios(Escenario e) {
        this.escenario = e;
        return "verescenarios.xhtml?faces-redirect=true";
    }

    public String actualizarEscenarios() {
        try {
            efl.edit(escenario);
            System.out.println("Escenario Modificado"); 
            return "verescenarios.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String preModificar(Escenario e) {
        setEscenario(e);
        return "editarescenarios.xhtml?faces-redirect=true";
    }

    public void eliminarEscenarios() {
        try {
            efl.remove(escenario);
            RecargarlistaRole();
            System.out.println("Escenario Elminado: " + escenario.getNombreEscenario());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarEscenarios() {
        int tm = escenarios.size();
        return tm;
    }

    public String index() {
        RecargarlistaRole();
        return "verescenarios.xhtml?faces-redirect=true";
    }
    
    public String nuevo(){
        return "registrarescenarios.xhtml?faces-redirect=true";
    }
    
    /**
     * Validacion de rol si es sqa carga todos
     * Si es cliente se cargan los escenarios asociadas a el
     */
    public void RecargarlistaRole() {
        if (sc.getPermisorol().equals("Sqa")) {
            //Recargar todas
            this.recargarEscenarios();
            System.out.println("Entro list escenarios sqa");
        }else{
            System.out.println("Entro list escenarios cliente");
            //Solo las asociadas
            this.recargarFiltroCliente();
        }
    }
    
    
    public void recargarEscenarios() {
        escenarios = efl.findAll();
    }
    
    public void recargarFiltroCliente(){
        System.out.println("ID: " + sc.getIdUsuarioConsultas());
        escenarios = efl.ObtenerEscenarioConUsuario(sc.getIdUsuarioConsultas());
    }
}
