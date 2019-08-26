/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Ambiente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface AmbienteFacadeLocal {

    void create(Ambiente ambiente);

    void edit(Ambiente ambiente);

    void remove(Ambiente ambiente);

    Ambiente find(Object id);

    List<Ambiente> findAll();

    List<Ambiente> findRange(int[] range);

    int count();
    
}
