/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.login.controllers.SessionController;
import com.framework.modelo.entities.Paso;
import com.framework.modelo.facades.PasoFacadeLocal;
import com.framework.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.json.JsonObject;
import org.primefaces.event.ReorderEvent;

/**
 *
 * @author SQA
 */
@Named(value = "pasosController")
@SessionScoped
public class PasosController implements Serializable {

    @EJB
    private PasoFacadeLocal pfl;

    private List<Paso> pasos;
    private Paso paso;
    static int tm;
    static SessionController sc;

    private int valorUltimoPaso;

    public PasosController() {
        sc = new SessionController();
    }

    @PostConstruct
    public void init() {
        pasos = pfl.findAll();
//        pasos = pfl.findAllOrderByStepOrder();
        this.RecargarlistaRole();
    }

    public PasoFacadeLocal getPfl() {
        return pfl;
    }

    public void setPfl(PasoFacadeLocal pfl) {
        this.pfl = pfl;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public String verPasos(Paso p) {
        this.paso = p;
        return "verpasos.xhtml?faces-redirect=true";
    }

    public int getValorUltimoPaso() {
        return valorUltimoPaso;
    }

    public void setValorUltimoPaso(int valorUltimoPaso) {
        this.valorUltimoPaso = valorUltimoPaso;
    }

    //Validacion de modificacion 
    public void verifityUpdate() {
        //Validacion las acciones que no puede modificar
        switch (paso.getActionStep()) {
            case "Ir Atras":
            case "Ir Adelante":
            case "Refrescar":
            case "Cerrar Navegador":
            case "Cambiar a Frame Principal":
            case "Alternar Frame Mismo Nivel":
            case "Cerrar Ventana":
            case "Cambiar de ventana":
            case "Volver a Ventana Original":
            case "Cambiar Pestana":
            case "Comparar Texto Alert":
            case "Click Boton \"Aceptar\"":
            case "Click Boton \"Cancelar\"":
            case "Limpiar Campo":
            case "Marcar Todos CheckBox":
            case "Desmarcar Todos CheckBox":
            case "Click":
            case "Doble Click":
            case "Click Derecho":
            case "Tomar Evidencia":
            case "Tomar Evidencia Elemento":
            case "Enviar formulario":
            case "Mover A Elemento":
            case "Funcion de teclado":
            case "Scroll Abajo a Arriba":
            case "Scroll Arriba a Abajo":
            case "Menu":
            case "Ir Atras Navegador":
            case "Cambiar Navegador":
            case "Ejecutar Paquete Aplicacion":
            case "Finalizar Ejecucion":
            case "Cerrar Aplicacion":
            case "EndLoop":
            case "Click Derecho En Pagina":
            case "Click JS":
            case "Click Mouse":
            case "Click Robot":
                MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUDEN MODIFICAR: ", "No esta permitido realizar modificacion a este paso.");
                this.RecargarlistaRole();

                //Validacion que solo puede editar el parametro
                break;
            case "Ingresar URL":
            case "Cambiar Frame":
            case "Ingresar Texto Alert":
            case "SendKey":
            case "Presionar Tecla":
            case "Seleccionar Elemento Lista (Visible Text)":
            case "Seleccionar Elemento Lista (Indext)":
            case "Seleccionar Elemento Lista (Value)":
            case "Deseleccionar Elemento Lista (Visible Text)":
            case "Deseleccionar Elemento Lista (Index)":
            case "Deseleccionar Elemento Lista (value)":
            case "Esperar (Millisegundos)":
            case "Esperar elemento":
            case "Ejecutar Aplicacion":
            case "Condicional":
            case "Sikuli":
            case "Nueva Pestana":
            case "SendKey Excel":
            case "Loop":
            case "Fila Inicial Excel":
            case "Borrar Cookies":
            case "SendKey Excel Robot":
            case "SendKey Robot":
            case "SendKey Excel Gui":
            case "SendKey Gui":
                if (paso.getParameterStep() != null && paso.getCorYStep() == null && paso.getCorXStep() == null) {

                    this.actualizarPasos();

                } else {
                    MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUDIERÓN MODIFICAR: ", "Solo se puede modificar el parametro pero no las coordenadas.");
                    this.RecargarlistaRole();
                }
                break;
            case "Scroll Elemento":
            case "Arrastrar y Soltar":
            case "Mover A Elemento Coordenadas":
            case "Scroll":
                if (paso.getParameterStep() != null && paso.getCorYStep() != null && paso.getCorXStep() != null) {
                    this.actualizarPasos();
                } else {
                    MessageUtil.enviarMensajeErrorGlobal("LOS DATOS NO SE PUDIERÓN MODIFICAR: ", "Por favor ingrese todos los datos para modificarlos.");
                    this.RecargarlistaRole();
                }
                break;
            default:
                break;
        }

    }

    public void actualizarPasos() {
        try {
            System.out.println("nombre: " + paso.getActionStep());
            pfl.edit(paso);
            for (Paso pass : pasos) {
                if (pass.getIdCaso().equals(paso.getIdCaso())) {
                    if (pass.getOrderstep() != null) {
                        if ((paso.getOrderstep()).equals(pass.getOrderstep())) {
                            if (paso.getId() != pass.getId()) {
                                if (paso.getOrderstep() <= pass.getOrderstep()) {
                                    System.out.println("paso modificado" + pass.getOrderstep());
                                    int order = pass.getOrderstep();
                                    order += 1;
                                    pass.setOrderstep(order);
                                    this.updatestep(pass);
                                    System.out.println("paso modificado" + pass.getOrderstep());
                                }
                            }
                        }
                    }
                }
            }
            this.RecargarlistaRole();
            System.out.println("Paso Modificado");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public String preModificar(Paso p) {
        setPaso(p);
        return "editarpasos.xhtml?faces-redirect=true";
    }

    public void eliminarPasos() {
        try {
            pfl.remove(paso);
            for (Paso pass : pasos) {
                if (pass.getIdCaso().equals(paso.getIdCaso())) {
                    if (pass.getOrderstep() != null) {
                        if (pass.getOrderstep() > paso.getOrderstep()) {
                            System.out.println("paso modificado" + pass.getOrderstep());
                            int order = pass.getOrderstep();
                            order -= 1;
                            pass.setOrderstep(order);
                            this.updatestep(pass);
                            System.out.println("paso modificado" + pass.getOrderstep());
                        }
                    }
                }
            }
            this.RecargarlistaRole();
            System.out.println("Paso Eliminado: " + paso.getActionStep());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updatestep(Paso p) {
        setPaso(p);
        pfl.edit(paso);
        System.out.println("modificado" + paso.getId()
        );
    }

    public int contarPasos() {
        int tm = pasos.size();
        return tm;
    }

    public String index() {
        this.RecargarlistaRole();
        return "verpasos.xhtml?faces-redirect=true";
    }

    /**
     * Validacion de rol si es sqa carga todos Si es cliente se cargan los casos
     * asociadas a el
     */
    public void RecargarlistaRole() {
        if (sc.getPermisorol().equals("Sqa")) {
            //Recargar todas
            this.recargarPasos();
        } else {
            //Solo las asociadas
            this.recargarFiltroCliente();
        }
    }

    //REcarga la lista con solo los pasos asociados
    public void recargarFiltroCliente() {
        pasos = pfl.ObtenerFiltroCliente(sc.getIdUsuarioConsultas());
    }

    //Recarga la suit con todos los pasos
    public void recargarPasos() {
        pasos = pfl.findAll();
//        pasos = pfl.findAllOrderByStepOrder();
    }

    public String nuevo() {
        
        return "registrarpasos.xhtml?faces-redirect=true";
    }

    public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fila movida ", "De: " + event.getFromIndex() + ", A:" + event.getToIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
