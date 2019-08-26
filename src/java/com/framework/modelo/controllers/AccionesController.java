/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.modelo.facades.AccionFacadeLocal;
import com.framework.modelo.entities.Accion;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "accionesController")
@SessionScoped
public class AccionesController implements Serializable {

    @EJB
    private AccionFacadeLocal afl;

    private List<Accion> acciones;
    private Accion accion;
    static int tm;
    String fragmento;

    public String getFragmento() {
        return fragmento;
    }

    public void setFragmento(String fragmento) {
        this.fragmento = fragmento;
    }

    public AccionesController() {
    }

    @PostConstruct
    public void init() {
        recargarAcciones();
    }

    public void recargarAcciones() {
        acciones = afl.findAll();
    }
    public void buscarAccionesNombreParcial() {
        acciones = afl.findByNombreParcialAccion(fragmento);
    }
    public AccionFacadeLocal getAfl() {
        return afl;
    }

    public void setAfl(AccionFacadeLocal afl) {
        this.afl = afl;
    }

    public List<Accion> getAcciones() {
        Collections.sort(acciones);
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public String verAcciones(Accion a) {
        this.accion = a;
        return "veracciones.xhtml?faces-redirect=true";
    }

    public String actualizarAcciones() {
        try {
            afl.edit(accion);
            System.out.println("Accion Modificada");
            return "veracciones.xhmtl?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String preModificar(Accion a) {
        setAccion(a);
        return "editaracciones.xhtml?faces-redirect=true";
    }

    public void eliminarAccion() {
        try {
            afl.remove(accion);
            recargarAcciones();
            System.out.println("Accion Eliminada: " + accion.getNombreAccion());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarAcciones() {
        int tm = acciones.size();
        return tm;
    }

    public String index() {
        recargarAcciones();
        return "veracciones.xhtml?faces-redirect=true";
    }
    
    public String nuevo(){
        return "registraracciones.xhtml?faces-redirect=true";
    }
}
