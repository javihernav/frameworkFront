/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Caso;
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
public class CasoFacade extends AbstractFacade<Caso> implements CasoFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasoFacade() {
        super(Caso.class);
    }

    @Override
    public List<Caso> ObtenerFiltroCliente(int idusuario) {
        Query q = em.createNativeQuery("SELECT * FROM casos INNER JOIN escenarios INNER JOIN suits"
                + " INNER JOIN usuarios WHERE casos.IdEscenario = escenarios.id AND escenarios.idSuit = suits.id AND"
                + " suits.idUsuario = usuarios.id AND usuarios.id = "+ idusuario, Caso.class);
        List<Caso> lcs = q.getResultList();
        
        return lcs;
    }
    
}
