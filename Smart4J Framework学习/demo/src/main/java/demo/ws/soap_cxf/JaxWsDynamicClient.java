package demo.ws.soap_cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author wangjianchun
 * @create 2018/1/16
 */
public class JaxWsDynamicClient {

    public static void main(String[] args) {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ws/soap/hello?wsdl");
        try {
            Object[] result = client.invoke("say", "world");
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
