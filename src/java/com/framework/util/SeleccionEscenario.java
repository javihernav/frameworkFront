/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author SQA-PRUEBA
 */
@ManagedBean
@SessionScoped
public class SeleccionEscenario implements Serializable {

    /**
     * Creates a new instance of SeleccionServicio
     */
    private int numeroSeleccionEscenario;
    private String nombreEscenario;

    public int getNumeroSeleccionEscenario() {
        return numeroSeleccionEscenario;
    }

    public void setNumeroSeleccionEscenario(int numeroSeleccionEscenario) {
        this.numeroSeleccionEscenario = numeroSeleccionEscenario;
    }

    public String getNombreEscenario() {
        return nombreEscenario;
    }

    public void setNombreEscenario(String nombreEscenario) {
        this.nombreEscenario = nombreEscenario;
    }

    public SeleccionEscenario() {
    }

    public int isSeleccionServicio() {
        return numeroSeleccionEscenario;
    }

    public void setSeleccionServicio(int seleccionServicio) {
        this.numeroSeleccionEscenario = seleccionServicio;
    }

    public void siServicio() {
        this.numeroSeleccionEscenario = 4;
    }
    public void noServicio() {
        this.numeroSeleccionEscenario = 1;
    }
}
