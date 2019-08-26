/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.login.controllers.SessionController;
import com.framework.modelo.entities.Suit;
import com.framework.modelo.facades.SuitFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "suitsController")
@SessionScoped
public class SuitsController implements Serializable {

    @EJB
    private SuitFacadeLocal sfl;

    static private List<Suit> suits;
    private Suit suit;
    static int tm;
    static SessionController sc;

    public SuitsController() {
        sc = new SessionController();
    }

    @PostConstruct
    public void init() {
        this.RecargarlistaRole();
    }

    public SuitFacadeLocal getSfl() {
        return sfl;
    }

    public void setSfl(SuitFacadeLocal sfl) {
        this.sfl = sfl;
    }

    public List<Suit> getSuits() {
        return suits;
    }

    public void setSuits(List<Suit> suits) {
        this.suits = suits;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String verSuits(Suit s) {
        this.suit = s;
        return "versuits.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        return "registrarsuits.xhtml?faces-redirect=true";
    }

    public String actualizarSuits() {
        try {
            sfl.edit(suit);
            System.out.println("Suit Modificada");
            return "versuits.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String preModificar(Suit s) {
        setSuit(s);
        return "editarsuits.xhtml?faces-redirect=true";
    }

    public void eliminarSuit() {
        try {
            sfl.remove(suit);
            RecargarlistaRole();
            System.out.println("Suit Elminado: " + suit.getNombreSuit());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarSuits() {
        int tm = suits.size();
        return tm;
    }

    public String index() {
        return "versuits.xhtml?faces-redirect=true";
    }

    /**
     * Validacion de rol si es sqa carga todos Si es cliente se cargan las suit
     * asociadas a el
     */
    public void RecargarlistaRole() {
        if (sc.getPermisorol().equals("Sqa")) {
            //Recargar todas
            this.recargarSuits();
        } else {
            //Solo las asociadas
            this.recargarFiltroCliente();
        }
    }

    //Recargar suits asociadas
    public void recargarFiltroCliente() {
        System.out.println("USUARIO SESION: " + sc.getIdUsuarioConsultas());
        suits = sfl.ObtenerSuitConUsuario(sc.getIdUsuarioConsultas());
    }

    //Recargar todas
    public void recargarSuits() {
        suits = sfl.findAll();
    }

}
