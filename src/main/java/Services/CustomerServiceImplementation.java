package Services;
import Dto.UserData;
import Repository.CustomerRepository;
import Repository.CustomerRepositoryImplementation;
import javax.swing.*;
import java.sql.SQLException;

public class CustomerServiceImplementation implements CustomerService {
    private static CustomerRepository customerRepository;{
        try {
            customerRepository = new CustomerRepositoryImplementation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double checkBalance() {
        String userIdString = JOptionPane.showInputDialog("Enter your user ID:");
        int userId = Integer.parseInt(userIdString);
        String password = JOptionPane.showInputDialog("Enter your password:");
        try {
            UserData user = customerRepository.getUserById(userId);
            if (user != null && user.getPassword().equals(password)) {
                double balance = customerRepository.checkBalance(user);
                JOptionPane.showMessageDialog(null, "Your current balance is: " + balance, "Balance", JOptionPane.INFORMATION_MESSAGE);
                return balance;
            } else {
                JOptionPane.showMessageDialog(null, "User not found or password incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0; // will return a default value in case of error
    }


    @Override
    public void addMoneyToBalance() {
        String userIdString = JOptionPane.showInputDialog("Enter your user ID:");
        int userId = Integer.parseInt(userIdString);
        String amountString = JOptionPane.showInputDialog("Enter the amount to add to your balance:");
        double amount = Double.parseDouble(amountString);
        try {
            UserData user = customerRepository.getUserById(userId);
            customerRepository.addMoneyToBalance(user, amount);
            JOptionPane.showMessageDialog(null, "Balance updated!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateUserInformation() {
        int userId = Integer.parseInt(JOptionPane.showInputDialog("Enter your user ID:"));
        String[] options = {"Full Name", "Email", "Password", "Street Address", "Building Number", "Apartment Number", "City", "Country", "ZIP/Postal Code"};
        String selectedOption = (String) JOptionPane.showInputDialog(null, "Select the information you want to update:", "Update User Information", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        try {
            UserData user = customerRepository.getUserById(userId);
            if (user != null) {
                switch(selectedOption) {
                    case "Full Name":
                        user.setUserNameSurname(JOptionPane.showInputDialog("Enter your full name:", user.getUserNameSurname()));
                        break;
                    case "Email":
                        user.setEmail(JOptionPane.showInputDialog("Enter your email address:", user.getEmail()));
                        break;
                    case "Password":
                        user.setPassword(JOptionPane.showInputDialog("Enter a password:", user.getPassword()));
                        break;
                    case "Street Address":
                        user.setAddressStreet(JOptionPane.showInputDialog("Enter your street address:", user.getAddressStreet()));
                        break;
                    case "Building Number":
                        user.setAddressBuildingNumber(JOptionPane.showInputDialog("Enter your building number:", user.getAddressBuildingNumber()));
                        break;
                    case "Apartment Number":
                        user.setApartmentNumber(JOptionPane.showInputDialog("Enter your apartment number (if applicable):", user.getApartmentNumber()));
                        break;
                    case "City":
                        user.setCity(JOptionPane.showInputDialog("Enter your city:", user.getCity()));
                        break;
                    case "Country":
                        user.setCountry(JOptionPane.showInputDialog("Enter your country:", user.getCountry()));
                        break;
                    case "ZIP/Postal Code":
                        user.setZipCode(JOptionPane.showInputDialog("Enter your ZIP/postal code:", user.getZipCode()));
                        break;
                    default:
                        break;
                }
                customerRepository.updateUserInformation(user);
                JOptionPane.showMessageDialog(null, "User updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
      /*  int userId = Integer.parseInt(JOptionPane.showInputDialog("Enter your user ID:"));
        try {
            UserData user = customerRepository.getUserById(userId);
            if (user != null) {
                user.setUserNameSurname(JOptionPane.showInputDialog("Enter your full name:", user.getUserNameSurname()));
                user.setEmail(JOptionPane.showInputDialog("Enter your email address:", user.getEmail()));
                user.setPassword(JOptionPane.showInputDialog("Enter a password:", user.getPassword()));
                user.setAddressStreet(JOptionPane.showInputDialog("Enter your street address:", user.getAddressStreet()));
                user.setAddressBuildingNumber(JOptionPane.showInputDialog("Enter your building number:", user.getAddressBuildingNumber()));
                user.setApartmentNumber(JOptionPane.showInputDialog("Enter your apartment number (if applicable):", user.getApartmentNumber()));
                user.setCity(JOptionPane.showInputDialog("Enter your city:", user.getCity()));
                user.setCountry(JOptionPane.showInputDialog("Enter your country:", user.getCountry()));
                user.setZipCode(JOptionPane.showInputDialog("Enter your ZIP/postal code:", user.getZipCode()));
               // user.setBalance(Double.parseDouble(JOptionPane.showInputDialog("Enter your balance:", user.getBalance())));
                customerRepository.updateUserInformation(user);
                JOptionPane.showMessageDialog(null, "User updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }*/
    //}

    @Override
    public void login() {
        String email = JOptionPane.showInputDialog("Enter your email address:");
        String password = JOptionPane.showInputDialog("Enter your password:");
        UserData user = new UserData();
        user.setEmail(email);
        user.setPassword(password);
        try {
            if (customerRepository.login(user)) {
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void registerUser() {  UserData user = new UserData();
        user.setUserNameSurname(JOptionPane.showInputDialog("Enter your full name:"));
        user.setEmail(JOptionPane.showInputDialog("Enter your email address:"));
        user.setPassword(JOptionPane.showInputDialog("Create a password:"));
        user.setAddressStreet(JOptionPane.showInputDialog("Enter your street name:"));
        user.setAddressBuildingNumber(JOptionPane.showInputDialog("Enter your building number:"));
        user.setApartmentNumber(JOptionPane.showInputDialog("Enter your apartment number (if applicable):"));
        user.setCity(JOptionPane.showInputDialog("Enter your city:"));
        user.setCountry(JOptionPane.showInputDialog("Enter your country:"));
        user.setZipCode(JOptionPane.showInputDialog("Enter your ZIP/postal code:"));
        user.setBalance(Double.parseDouble(JOptionPane.showInputDialog("You can add money to your grocery Store account. " +
                "If you would like to do so, please enter sum to add, or enter 0:")));
        try {
            customerRepository.registerUser(user);
            JOptionPane.showMessageDialog(null, "User registered successfully. User ID is: " + user.getUserId() + ". Please remember your ID." +
                    "You will need it in order to perform further actions", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
