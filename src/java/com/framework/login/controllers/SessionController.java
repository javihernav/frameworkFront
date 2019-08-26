/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.login.controllers;

import com.framework.modelo.entities.Permiso;
import com.framework.modelo.entities.Rol;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Oswald1107
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

    @EJB
    private SessionRule sr;
    @EJB
    private UsuarioFacadeLocal ufl;

    static int idUsuarioConsultas;
    private String clave;
    private Long documento;
    private Rol rolSeleccionado;
    static String permisorol;
    private Usuario usuario;
    private Integer rol;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    @PostConstruct
    public void init() {
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getPermisorol() {
        return permisorol;
    }

    public void setPermisorol(String permisorol) {
        this.permisorol = permisorol;
    }

    public static int getIdUsuarioConsultas() {
        return idUsuarioConsultas;
    }

    public static void setIdUsuarioConsultas(int idUsuarioConsultas) {
        SessionController.idUsuarioConsultas = idUsuarioConsultas;
    }

    public String iniciarSesion() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            String urlDestino = "";
            usuario = sr.iniciar(documento, clave);
            idUsuarioConsultas = usuario.getId();

            if (usuario != null) {
                rolSeleccionado = sr.validarRol(usuario);
                if (rolSeleccionado != null) {
                    List<Rol> roles = usuario.getRoles();
                    for (Rol role : roles) {
                        if (role.getIdrol() != null) {
                            rol = role.getIdrol();
                            permisorol = role.getNombre();
                        }
                    }
                    switch (rol) {
                        case 1:
                            ec.getSessionMap().put("user", usuario);
                            urlDestino = "administrador/administrador.xhtml?faces-redirect=true";
                            break;
                        case 2:
                            ec.getSessionMap().put("user", usuario);
                            urlDestino = "cliente/cliente.xhtml?faces-redirect=true";
                            break;
                        default:
                            urlDestino = "";
                    }
                } else {
                    usuario = null;
                }
            }
            return urlDestino;
        } catch (NullPointerException e) {
            System.out.println("E" + e.getMessage());
        }
        return null;
    }

    public void validarSesion() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            if (usuario == null) {
                ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        rolSeleccionado = null;
        clave = "";
        documento = null;
        return "/login.xhtml?faces-redirect=true";
    }

    public Boolean inicioSesion() {
        return (usuario != null);
    }

    public Boolean tienePermiso(String urlRecurso) {
        if (urlRecurso.endsWith("cliente/cliente.xhtml")) {
            return true;
        }
        for (Permiso p : rolSeleccionado.getPermisos()) {
            if (p.getUrl() != null && urlRecurso.endsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public void actualizarDatos() {
        try {
            ufl.edit(usuario);
            MessageUtil.enviarMensajeInformacion("form-actualizarDatos", "Actualización", "Los datos del usuario se han actualizado correctamente.");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error al modifcar datos del usuario", e.getStackTrace().toString());
        }
    }

    @PreDestroy
    public void perDestroy() {
        cerrarSesion();
    }

    public void refreshUsuario() {
        usuario = ufl.find(usuario.getId());
    }

    public String resetPassword() {
        return "faces/resetpassword.xhtml";
    }

}
