import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //Create an instance of the MainPage class
                RegistrationGUI registrationGUIPage = new RegistrationGUI();
                registrationGUIPage.setVisible(true);
            }
        });
    }
}
