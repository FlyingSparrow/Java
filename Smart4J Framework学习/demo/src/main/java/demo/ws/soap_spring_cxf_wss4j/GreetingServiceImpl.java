package demo.ws.soap_spring_cxf_wss4j;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author wangjianchun
 * @create 2018/1/15
 */
@WebService
@Component("greetingServiceImpl")
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String name) {
        return "Nice to meet you! "+name;
    }
}
