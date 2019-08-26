/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.GrupoAccion;
import com.framework.modelo.facades.GrupoAccionFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author SQA
 */
@Named(value = "registroGrupoAccionesController")
@RequestScoped
public class RegistroGrupoAccionesController {

    @EJB
    private GrupoAccionFacadeLocal gafl;

    private GrupoAccion nuevoGrupoAccion;

    private List<GrupoAccion>grupoAcciones;

    public RegistroGrupoAccionesController() {

    }

    @PostConstruct
    public void init() {
        grupoAcciones = gafl.findAll();
        nuevoGrupoAccion = new GrupoAccion();
    }

    public GrupoAccionFacadeLocal getGafl() {
        return gafl;
    }

    public void setGafl(GrupoAccionFacadeLocal gafl) {
        this.gafl = gafl;
    }

    public GrupoAccion getNuevoGrupoAccion() {
        return nuevoGrupoAccion;
    }

    public void setNuevoGrupoAccion(GrupoAccion nuevoGrupoAccion) {
        this.nuevoGrupoAccion = nuevoGrupoAccion;
    }

    public String registrar() {
        gafl.create(nuevoGrupoAccion);
        System.out.println("Grupo Acciones Creado");
        init();
        return "vergrupoacciones.xhtml";
    }

    public void recargarGrupoAcciones() {
        grupoAcciones = gafl.findAll();
    }
}
