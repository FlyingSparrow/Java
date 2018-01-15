package demo.ws.soap_cxf;

import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * @author wangjianchun
 * @create 2018/1/15
 */
public class SimpleServer {

    public static void main(String[] args) {
        ServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/soap/hello");
        factory.setServiceClass(HelloService.class);
        factory.setServiceBean(new HelloServiceImpl());
        factory.create();
        System.out.println("soap ws is published");
    }
}
