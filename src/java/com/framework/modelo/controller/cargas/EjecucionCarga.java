/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controller.cargas;

import com.framework.modelo.controllers.UsuariosController;
import com.framework.modelo.entities.Ambiente;
import com.framework.modelo.entities.Caso;
import com.framework.modelo.entities.Escenario;
import com.framework.modelo.entities.Navegador;
import com.framework.modelo.entities.Paso;
import com.framework.modelo.entities.Suit;
import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.SuitFacadeLocal;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author SQA
 */
public class EjecucionCarga {

    @EJB
    private UsuarioFacadeLocal ufl;
    private SuitFacadeLocal sfl;
    private List<Ambiente> ambientes;
    private List<Suit> suits;
    private List<Usuario> usuarios;
    private List<Escenario> escenarios;
    private List<Caso> casos;
    private List<Paso> pasos;
    private Suit nuevaSuit;
    private Escenario nuevoEscenario;
    private Caso nuevoCaso;
    private Paso nuevoPaso;

    public List<Suit> getSuits() {
        return suits;
    }

    public void setSuits(List<Suit> suits) {
        this.suits = suits;
    }

    public Suit getNuevaSuit() {
        return nuevaSuit;
    }

    public void setNuevaSuit(Suit nuevaSuit) {
        this.nuevaSuit = nuevaSuit;
    }

    public Escenario getNuevoEscenario() {
        return nuevoEscenario;
    }

    public void setNuevoEscenario(Escenario nuevoEscenario) {
        this.nuevoEscenario = nuevoEscenario;
    }

    public Caso getNuevoCaso() {
        return nuevoCaso;
    }

