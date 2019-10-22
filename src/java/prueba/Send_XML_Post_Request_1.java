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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Send_XML_Post_Request_1 {
 public static void main(String[] args) {
 try {
// String url = "http://www.holidaywebservice.com/HolidayService_v2/HolidayService2.asmx?op=GetHolidaysAvailable";
 String url = "http://localhost:8080/EjecutarBackend?op=Ejecutar";
 URL obj = new URL(url);
 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 con.setRequestMethod("POST");
 con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");

 String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://Servicios/\">\n" +
"   <soapenv:Header/>\n" +
"   <soapenv:Body>\n" +
"      <ser:ejecutar xmlns=\"http://localhost:8080/EjecutarBackend/\">\n" +
"         <!--Optional:-->\n" +
"         <documento>1234</documento>\n" +
"         <!--Optional:-->\n" +
"         <contrasena>1234</contrasena>\n" +
"      </ser:ejecutar>\n" +
"   </soapenv:Body>\n" +
"</soapenv:Envelope>";

// String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
// "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> " +
// " <soap12:Body> " +
// " <Ejecutar xmlns=\"http://localhost:8080/EjecutarBackend/Ejecutar/\"> " +
// " <documento>1234</documento>" +
// " <contrasena>1234</contrasena>" +
// " </Ejecutar>" +
// " </soap12:Body>" +
// "</soap12:Envelope>";
 
 
// String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
// "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> " +
// " <soap12:Body> " +
// " <GetHolidaysAvailable xmlns=\"http://www.holidaywebservice.com/HolidayService_v2/\"> " +
// " <countryCode>"+countryCode+"</countryCode>" +
// " </GetHolidaysAvailable>" +
// " </soap12:Body>" +
// "</soap12:Envelope>";
 con.setDoOutput(true);
 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
 wr.writeBytes(xml);
 wr.flush();
 wr.close();
 String responseStatus = con.getResponseMessage();
 System.out.println(responseStatus);
 BufferedReader in = new BufferedReader(new InputStreamReader(
 con.getInputStream()));
 String inputLine;
 StringBuffer response = new StringBuffer();
 while ((inputLine = in.readLine()) != null) {
 response.append(inputLine);
 }
 in.close();
 System.out.println("response:" + response.toString());
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}


// String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://Servicios/\">\n" +
//"   <soap12:Header/>\n" +
//"   <soap12:Body>\n" +
//"      <Ejecutar xmlns=\"http://localhost:8080/EjecutarBackend/\">\n" +
//"         <!--Optional:-->\n" +
//"         <documento>1234</documento>\n" +
//"         <!--Optional:-->\n" +
//"         <contrasena>1234</contrasena>\n" +
//"      </Ejecutar>\n" +
//"   </soap12:Body>\n" +
//"</soap12:Envelope>";