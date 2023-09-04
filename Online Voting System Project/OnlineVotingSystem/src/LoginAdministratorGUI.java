import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginAdministratorGUI extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel background;
    private JLabel errorLabel;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JPanel adminPanel;


    public LoginAdministratorGUI() {
        setTitle("Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("background.jpeg");
        background = new JLabel(backgroundImage);
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        adminPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add logo
        ImageIcon logoImage = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoImage);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        adminPanel.add(logoLabel, constraints);

        // Add id label and field
        idLabel = new JLabel("ID:");
        idField = new JTextField(15);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        adminPanel.add(idLabel, constraints);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        adminPanel.add(idField, constraints);

        // Add password label and field
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        adminPanel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        adminPanel.add(passwordField, constraints);

        //Add error Label
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED); // Set the text color to red
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        adminPanel.add(errorLabel, constraints);

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(142, 68, 173));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        adminPanel.add(loginButton, constraints);

        // Add previous page button
        JButton previousPageButton = new JButton("Previous Page");
        previousPageButton.setBackground(new Color(142, 68, 173)); // Set purple color
        previousPageButton.setForeground(Color.WHITE);
        previousPageButton.setFocusPainted(false);
        previousPageButton.setFont(previousPageButton.getFont().deriveFont(Font.BOLD));
        previousPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrationGUI registrationGUI = new RegistrationGUI();
                registrationGUI.setVisible(true);
                dispose();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        adminPanel.add(previousPageButton, constraints);

        // Set grid height and weight to make the gray box bigger
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.weighty = 3.0;
        constraints.weightx = 3.0;
        adminPanel.add(new JPanel(), constraints);

        add(adminPanel);

        // Add key listener for Enter key
        EnterKeyListener enterKeyListener = new EnterKeyListener();
        idField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
    }

    // Create method to check if the ID and password match
    public void login() {
        String username = idField.getText();
        String password = new String(passwordField.getPassword());

        // Add your authentication logic here
        // For example, you can check if the user id and password match a database record
        if (username.equals("admin") && password.equals("123")) {
            adminPanel.setVisible(false);

            // Bring the page to admin home
            AdminHomeGUI adminHomeGUI = new AdminHomeGUI();
            adminHomeGUI.setVisible(true);

            // Close this window
            dispose();
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    private class EnterKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.getSource() == idField) {
                    passwordField.requestFocus(); // Move focus to passwordField
                } else if (e.getSource() == passwordField) {
                    login();// Perform login
                }
            }
        }
    }
}
