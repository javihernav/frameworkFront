/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Navegador;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface NavegadorFacadeLocal {
    
    void create(Navegador navegador);
    
    void edit(Navegador navegador);
    
    void remove(Navegador navegador);
    
    Navegador find(Object id);
    
    List<Navegador> findAll();
}
