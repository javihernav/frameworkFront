/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author SQA
 */
@Named(value = "clienteEjecucionController")
@SessionScoped
public class ClienteEjecucionController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usfl;

    private List<Usuario> usuarios;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.recargar();
    }

    /**
     * Creates a new instance of ClienteEjecucionController
     */
    public ClienteEjecucionController() {
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

    public void recargar() {
        usuarios = usfl.findClient();
    }

    public void activar(Usuario u) {
        setUsuario(u);
        List<Usuario> list = usfl.findAll();
        for (Usuario l : list) {
            System.out.println("Usuarios: " + l.getPrimerNombre());
            if (l.getEmpresa().equals("SQA")) {
                System.out.println("Entro");
                l.setEjecucion("2");
                usfl.edit(l);
            }
        }
        for (Usuario usu : usuarios) {
            // String encript = encriptar("2");
            if (usu.getEmpresa().equals("SQA")) {
                usu.setEjecucion("2");
            }
            usu.setEjecucion("2");
            usfl.edit(usu);
        }

        //String encript2 = encriptar("1");
        usuario.setEjecucion("1");
        usfl.edit(usuario);
        MessageUtil.enviarMensajeInformacionGlobal("Cliente Activado Para Ejecución: ",
                "Recuerde que los demas clientes son desactivados.");
    }
    
    public void ejecutar(Usuario u) {
        setUsuario(u);
        List<Usuario> list = usfl.findAll();
        for (Usuario l : list) {
            System.out.println("Usuarios: " + l.getPrimerNombre());
            if (l.getEmpresa().equals("SQA")) {
                System.out.println("Entro");
                l.setEjecucion("2");
                usfl.edit(l);
            }
        }
        for (Usuario usu : usuarios) {
            // String encript = encriptar("2");
            if (usu.getEmpresa().equals("SQA")) {
                usu.setEjecucion("2");
            }
            usu.setEjecucion("2");
            usfl.edit(usu);
        }

        //String encript2 = encriptar("1");
        usuario.setEjecucion("1");
        usfl.edit(usuario);
        String arch="C:\\Users\\SQA\\Desktop\\Estable\\frameworkSQAIC\\Ejecutar.bat";
        try {

            File objetofile = new File(arch);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }
        
        MessageUtil.enviarMensajeInformacionGlobal("Ejecutando casos. ",
                "Recuerde que los demas clientes son desactivados.");
    }
    
    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    public String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

}
