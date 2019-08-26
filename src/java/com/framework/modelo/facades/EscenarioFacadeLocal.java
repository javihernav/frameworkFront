/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Escenario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface EscenarioFacadeLocal {

    void create(Escenario escenario);

    void edit(Escenario escenario);

    void remove(Escenario escenario);

    Escenario find(Object id);

    List<Escenario> findAll();

    List<Escenario> findRange(int[] range);

    List<Escenario> ObtenerEscenarioConUsuario(int idusuario);
    
    int count();
    
}
