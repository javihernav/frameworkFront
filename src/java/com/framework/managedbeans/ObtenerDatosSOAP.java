/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.managedbeans;

import com.framework.util.LeerXML;
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
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author SQA-PRUEBA
 */
@Named(value = "obtenerDatosSOAP")
@RequestScoped
public class ObtenerDatosSOAP {

    /**
     * Creates a new instance of ObtenerDatosSOAP
     */
    private String url;
    private static ArrayList metodos;
    public ObtenerDatosSOAP() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList getMetodos() {
        return metodos;
    }

    public void setMetodos(ArrayList metodos) {
        this.metodos = metodos;
    }
    
    
        public static void obtenerEstructuraXml(String url) throws JDOMException, IOException {
        //agrega ?wsdl al final de la dirección si no se ha agregado
        if (!url.contains("?wsdl") && !url.contains("?WSDL")) {
            url = url.concat("?wsdl");
        }
        String contenido = LeerXML.leerDesdeWeb(url);

        File archivo = LeerXML.convertirStringAArchivo(contenido, "wsdl.xml");
        List estructura = new ArrayList();

        Element elemento = ((Document) (new SAXBuilder()).build(new File("wsdl.xml"))).getRootElement();
        Namespace namespc = elemento.getNamespace();
        
        String targetnmspc = elemento.getAttribute("targetNamespace").getValue();
        
//        System.out.println("Elemento raiz: " + elemento.getName() + " Namespace: " + elemento.getNamespace());

        List<Element> types = new ArrayList<>();
//        temas = elemento.getChildren("types", Namespace.getNamespace(namespc));
        types = elemento.getChildren("types", namespc);
//        System.out.println("nombre: "+elemento.getChildren("service",namespc).get(0).getAttributeValue("name"));
        String nombreServicio=elemento.getChildren("service",namespc).get(0).getAttributeValue("name");
        for (Element e : types) {
//            System.out.println("hijo: " + e.getName());
            List<Element> schema = e.getChildren();
            for (Element sub : schema) {//schema
//                System.out.println("*      nieto: " + sub.getName());

                List<Element> elem = sub.getChildren();
                for (Element subsub : elem) {//complextype
                    if (subsub.getName().equals("element")) {
//                    System.out.println("*             nombreClase: " + subsub.getAttribute("name").getValue()+" "+subsub.getName());
                    estructura.add(new String("**" + subsub.getAttribute("name").getValue()));//marca los métodos con **
                    }
                        
                    
                    List<Element> subsubsubtemas = subsub.getChildren();
                    for (Element subsubsub : subsubsubtemas) {//sequence
//                        System.out.println("*                     tataranieto: " + subsubsub.getName());
                        List<Element> subsubsubsubtemas = subsubsub.getChildren();
                        for (Element subsubsubsub : subsubsubsubtemas) {//element
//                            System.out.println("*                             tataratataranieto: " + subsubsubsub.getName());
                            List<Element> subsubsubsubsubtemas = subsubsub.getChildren();
                            for (Element subsubsubsubsub : subsubsubsubsubtemas) {//element
//                                System.out.println("*                                     tataratataranieto: " + subsubsubsubsub.getName());
                                List<Element> ssubtemas = subsubsubsubsub.getChildren();
                                
                                for (Element x : ssubtemas) {//element
//                                    System.out.println("*                                     nombreVariable: " + x.getAttributeValue("name") + " " + x.getAttributeValue("type"));
                                    estructura.add(new String(x.getAttribute("name").getValue()));
                                    estructura.add(new String(x.getAttribute("type").getValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
//        namespc=elemento.getAdditionalNamespaces().get(1);
//        estructura.add(namespc.getURI());
        estructura.add(targetnmspc);
        estructura.add("SS"+nombreServicio);
        
        
        metodos=(ArrayList) estructura;
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

        
        URL url;
        BufferedReader in = null;
        try {
            url = new URL(direccion);

            //lee todo el texto retornado por el servidor
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((str1 = in.readLine()) != null) {
                str2 = str2 + str1;
            }
            in.close();
        } catch (MalformedURLException ex) {
             ex.printStackTrace(System.out);
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             ex.printStackTrace(System.out);
            Logger.getLogger(LeerXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Texto obtenido de: "+direccion);
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
            bw = new BufferedWriter(new FileWriter(archivo,false));
            bw.write(contenido);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(contenido);
        }
        bw.close();
        return archivo;
    }
}
