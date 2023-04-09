package VerificationAndValidation;

import javax.swing.*;

public class verifyPassword {
    private static final String PASSWORD = "admin123";

    public static boolean verifyPassword() {
        String password = JOptionPane.showInputDialog("Please enter the administrator password for further actions :");
        if (password == null) {
            System.exit(0);
        } else if (!password.equals(PASSWORD)) {
            JOptionPane.showMessageDialog(null, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
