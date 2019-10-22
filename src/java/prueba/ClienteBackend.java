/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

/**
 *
 * @author SQA-PRUEBA
 */
public class ClienteBackend {
    private static String ejecutar(java.lang.String documento, java.lang.String contrasena) {
        cliente.Ejecutar_Service service = new cliente.Ejecutar_Service();
        cliente.Ejecutar port = service.getEjecutarPort();
        return port.ejecutar(documento, contrasena);
    }
    public static void main(String[] args) {
        ejecutar("1234","1234");
    }
    
}
