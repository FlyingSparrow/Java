package demo.ws.soap_spring_cxf_wss4j;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author wangjianchun
 * @create 2018/1/15
 */
@WebService
public interface GreetingService {

    String greet(@WebParam String name);
}
