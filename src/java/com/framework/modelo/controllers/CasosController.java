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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "casosController")
@SessionScoped
public class CasosController implements Serializable {

    @EJB

    private CasoFacadeLocal cfl;

    private List<Caso> casos;
    private Caso caso;
    static int tm;
    static SessionController sc;

    public CasosController() {
        sc = new SessionController();
    }

    @PostConstruct
    public void init() {
        RecargarlistaRole();
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

    public String verCasos(Caso c) {
        this.caso = c;
        return "vercasos.xhtml?faces-redirect=true";
    }

    public String actualizarCasos() {
        try {
            cfl.edit(caso);
            System.out.println("Caso Modificado");
            return "vercasos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String preModificar(Caso c) {
        setCaso(c);
        return "editarcasos.xhtml?faces-redirect=true";
    }

    public void eliminarCasos() {
        try {
            cfl.remove(caso);
            RecargarlistaRole();
            System.out.println("Caso Elminado: " + caso.getNombreCaso());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarCasos() {
        int tm = casos.size();
        return tm;
    }

    public String index() {
        RecargarlistaRole();
        return "vercasos.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        return "registrarcasos.xhtml?faces-redirect=true";
    }

    /**
     * Validacion de rol si es sqa carga todos Si es cliente se cargan los casos
     * asociadas a el
     */
    public void RecargarlistaRole() {
        if (sc.getPermisorol().equals("Sqa")) {
            //Recargar todas
            this.recargarCasos();
        } else {
            //Solo las asociadas
            this.recargarFiltroCliente();
        }
    }

    public void recargarCasos() {
        casos = cfl.findAll();
    }

    public void recargarFiltroCliente() {
        casos = cfl.ObtenerFiltroCliente(sc.getIdUsuarioConsultas());
    }
}
