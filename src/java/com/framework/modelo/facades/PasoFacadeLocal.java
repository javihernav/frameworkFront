/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.facades;

import com.framework.modelo.entities.Paso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author SQA
 */
@Local
public interface PasoFacadeLocal {

    void create(Paso paso);

    void edit(Paso paso);

    void remove(Paso paso);

    void agregarPaso(Paso paso, int cantidadPasos);
    
    Paso find(Object id);

    List<Paso> findAll();

    List<Paso> findRange(int[] range);
    
    List<Paso> ObtenerFiltroCliente(int idusuario);
    
    List<Paso> ObtenerPasosAjax(int idCaso);
    
    List<Paso> obtenerValorOrder(int idCaso);
List<Paso> findAllOrderByStepOrder();
    int count();
    
}
