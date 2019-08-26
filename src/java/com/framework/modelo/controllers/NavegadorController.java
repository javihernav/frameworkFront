/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Navegador;
import com.framework.modelo.facades.NavegadorFacadeLocal;
import com.framework.util.MessageUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author SQA
 */
@Named(value = "navegadorController")
@SessionScoped
public class NavegadorController implements Serializable {

    @EJB
    private NavegadorFacadeLocal nfl;

    private Navegador navegador;
    private List<Navegador> navegadores;

    /**
     * Creates a new instance of NavegadorController
     */
    public NavegadorController() {
    }

    @PostConstruct
    public void init() {
        navegador = new Navegador();
        this.recargarNavegador();
    }

    public List<Navegador> getNavegadores() {
        return navegadores;
    }

    public void setNavegadores(List<Navegador> navegadores) {
        this.navegadores = navegadores;
    }

    public Navegador getNavegador() {
        return navegador;
    }

    public void setNavegador(Navegador navegador) {
        this.navegador = navegador;
    }

    public Date fechaSistema() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String strTimeStamp = dateFormat.format(date);
        System.out.println(strTimeStamp);
        Date fecha = dateFormat.parse(strTimeStamp);
        return fecha;
    }

    public void recargarNavegador() {
        navegadores = nfl.findAll();
        for (Navegador nav : navegadores) {
            System.out.println(nav.getNombreNavegador());
        }
    }

    public void eliminarNavegador() {
        try {
            nfl.remove(navegador);
            recargarNavegador();
            System.out.println("Navegador Eliminado :" + navegador.getNombreNavegador());
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public String preModificar(Navegador nav) {
        setNavegador(nav);
        return "editarnavegadores.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        navegador = new Navegador();
        return "registrarnavegador.xhtml?faces-redirect=true";
    }

    //Metodo para registrar un nuevo navegador
    public String registrar() throws ParseException {
        //Variable para verificar existencia
        String save = "";
        //ForEach para recorrer la tabla y verificar existencia
        for (Navegador nav : navegadores) {
            //Si existe devuelvo 'save' como no, lo contrario la devuelve yes 
            if (navegador.getNombreNavegador().equals(nav.getNombreNavegador())) {
                save = "no";
                System.out.println("ya existe");
            }
        }

        if (save.equals("no")) {
            MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUEDEN GUARDAR: ", "El navegador " + navegador.getNombreNavegador() + " ya existe.");
            return null;
        } else {
            navegador.setFechaCreacion(fechaSistema());
            nfl.create(navegador);
            System.out.println("Navegador creador");
            return "vernavegadores.xhtml?faces-redirect=true";
        }
    }

    public void editar() {
        //Variable para verificar existencia
        String save = "";
        navegadores = nfl.findAll();
        //ForEach para recorrer la tabla y verificar existencia
        for (Navegador nav : navegadores) {
            //Si existe devuelvo 'save' como no, lo contrario la devuelve yes 
            if (nav.getId() != navegador.getId()) {
                if (navegador.getNombreNavegador().equals(nav.getNombreNavegador())) {
                    save = "no";
                }
            }
        }

        if (save.equals("no")) {
            MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUEDEN MODIFICAR LOS DATOS: ", "El navegador " + navegador.getNombreNavegador() + " ya existe.");
            System.out.println("ya existe");
        } else {
            MessageUtil.enviarMensajeInformacionGlobal("LOS DATOS FUERÓN MODIFICADOS EXITOSAMENTE: ", "Por favor refrescar la tabla para visualizar los cambios");
            nfl.edit(navegador);
            System.out.println("Editado");
        }
    }

}
