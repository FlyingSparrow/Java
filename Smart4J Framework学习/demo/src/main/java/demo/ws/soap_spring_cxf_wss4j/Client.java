package demo.ws.soap_spring_cxf_wss4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangjianchun
 * @create 2018/1/16
 */
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-cxf-wss4j-client.xml");

        GreetingService greetingService = context.getBean("greetingService", GreetingService.class);
        String result = greetingService.greet("Jack");
        System.out.println(result);
    }

}
