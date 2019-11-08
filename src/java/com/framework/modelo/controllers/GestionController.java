/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.controller.cargas.EjecucionCarga;
import com.framework.modelo.entities.*;
import com.framework.modelo.facades.*;
import com.framework.util.LeerXML;
import com.framework.util.MessageUtil;
import com.framework.util.MetodoUtil;
import com.framework.util.ParametroUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.component.behavior.AjaxBehavior;
import javax.servlet.http.Part;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author SQA
 */
@Named(value = "gestionController")
@RequestScoped
public class GestionController {

    private static List<MetodoUtil> metodos;
    private String[] columnasExcel;

    @EJB
    private SuitFacadeLocal suitfacadelocal;
    private Suit nuevasuit;
    private List<Suit> suits;

    @EJB
    private UsuarioFacadeLocal ufl;
    private List<Usuario> usuarios;

    @EJB
    private EscenarioFacadeLocal efl;
    private Escenario nuevoescenario;
    private List<Escenario> escenarios;

    @EJB
    private CasoFacadeLocal csfl;
    private Caso nuevocaso;
    private List<Caso> casos;

    @EJB
    private PasoFacadeLocal pasofacadelocal;
    private Paso nuevopaso;
    private Paso paso;
    private List<Paso> pasos;

    @EJB
    private AmbienteFacadeLocal ambientfacadelocal;
    private List<Ambiente> ambientes;

    @EJB
    private DispositivoAppiumFacadeLocal dpufl;
    private DispositivoAppium nuevodispo;

    @EJB
    private AccionFacadeLocal acfl;
    private List<Accion> acciones;

    @EJB
    private NavegadorFacadeLocal nfl;
    private List<Navegador> navegadores;

    @EJB
    private MetodoFacadeLocal mfl;
    private Metodo nuevometodo;

    @EJB
    private ParametroFacadeLocal pfl;
    private Parametro nuevoparametro;

    private Part archivoEjecucion;

    //Atributos Para Validar existencia
    private String validarsuit;
    private String validarescenario;

//    variables para poder borrar los campos después de guardar
    private String ActionStep;
    private String Navegador;
    private String TypeStep;
    private String ValueStep;
    private String ParameterStep;
    private int CorXStep;
    private int CorYStep;
    private int Orderstep;
    private List opcionesExcel;

//    variables para poder borrar los campos después de guardar
    //Getters and Setters de existencia
    public String getValidarescenario() {
        return validarescenario;
    }

    public void setValidarescenario(String validarescenario) {
        this.validarescenario = validarescenario;
    }

    public String getValidarsuit() {
        return validarsuit;
    }

    public void setValidarsuit(String validarsuit) {
        this.validarsuit = validarsuit;
    }

    //Lista Para agregar los pasos
    private static List<Paso> listpaso = new ArrayList();

    //Getter and Setters de los pasos
    public List<Paso> getListpaso() {
        return listpaso;
    }

    public void setListpaso(List<Paso> listpaso) {
        GestionController.listpaso = listpaso;
    }

    public Part getArchivoEjecucion() {
        return archivoEjecucion;
    }

