/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.controller.cargas.*;
import com.framework.modelo.entities.Rol;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.RolFacadeLocal;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import static com.framework.util.ValidacionesUtil.sololetras;
import static com.framework.util.ValidacionesUtil.validarEmailFuerte;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author SQA
 */
@Named(value = "usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuarioFacadeLocal ufl;
    
    @EJB
    private RolFacadeLocal rfl;

    private List<Usuario> usuarios;
    private Usuario usuario;
    private List<Rol> roles;
    private int rol;
    static int tm;

    private Part archivo;

    /**
     * Creates a new instance of UsuarioController1
     */
    public UsuariosController() {
        //recargarUsuarios();
    }

    @PostConstruct
    public void init() {
        roles = rfl.findAll();
        recargarUsuarios();
    }

    public List<Usuario> recargarUsuarios() {
        usuarios = ufl.findAll();
        return usuarios;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

    // VER 
    public String verUsuario(Usuario u) {
        this.usuario = u;
        return "verusuarios.xthml?faces-redirect=true";
    }

    // ACTUALIZAR DATOS
    public String actualizarUsuarios() {
        try {
            String existe = "";

            for (Usuario usu : usuarios) {
                if (usu.getCorreo().equals(usuario.getCorreo()) && !Objects.equals(usu.getId(), usuario.getId())) {
                    existe = "yes";
                } else if (usu.getDocumento().equals(usuario.getDocumento()) && !Objects.equals(usu.getId(), usuario.getId())) {
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
                if (validarEmailFuerte(usuario.getCorreo())) {
                    if (sololetras(usuario.getPrimerNombre())) {
                        if (sololetras(usuario.getSegundoNombre())) {
                            if (sololetras(usuario.getPrimerApellido())) {
                                if (sololetras(usuario.getSegundoApellido())) {
                                    ufl.edit(usuario);
                                    System.out.println("Usuario Modificado");
                                    return "verusuarios.xhtml?faces-redirect=true";
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    // EDITAR
    public String preModificar(Usuario u) {
        setUsuario(u);
        return "editarusuario.xhtml?faces-redirect=true";
    }

    // ELIMINAR USUARIO
    public void eliminarUsuario() {
        try {
            ufl.remove(usuario);
            recargarUsuarios();
            System.out.println("Usuario Eliminado: " + usuario.getPrimerApellido());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int contarUsuarios() {
        int tm = usuarios.size();
        return tm;
    }

    public String index() {
        recargarUsuarios();
        return "verusuarios.xhtml?faces-redirect=true";
    }

    public String nuevo() {
        return "registrarusuarios.xhtml?faces-redirect=true";
    }

    public void doUppload() {
        try {
            String ruta = System.getProperty("user.home") + "\\Desktop\\" + archivo.getSubmittedFileName();
            System.out.println("RUTA: " + ruta);
            UsuariosCarga carga = new UsuariosCarga();
            usuarios = carga.LeerArchivoExcel(ruta);
            String create = "no";
            if (usuarios.size() > 0) {
                for (Usuario usu : usuarios) {
                    for (Usuario usuveri : ufl.findAll()) {
                        if (usu.getDocumento().equals(usuveri.getDocumento())) {
                            MessageUtil.enviarMensajeErrorGlobal("NUMERO DE DOCUMMENTO DUPLICADO: ", "El documento del cliente "
                                    + "" + usu.getPrimerNombre() + " " + usu.getSegundoNombre() + " ya existe.");
                            create = "no";
                        } else if (usu.getCorreo().equals(usuveri.getCorreo())) {
                            MessageUtil.enviarMensajeErrorGlobal("CORREO ELECTRONICO DUPLICADO: ", "El correo eléctronico del cliente "
                                    + "" + usu.getPrimerNombre() + " " + usu.getSegundoNombre() + " ya existe.");
                            create = "no";
                        } else {
                            create = "yes";
                        }
                    }

                    if ("yes".equals(create)) {
                        ufl.create(usu);
                        ufl.asingRol(usu.getId());
                        MessageUtil.enviarMensajeInformacionGlobal("ARCHIVO CARGADO CON EXITO: ", "Los datos del archivo han sido guardados exitosamente.");
                    } else {
                        MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO CARGAR EL ARCHIVO: ", "Por favor asegurese de que el archivo este en el escritorio y sea el formato brindalo por el aplicativo.");
                    }
                }
                usuarios = ufl.findAll();
            } else {
                MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO CARGAR EL ARCHIVO: ", "Por favor asegurese de que el archivo este en el escritorio y sea el formato brindalo por el aplicativo.");
                usuarios = ufl.findAll();

            }

        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("NO SE PUDO CARGAR EL ARCHIVO: ", "Por favor asegurese de que el archivo este en el escritorio y sea el formato brindalo por el aplicativo.");
            e.getMessage();
        }
    }
}
