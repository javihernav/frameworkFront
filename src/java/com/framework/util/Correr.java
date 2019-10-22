package com.framework.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SQA-PRUEBA
 */
public class Correr {

    public static void ejecutarBackend() {
        Runtime aplicacion = Runtime.getRuntime();
        String ruta;
        try {
ruta=System.getProperty("user.home");
            aplicacion.exec("cmd.exe /K start "+ruta+"/Desktop/Estable/frameworkSQAIC/Ejecutar.bat");

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        Runtime aplicacion = Runtime.getRuntime();
        try {

            aplicacion.exec("cmd.exe /K start C:/Users/SQA-PRUEBA/Desktop/frameworkSQAIC/Ejecutar.bat");

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}
