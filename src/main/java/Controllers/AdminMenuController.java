package Controllers;

import Repository.SalesRepositoryImplementation;
import Services.ProductServiceImplementation;
import VerificationAndValidation.verifyPassword;
//import verificationAndValidation.verifyPassword;

import javax.swing.*;
import java.sql.SQLException;

public class AdminMenuController {
    public static void showAdminMenu() {
        if (verifyPassword.verifyPassword()) {
            String[] options = {"Add Product", "Update product information", "View Sales Report", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Please choose an option:", "Administrator Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            ProductServiceImplementation callProductService = new ProductServiceImplementation();
            SalesRepositoryImplementation callSalesRepository = null;

            try {
                callSalesRepository = new SalesRepositoryImplementation();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            switch (choice) {
                case 0:
                    callProductService.addProduct();
                    break;
                case 1:
                    callProductService.updateProductInfo();
                    break;
                case 2:
                    try {
                        callSalesRepository.getAllSales();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "You were logged out");
                    System.exit(0);
                    break;
            }
            showAdminMenu();
        }
    }


}