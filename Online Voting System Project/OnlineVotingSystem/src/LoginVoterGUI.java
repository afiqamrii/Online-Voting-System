import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginVoterGUI extends JFrame {
    private JTextField usernameField;
    private JPanel loginPanel;
    private JButton loginButton;
    private JLabel backLabel;
    private JPasswordField passwordField;
    private JLabel loginLabel;
    private JLabel messageLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel errorLabel;

    public LoginVoterGUI() {
        setTitle("University Malaysia of Terengganu Election");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        // Set background image
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.jpeg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Create a vertically curved white panel
        loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(45, 45); // Adjust the arc size as needed
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(Color.white); // Set the color of the curved panel
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // Draw the curved panel
            }
        };
        loginPanel.setOpaque(false); // Make the panel transparent
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(465, 520)); // Set the size of the curved panel

        loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font(loginLabel.getFont().getName(), Font.PLAIN, 48));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        loginPanel.add(loginLabel, constraints);

        usernameLabel = new JLabel("Student ID");
        usernameLabel.setForeground(new Color(69, 49, 120)); // Set the foreground color
        usernameLabel.setFont(new Font(usernameLabel.getFont().getName(), Font.PLAIN, 19));
        usernameLabel.setHorizontalAlignment(JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(usernameLabel, constraints);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 32));
        usernameField.setFont(new Font(usernameField.getFont().getName(), Font.PLAIN, 15));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(usernameField, constraints);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(69, 49, 120)); // Set the foreground color
        passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.PLAIN, 19));
        passwordLabel.setHorizontalAlignment(JLabel.LEFT);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 32));
        passwordField.setFont(new Font(passwordField.getFont().getName(), Font.PLAIN, 15));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(passwordField, constraints);

        messageLabel = new JLabel("StudentID is MatricNo & Password as myNemo.");
        messageLabel.setForeground(Color.gray); // Set the text color to red
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(messageLabel, constraints);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED); // Set the text color to red
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(errorLabel, constraints);

        loginButton = new JButton("Login");
        loginButton.setOpaque(true);
        loginButton.setBackground(new Color(69, 49, 120)); // Set the background color
        loginButton.setForeground(new Color(255, 255, 255)); // Set the foreground (text) color to white
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setFont(new Font(loginButton.getFont().getName(), Font.PLAIN, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 6; // Increment the grid y value
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Center the login button
        loginPanel.add(loginButton, constraints);

        //Add back button
        backLabel = new JLabel("Previous Page.. ");
        backLabel.setForeground(new Color(69, 49, 120)); // Set the foreground color
        backLabel.setFont(new Font(backLabel.getFont().getName(), Font.PLAIN, 17));
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Return to Registration page if this we're clicked
                RegistrationGUI registrationGUI = new RegistrationGUI();
                registrationGUI.setVisible(true);

                //Close this page
                dispose();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 7; // Increment the grid y value
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER; // Center the login button
        loginPanel.add(backLabel, constraints);


        add(loginPanel, constraints);

        // Add key listener for Enter key
        EnterKeyListener enterKeyListener = new EnterKeyListener();
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Add your authentication logic here
        // For example, you can check if the username and password match a database record
        if ((username.contains("S") || username.contains("s"))) { //The password we don't consider because that'll need link with UMT System , so we assume all password entered are true.
            this.setVisible(false);

            //Obj for parameter in VoterGUI
            CandidateData candidateData = new CandidateData();

            //Display voting GUI
            VoterGUI voterGUI = new VoterGUI(candidateData);

            //Close this window
            dispose();
        }
        else {
            messageLabel.setVisible(false);
            errorLabel.setText("Invalid username or password.");
        }
    }
    //Put key listener...
    private class EnterKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (e.getSource() == usernameField) {
                    passwordField.requestFocus(); // Move focus to passwordField
                } else if (e.getSource() == passwordField) {
                    login(); // Perform login
                }
            }
        }
    }
}
