/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.DispositivoAppium;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface DispositivoAppiumFacadeLocal {

    void create(DispositivoAppium dispositivoAppium);

    void edit(DispositivoAppium dispositivoAppium);

    void remove(DispositivoAppium dispositivoAppium);

    DispositivoAppium find(Object id);

    List<DispositivoAppium> findAll();

    List<DispositivoAppium> findRange(int[] range);

    int count();
    
}
