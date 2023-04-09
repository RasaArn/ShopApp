package Controllers;

import javax.swing.*;

public class MainMenuController {

    public static void start() {
        Object[] options = {"I am a Customer", " I am an Administrator", "Exit program"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to the grocery shop. Please choose what is relevant for you and login.",
                "Please choose: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                CustomerStartMenuController.showMenu();
                break;
            case 1:
                AdminMenuController.showAdminMenu();
                break;
            case 2:
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please choose again.");

        } start();
    }
}
