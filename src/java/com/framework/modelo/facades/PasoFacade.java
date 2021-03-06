/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Paso;
import java.util.Collections;
import java.util.Comparator;
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
public class PasoFacade extends AbstractFacade<Paso> implements PasoFacadeLocal {

    @PersistenceContext(unitName = "FrameWorkBackPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasoFacade() {
        super(Paso.class);
    }

    @Override
    public void agregarPaso(Paso paso, int cantidadPasos) {
        try {
//            Query pasosCaso = em.createNativeQuery("SELECT * FROM pasos WHERE pasos.idCaso = ? AND pasos.orderstep >= ? ORDER BY orderstep asc");
//
//            pasosCaso.setParameter(1, paso.getIdCaso().getId());
//            pasosCaso.setParameter(2, paso.getOrderstep());
//
//            List pasos = pasosCaso.getResultList();
//            if (pasos != null) {
//                if (pasos.size() > 0) {
//
//                    for (int i = 0; i < pasos.size(); i++) {
//                        Query q = em.createNativeQuery("update pasos set orderstep=? where id = ?");
//                        q.setParameter(1, ((Paso)pasos.get(i)).getOrderstep() + 1);
//                        q.setParameter(2, ((Paso)pasos.get(i)).getIdCaso().getId());
//                        q.executeUpdate();
//                    }
//                }
//            }

            Query q = em.createNativeQuery("CALL AGREGARPASO( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            q.setParameter(1, paso.getActionStep());
            q.setParameter(2, paso.getNavegador());
            q.setParameter(3, paso.getTypeStep());
            q.setParameter(4, paso.getValueStep());
            q.setParameter(5, paso.getParameterStep());
            q.setParameter(6, (paso.getCorXStep() == null ? 0 : paso.getCorXStep()));
            q.setParameter(7, (paso.getCorYStep() == null ? 0 : paso.getCorYStep()));
            q.setParameter(8, paso.getIdCaso().getId());
            q.setParameter(9, paso.getOrderstep());
//        q.setParameter(10,cantidadPasos);

            q.executeUpdate();

        } catch (Exception e) {
            System.out.println("No sirve : " + e);
        }
    }

    ;

    @Override
    public List<Paso> ObtenerFiltroCliente(int idusuario) {
        Query q = em.createNativeQuery("SELECT * FROM pasos INNER JOIN casos INNER JOIN escenarios INNER JOIN suits"
                + " INNER JOIN usuarios WHERE pasos.idCaso = casos.id AND casos.IdEscenario = escenarios.id AND escenarios.idSuit"
                + " = suits.id AND suits.idUsuario = usuarios.id AND usuarios.id = " + idusuario, Paso.class);
        List<Paso> lps = q.getResultList();
        return lps;
    }

    @Override
    public List<Paso> ObtenerPasosAjax(int idCaso) {
        Query q = em.createNativeQuery("(SELECT * FROM pasos WHERE pasos.orderstep IS NOT NULL AND pasos.idCaso = " + idCaso + ")ORDER BY pasos.orderstep ASC", Paso.class);

        List<Paso> lpsa = q.getResultList();

        return lpsa;
    }

    @Override
    public List<Paso> findAll() {
        Query q = em.createNativeQuery("SELECT * FROM pasos ORDER BY idCaso,orderstep", Paso.class);

        List<Paso> pasosPorCasosPorOrderstep = q.getResultList();

        return pasosPorCasosPorOrderstep;
    }

    @Override
    public List<Paso> obtenerValorOrder(int idCaso) {
        Query q = em.createNativeQuery("(SELECT * FROM pasos WHERE pasos.orderstep IS NOT NULL AND pasos.idCaso ="
                + idCaso + ") order by orderstep DESC LIMIT 1", Paso.class);

        List<Paso> lpsa = q.getResultList();

        return lpsa;
    }

    public List<Paso> findAllOrderByStepOrder() {
        List<Paso> pasos = findAll();
        Paso tempPaso = new Paso();

        Collections.sort(pasos, new Comparator<Paso>() {

            @Override
            public int compare(Paso p1, Paso p2) {
                return (p1.getOrderstep() < p2.getOrderstep() ? -1 : 1);
            }
        });
        return pasos;
    }
}
