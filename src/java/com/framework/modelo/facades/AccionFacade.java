/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Accion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author SQA
 */
@Stateless
public class AccionFacade extends AbstractFacade<Accion> implements AccionFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccionFacade() {
        super(Accion.class);
    }
        @Override
    public List<Accion> findByNombreParcialAccion(String nombreAccion) {
        String $fragmento = "%" + nombreAccion.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Accion> criteriaQuery = criteriaBuilder.createQuery(Accion.class);
        Root<Accion> acccion = criteriaQuery.from(Accion.class);
        criteriaQuery.select(acccion);
        criteriaQuery.where(criteriaBuilder.like(acccion.get("nombreAccion").as(String.class), $fragmento));
        List<Accion> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }
}
