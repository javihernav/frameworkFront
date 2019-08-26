/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Suit;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface SuitFacadeLocal {

    void create(Suit suit);

    void edit(Suit suit);

    void remove(Suit suit);

    Suit find(Object id);

    List<Suit> findAll();

    List<Suit> findRange(int[] range);
    
    List<Suit> ObtenerSuitConUsuario(int idusuario);

    int count();
    
}
