package demo.ws.rest_cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的WebClient 客户端
 * @author wangjianchun
 * @create 2018/1/20
 */
public class CXFWebClient {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        List<Object> providerList = new ArrayList<Object>();
        providerList.add(new JacksonJsonProvider());

        /*List productList = WebClient.create(baseAddress, providerList)
                .path("/products").accept(MediaType.APPLICATION_JSON)
                .get(List.class);
        for(Object product : productList){
            System.out.println(product);
        }*/

        List<Product> productList = WebClient.create(baseAddress, providerList)
                .path("/products").accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>(){});
        for(Product product : productList){
            System.out.println(product);
        }
    }

}
