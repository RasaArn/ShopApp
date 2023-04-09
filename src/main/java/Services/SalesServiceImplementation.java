package Services;
import Dto.ProductData;
import Dto.SalesData;
import Dto.UserData;
import Repository.ProductRepository;
import Repository.CustomerRepository;
import Repository.SalesRepository;
import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class SalesServiceImplementation implements SalesService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;

    public SalesServiceImplementation(CustomerRepository customerRepository, ProductRepository productRepository, SalesRepository salesRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
    }

    public void executeSale() {
        String userIdString = JOptionPane.showInputDialog("Enter your user ID:");
        int userId = Integer.parseInt(userIdString);
        String password = JOptionPane.showInputDialog("Enter your password:");
        try {
            UserData user = customerRepository.getUserById(userId);
            if (user != null && user.getPassword().equals(password)) {
                String productName = JOptionPane.showInputDialog("Enter the name of the product you would like to purchase:");
                ProductData product = productRepository.getProductByName(productName);
                if (product != null) {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity of packages " + productName + " you would like to purchase (all packages contain 1 kg):"));
                    if (quantity > 0 && quantity <= product.getQuantityInStock()) {
                        double totalPrice = quantity * product.getSellingPrice();
                        if (user.getBalance() >= totalPrice) {
                            user.setBalance(user.getBalance() - totalPrice);
                            customerRepository.updateUserInformation(user);
                            product.setQuantityInStock(product.getQuantityInStock() - quantity);
                            productRepository.updateProductInfo(product);
                            JOptionPane.showMessageDialog(null, "Purchase successful! Your new balance is: " + user.getBalance(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            SalesData saleData = new SalesData(0, new Date(System.currentTimeMillis()), product, user, quantity, totalPrice);
                            salesRepository.recordSale(saleData.getProduct(), saleData.getUser(), saleData.getPurchasedQuantity(), saleData.getTotalPricePerSale());
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient balance. Please add more money to your account.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid quantity. Not enough goods in stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found or password incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void runSalesReport() {
    }
}
