/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Metodo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SQA-PRUEBA
 */
@Stateless
public class MetodoFacade extends AbstractFacade<Metodo> implements MetodoFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MetodoFacade() {
        super(Metodo.class);
    }
    
}
