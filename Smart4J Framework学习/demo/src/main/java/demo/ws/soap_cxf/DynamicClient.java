package demo.ws.soap_cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

/**
 * @author wangjianchun
 * @create 2018/1/16
 */
public class DynamicClient {

    public static void main(String[] args) {
        DynamicClientFactory factory = DynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ws/soap/hello?wsdl");

        try {
            Object[] result = client.invoke("say", "world");
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
