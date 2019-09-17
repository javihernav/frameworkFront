/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.modelo.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author SQA-PRUEBA
 */
public class LeerXmlController {

    public static void leerArchivoXml() throws JDOMException, IOException {
        Element elemento = ((Document) (new SAXBuilder()).build(new File("wsdl.xml"))).getRootElement();
        System.out.println("Elemento raiz: " + elemento.getName());
        List<Element> temas = new ArrayList<>();
        temas = elemento.getChildren();
        for (Element e : temas) {
            System.out.println("elemento hijo: " + e.getName());
            List<Element> subtemas = e.getChildren();
            for (Element sub : subtemas) {
                System.out.println("*      elemento nieto: " + sub.getName());

            List<Element> subsubtemas = e.getChildren();
            for (Element subsub : subsubtemas) {
                System.out.println("*             elemento bisnieto: " + subsub.getName());

            }
            }
        }
    }

    public static String leerDesdeWeb(String direccion) {
        String str1 = "";
        String str2 = "";

        // Create a URL for the desired page
        URL url;
        BufferedReader in = null;
        try {
            url = new URL(direccion);

            // Read all the text returned by the server
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((str1 = in.readLine()) != null) {
                str2 = str2 + str1;
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LeerXmlController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerXmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str2;
    }

    public static File convertirStringAArchivo(String texto, String ubicacion) throws IOException {
        String ruta = ubicacion;
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(texto);
        }
        bw.close();
        return archivo;
    }
//leerDesdeWeb("http:\\www.dneonline.com\\calculator.asmx?WSDL")

    public static void main(String[] args) throws JDOMException, IOException {
        String contenido = LeerXmlController.leerDesdeWeb("http://www.dneonline.com//calculator.asmx?WSDL");
        System.out.println("contenido: " + contenido);
        File archivo = LeerXmlController.convertirStringAArchivo(contenido, "wsdl.xml");

        LeerXmlController.leerArchivoXml();
    }

}
