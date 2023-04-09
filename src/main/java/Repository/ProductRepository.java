package Repository;

import Dto.ProductData;
import Entities.ProductType;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    void addProduct(ProductData product) throws SQLException;
    void updateProductInfo(ProductData product) throws SQLException;
    ProductData getProductByName(String productName) throws SQLException;
    ProductData getAllProductByName(String productName) throws SQLException;
    ProductData getProductById(int productId) throws SQLException;
   // List<ProductData> getAllProductsQuantityInTheStore() throws SQLException;
    // List<ProductData> getAllProductsByproductType() throws SQLException;
}
