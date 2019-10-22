/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import cliente.Ejecutar;
import cliente.Ejecutar_Service;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 *
 * @author SQA-PRUEBA
 */
public class Probar {
        public static void probar(String direccionip,String puerto) throws MalformedURLException{
        if (direccionip.equals("")||direccionip==null) {
            direccionip="127.0.0.1";
        }
        if (puerto.equals("")||puerto==null) {
            puerto="8080";
        }
    Ejecutar ejec = new Ejecutar_Service("http://"+direccionip+":"+puerto+"/EjecutarBackend/Ejecutar?wsdl",new QName("http://Servicios/", "Ejecutar")).getEjecutarPort();
    
    ejec.ejecutar("1234", "1234");
    }
    public static void main(String[] args) {
        try {
            probar("127.0.0.1","8080");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Probar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
