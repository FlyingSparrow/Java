package demo.ws.soap_cxf;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @author wangjianchun
 * @create 2018/1/15
 */
@WebService
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String name) {
        return "hello "+name;
    }
}
