package com.framework.modelo.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.framework.login.controllers.SessionController;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import com.framework.util.MessageUtil;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SQA
 */
@Named(value = "emailController")
@RequestScoped
public class EmailController {

    @EJB
    private UsuarioFacadeLocal usfl;

    static SessionController sc;
    private Usuario usuario;
    private String email;
    private String passwordConfirm;
    private String passwordActual;
    private String passwordNueva;

    public EmailController() {
        sc = new SessionController();
    }

    @PostConstruct
    public void init() {
        email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordActual() {
        return passwordActual;
    }

    public void setPasswordActual(String passwordActual) {
        this.passwordActual = passwordActual;
    }

    public String getPasswordNueva() {
        return passwordNueva;
    }

    public void setPasswordNueva(String passwordNueva) {
        this.passwordNueva = passwordNueva;
    }

    public void validarMail() {
        if ("".equals(email)) {
            MessageUtil.enviarMensajeErrorGlobal("Verificar:", "EL campo Correo Electronico esta vacio.");
        } else {
            try {
                usuario = usfl.findEmail(email);
                System.out.println(usuario);
                this.sendEmail(usuario);
            } catch (MessagingException e) {
                MessageUtil.enviarMensajeErrorGlobal("Error:", "EL correo eléctronico ingresado no se encuentra registrado en el aplicativo.");
                email = null;
            }
        }
    }

    public void sendEmail(Usuario usuario) throws MessagingException {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "frameworksqasa@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("frameworksqasa@gmail.com"));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(usuario.getCorreo()));
            message.setSubject("Framework SQASA - Recuperación de Contraseña");
            message.setContent("<h:body style='background-color:red'>"
                    + "<p>Estimado(a) <strong>" + usuario.getPrimerNombre() + " " + usuario.getSegundoNombre() + "</strong> hemos recuperado tu contraseña, intenta ingresar nuevamente con tu respectivo usuario y contraseña.</p>"
                    + "</br>"
                    + "<p><strong>Usuario:</strong>" + usuario.getDocumento() + " </p>"
                    + "<p><strong>Contraseña</strong>:" + usuario.getClave() + "</p>"
                    + "</br>"
                    + "<p>Le recordamos que esta dirección de e-mail es utilizada solamente para los envíos de la información solicitada. Por favor no responda con consultas personales ya que no podrán ser respondidas.</p>"
                    + "<p>Cordialmente.</p>"
                    + "<p>Framework SQA - SQASA</p>"
                    + "<center><img src=\"https://www.sqasa.com/img/home/logo.png\" height='175px' wiidth='294px'></img></center>"
                    + "</h:body>", "text/html; charset=utf-8");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("frameworksqasa@gmail.com", "3193146273");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
            email = null;
            MessageUtil.enviarMensajeInformacionGlobal("Mensaje Enviado", "Se envio un correo para restablecer la contraseña.");
        } catch (Exception e) {
            MessageUtil.enviarMensajeErrorGlobal("Error:", "EL correo eléctronico ingresado no se encuentra registrado en el aplicativo.");

            e.printStackTrace();
        }
    }

    public String cambiarContraseña() {
        int todo = 5;
        try {

            if (passwordActual == null | "".equals(passwordActual)) {
                MessageUtil.enviarMensajeErrorGlobal("Campo Vacio", "La caja de texto contraseña actual esta vacia.");
                todo -= 1;
            } else {
                try {
                    usuario = usfl.findClave(passwordActual, sc.getUsuario().getId());
                    System.out.println(usuario);
                } catch (Exception e) {
                    MessageUtil.enviarMensajeErrorGlobal("Error:", "La contraseña ingresada no es correcta.");
                    todo -= 1;
                }
            }

            if (passwordNueva == null | "".equals(passwordNueva)) {
                MessageUtil.enviarMensajeErrorGlobal("Campo Vacio", "La caja de texto Contraseña Nueva esta vacia.");
                todo -= 1;
            }

            if (passwordConfirm == null | "".equals(passwordConfirm)) {
                MessageUtil.enviarMensajeErrorGlobal("Campo Vacio", "La caja de texto Confirmar Contraseña esta vacia.");
                todo -= 1;
            }

            if (passwordNueva == null ? passwordConfirm != null : !passwordNueva.equals(passwordConfirm)) {
                MessageUtil.enviarMensajeErrorGlobal("No se puede guardar", "Las Contraseñas no coinciden.");
                todo -= 1;
            } else {
                usuario.setClave(passwordNueva);
            }

            if (todo == 5) {
                System.out.println("editelo");
                try {
                    usfl.edit(usuario);
                    MessageUtil.enviarMensajeInformacionGlobal("Datos Modificados:", "La contraseña fue modificada con exito.");
                    passwordActual = null;
                    passwordNueva = null;
                    passwordConfirm = null;
                    this.init();
                } catch (Exception e) {
                    System.out.println("El error: " + e.getMessage());
                }
                return null;
            } else {
                System.out.println("no haqga nada");
                return null;
            }
        } catch (Exception e) {
            System.out.println("paila");
        }
        return null;
    }

}
