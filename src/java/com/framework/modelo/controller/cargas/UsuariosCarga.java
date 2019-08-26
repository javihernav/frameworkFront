/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controller.cargas;

import com.framework.modelo.entities.Usuario;
import com.framework.modelo.facades.UsuarioFacadeLocal;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.ejb.EJB;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author SQA
 */
public class UsuariosCarga {

    @EJB
    private UsuarioFacadeLocal ufl;
    private Usuario nuevoUsuario;
    private ArrayList<Usuario> usuarios;
    /**
     * DATOS PARA LEER EL EXCEL
     */
    BigInteger documento, telefono;
    String primerNombre, segundoNombre;
    String primerApellido, segundoApellido;
    String correoElectronico, empresa, clave, rol;

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public UsuarioFacadeLocal getUfl() {
        return ufl;
    }

    public void setUfl(UsuarioFacadeLocal ufl) {
        this.ufl = ufl;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> LeerArchivoExcel(String archivoDestino) {
        int contador = 1;
        usuarios = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
            //Recorrer Hoja Excel
            for (int hojas = 0; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int numeroColumnas = hoja.getColumns();
                int numeroFilas = hoja.getRows();
                String datos;
                //Recorrer cada fila del archivo
                for (int fila = 2; fila < numeroFilas; fila++) {
                    nuevoUsuario = new Usuario();
                    for (int columna = 0; columna < numeroColumnas; columna++) {
                        datos = hoja.getCell(columna, fila).getContents();
                        System.out.println(datos + "dato");
                        if ("".equals(datos)) {
                            System.out.println("nul este");
                            fila = numeroFilas;
                            columna = numeroColumnas;
                        } else {
                            switch (contador) {
                                case 1:
                                    documento = new BigInteger(datos);
                                    nuevoUsuario.setDocumento(documento);
                                    contador++;
                                    break;

                                case 2:
                                    primerNombre = datos;
                                    nuevoUsuario.setPrimerNombre(primerNombre);

                                    contador++;
                                    break;
                                case 3:
                                    segundoNombre = datos;
                                    nuevoUsuario.setSegundoNombre(segundoNombre);
                                    contador++;
                                    break;
                                case 4:
                                    primerApellido = datos;
                                    nuevoUsuario.setPrimerApellido(primerApellido);
                                    contador++;
                                    break;
                                case 5:
                                    segundoApellido = datos;
                                    nuevoUsuario.setSegundoApellido(segundoApellido);
                                    contador++;
                                    break;
                                case 6:
                                    correoElectronico = datos;
                                    nuevoUsuario.setCorreo(correoElectronico);
                                    contador++;
                                    break;
                                case 7:
                                    telefono = new BigInteger(datos);
                                    nuevoUsuario.setTelefono(telefono);
                                    contador++;
                                    break;

                                case 8:
                                    empresa = datos;
                                    nuevoUsuario.setEmpresa(empresa);
                                    contador++;
                                    break;
                                case 9:
                                    clave = datos;
                                    nuevoUsuario.setClave(clave);
                                    nuevoUsuario.setEstado(1);
                                    nuevoUsuario.setEjecucion("2");
                                    usuarios.add(nuevoUsuario);
                                    contador = 1;
                                    break;
                            }
                        }
                    }

                }
            }

        } catch (IOException | IndexOutOfBoundsException | BiffException ioe) {
        }
        return usuarios;
    }

}
