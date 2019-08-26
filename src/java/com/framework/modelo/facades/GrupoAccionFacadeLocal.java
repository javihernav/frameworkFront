/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.GrupoAccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface GrupoAccionFacadeLocal {

    void create(GrupoAccion grupoAccion);

    void edit(GrupoAccion grupoAccion);

    void remove(GrupoAccion grupoAccion);

    GrupoAccion find(Object id);

    List<GrupoAccion> findAll();

    List<GrupoAccion> findRange(int[] range);

    int count();
    
}
