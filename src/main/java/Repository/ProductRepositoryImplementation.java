package Repository;
import Dto.ProductData;
import Entities.ProductType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImplementation implements ProductRepository {
    private Connection connection;
    public ProductRepositoryImplementation() throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    public ProductData getProductByName(String productName) throws SQLException {
        String sql = "SELECT * FROM product_data WHERE productName=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ProductData product = new ProductData(
                    resultSet.getInt("productId"),
                    resultSet.getString("productName"),
                    resultSet.getDouble("weight"),
                    resultSet.getInt("quantityInStock"),
                    resultSet.getDouble("costPrice"),
                    resultSet.getDouble("sellingPrice"),
                    ProductType.valueOf(resultSet.getString("productType"))
            );
            return product;
        }
        return null;
    }
    public ProductData getAllProductByName(String productName) throws SQLException {
        List<ProductData> productList = new ArrayList<>();
        String sql = "SELECT * FROM product_data WHERE productName LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + productName + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ProductData product = new ProductData(
                    resultSet.getInt("productId"),
                    resultSet.getString("productName"),
                    resultSet.getDouble("weight"),
                    resultSet.getInt("quantityInStock"),
                    resultSet.getDouble("costPrice"),
                    resultSet.getDouble("sellingPrice"),
                    ProductType.valueOf(resultSet.getString("productType"))
            );
            productList.add(product);
        }
        return (ProductData) productList;
    }
    @Override
    public void addProduct(ProductData product) throws SQLException {
        String sql = "INSERT INTO product_data (productName, weight, quantityInStock, costPrice, sellingPrice, productType) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getProductName());
        statement.setDouble(2, product.getWeight());
        statement.setInt(3, product.getQuantityInStock());
        statement.setDouble(4, product.getCostPrice());
        statement.setDouble(5, product.getSellingPrice());
        if (product.getProductType() != null) {
            statement.setString(6, product.getProductType().toString());
        } else {
            statement.setNull(6, Types.VARCHAR);
        }
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int productId = generatedKeys.getInt(1);
            product.setProductId(productId);
        }
    }

    @Override
    public void updateProductInfo(ProductData product) throws SQLException {
        String sql = "UPDATE product_data SET productName=?, weight=?, quantityInStock=?, costPrice=?, sellingPrice=?, productType=? WHERE productId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getProductName());
        statement.setDouble(2, product.getWeight());
        statement.setInt(3, product.getQuantityInStock());
        statement.setDouble(4, product.getCostPrice());
        statement.setDouble(5, product.getSellingPrice());
        statement.setString(6, product.getProductType().toString());
        statement.setInt(7, product.getProductId());
        statement.executeUpdate();
    }

   /* @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM product_data WHERE productId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        statement.executeUpdate();
    }*/

    @Override
    public ProductData getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM product_data WHERE productId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            ProductData product = new ProductData(
                    resultSet.getInt("productId"),
                    resultSet.getString("productName"),
                    resultSet.getDouble("weight"),
                    resultSet.getInt("quantityInStock"),
                    resultSet.getDouble("costPrice"),
                    resultSet.getDouble("sellingPrice"),
                    ProductType.valueOf(resultSet.getString("productType"))
            );
            return product;
        }
        return null;
    }
}