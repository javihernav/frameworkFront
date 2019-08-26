
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author SQA
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario login(Long documento, String clave) {
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.findLogin", Usuario.class);
            q.setParameter("documento", documento);
            q.setParameter("clave", clave);
            System.out.println("USER: " + q.getSingleResult());
            System.out.println("USER: " + q);
            return q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error consulta Login: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> findClient() {
        Query q = em.createNativeQuery("SELECT * FROM usuarios INNER JOIN rolesusuarios"
                + " WHERE rolesusuarios.idUsuario = usuarios.id AND rolesusuarios.idRol = 2", Usuario.class);
        List<Usuario> lclist = q.getResultList();

        return lclist;
    }

    @Override
    public Usuario findEmail(String email) {
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.findByCorreo", Usuario.class);
            q.setParameter("correo", email);
            System.out.println("usuario: " + q.getSingleResult());
            System.out.println("usario: " + q);
            return q.getSingleResult();

        } catch (Exception e) {
            System.out.println("Error consulta de email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario findClave(String clave, int id) {
        Query q = em.createNativeQuery("SELECT * FROM usuarios WHERE usuarios.clave = '"+clave+"' AND usuarios.id = "+id, Usuario.class);
        return (Usuario) q.getSingleResult();
    }
    
    @Override
    public void asingRol(int id) {
        Query q = em.createNativeQuery("INSERT INTO `rolesusuarios`(`idRol`, `idUsuario`)"
                + " VALUES (2,"+id);
    }
  

}
