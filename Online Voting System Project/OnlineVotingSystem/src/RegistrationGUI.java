import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegistrationGUI extends JFrame {
    private JPanel firstPanel;

    public RegistrationGUI() {
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
        constraints.insets = new Insets(1, 1, 1, 1);

        // Create a vertically curved white panel
        firstPanel = new JPanel() {
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
        firstPanel.setOpaque(false); // Make the panel transparent
        firstPanel.setLayout(new GridBagLayout());
        firstPanel.setPreferredSize(new Dimension(465, 520)); // Set the size of the curved panel

        JLabel loginLabel = new JLabel("What Are You Here For ?");
        loginLabel.setFont(new Font(loginLabel.getFont().getName(), Font.PLAIN, 30));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.NORTH; // Align the label to the top
        constraints.insets = new Insets(60, 0, 20, 0); // Add top and bottom padding
        firstPanel.add(loginLabel, constraints);

        JButton votersButton = new JButton("     Voters      ");
        votersButton.setOpaque(true);
        votersButton.setBackground(new Color(69, 49, 120)); // Set the background color
        votersButton.setForeground(new Color(255, 255, 255)); // Set the foreground (text) color to white
        votersButton.setPreferredSize(new Dimension(180, 40));
        votersButton.setFont(new Font(votersButton.getFont().getName(), Font.PLAIN, 20));
        votersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPanel.setVisible(false);

                // Display the new GUI here
                LoginVoterGUI loginVoterGUI = new LoginVoterGUI();
                loginVoterGUI.setVisible(true);

                //Close this window
                dispose();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 6; // Increment the grid y value
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.CENTER; // Center the voters button
        firstPanel.add(votersButton, constraints);

        //Add second button for administrator
        JButton adminButton = new JButton(" Administrator ");
        adminButton.setOpaque(true);
        adminButton.setBackground(new Color(69, 49, 120)); // Set the background color
        adminButton.setForeground(new Color(255, 255, 255)); // Set the foreground (text) color to white
        adminButton.setPreferredSize(new Dimension(180, 40));
        adminButton.setFont(new Font(adminButton.getFont().getName(), Font.PLAIN, 20));
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPanel.setVisible(false);

                // Display the new GUI here
                LoginAdministratorGUI loginAdministratorGUI = new LoginAdministratorGUI();
                loginAdministratorGUI.setVisible(true);

                //Close this window
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 12; // Increment the grid y value
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.CENTER; // Center the login button
        firstPanel.add(adminButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 15; // Increase the grid y value to create space
        constraints.gridwidth = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        firstPanel.add(Box.createVerticalStrut(20), constraints); // Add vertical spacing

        //Add to plainPanel
        add(firstPanel, constraints);
    }
}