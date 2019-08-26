/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;


import com.framework.modelo.entities.Ambiente;
import com.framework.modelo.facades.AmbienteFacadeLocal;
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
@Named(value = "ambientesController")
@SessionScoped
public class AmbientesController implements Serializable {

    @EJB
    private AmbienteFacadeLocal afl;

    private List<Ambiente> ambientes;
    private Ambiente ambiente;
    static int tm;

    public AmbientesController() {
    }

    @PostConstruct
    public void init() {
        recargarAmbientes();
    }

    public void recargarAmbientes() {
        ambientes = afl.findAll();
    }

    public AmbienteFacadeLocal getAfl() {
        return afl;
    }

    public void setAfl(AmbienteFacadeLocal afl) {
        this.afl = afl;
    }

    public List<Ambiente> getAmbientes() {
        // Lista de acciones correspondiete al ambiente id static
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public String verAmbientes(Ambiente a) {
        this.ambiente = a;
        return "verambientes.xhtml?faces-redirect=true";
    }

    public String actualizarAmbientes() {
        try {
            afl.edit(ambiente);
            System.out.println("Ambiente Modificado");
            return "verambientes.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String preModificar(Ambiente a) {
        setAmbiente(a);
        return "editarambientes.xhtml?faces-redirect=true";
    }

    public void eliminarAmbiente() {
        try {
            afl.remove(ambiente);
            recargarAmbientes();
            System.out.println("Ambiente Eliminado: " + ambiente.getNombreAmbiente());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarAcciones() {
        int tm = ambientes.size();
        return tm;
    }

    public String index() {
        recargarAmbientes();
        return "verambientes.xhtml?faces-redirect=true";
    }
    
    public String nuevo(){
        return "registrarambiente.xhtml?faces-redirect=true";
    }
}
