package demo.ws.rest_cxf;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangjianchun
 * @create 2018/1/17
 */
@Component
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> retrieveAllProducts() {
        List<Product> result = new ArrayList<Product>();
        for(int i=0; i<10; i++){
            Product product = new Product();
            product.setId(i+1);
            product.setCode((i+1)+"");
            product.setName("product"+(i+1));
            product.setPrice(10D);
            result.add(product);
        }
        return result;
    }

    @Override
    public Product retrieveProductById(long id) {
        return null;
    }

    @Override
    public List<Product> retrieveProductsByName(String name) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProductById(long id, Map<String, Object> fieldMap) {
        return null;
    }

    @Override
    public Product deleteProductById(long id) {
        return null;
    }
}
