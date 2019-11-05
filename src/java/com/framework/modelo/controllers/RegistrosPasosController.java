/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Paso;
import com.framework.modelo.facades.PasoFacadeLocal;
import com.framework.util.MessageUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author SQA
 */
@Named(value = "registroPasosController")
@RequestScoped
public class RegistrosPasosController {

    @EJB

    private PasoFacadeLocal pfl;

    private List<Paso> pasos;

    private Paso nuevopaso;
    private Paso paso;
    static int valorUltimoPaso;
    private int valoralterno;
    private String validarrango;
    private int Valordupli;
    static int guardar;

    public RegistrosPasosController() {
    }

    @PostConstruct
    public void init() {
        nuevopaso = new Paso();
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public Paso getNuevopaso() {
        return nuevopaso;
    }

    public void setNuevopaso(Paso nuevopaso) {
        this.nuevopaso = nuevopaso;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public int getValorUltimoPaso() {
        return valorUltimoPaso;
    }

    public void setValorUltimoPaso(int valorUltimoPaso) {
        this.valorUltimoPaso = 1;
    }

    public int getValoralterno() {
        return valoralterno;
    }

    public void setValoralterno(int valoralterno) {
        this.valoralterno = valoralterno;
    }

    public String getValidarrango() {
        return validarrango;
    }

    public void setValidarrango(String validarrango) {
        this.validarrango = validarrango;
    }

    public void validarValorAlterno(AjaxBehaviorEvent event) {
        if (valoralterno > valorUltimoPaso) {
            validarrango = "Por favor escriba un valor en el rango(1 - " + valorUltimoPaso + ") o no escriba ningun valor y se tomara el valor determinado";
            RegistrosPasosController.guardar = 1;
        } else if (valoralterno < 0) {
            validarrango = "Por favor escriba un valor en el rango(1 - " + valorUltimoPaso + ") o no escriba ningun valor y se tomara el valor determinado";
            RegistrosPasosController.guardar = 1;
        } else {
            RegistrosPasosController.guardar = 2;
        }
    }

    public String registrar() {
        if (this.valoralterno == 0) {
            nuevopaso.setOrderstep(valorUltimoPaso);
            this.ValidarAction();
        } else if (RegistrosPasosController.guardar == 2) {
            String verifialter = String.valueOf(valoralterno);
            if (verifialter != null) {
                nuevopaso.setOrderstep(valoralterno);
                this.ValidarAction();
            }
        } else if (RegistrosPasosController.guardar == 1) {
            System.out.println(RegistrosPasosController.guardar);
            MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUDEN AGREGAR: ", "Verifique el orden de los pasos");
            return null;
        }
        return null;
    }
    public String registrar2() {
        nuevopaso.setOrderstep(valoralterno);
        pfl.agregarPaso(nuevopaso, valorUltimoPaso );
        return null;
    }

    public void ValidarAction() {
        System.out.println(nuevopaso.getOrderstep());
        pasos = pfl.findAll();
        
        for (Paso pass : pasos) {
            if (pass.getIdCaso().equals(nuevopaso.getIdCaso())) {
                System.out.println("entro al igual");
                if (pass.getActionStep().equals("Ingresar URL")) {
                    nuevopaso.setNavegador(pass.getNavegador());
                    if (nuevopaso.getActionStep().equals("Ingresar URL")) {
                        System.out.println("Ya existe");
                        this.Valordupli = 1;
                    } else if (this.Valordupli != 1) {
                        if (pass.getOrderstep() != null) {
                            if (nuevopaso.getOrderstep() <= pass.getOrderstep()) {
                                System.out.println("paso modificado" + pass.getOrderstep());
                                int order = pass.getOrderstep();
                                order += 1;
                                pass.setOrderstep(order);
                                this.updatestep(pass);
                                System.out.println("paso modificado" + pass.getOrderstep());
                            }
                        }
                        this.Valordupli = 2;
                    }
                }
            } else if (this.Valordupli != 1) {
                if (pass.getIdCaso().equals(nuevopaso.getIdCaso())) {
                    if (pass.getOrderstep() != null) {
                        if (nuevopaso.getOrderstep() <= pass.getOrderstep()) {
                            System.out.println("paso modificado" + pass.getOrderstep());
                            int order = pass.getOrderstep();
                            order += 1;
                            pass.setOrderstep(order);
                            this.updatestep(pass);
                            System.out.println("paso modificado" + pass.getOrderstep());
                        }
                    }
                    this.Valordupli = 2;
                }
            }
        }
        if (this.Valordupli == 1) {
            System.out.println("No puede guardar");
            MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUDEN AGREGAR: ", "La Accion Ingresar URL ya existe");

        } else {
            this.guardarTodo();
        }
    }

    public String guardarTodo() {

        pfl.create(nuevopaso);
        System.out.println("Paso Creado");

        return "verpasos.xhtml?faces-redirect=true";
    }

    public void updatestep(Paso p) {
        setPaso(p);
        pfl.edit(paso);
        System.out.println("modificado" + paso.getId()
        );
    }

    public void cargarPasos(AjaxBehaviorEvent event) {
        System.out.println("entro");
        pasos = pfl.obtenerValorOrder(nuevopaso.getIdCaso().getId());
        valorUltimoPaso = 1;
        nuevopaso.setOrderstep(valorUltimoPaso);

        for (Paso pass : pasos) {
            valorUltimoPaso = pass.getOrderstep() + 1;
            System.out.println(valorUltimoPaso);
            nuevopaso.setOrderstep(valorUltimoPaso);
        }
        pasos = pfl.ObtenerPasosAjax(nuevopaso.getIdCaso().getId());
    }
}
