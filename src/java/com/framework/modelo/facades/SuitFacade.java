/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Suit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SQA
 */
@Stateless
public class SuitFacade extends AbstractFacade<Suit> implements SuitFacadeLocal {

    
    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuitFacade() {
        super(Suit.class);
    }

    @Override
    public List<Suit> ObtenerSuitConUsuario(int idusuario){
        Query q = em.createNativeQuery("SELECT * FROM suits INNER JOIN usuarios WHERE suits.idUsuario ="
                +" usuarios.id AND usuarios.id = "+idusuario, Suit.class);
        List<Suit> lst =q.getResultList();
        return lst;
    }


}
