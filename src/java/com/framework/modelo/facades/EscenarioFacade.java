/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Escenario;
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
public class EscenarioFacade extends AbstractFacade<Escenario> implements EscenarioFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EscenarioFacade() {
        super(Escenario.class);
    }

    /**
     *
     * @param idusuario
     * @return
     */
    @Override
    public List<Escenario> ObtenerEscenarioConUsuario(int idusuario) {
       Query q = em.createNativeQuery("SELECT escenarios.id, escenarios.nombreEscenario, escenarios.descripcionEscenario,"
               +" escenarios.idSuit, escenarios.idAmbiente, escenarios.fecha_creacion, escenarios.estadoEscenario, escenarios.iddispositivo"
               +" FROM escenarios INNER JOIN usuarios INNER JOIN suits WHERE escenarios.idSuit = suits.id AND suits.idUsuario = usuarios.id"
               +" AND usuarios.id = "+idusuario, Escenario.class);
       
       List<Escenario> lest = q.getResultList();
        return lest;                  
    }
    
}
