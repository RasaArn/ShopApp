package Controllers;
import Repository.*;
import Services.CustomerServiceImplementation;
import Services.SalesServiceImplementation;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class CustomerActionsMenuController {

    protected static void showCustomerActionsMenu() throws SQLException {

        String[] options = {"Check balance", "Add money to balance", "Update your personal information", "Shopping", "Exit"};
        int choice = JOptionPane.showOptionDialog(null, "Please choose what would you like to do: ", "Customer Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        CustomerServiceImplementation callCustomerService= new CustomerServiceImplementation();
        SalesServiceImplementation callSalesService = new SalesServiceImplementation(new CustomerRepositoryImplementation(), new ProductRepositoryImplementation(), new SalesRepositoryImplementation());

        switch (choice) {

            case 0:
                callCustomerService.checkBalance();
                break;
            case 1:
                callCustomerService.addMoneyToBalance();
                break;
            case 2:
                callCustomerService.updateUserInformation();
                break;
            case 3:
                callSalesService.executeSale();
                break;
            default:
                System.exit(0);
                break;

        } showCustomerActionsMenu();
    }
}
