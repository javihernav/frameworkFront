/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author SQA-PRUEBA
 */
/**
 * Clase con los métodos para procesar el archivo wsdl del servicio web SOAP.
 *
 * @author SQA-PRUEBA
 */
public class LeerXML {

    /**
     * Retorna un List con los nombres de las funciones, las variables y los
     * tipos de variables.
     *
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    
    public static List obtenerEstructuraXml(String url) throws JDOMException, IOException {
        if (!url.contains("?wsdl") && !url.contains("?WSDL")) {
            url=url.concat("?wsdl");
        }
        String contenido = LeerXML.leerDesdeWeb(url);

        File archivo = LeerXML.convertirStringAArchivo(contenido, "wsdl.xml");
        List estructura = new ArrayList();

        Element elemento = ((Document) (new SAXBuilder()).build(new File("wsdl.xml"))).getRootElement();
        Namespace namespc = elemento.getNamespace();
        System.out.println("Elemento raiz: " + elemento.getName());

        List<Element> temas = new ArrayList<>();
//        temas = elemento.getChildren("types", Namespace.getNamespace(namespc));
        temas = elemento.getChildren("types", namespc);

        for (Element e : temas) {
            System.out.println("hijo: " + e.getName());
            List<Element> subtemas = e.getChildren();
            for (Element sub : subtemas) {//schema
                System.out.println("*      nieto: " + sub.getName());

                List<Element> subsubtemas = sub.getChildren();
                for (Element subsub : subsubtemas) {//complextype
                    System.out.println("*             nombreClase: " + subsub.getAttribute("name").getValue());
                    estructura.add(new String("**"+subsub.getAttribute("name").getValue()));
                    List<Element> subsubsubtemas = subsub.getChildren();
                    for (Element subsubsub : subsubsubtemas) {//sequence
                        System.out.println("*                     tataranieto: " + subsubsub.getName());
                        List<Element> subsubsubsubtemas = subsubsub.getChildren();
                        for (Element subsubsubsub : subsubsubsubtemas) {//element
                            System.out.println("*                             tataratataranieto: " + subsubsubsub.getName());
                            List<Element> subsubsubsubsubtemas = subsubsub.getChildren();
                            for (Element subsubsubsubsub : subsubsubsubsubtemas) {//element
                                System.out.println("*                                     tataratataranieto: " + subsubsubsubsub.getName());
                                List<Element> ssubtemas = subsubsubsubsub.getChildren();
                                for (Element x : ssubtemas) {//element
                                    System.out.println("*                                     nombreVariable: " + x.getAttributeValue("name") + " " + x.getAttributeValue("type"));
                                    estructura.add(new String(x.getAttribute("name").getValue()));
                                    estructura.add(new String(x.getAttribute("type").getValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return estructura;
    }

    /**
     * Lee un archivo que esta en la ubicación de internet proporcionada en la
     * variable direccion.
     *
     * @param direccion
     * @return
     */
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
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str2;
    }

    /**
     * Toma una cadena de texto y la pone como contenido de un archivo cuyo
     * nombre se especifica en nombreArchivo y esta ubicado en la raiz del
     * proyecto
     *
     * @param contenido
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public static File convertirStringAArchivo(String contenido, String nombreArchivo) throws IOException {
        String ruta = nombreArchivo;
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenido);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenido);
        }
        bw.close();
        return archivo;
    }
//leerDesdeWeb("http:\\www.dneonline.com\\calculator.asmx?WSDL")

    public static void main(String[] args) throws JDOMException, IOException {
//        String contenido = LeerXML.leerDesdeWeb("http://www.dneonline.com//calculator.asmx?WSDL");
//        System.out.println("contenido: " + contenido);
//        File archivo = LeerXML.convertirStringAArchivo(contenido, "wsdl.xml");

        List estruc = LeerXML.obtenerEstructuraXml("http://fx.cloanto.com/webservices/CloantoCurrencyServer.asmx");
        System.out.println("Datos: " + estruc.toString());
    }

}
