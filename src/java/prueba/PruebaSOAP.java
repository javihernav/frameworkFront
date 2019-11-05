package prueba;

import com.framework.util.LeerXML;
import com.framework.util.MetodoUtil;
import com.framework.util.ParametroUtil;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SQA-PRUEBA
 */
public class PruebaSOAP {

    private ArrayList metodos;

    //métodos para leer servicio web soap
    public void obtenerEstructuraXml(String url) throws JDOMException, IOException {
        metodos=new ArrayList();
        System.out.println("Obteniendo estructura de: " + url);
        //agrega ?wsdl al final de la dirección si no se ha agregado

        List<Element> eleMetodos = new ArrayList<>();

        if (!url.contains("?wsdl") && !url.contains("?WSDL")) {
            url = url.concat("?wsdl");
        }
        String contenido = LeerXML.leerDesdeWeb(url);

        File archivo = LeerXML.convertirStringAArchivo(contenido, "wsdl.xml");
        List estructura = new ArrayList();

        Element elemento = ((Document) (new SAXBuilder()).build(new File("wsdl.xml"))).getRootElement();//toda la estructura wsdl
        Namespace namespc = elemento.getNamespace();

        List<Element> messages = elemento.getChildren("message", namespc);
        List<Element> types = elemento.getChildren("types", namespc);
        Element schema = types.get(0);
        List<Element> schemas = schema.getChildren();//contiene un solo elemento schema
//        System.out.println("------------------------schemas: "+schemas.get(0).getName());

        List<Element> elements = schemas.get(0).getChildren();
        System.out.println("------------------------tam elements: "+elements.size());
//        for (Element e:elements) {
//            System.out.println("element:name:"+e.getAttributeValue("name"));
//            
//        }

        System.out.println("Namespace: " + namespc);

        List<Element> portType = elemento.getChildren("portType", namespc);
        for (Element e : portType) {
            System.out.println("portType: " + e.getAttributeValue("name"));
        }
        List<Element> operations = portType.get(0).getChildren("operation", namespc);
        for (Element op : operations) {
            System.out.println("operation: " + op.getAttributeValue("name"));
            MetodoUtil metodotemp = new MetodoUtil();
            metodotemp.setNombre(op.getAttributeValue("name"));
            System.out.println("message: " + op.getChild("input", namespc).getAttributeValue("message"));
            metodotemp.setInput(op.getChild("input", namespc).getAttributeValue("message"));//añade el nombre de input para asociar con message
            for (Element mesj : messages) {
                if (metodotemp.getInput().contains(mesj.getAttributeValue("name"))) {
                    System.out.println("messagesOK: " + mesj.getAttributeValue("name"));
                    Element part = mesj.getChild("part", namespc);
                    System.out.println("part:message: " + part.getAttributeValue("element"));//muestra el nombre del método con prefijo
                    //recorrer elements
                    for (Element e : elements) {
                        if(e.getName().equals("element")){
                        System.out.println("///////////////////////////////////////////////////////elements.size: "+elements.size());
                        System.out.println("///////////////////////////////////////////////////////"+part.getAttributeValue("element"));
                        System.out.println("///////////////////////////////////////////////////////"+e.getName());
                        System.out.println("///////////////////////////////////////////////////////"+e.getAttributeValue("name"));
                        if (part.getAttributeValue("element").contains(e.getAttributeValue("name"))) {
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++name: " + e.getName());
                            List<Element> sequences = e.getChildren();

                            sequences = sequences.get(0).getChildren();

                            List<Element> parametros = sequences.get(0).getChildren();
                            for (Element ekl : parametros) {
                                ParametroUtil parametrotemp = new ParametroUtil();
                                parametrotemp.setNombre(ekl.getAttributeValue("name"));
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++tam parametros: " + parametrotemp.getNombre());
                                parametrotemp.setTipo(ekl.getAttributeValue("type"));
                                metodotemp.getParametros().add(parametrotemp);
                            }

                        }
                    }
                    }
                }
            }
            
            metodos.add(metodotemp);
        }

        String targetnmspc = elemento.getAttribute("targetNamespace").getValue();
        Namespace tNamespace = Namespace.getNamespace(targetnmspc);
        metodos = (ArrayList) estructura;
        System.out.println("GestionController 963 metodos: " + metodos.toString());
    }

    /**
     * Lee un archivo que esta en la ubicación de internet proporcionada en la
     * variable direccion.
     *
     * @param direccion
     * @return
     */
    public String leerDesdeWeb(String direccion) {
        String stringTemporal = "";
        String textoCompleto = "";

        URL url;
        BufferedReader in = null;
        try {
            url = new URL(direccion);

            //lee todo el texto retornado por el servidor
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((stringTemporal = in.readLine()) != null) {
                textoCompleto = textoCompleto + stringTemporal;
            }
            in.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace(System.out);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);

        }
        System.out.println("Texto obtenido de: " + direccion);
        return textoCompleto;
    }

    /**
     * Toma una cadena de texto y la pone como contenido de un archivo cuyo
     * nombre se especifica en nombreArchivo y esta ubicado en la raiz del
     * proyecto
     *
     * @param textoArchivo
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public File convertirStringAArchivo(String textoArchivo, String nombreArchivo) throws IOException {
        String ruta = nombreArchivo;
        File archivo = new File(ruta);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo, false));
            bw.write(textoArchivo);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(textoArchivo);
        }
        bw.close();
        return archivo;
    }

    public static void main(String[] args) {
        try {
            PruebaSOAP prueba = new PruebaSOAP();
//            prueba.obtenerEstructuraXml("http://www.dneonline.com/calculator.asmx?wsdl");
            prueba.obtenerEstructuraXml("http://www.crcind.com/csp/samples/SOAP.Demo.cls?wsdl");
        } catch (JDOMException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);

        }
    }
}
