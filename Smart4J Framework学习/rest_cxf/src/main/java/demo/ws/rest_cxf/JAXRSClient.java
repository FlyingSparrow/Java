package demo.ws.rest_cxf;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一种：JAX-RS 1.0 时代的客户端
 * @author wangjianchun
 * @create 2018/1/17
 */
public class JAXRSClient {

    public static void main(String[] args) {
        String baseAddress = "http://localhost:8080/ws/rest";

        List<Object> providerList = new ArrayList<Object>();
        providerList.add(new JacksonJsonProvider());

        ProductService productService = JAXRSClientFactory.create(baseAddress, ProductService.class, providerList);
        List<Product> productList = productService.retrieveAllProducts();
        for(Product product : productList){
            System.out.println(product);
        }
    }

}
