package Controllers;

import Services.CustomerServiceImplementation;

import javax.swing.*;
import java.sql.SQLException;

public class CustomerStartMenuController {

    protected static void showMenu() {
      /*  String[] options = {"Login", "Register", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Please choose an option:", "Customer Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        CustomerServiceImplementation customerActionsService= new CustomerServiceImplementation();

        switch (choice) {
            case 0:
                customerActionsService.login();
                CustomerActionsMenuController.showCustomerActionsMenu();
                break;
            case 1:
                customerActionsService.registerUser();
                CustomerActionsMenuController.showCustomerActionsMenu();
                break;
            default:
                System.exit(0);
                break;
        }
    }
}*/
        String[] options = {"Login", "Register", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Please choose an option:", "Customer Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        CustomerServiceImplementation customerActionsService= new CustomerServiceImplementation();

        switch (choice) {
            case 0:
                try {
                    customerActionsService.login();
                    CustomerActionsMenuController.showCustomerActionsMenu();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                try {
                    customerActionsService.registerUser();
                    CustomerActionsMenuController.showCustomerActionsMenu();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                System.exit(0);
                break;
        }
    }
}
