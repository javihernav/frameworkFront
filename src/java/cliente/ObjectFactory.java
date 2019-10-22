
package cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cliente package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Ejecutar_QNAME = new QName("http://Servicios/", "ejecutar");
    private final static QName _EjecutarResponse_QNAME = new QName("http://Servicios/", "ejecutarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EjecutarResponse }
     * 
     */
    public EjecutarResponse createEjecutarResponse() {
        return new EjecutarResponse();
    }

    /**
     * Create an instance of {@link Ejecutar_Type }
     * 
     */
    public Ejecutar_Type createEjecutar_Type() {
        return new Ejecutar_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ejecutar_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "ejecutar")
    public JAXBElement<Ejecutar_Type> createEjecutar(Ejecutar_Type value) {
        return new JAXBElement<Ejecutar_Type>(_Ejecutar_QNAME, Ejecutar_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EjecutarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "ejecutarResponse")
    public JAXBElement<EjecutarResponse> createEjecutarResponse(EjecutarResponse value) {
        return new JAXBElement<EjecutarResponse>(_EjecutarResponse_QNAME, EjecutarResponse.class, null, value);
    }

}
