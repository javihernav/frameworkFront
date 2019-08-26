/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Rol;
import com.framework.modelo.facades.RolFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.management.relation.Role;

/**
 *
 * @author SQA
 */
@Named(value = "rolesController")
@RequestScoped

public class RolController {

    @EJB

    private RolFacadeLocal rfl;

    private List<Rol> roles;
    private Rol rol;
    static int tm;
    
    public RolController(){
        
    }
    @PostConstruct
    public void init(){
        recargarRoles();
    }
    
    public void recargarRoles(){
        roles = rfl.findAll();
    }

    public RolFacadeLocal getRfl() {
        return rfl;
    }

    public void setRfl(RolFacadeLocal rfl) {
        this.rfl = rfl;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