    public void setNuevoCaso(Caso nuevoCaso) {
        this.nuevoCaso = nuevoCaso;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Escenario> getEscenarios() {
        return escenarios;
    }

    public void setEscenarios(List<Escenario> escenarios) {
        this.escenarios = escenarios;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public Paso getNuevoPaso() {
        return nuevoPaso;
    }

    public void setNuevoPaso(Paso nuevoPaso) {
        this.nuevoPaso = nuevoPaso;
    }

    public List<Suit> leerArchivoExcelSuit(String archivoDestino, List<Usuario> usuarioslist) {
        int contador = 1;
        suits = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

            //Recorre la primer hoja excel
            for (int hojas = 0; hojas < 1; hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numeroColumnas = hoja.getColumns();
                int numerofilas = hoja.getRows();
                String datos;

                //Recorrer cada fila
                for (int fila = 2; fila < numerofilas; fila++) {
                    nuevaSuit = new Suit();
                    for (int columna = 0; columna < numeroColumnas; columna++) {
                        datos = hoja.getCell(columna, fila).getContents();
                        if ("".equals(datos)) {
                            System.out.println("nulo");
                            fila = numerofilas;
                            columna = numeroColumnas;
                        } else {
                            switch (contador) {
                                case 1:
                                    nuevaSuit.setNombreSuit(datos);
                                    if (suits.size() > 0) {
                                        for (Suit su : suits) {
                                            if (nuevaSuit.getNombreSuit().toLowerCase().equals(su.getNombreSuit().toLowerCase())) {
                                                nuevaSuit.setNombreSuit("");
                                            }
                                        }
                                    }
                                    contador++;
                                    break;
                                case 2:
                                    nuevaSuit.setDescripcionSuit(datos);
                                    contador++;
                                    break;
                                case 3:
                                    String correo = datos;
                                    usuarios = usuarioslist;
                                    for (Usuario usu : usuarios) {
                                        if (correo.equals(usu.getCorreo())) {
                                            nuevaSuit.setIdUsuario(usu);
                                        }
                                    }
                                    contador++;
                                    break;
                                case 4:
                                    String estado = datos.toUpperCase();
                                    if ("ACTIVO".equals(estado)) {
                                        nuevaSuit.setEstadoSuit(1);
                                    } else if ("INACTIVO".equals(estado)) {
                                        nuevaSuit.setEstadoSuit(2);
                                    } else {
                                        nuevaSuit.setEstadoSuit(0);
                                    }
                                    suits.add(nuevaSuit);
                                    contador = 1;
                                    break;
                            }
                        }
                    }
                }

            }
        } catch (IOException | IndexOutOfBoundsException | BiffException e) {
        }

        return suits;
    }

    public List<Escenario> leerArchivoExcelEscenario(String archivoDestino, List<Suit> suitlista, List<Ambiente> ambientelist, List<Usuario> usuarioslist) {
        int contador = 1;
        escenarios = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

            //Recorrer hoja excel
            for (int hojas = 1; hojas < 2; hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numerocolumnas = hoja.getColumns();
                int numerofilas = hoja.getRows();
                String datos;
                suits.clear();
                //Recorre cada fila del archivo
                for (int fila = 2; fila < numerofilas; fila++) {
                    nuevoEscenario = new Escenario();
                    for (int columna = 0; columna < numerocolumnas; columna++) {
                        datos = hoja.getCell(columna, fila).getContents();
                        if ("".equals(datos)) {
                            System.out.println("null");
                            fila = numerofilas;
                            columna = numerocolumnas;
                        } else {
                            switch (contador) {
                                case 1:
                                    nuevoEscenario.setNombreEscenario(datos);

                                    contador++;
                                    break;
                                case 2:
                                    nuevoEscenario.setDescripcionEscenario(datos);
                                    contador++;
                                    break;
                                case 3:
                                    String name = datos;
                                    suits = suitlista;
                                    for (Suit suit : suits) {
                                        if (name.equals(suit.getNombreSuit())) {
                                            nuevoEscenario.setIdSuit(suit);
                                        }
                                    }

                                    if (nuevoEscenario.getIdSuit() == null) {
                                        suits = this.leerArchivoExcelSuit(archivoDestino, usuarioslist);
                                        for (Suit st : suits) {
                                            if (name.equals(st.getNombreSuit())) {
                                                nuevoEscenario.setIdSuit(st);
                                            }
                                        }
                                    }

                                    if (escenarios.size() > 0) {
                                        for (Escenario esce : escenarios) {
                                            if (esce.getIdSuit() != null) {
                                                String veri = nuevoEscenario.getIdSuit().getNombreSuit().toLowerCase();
                                                String forveri = esce.getIdSuit().getNombreSuit().toLowerCase();
                                                if (nuevoEscenario.getNombreEscenario().toLowerCase().equals(esce.getNombreEscenario().toLowerCase())) {
                                                    if (veri.equals(forveri)) {
                                                        nuevoEscenario.setNombreEscenario("YA ESTA CON ESTA SUIT");
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    contador++;
                                    break;
                                case 4:
                                    String ambiente = datos.toLowerCase();
                                    ambientes = ambientelist;
                                    for (Ambiente ambi : ambientes) {
                                        if (ambiente.equals(ambi.getNombreAmbiente().toLowerCase())) {
                                            nuevoEscenario.setIdAmbiente(ambi);
                                        }
                                    }
                                    contador++;
                                    break;
                                case 5:
                                    String estado = datos.toUpperCase();
                                    if ("ACTIVO".equals(estado)) {
                                        nuevoEscenario.setEstadoEscenario(1);
                                    } else if ("INACTIVO".equals(estado)) {
                                        nuevoEscenario.setEstadoEscenario(2);
                                    } else {
                                        nuevoEscenario.setEstadoEscenario(0);
                                    }
                                    escenarios.add(nuevoEscenario);
                                    contador = 1;
                                    break;

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return escenarios;
    }

    public List<Caso> leerArchivoExcelCasos(String archivoDestino, List<Escenario> escenarioslist, List<Suit> suitlista, List<Ambiente> ambientelist, List<Usuario> usuarioslist) {
        int contador = 1;
        casos = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            //Recorrer Excel de casos
            for (int hojas = 2; hojas < 3; hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numerocolumnas = hoja.getColumns();
                int numerofilas = hoja.getRows();
                String datos;
                for (int fila = 2; fila < numerofilas; fila++) {
                    nuevoCaso = new Caso();
                    for (int columna = 0; columna < numerocolumnas; columna++) {
                        datos = hoja.getCell(columna, fila).getContents();
                        if ("".equals(datos)) {
                            System.out.println("null");
                            fila = numerofilas;
                            columna = numerocolumnas;
                        } else {
                            switch (contador) {
                                case 1:
                                    nuevoCaso.setNombreCaso(datos);
                                    contador++;
                                    break;
                                case 2:
                                    String escenario = datos;
                                    escenarios = escenarioslist;
                                    for (Escenario esce : escenarios) {
                                        if (escenario.equals(esce.getNombreEscenario())) {
                                            nuevoCaso.setIdEscenario(esce);
                                        }
                                    }

                                    if (nuevoCaso.getIdEscenario() == null) {
                                        escenarios = this.leerArchivoExcelEscenario(archivoDestino, suitlista, ambientelist, usuarioslist);
                                        for (Escenario esce : escenarios) {
                                            if (escenario.equals(esce.getNombreEscenario())) {
                                                nuevoCaso.setIdEscenario(esce);
                                            }
                                        }
                                    }

                                    if (casos.size() > 0) {
                                        for (Caso cass : casos) {
                                            if (nuevoCaso.getIdEscenario() != null) {
                                                String veri = nuevoCaso.getIdEscenario().getNombreEscenario().toLowerCase();
                                                String forveri = cass.getIdEscenario().getNombreEscenario().toLowerCase();
                                                if (veri.equals(forveri)) {
                                                    nuevoCaso.setNombreCaso("YA ESTA CON ESTE ESCENARIO");
                                                }
                                            }
                                        }
                                    }

                                    contador++;
                                    break;
                                case 3:
                                    String estado = datos.toUpperCase();
                                    if ("ACTIVO".equals(estado)) {
                                        nuevoCaso.setEstadoCaso(1);
                                    } else if ("INACTIVO".equals(estado)) {
                                        nuevoCaso.setEstadoCaso(2);
                                    } else {
                                        nuevoCaso.setEstadoCaso(0);
                                    }
                                    casos.add(nuevoCaso);
                                    contador = 1;
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return casos;
    }

    public List<Paso> leerArchivoExcelPasos(String archivoDestino, List<Caso> casoexcel, List<Escenario> escenarioslist, List<Suit> suitlista, List<Ambiente> ambientelist, List<Usuario> usuarioslist) {
        int contador = 1;
        pasos = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

            //Recorrer hoja excel de pasos
            for (int hojas = 3; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numerocolumnas = hoja.getColumns();
                int numerofilas = hoja.getRows();
                String datos;
                //Recorrer las filas de la hojas
                for (int fila = 2; fila < numerofilas; fila++) {
                    nuevoPaso = new Paso();
                    for (int columna = 0; columna < numerocolumnas; columna++) {
                        datos = hoja.getCell(columna, fila).getContents();

                        switch (contador) {
                            case 1:
                                nuevoPaso.setActionStep(datos);
                                if (pasos.size() > 0) {
                                    for (Paso pass : pasos) {
                                        if ("Ingresar URL".equals(pass.getActionStep()) && "Ingresar URL".equals(datos)) {
                                            nuevoPaso.setActionStep("Ya existe la accion Ingresar URL");
                                        }
                                    }
                                }
                                contador++;
                                break;
                            case 2:
                                nuevoPaso.setNavegador(datos);
                                contador++;
                                break;
                            case 3:
                                nuevoPaso.setTypeStep(datos);
                                contador++;
                                break;
                            case 4:
                                nuevoPaso.setValueStep(datos);
                                contador++;
                                break;
                            case 5:
                                nuevoPaso.setParameterStep(datos);
                                contador++;
                                break;
                            case 6:
                                if (!"".equals(datos)) {
                                    nuevoPaso.setCorXStep(Integer.parseInt(datos));
                                } else {
                                    nuevoPaso.setCorXStep(null);
                                }
                                contador++;
                                break;
                            case 7:
                                if (!"".equals(datos)) {
                                    nuevoPaso.setCorYStep(Integer.parseInt(datos));
                                } else {
                                    nuevoPaso.setCorYStep(null);
                                }
                                contador++;
                                break;
                            case 8:
                                String caso = datos;
                                casos = casoexcel;
                                if (casos.size() > 0) {
                                    for (Caso cass : casos) {
                                        if (caso.equals(cass.getNombreCaso())) {
                                            nuevoPaso.setIdCaso(cass);
                                        }
                                    }
                                }

                                if (nuevoPaso.getIdCaso() == null) {
                                    casos = this.leerArchivoExcelCasos(archivoDestino, escenarioslist, suitlista, ambientelist, usuarioslist);
                                    if (casos.size() > 0) {
                                        for (Caso cass : casos) {
                                            if (caso.equals(cass.getNombreCaso())) {
                                                nuevoPaso.setIdCaso(cass);
                                            }
                                        }
                                    }
                                }

                                pasos.add(nuevoPaso);
                                contador ++;
                                break;
                            case 9:
                                String orden = datos;
                                try {
                                    nuevoPaso.setOrderstep(Integer.parseInt(orden));
                                } catch (Exception e) {
                                    nuevoPaso.setOrderstep(0);
                                }
                                contador =1;
                                break;

                        }
                    }
                }
            }

        } catch (Exception e) {
        }
        return pasos;
    }

}
