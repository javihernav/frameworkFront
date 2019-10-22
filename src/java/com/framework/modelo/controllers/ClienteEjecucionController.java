/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.Correr;
import com.framework.util.MessageUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import prueba.Probar;

/**
 *
 * @author SQA
 */
@Named(value = "clienteEjecucionController")
@SessionScoped
public class ClienteEjecucionController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usfl;

    private static String direccionip;
    private static String puerto;
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
        direccionip="127.0.0.1";
        puerto="8080";
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

    //Se encarga de colocar como activo al usuario cuyo botón es presionado
    public void ejecutar(Usuario u) {
        setUsuario(u);
        List<Usuario> listaUsuarios = usfl.findAll();
        for (Usuario us : listaUsuarios) {
            System.out.println("Usuarios: " + us.getPrimerNombre());
            if (us.getEmpresa().equals("SQA")) {
                System.out.println("Entro");
                us.setEjecucion("2");
                usfl.edit(us);
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
//        String arch=System.getProperty("User.home")+"\\Desktop\\frameworkSQAIC\\Ejecutar.bat";

//        try {
//
//            File objetofile = new File(arch);
//            Desktop.getDesktop().open(objetofile);
//
//        } catch (IOException ex) {
//
//            System.out.println(ex);
//
//        }
        MessageUtil.enviarMensajeInformacionGlobal("Ejecutando casos. ",
                "Recuerde que los demas clientes son desactivados.");
    }

    public static void ejecutarBackend() {
        try {
            System.out.println("Preparado para ejecutar backend");
//        Correr.ejecutarBackend();//ejecución en la misma máquina
Probar.probar(direccionip, puerto);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClienteEjecucionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    public String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

    public String getDireccionip() {
        return direccionip;
    }

    public void setDireccionip(String direccionip) {
        this.direccionip = direccionip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

}