    public void setArchivoEjecucion(Part archivoEjecucion) {
        this.archivoEjecucion = archivoEjecucion;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public List<Navegador> getNavegadores() {
        return navegadores;
    }

    public void setNavegadores(List<Navegador> navegadores) {
        this.navegadores = navegadores;
    }

    //Obtener fecha exacta del sistema
    public Date fechaSistema() throws ParseException {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String strTimeStamp = dateFormat.format(date);
        //System.out.println(strTimeStamp);
        Date fecha = dateFormat.parse(strTimeStamp);
        return fecha;
    }

    //Constructor
    public GestionController() {

        ActionStep = "";
        Navegador = "";
        TypeStep = "";
        ValueStep = "";
        ParameterStep = "";
        CorXStep = 0;
        CorYStep = 0;
        Orderstep = 0;
    }

    //Method init
    @PostConstruct
    public void init() {
        suits = suitfacadelocal.findAll();
        usuarios = ufl.findAll();
        escenarios = efl.findAll();

        nuevasuit = new Suit();
        nuevoescenario = new Escenario();
        nuevocaso = new Caso();
        nuevopaso = new Paso();
        nuevodispo = new DispositivoAppium();
    }

    //Getters and Setters de los campos
    public SuitFacadeLocal getSfl() {
        return suitfacadelocal;
    }

    public void setSfl(SuitFacadeLocal sfl) {
        this.suitfacadelocal = sfl;
    }

    public Suit getNuevasuit() {
        return nuevasuit;
    }

    public void setNuevasuit(Suit nuevasuit) {
        this.nuevasuit = nuevasuit;
    }

    public List<Suit> getSuits() {
        return suits;
    }

    public void setSuits(List<Suit> suits) {
        this.suits = suits;
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public EscenarioFacadeLocal getEsfl() {
        return efl;
    }

    public void setEsfl(EscenarioFacadeLocal efl) {
        this.efl = efl;
    }

    public Escenario getNuevoescenario() {
        return nuevoescenario;
    }

    public void setNuevoescenario(Escenario nuevoescenario) {
        this.nuevoescenario = nuevoescenario;
    }

    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public CasoFacadeLocal getCsfl() {
        return csfl;
    }

    public void setCsfl(CasoFacadeLocal csfl) {
        this.csfl = csfl;
    }

    public Caso getNuevocaso() {
        return nuevocaso;
    }

    public void setNuevocaso(Caso nuevocaso) {
        this.nuevocaso = nuevocaso;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public PasoFacadeLocal getPsfl() {
        return pasofacadelocal;
    }

    public void setPsfl(PasoFacadeLocal psfl) {
        this.pasofacadelocal = psfl;
    }

    public Paso getNuevopaso() {
        return nuevopaso;
    }

    public void setNuevopaso(Paso nuevopaso) {
        this.nuevopaso = nuevopaso;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public DispositivoAppiumFacadeLocal getDpufl() {
        return dpufl;
    }

    public void setDpufl(DispositivoAppiumFacadeLocal dpufl) {
        this.dpufl = dpufl;
    }

    public DispositivoAppium getNuevodispo() {
        return nuevodispo;
    }

    public void setNuevodispo(DispositivoAppium nuevodispo) {
        this.nuevodispo = nuevodispo;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    //Metodo para agregacion del paso a la lista
    String puedeContinuar = "yes";
    int ordenPaso = 1;

    public void agregarpaso() {
        if (ActionStep != null) {

            if (!ActionStep.equals("")) {

                nuevopaso.setIdCaso(nuevocaso);

                nuevopaso.setActionStep(ActionStep);
                nuevopaso.setNavegador(Navegador);
                nuevopaso.setTypeStep(TypeStep);
                nuevopaso.setValueStep(ValueStep);
                nuevopaso.setParameterStep(ParameterStep);
                nuevopaso.setCorXStep(CorXStep);
                nuevopaso.setCorYStep(CorYStep);
                nuevopaso.setOrderstep(Orderstep);
                //Foreach para saber si existe la accion de ingresar url
                this.listpaso.forEach((paso) -> {
                    if ("Ingresar URL".equals(paso.getActionStep())) {
                        if (nuevopaso.getActionStep().equals(paso.getActionStep())) {
                            puedeContinuar = "no";
                        }
                    }
                    ordenPaso += 1;
                    nuevopaso.setId(paso.getId());
                });

                //Condicional para agregar la accion a la lista
                if (puedeContinuar.equals("yes")) {

                    int idre = 0;
                    if (nuevopaso.getId() == null) {
                        nuevopaso.setId(1);
                    } else {
                        idre = nuevopaso.getId() + 1;
                        nuevopaso.setId(idre);
                    }
                    nuevopaso.setOrderstep(ordenPaso);
                    this.listpaso.add(nuevopaso);
//            nuevopaso = new Paso();//añadido para borrar campos

//            nuevopaso.setActionStep("");
//            nuevopaso.setNavegador("");
//            nuevopaso.setTypeStep("");
//            nuevopaso.setValueStep("");
//            nuevopaso.setParameterStep("");
//            nuevopaso.setCorXStep(0);
//            nuevopaso.setCorYStep(0);
//            nuevopaso.setOrderstep(0);
//            nuevopaso.setIdCaso(nuevocaso);
                } else {
                    MessageUtil.enviarMensajeErrorGlobal("N0 SE PUEDE AGREGAR:", "La acción " + nuevopaso.getActionStep() + " ya existe.");
                }

                GestionController.listpaso.forEach((pass) -> {
                    if ("Ingresar URL".equals(pass.getActionStep())) {
                        nuevopaso.setNavegador(pass.getNavegador());
                        MessageUtil.enviarMensajeInformacionGlobal("Se modifico la lista:", "El campo navegador se modifico, existe o se agrego la accion de Ingresar URL los demás pasos tomaran su valor de navegador.");
                        this.renombrarNavegador();
                    }
                });
                //limpiar los campos
//        limpiarCampos();
                ActionStep = "";
                Navegador = "";
                TypeStep = "";
                ValueStep = "";
                ParameterStep = "";
                CorXStep = 0;
                CorYStep = 0;
                Orderstep = 0;
            }//fin if
        }//fin if
    }

    //Método para limpiar los campos después de agregar un paso.
    public void limpiarCampos() {
        this.nuevopaso.setCorXStep(null);
        this.nuevopaso.setValueStep(null);
        this.nuevopaso.setNavegador(null);
        this.nuevopaso.setCorYStep(null);
        this.nuevopaso.setParameterStep(null);
        this.nuevopaso.setTypeStep(null);
        this.setAcciones(null);

    }

    //Metodo para renombrar los navegadores de la lista
    public void renombrarNavegador() {
        GestionController.listpaso.forEach((pass) -> {
            pass.setNavegador(nuevopaso.getNavegador());
        });
    }

    //Eliminacion del paso de la lista
    public void eliminarpaso(Paso p) {
        GestionController.listpaso.remove(p);
        //System.out.println("paso eliminnado");
    }

    //Metodo para registrar suit,escenario,caso y pasos NOTA:METODO ESPECIFICO CUANDO EL ESCENARIO NO ES MOVIL
    public void registrarsuit() throws ParseException {
        this.crearsuit();

        this.crearEscenario();

        this.agregarCaso();

        this.crearPasos();

        init();
        listpaso.clear();

    }

    //Metodo para registrar suit,escenario,caso y pasos 
    //NOTA:METODO ESPECIFICO CUANDO EL ESCENARIO ES MOVIL
    public void registerwithdispo() throws ParseException {

        this.crearsuit();

        this.crearEscenarioDispo();

        this.agregarCaso();

        this.crearPasos();

        init();
        listpaso.clear();
        //System.out.println("Registro con android");
    }

    //Metodo agregar caso
    public void agregarCaso() throws ParseException {
        nuevocaso.setFechaCreacion(fechaSistema());
        nuevocaso.setIdEscenario(nuevoescenario);
        csfl.create(nuevocaso);
        //System.out.println("Caso Creado");
    }

    //Metodo agregar escenario sin dispositivo
    public void crearEscenario() throws ParseException {
        nuevoescenario.setFechaCreacion(fechaSistema());
        nuevoescenario.setIdSuit(nuevasuit);
        efl.create(nuevoescenario);
        //System.out.println("Escenario Creado");
    }

    //Metodo agregar escenario con dispositivo
    public void crearEscenarioDispo() throws ParseException {
        dpufl.create(nuevodispo);
        //System.out.println("Dispositivo creado");

        nuevoescenario.setFechaCreacion(fechaSistema());
        nuevoescenario.setIdSuit(nuevasuit);
        nuevoescenario.setIddispositivo(nuevodispo);
        efl.create(nuevoescenario);
        //System.out.println("Escenario Creado");
    }

    //Metodo para agregar suits
    public void crearsuit() throws ParseException {
        nuevasuit.setFechaCreacion(fechaSistema());
        suitfacadelocal.create(nuevasuit);
        //System.out.println("Suit Creada");
    }

    //Metodo para agregar los pasos
    //Toma la lista de pasos y la persiste en base de datos
    public void crearPasos() {
        System.out.println("crear pasos");
        for (Paso pass : GestionController.listpaso) {

            nuevopaso.setActionStep(pass.getActionStep());
            nuevopaso.setNavegador(pass.getNavegador());
            nuevopaso.setTypeStep(pass.getTypeStep());
            nuevopaso.setValueStep(pass.getValueStep());
            nuevopaso.setParameterStep(pass.getParameterStep());
            nuevopaso.setCorXStep(pass.getCorXStep());
            nuevopaso.setCorYStep(pass.getCorYStep());
            nuevopaso.setOrderstep(pass.getOrderstep());
            nuevopaso.setIdCaso(nuevocaso);
            pasofacadelocal.create(nuevopaso);
            System.out.println("Paso creado");
            System.out.println("pasocreado:" + nuevopaso);
            if (nuevopaso.getActionStep().equals("probar servicio web soap")) {
                for (MetodoUtil met : GestionController.metodos) {

                    nuevometodo = new Metodo();
//                    nuevometodo.setId(1);
                    nuevometodo.setIdPaso(nuevopaso);
                    nuevometodo.setNombre(met.getNombre());
                    nuevometodo.setTargetnamespace(met.getTargetNamespace());
                    nuevometodo.setValoresperado(met.getValorEsperado());
                    nuevometodo.setTargetnamespace(met.getTargetNamespace());
                    nuevometodo.setContenttype(met.getContenttype());

                    mfl.create(nuevometodo);
                    System.out.println("metodo creado:" + nuevometodo);
                    for (ParametroUtil param : met.getParametros()) {

                        nuevoparametro = new Parametro();
                        nuevoparametro.setIdmetodo(nuevometodo);
                        nuevoparametro.setNombre(param.getNombre());
                        nuevoparametro.setOrigen(param.getOrigen());
                        nuevoparametro.setTipo(param.getTipo());
                        pfl.create(nuevoparametro);
                        System.out.println("parametro creado:" + nuevoparametro);

                    }
                }

            }

        }
        nuevopaso = new Paso();
    }

    //Metodo para validacion de esxistencia con sobrecarga de evento AJAX
    public void validatesuit(AjaxBehavior e) {
        //Validar si la suit existe
        for (Suit suites : suits) {
            if (suites.getNombreSuit().equals(this.nuevasuit.getNombreSuit())) {
                this.validarsuit = "El nombre de la suit:" + this.nuevasuit.getNombreSuit() + " !Ya Existe¡";
            }
        }
        //Validar si el escenario existe
        for (Escenario esce : escenarios) {
            if (esce.getNombreEscenario().equals(this.nuevoescenario.getNombreEscenario())) {
                this.validarescenario = "El nombre del Escenario:" + this.nuevoescenario.getNombreEscenario() + " ¡Ya Existe!";
            }
        }
    }

    //Variables para verificar validaciones
    int crearsuit = 0;
    int crearescenario = 0;
    int crearcaso = 0;
    int crearpaso = 0;

    //Variables para saber cantidad de datos encontrados y comparar con validaciones
    int cantidadsuit = 0;
    int cantidadescenario = 0;
    int cantidadcaso = 0;
    int cantidadpaso = 0;

    //Instancia de la clase que lee el archivo
    EjecucionCarga ejecar = new EjecucionCarga();

    //Metodo para carga masiva de ejecucion
    public void uploadEjecucion() throws ParseException {

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        try {

            //Envio del archivo
            String ruta = System.getProperty("user.home") + "\\Desktop\\" + archivoEjecucion.getSubmittedFileName();
            //System.out.println("RUTA: " + ruta);

//Metodo para leer la hoja de suit
            opcionesExcel = ejecar.leerArchivoExcelSuit(ruta, ufl.findAll());
            cantidadsuit = suits.size();
            for (Suit su : suits) {
                su.setFechaCreacion(fechaSistema());

                //Validacion del usuario en la suit
                if (su.getIdUsuario() != null) {
                    crearsuit += 1;
                } else {
                    //System.out.println("no se pudo");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR LA SUIT '" + su.getNombreSuit() + "': ", "El Correo del usuario no coincide con ningun usuario registrado.");
                }

                //Validacion de estado valido
                if (su.getEstadoSuit() == 1 || su.getEstadoSuit() == 2) {
                    crearsuit += 1;
                } else {
                    //System.out.println("Estado no valido");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR LA SUIT '" + su.getNombreSuit() + "':", "El estado de la suit no es valido, asegurese que sea Activo o Inactivo.");
                }

                //Validacion de nombre vacio
                if ("".equals(su.getNombreSuit())) {
                    crearsuit = 0;
                    //System.out.println("suit vacia");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR LA SUIT: ", "El nombre de la suit esta vacia o ya existe en la lista.");
                } else {
                    crearsuit += 1;
                }

                //Validacion de existencia
                suits = suitfacadelocal.findAll();
                for (Suit sut : suits) {
                    if (su.getNombreSuit().toLowerCase().equals(sut.getNombreSuit().toLowerCase())) {
                        crearsuit = 0;
                        //System.out.println("suit Repetida");
                        MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR LA SUIT '" + su.getNombreSuit() + "':", "La suit que se quiere registrar ya existe.");
                    }
                }
            }

            //Metodo para leer la hoja de escenarios
            escenarios = ejecar.leerArchivoExcelEscenario(ruta, suitfacadelocal.findAll(), ambientfacadelocal.findAll(), ufl.findAll());
            cantidadescenario = escenarios.size();
            for (Escenario esce : escenarios) {
                esce.setFechaCreacion(fechaSistema());
                //Validacion de suit vacia
                if (esce.getIdSuit() != null) {
                    crearescenario += 1;
                } else {
                    crearescenario = 0;
                    //System.out.println("suit null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL ESCENARIO '" + esce.getNombreEscenario() + "':", "La suit a la que se quiere asignar no existe.");
                }

                //Validacion de ambiente vacio y valido
                if (esce.getIdAmbiente() != null) {
                    crearescenario += 1;
                } else {
                    crearescenario = 0;
                    //System.out.println("ambientes escenario null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL ESCENARIO '" + esce.getNombreEscenario() + "':", " El ambiente del escenario '" + esce.getNombreEscenario() + "' no es valido.");
                }

                //Validacion de estado vacio
                if (esce.getEstadoEscenario() == 1 || esce.getEstadoEscenario() == 2) {
                    crearescenario += 1;
                } else {
                    crearescenario = 0;
                    //System.out.println("el estado del escenario null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL ESCENARIO '" + esce.getNombreEscenario() + "':", "El estado del Escenario no es valido, asegurese que sea Activo o Inactivo.");
                }

                //Validacion de existencia en el escenario
                if ("".equals(esce.getNombreEscenario())) {
                    crearescenario = 0;
                    //System.out.println("escenario vacio");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL ESCENARIO: ", "El nombre del Escenario llego vacio.");
                } else if ("YA ESTA CON ESTA SUIT".equals(esce.getNombreEscenario())) {
                    crearescenario = 0;
                    //System.out.println("ya esta en la lista");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL ESCENARIO:", "El escenario ya existe en la lista asignada a la suit '" + esce.getIdSuit().getNombreSuit() + "'");
                } else {
                    crearescenario += 1;
                }
            }

            //Metodo para leer la hoja de casos
            casos = ejecar.leerArchivoExcelCasos(ruta, efl.findAll(), suitfacadelocal.findAll(), ambientfacadelocal.findAll(), ufl.findAll());
            cantidadcaso = casos.size();
            for (Caso cass : casos) {
                cass.setFechaCreacion(fechaSistema());

                //Validacion de escenario vacio
                if (cass.getIdEscenario() != null) {
                    crearcaso += 1;
                } else {
                    crearcaso = 0;
                    //System.out.println("Escenario null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL CASO '" + cass.getNombreCaso() + "':", "El Escenario al que se quiere asignar el caso no existe.");
                }

                //Validacion de estado valido
                if (cass.getEstadoCaso() == 1 || cass.getEstadoCaso() == 2) {
                    crearcaso += 1;
                } else {
                    crearcaso = 0;
                    //System.out.println("Estado caso null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL CASO '" + cass.getNombreCaso() + "':", "El estado del Caso no es valido, asegurese que sea Activo o Inactivo.");
                }

                //Validacion de existencia
                if ("".equals(cass.getNombreCaso())) {
                    crearcaso = 0;
                    //System.out.println("nombre caso null");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL CASO:", "El nombre del caso llego vacio.");
                } else if ("YA ESTA CON ESTE ESCENARIO".equals(cass.getNombreCaso())) {
                    crearcaso = 0;
                    //System.out.println("caso ya esta");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL CASO '" + cass.getNombreCaso() + "':", "El caso ya existe en la lista asignada al Escenario '" + cass.getIdEscenario().getNombreEscenario() + "'.");
                } else {
                    crearcaso += 1;
                }
            }

            //Metodo para leer la hoja de pasos
            pasos = ejecar.leerArchivoExcelPasos(ruta, csfl.findAll(), escenarios, suits, ambientes, usuarios);
            cantidadpaso = pasos.size();
            for (Paso pass : pasos) {
                acciones = acfl.findAll();
                navegadores = nfl.findAll();
                String Accionvalida = "no";
                String NavegadorVallido = "no";
                String TypeValido = "no";

                //Validacion de que la accion ingresada exista
                if (!"".equals(pass.getActionStep())) {
                    for (Accion ac : acciones) {
                        if (pass.getActionStep().toLowerCase().equals(ac.getNombreAccion().toLowerCase())) {
                            Accionvalida = "yes";
                            crearpaso += 1;
                        }
                    }
                } else {
                    crearpaso = 0;
                    //System.out.println("no llega");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO: ", "El valor de accion llego vacio.");
                }

                //Validacion de que el navegador ingresado exista
                if (!"".equals(pass.getNavegador())) {
                    for (Navegador nave : navegadores) {
                        if (nave.getNombreNavegador().toUpperCase() == null ? pass.getNavegador().toUpperCase() == null : nave.getNombreNavegador().toUpperCase().equals(pass.getNavegador().toUpperCase())) {
                            NavegadorVallido = "yes";
                            crearpaso += 1;
                        }
                    }
                }

                //Validacion de que le tipo de localizador sea valido
                if (null != pass.getTypeStep()) {
                    switch (pass.getTypeStep()) {
                        case "ID":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "Name":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "ClassName":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "LinkText":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "PartialLinkText":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "TagName":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "XPath":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "CssSelector":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        case "":
                            TypeValido = "yes";
                            crearpaso += 1;
                            break;
                        default:
                            crearpaso = 0;
                            break;
                    }
                }

                //Validacion de existencia de accion url
                if ("Ya existe la accion Ingresar URL".equals(pass.getActionStep())) {
                    //System.out.println("ya existe");
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO:", "Ya existe la accion Ingresar URL verifique los valores.");
                }

                //Mensajes error
                if ("no".equals(Accionvalida)) {
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO:", "Esta accion no esta registrada.");
                }

                if ("no".equals(NavegadorVallido)) {
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO:", "El navegador registrado no existe.");
                }

                if ("no".equals(TypeValido)) {
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO:", "El tipo de localizador no es valido.");
                }

                //Validacion de existencia del caso a asignar
                if (pass.getIdCaso() != null) {
                    crearpaso += 1;
                } else {
                    crearpaso = 0;
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO: ", "El caso al que se quiere asignar el paso no exsiste.");
                }

                if (pass.getOrderstep() == 0) {
                    crearpaso = 0;
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO REGISTRAR EL PASO: ", "El orden de algun paso esta vacio o el valor es 0.");
                } else {
                    crearpaso += 1;
                }

                //System.out.println(crearsuit);
                //System.out.println(crearescenario);
                //System.out.println(crearcaso);
                //System.out.println(crearpaso);
            }
            this.registrocargas(ruta, csfl.findAll(), escenarios, suits, ambientes, usuarios);
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO CARGAR EL ARCHIVO: ", "Por favor asegurese de que el archivo este en el escritorio y sea el formato brindalo por el aplicativo.");
            e.printStackTrace();
        }
    }

    //Metodo para carga masiva de ejecucion de pruebas en servicios
    /*
    public void uploadEjecucionServicios() throws ParseException {

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }

        try {

            //Envio del archivo
            String ruta = System.getProperty("user.home") + "\\Desktop\\" + archivoEjecucion.getSubmittedFileName();
            System.out.println("RUTA: " + ruta);

            //Metodo para leer la hoja de suit
            opcionesExcel = ejecar.leerArchivoExcelServicios(ruta);


        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO CARGAR EL ARCHIVO: ", "Por favor asegurese de que el archivo este en el escritorio y sea el formato brindalo por el aplicativo.");
            e.printStackTrace(System.out);
        }
    }
     */
    public void escribirParametrosURL() {
        try {
            nuevopaso.setValueStep(LeerXML.obtenerEstructuraXml(nuevopaso.getParameterStep()).toString());
        } catch (JDOMException ex) {
            Logger.getLogger(GestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void registrocargas(String archivoDestino, List<Caso> casoexcel, List<Escenario> escenarioslist, List<Suit> suitlista, List<Ambiente> ambientelist, List<Usuario> usuarioslist) throws ParseException {
        int totalsuit = cantidadsuit * 3;
        int totalescenario = cantidadescenario * 4;
        int totalcaso = cantidadcaso * 3;
        int totalpaso = cantidadpaso * 5;

        if (crearsuit == totalsuit) {
            if (crearescenario == totalescenario) {
                if (crearcaso == totalcaso) {
                    if (crearpaso == totalpaso) {
                        //Registro de suits
                        suits = ejecar.leerArchivoExcelSuit(archivoDestino, usuarioslist);
                        if (suits.size() > 0) {
                            for (Suit st : suits) {
                                nuevasuit.setNombreSuit(st.getNombreSuit());
                                nuevasuit.setDescripcionSuit(st.getDescripcionSuit());
                                nuevasuit.setFechaCreacion(fechaSistema());
                                nuevasuit.setEstadoSuit(st.getEstadoSuit());
                                nuevasuit.setIdUsuario(st.getIdUsuario());
                                suitfacadelocal.create(nuevasuit);
                            }
                            MessageUtil.enviarMensajeInformacionGlobal("SE REGISTRARON LAS SUITS: ", "Se registraron " + suits.size() + " suits.");
                            if (escenarios.size() > 0) {
                                for (Escenario es : escenarios) {
                                    nuevoescenario.setNombreEscenario(es.getNombreEscenario());
                                    nuevoescenario.setDescripcionEscenario(es.getDescripcionEscenario());
                                    nuevoescenario.setFechaCreacion(fechaSistema());
                                    nuevoescenario.setEstadoEscenario(es.getEstadoEscenario());
                                    suits = suitfacadelocal.findAll();
                                    for (Suit sts : suits) {
                                        if (es.getIdSuit().getNombreSuit().equals(sts.getNombreSuit())) {
                                            nuevoescenario.setIdSuit(sts);
                                        }
                                    }
                                    nuevoescenario.setIdAmbiente(es.getIdAmbiente());
                                    nuevoescenario.setIddispositivo(es.getIddispositivo());
                                    efl.create(nuevoescenario);
                                }
                                MessageUtil.enviarMensajeInformacionGlobal("SE REGISTRARON LOS ESCENARIOS:", " Se registraron " + escenarios.size() + " escenarios.");

                                casos = ejecar.leerArchivoExcelCasos(archivoDestino, escenarioslist, suitlista, ambientelist, usuarioslist);
                                if (casos.size() > 0) {
                                    for (Caso cs : casos) {
                                        nuevocaso.setNombreCaso(cs.getNombreCaso());
                                        escenarios = efl.findAll();
                                        for (Escenario escena : escenarios) {
                                            if (cs.getIdEscenario().getNombreEscenario().equals(escena.getNombreEscenario())) {
                                                nuevocaso.setIdEscenario(escena);
                                            }
                                        }
                                        nuevocaso.setFechaCreacion(fechaSistema());
                                        nuevocaso.setEstadoCaso(cs.getEstadoCaso());
                                        csfl.create(nuevocaso);
                                    }
                                    MessageUtil.enviarMensajeInformacionGlobal("SE REGISTRARON LOS CASOS: ", "Se registraron " + casos.size() + " casos.");

                                    pasos = ejecar.leerArchivoExcelPasos(archivoDestino, casoexcel, escenarioslist, suitlista, ambientelist, usuarioslist);
                                    if (pasos.size() > 0) {
                                        for (Paso ps : pasos) {
                                            nuevopaso.setActionStep(ps.getActionStep());
                                            nuevopaso.setNavegador(ps.getNavegador());
                                            nuevopaso.setTypeStep(ps.getTypeStep());
                                            nuevopaso.setValueStep(ps.getValueStep());
                                            nuevopaso.setParameterStep(ps.getParameterStep());
                                            nuevopaso.setCorXStep(ps.getCorXStep());
                                            nuevopaso.setCorYStep(ps.getCorYStep());
                                            casos = csfl.findAll();
                                            for (Caso cass : casos) {
                                                if (ps.getIdCaso().getNombreCaso().equals(cass.getNombreCaso())) {
                                                    nuevopaso.setIdCaso(cass);
                                                }
                                            }
                                            nuevopaso.setOrderstep(ps.getOrderstep());
                                            pasofacadelocal.create(nuevopaso);
                                        }
                                        MessageUtil.enviarMensajeInformacionGlobal("SE REGISTRARON LOS PASOS: ", "Se registraron " + pasos.size() + " pasos.");
                                    }
                                }
                            }
                        }
                    } else {
                        MessageUtil.enviarMensajeErrorGlobal("NO SE PUEDE REGISTRAR LOS DATOS:", "Verificar los datos de los pasos.");
                    }
                } else {
                    MessageUtil.enviarMensajeErrorGlobal("NO SE PUEDE REGISTRAR LOS DATOS:", "Verificar los datos de los casos.");
                }
            } else {
                MessageUtil.enviarMensajeErrorGlobal("NO SE PUEDE REGISTRAR LOS DATOS:", "Verificar los datos de los escenario.");
            }
        } else {
            MessageUtil.enviarMensajeErrorGlobal("NO SE PUEDE REGISTRAR LOS DATOS: ", "Verificar los datos de las suit.");
        }
    }

    //métodos para leer servicio web soap
    public void obtenerEstructuraXml(String url) throws JDOMException, IOException {
        metodos = new ArrayList();
        //System.out.println("Obteniendo estructura de: " + url);
        //agrega ?wsdl al final de la dirección si no se ha agregado

        List<Element> eleMetodos = new ArrayList<>();

        if (!url.contains("?wsdl") && !url.contains("?WSDL")) {
            url = url.concat("?wsdl");
        }
        String contenido = LeerXML.leerDesdeWeb(url);

        File archivo = LeerXML.convertirStringAArchivo(contenido, "wsdl.xml");
        List estructura = new ArrayList();

        Element elemento = ((Document) (new SAXBuilder()).build(new File("wsdl.xml"))).getRootElement();//toda la estructura wsdl
        Namespace namespc = elemento.getNamespace();

        String targetnmspc = elemento.getAttribute("targetNamespace").getValue();

        List<Element> messages = elemento.getChildren("message", namespc);
        List<Element> types = elemento.getChildren("types", namespc);
        Element schema = types.get(0);
        List<Element> schemas = schema.getChildren();//contiene un solo elemento schema
//        //System.out.println("------------------------schemas: "+schemas.get(0).getName());

        List<Element> elements = schemas.get(0).getChildren();
        //System.out.println("------------------------tam elements: " + elements.size());
//        for (Element e:elements) {
//            //System.out.println("element:name:"+e.getAttributeValue("name"));
//            
//        }

        //System.out.println("Namespace: " + namespc);
        List<Element> portType = elemento.getChildren("portType", namespc);
        for (Element e : portType) {
            //System.out.println("portType: " + e.getAttributeValue("name"));
        }
        List<Element> operations = portType.get(0).getChildren("operation", namespc);
        for (Element op : operations) {
            //System.out.println("operation: " + op.getAttributeValue("name"));
            MetodoUtil metodotemp = new MetodoUtil();
            metodotemp.setTargetNamespace(targetnmspc);
            metodotemp.setNombre(op.getAttributeValue("name"));
            //System.out.println("message: " + op.getChild("input", namespc).getAttributeValue("message"));
            metodotemp.setInput(op.getChild("input", namespc).getAttributeValue("message"));//añade el nombre de input para asociar con message
            for (Element mesj : messages) {
                if (metodotemp.getInput().contains(mesj.getAttributeValue("name"))) {
                    //System.out.println("messagesOK: " + mesj.getAttributeValue("name"));
                    Element part = mesj.getChild("part", namespc);
                    //System.out.println("part:message: " + part.getAttributeValue("element"));//muestra el nombre del método con prefijo
                    //recorrer elements
                    for (Element e : elements) {
                        if (e.getName().equals("element")) {
//                        //System.out.println("///////////////////////////////////////////////////////elements.size: "+elements.size());
//                        //System.out.println("///////////////////////////////////////////////////////"+part.getAttributeValue("element"));
//                        //System.out.println("///////////////////////////////////////////////////////"+e.getName());
//                        //System.out.println("///////////////////////////////////////////////////////"+e.getAttributeValue("name"));
                            if (part.getAttributeValue("element").contains(e.getAttributeValue("name"))) {
                                //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++name: " + e.getName());
                                List<Element> sequences = e.getChildren();

                                sequences = sequences.get(0).getChildren();

                                List<Element> parametros = sequences.get(0).getChildren();
                                for (Element ekl : parametros) {
                                    ParametroUtil parametrotemp = new ParametroUtil();
                                    parametrotemp.setNombre(ekl.getAttributeValue("name"));
                                    //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++tam parametros: " + parametrotemp.getNombre());
                                    parametrotemp.setTipo(ekl.getAttributeValue("type"));
                                    metodotemp.getParametros().add(parametrotemp);
                                }

                            }
                        }
                    }
                }
            }

            metodos.add(metodotemp);
        }

//        String targetnmspc = elemento.getAttribute("targetNamespace").getValue();
        Namespace tNamespace = Namespace.getNamespace(targetnmspc);
//        metodos = (ArrayList) estructura;
        //System.out.println("GestionController 963 metodos: " + metodos.toString());
    }

    /**
     * Lee un archivo que esta en la ubicación de internet proporcionada en la
     * variable direccion.
     *
     * @param direccion
     * @return
     */
    public String leerDesdeWeb(String direccion) {
        String stringTemporal = "";
        String textoCompleto = "";

        URL url;
        BufferedReader in = null;
        try {
            url = new URL(direccion);

            //lee todo el texto retornado por el servidor
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((stringTemporal = in.readLine()) != null) {
                textoCompleto = textoCompleto + stringTemporal;
            }
            in.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Texto obtenido de: " + direccion);
        return textoCompleto;
    }

    /**
     * Toma una cadena de texto y la pone como contenido de un archivo cuyo
     * nombre se especifica en nombreArchivo y esta ubicado en la raiz del
     * proyecto
     *
     * @param textoArchivo
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public File convertirStringAArchivo(String textoArchivo, String nombreArchivo) throws IOException {
        String ruta = nombreArchivo;
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo, false));
            bw.write(textoArchivo);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(textoArchivo);
        }
        bw.close();
        return archivo;
    }

    public List getMetodos() {
        return metodos;
    }

    public void setMetodos(List metodos) {
        this.metodos = metodos;
    }

//setters y getters de variables para borrar campos
    public String getActionStep() {
        return ActionStep;
    }

    public void setActionStep(String ActionStep) {
        this.ActionStep = ActionStep;
    }

    public String getNavegador() {
        return Navegador;
    }

    public void setNavegador(String Navegador) {
        this.Navegador = Navegador;
    }

    public String getTypeStep() {
        return TypeStep;
    }

    public void setTypeStep(String TypeStep) {
        this.TypeStep = TypeStep;
    }

    public String getValueStep() {
        return ValueStep;
    }

    public void setValueStep(String ValueStep) {
        this.ValueStep = ValueStep;
    }

    public String getParameterStep() {
        return ParameterStep;
    }

    public void setParameterStep(String ParameterStep) {
        this.ParameterStep = ParameterStep;
    }

    public int getCorXStep() {
        return CorXStep;
    }

    public void setCorXStep(int CorXStep) {
        this.CorXStep = CorXStep;
    }

    public int getCorYStep() {
        return CorYStep;
    }

    public void setCorYStep(int CorYStep) {
        this.CorYStep = CorYStep;
    }

    public int getOrderstep() {
        return Orderstep;
    }

    public void setOrderstep(int Orderstep) {
        this.Orderstep = Orderstep;
    }
//setters y getters de variables para borrar campos

    public List getOpcionesExcel() {
        return opcionesExcel;
    }

    public void setOpcionesExcel(List opcionesExcel) {
        this.opcionesExcel = opcionesExcel;
    }

    public Metodo getNuevometodo() {
        return nuevometodo;
    }

    public void setNuevometodo(Metodo nuevometodo) {
        this.nuevometodo = nuevometodo;
    }

    public Parametro getNuevoparametro() {
        return nuevoparametro;
    }

    public void setNuevoparametro(Parametro nuevoparametro) {
        this.nuevoparametro = nuevoparametro;
    }
}
