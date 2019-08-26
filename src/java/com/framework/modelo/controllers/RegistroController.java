/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Rol;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.RolFacadeLocal;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import static com.framework.util.ValidacionesUtil.sololetras;
import static com.framework.util.ValidacionesUtil.sololetrassegundoapellido;
import static com.framework.util.ValidacionesUtil.validarEmailFuerte;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;

/**
 *
 * @author SQA
 */
@Named(value = "registroController")
@RequestScoped
public class RegistroController {

    /**
     * Creates a new instance of RegistroController
     */
    @EJB
    private UsuarioFacadeLocal ufl;
    @EJB
    private RolFacadeLocal rfl;

    private Usuario nuevoUsuario;

    private List<Usuario> usuarios;

    private List<Rol> roles;

    private int rol;

    public RegistroController() {
    }

    @PostConstruct
    public void init() {
        usuarios = ufl.findAll();
        nuevoUsuario = new Usuario();
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String registrar() {

        String existe = "";

        for (Usuario usu : usuarios) {
            if (usu.getCorreo().equals(nuevoUsuario.getCorreo())) {
                existe = "yes";
            } else if (usu.getDocumento().equals(nuevoUsuario.getDocumento())) {
                existe = "yes1";
            }
        }

        if (existe.equals("yes")) {
            MessageUtil.enviarMensajeErrorGlobal("No se puede guardar: ", "El Correo Eléctronico ya existe.");
            return null;
        } else if (existe.equals("yes1")) {
            MessageUtil.enviarMensajeErrorGlobal("No se puede guardar: ", "El Numero de Documento ya existe");
            return null;
        } else {
            if (validarEmailFuerte(nuevoUsuario.getCorreo())) {
                if (sololetras(nuevoUsuario.getPrimerNombre())) {
                    if (sololetras(nuevoUsuario.getSegundoNombre())) {
                        if (sololetras(nuevoUsuario.getPrimerApellido())) {
                            if (sololetrassegundoapellido(nuevoUsuario.getSegundoApellido())) {
                                nuevoUsuario.setEstado(1);
                                nuevoUsuario.setRoles(new ArrayList<Rol>());
                                nuevoUsuario.getRoles().add(rfl.find(rol));
                                nuevoUsuario.setEjecucion("2");
                                ufl.create(nuevoUsuario);
                                System.out.println("Usuario Creado");
                                init();
                                return "registrarusuarios.xhtm.xhtml?faces-redirect=true";
                            } else {
                                MessageUtil.enviarMensajeErrorGlobal("No se pudo guardar: ", "El campo Segundo Apellido no tiene el formato correcto");
                                return null;
                            }
                        } else {
                            MessageUtil.enviarMensajeErrorGlobal("No se pudo guardar: ", "El campo Primer Apellido no tiene el formato correcto");
                            return null;
                        }
                    } else {
                        MessageUtil.enviarMensajeErrorGlobal("No se pudo guardar: ", "El campo Segundo Nombre no tiene el formato correcto");
                        return null;
                    }
                } else {
                    MessageUtil.enviarMensajeErrorGlobal("No se pudo guardar: ", "El campo Primer Nombre no tiene el formato correcto");
                    return null;
                }

            } else {
                MessageUtil.enviarMensajeErrorGlobal("No se puede guardar: ", "Ingrese una formato de correo electrnico valida.");
                return null;
            }
        }

    }

    public void recargarUsuarios() {
        usuarios = ufl.findAll();
    }
}
