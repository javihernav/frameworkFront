/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.login.controllers;

import com.framework.modelo.entities.Rol;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Oswald1107
 */
@Stateless
public class SessionRule {

    @EJB
    UsuarioFacadeLocal ufl;

    public Usuario iniciar(Long documento, String clave) {
        Usuario u = null;
        if (documento != null && documento > 0
                && clave != null && clave.length() > 0) {
            u = ufl.login(documento, clave);
            if (u != null) {
                if (u.getEstado() == 2) {
                    u = null;
                    MessageUtil.enviarMensajeErrorGlobal(
                            "USUARIO BLOQUEADO: ",
                            "Contacte al administrador, para solucionar el incoveniente.");
                }
            } else {
                MessageUtil.enviarMensajeErrorGlobal(
                        "DATOS INCORRECTOS: ",
                        "Documento y/o clave invalidos");
            }
        } else {
            MessageUtil.enviarMensajeErrorGlobal(
                    "DATOS OBLIGATORIOS: ",
                    "Documento y/o clave son necesarios para iniciar sesión");
        }
        return u;
    }

    public Rol validarRol(Usuario u) {
        Rol r = null;
        if (u.getRoles() != null && u.getRoles().size() > 0) {
            r = u.getRoles().get(0);
        } else {
            MessageUtil.enviarMensajeErrorGlobal(
                    "Rol no asignado",
                    "Debe esperar a que se le asigne un rol dentro del sistema");
        }
        return r;
    }

}
