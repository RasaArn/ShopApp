package Repository;

import Dto.ProductData;
import Dto.SalesData;
import Dto.UserData;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class SalesRepositoryImplementation implements SalesRepository {
    private Connection connection;

    public SalesRepositoryImplementation() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }




    @Override
    public void recordSale(ProductData product, UserData user, int quantity, double totalPrice) throws SQLException {
        String sql = "INSERT INTO sales_data (saleDate, product_id, user_id, purchasedQuantity, totalPricePerSale, costPrice, productName) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, new Date(System.currentTimeMillis()));
        statement.setInt(2, product.getProductId());
        statement.setInt(3, user.getUserId());
        statement.setInt(4, quantity);
        statement.setDouble(5, totalPrice);
        statement.setDouble(6, product.getCostPrice());
        statement.setString(7, product.getProductName());
        statement.executeUpdate();
    }


    @Override
    public List<SalesData> getAllSales() throws SQLException {
        String query = "SELECT DATE_FORMAT(saleDate, '%Y-%m') AS month, " +
                "p.productName, " +
                "SUM(s.costPrice * s.purchasedQuantity) AS costOfGoodsSold, " +
                "SUM(s.totalPricePerSale) AS revenue, " +
                "SUM(s.totalPricePerSale) - SUM(s.costPrice * s.purchasedQuantity) AS profit " +
                "FROM sales_data s " +
                "INNER JOIN product_data p ON s.product_id = p.productId " +
                "GROUP BY DATE_FORMAT(s.saleDate, '%Y-%m'), p.productName";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        String report = "Monthly Sales Report:\n\n";
        while (result.next()) {
            String month = result.getString("month");
            String productName = result.getString("productName");
            double costOfGoodsSold = result.getDouble("costOfGoodsSold");
            double revenue = result.getDouble("revenue");
            double profit = result.getDouble("profit");
            report += String.format("Month: %s\nProduct: %s\nRevenue: $%.2f\nCost of Goods Sold: $%.2f\nProfit: $%.2f\n\n",
                    month, productName, revenue, costOfGoodsSold, profit);
        }
        JOptionPane.showMessageDialog(null, report, "Sales Report", JOptionPane.INFORMATION_MESSAGE);

        return null;
    }

}
