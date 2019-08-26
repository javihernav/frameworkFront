/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.GrupoAccion;
import com.framework.modelo.facades.GrupoAccionFacadeLocal;
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
@Named(value = "grupoAccionesController")
@SessionScoped
public class GrupoAccionesController implements Serializable {

    @EJB
    private GrupoAccionFacadeLocal gafl;

    private List<GrupoAccion> grupoacciones;
    private GrupoAccion grupoaccion;
    static int tm;

    public GrupoAccionesController() {
    }

    @PostConstruct
    public void init() {
        recargarGrupoAcciones();
    }

    public void recargarGrupoAcciones() {
        grupoacciones = gafl.findAll();
    }

    public GrupoAccionFacadeLocal getGafl() {
        return gafl;
    }

    public void setGafl(GrupoAccionFacadeLocal gafl) {
        this.gafl = gafl;
    }

    public List<GrupoAccion> getGrupoacciones() {
        return grupoacciones;
    }

    public void setGrupoacciones(List<GrupoAccion> grupoacciones) {
        this.grupoacciones = grupoacciones;
    }

    public GrupoAccion getGrupoaccion() {
        return grupoaccion;
    }

    public void setGrupoaccion(GrupoAccion grupoaccion) {
        this.grupoaccion = grupoaccion;
    }

    // VER 
    public String verGrupoaccion(GrupoAccion ga) {
        this.grupoaccion = ga;
        return "vergrupoacciones?faces-redirect=true";
    }

    // ACTUALIZAR DATOS
    public String actualizarGrupoaccion() {
        try {
            gafl.edit(grupoaccion);
            System.out.println("Grupo Acciones Modificado");
            return "vergrupoacciones.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // EDITAR
    public String preModificar(GrupoAccion ga) {
        setGrupoaccion(ga);
        return "editargrupoacciones.xthml?faces-redirect=true";
    }

    // ELIMINAR USUARIO
    public void eliminargrupoacciones() {
        try {
            gafl.remove(grupoaccion);
            recargarGrupoAcciones();
            System.out.println("Grupo de Acciones Eliminados: " + grupoaccion.getNombreGrupoAccion());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarGrupoAcciones() {
        int tm = grupoacciones.size();
        return tm;
    }

    public String index() {
        recargarGrupoAcciones();
        return "vergrupoacciones.xhtml?faces-redirect=true";
    }
    
    public String nuevo(){
        return "registrargrupoacciones.xhtml?faces-rediretc=true";
    }
}
