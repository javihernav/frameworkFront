/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Metodo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA-PRUEBA
 */
@Local
public interface MetodoFacadeLocal {

    void create(Metodo metodo);

    void edit(Metodo metodo);

    void remove(Metodo metodo);

    Metodo find(Object id);

    List<Metodo> findAll();

    List<Metodo> findRange(int[] range);

    int count();
    
}
