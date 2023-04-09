package Services;

import Dto.ProductData;
import Entities.ProductType;
import Repository.ProductRepositoryImplementation;

import javax.swing.*;
import java.sql.SQLException;

public class ProductServiceImplementation implements ProductService {

    @Override
    public void addProduct() {
        // Prompt admin to select product type
        ProductType[] productTypes = ProductType.values();
        String[] options = new String[productTypes.length];
        for (int i = 0; i < productTypes.length; i++) {
            options[i] = productTypes[i].toString();
        }
        int typeIndex = JOptionPane.showOptionDialog(null, "Please select a product type:", "Add Productadmin", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        ProductType selectedType = productTypes[typeIndex];

        // Prompt admin for product information
        String productName = JOptionPane.showInputDialog("Please enter the product name:");
        double weight = Double.parseDouble(JOptionPane.showInputDialog("Please enter the total weight in Kg per package:"));
        int quantityInStock = Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity of packages in stock:"));
        double costPrice = Double.parseDouble(JOptionPane.showInputDialog("Please enter the cost price in EUR per package:"));
        double sellingPrice = Double.parseDouble(JOptionPane.showInputDialog("Please enter the selling price in EUR per package:"));

        // Creating product data object
        ProductData newProduct = new ProductData(productName, weight, quantityInStock, costPrice, sellingPrice, selectedType);
        try {
            ProductRepositoryImplementation repo = new ProductRepositoryImplementation();
            repo.addProduct(newProduct);
            JOptionPane.showMessageDialog(null, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void updateProductInfo() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the product ID to update:", "Update Product", JOptionPane.PLAIN_MESSAGE));

        try {
            // Create an instance of the ProductRepositoryImplement class
            ProductRepositoryImplementation productRepo = new ProductRepositoryImplementation();

            // take current product data from the database
            ProductData product = productRepo.getProductById(productId);

            // Display an input dialog to prompt  admin to enter the updated information
            String newProductName = JOptionPane.showInputDialog(null, "Enter the new product name:", product.getProductName());
            double newWeight = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new weight:", product.getWeight()));
            int newQuantityInStock = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new quantity in stock:", product.getQuantityInStock()));
            double newCostPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new cost price:", product.getCostPrice()));
            double newSellingPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new selling price:", product.getSellingPrice()));
            ProductType newProductType = (ProductType) JOptionPane.showInputDialog(null, "Select the new product type:", "Product Type", JOptionPane.PLAIN_MESSAGE, null, ProductType.values(), product.getProductType());

            // Update the product data in the database
            ProductData updatedProduct = new ProductData(productId, newProductName, newWeight, newQuantityInStock, newCostPrice, newSellingPrice, newProductType);
            productRepo.updateProductInfo(updatedProduct);

            JOptionPane.showMessageDialog(null, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
