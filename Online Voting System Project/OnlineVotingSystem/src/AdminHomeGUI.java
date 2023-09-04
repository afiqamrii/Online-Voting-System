import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomeGUI extends JFrame {
    public AdminHomeGUI() {
        setTitle("Admin Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("background.jpeg");
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        // Logo
        ImageIcon logoImage = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logoImage);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Set the grid width to span 2 columns
        panel.add(logoLabel, gbc);

        JButton viewCandidatesButton = new JButton("View Candidates");
        viewCandidatesButton.setPreferredSize(new Dimension(200, 50)); // Adjust button size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset the grid width to 1
        panel.add(viewCandidatesButton, gbc);

        JButton viewResultsButton = new JButton("View Results");
        viewResultsButton.setPreferredSize(new Dimension(200, 50)); // Adjust button size
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(viewResultsButton, gbc);

        JButton logoutButton = new JButton("Log Out");
        logoutButton.setPreferredSize(new Dimension(200, 50)); // Adjust button size
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(panel, gbc);

        // Action listener for "View Candidates" button
        viewCandidatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Open the Candidate class or perform desired action
                CandidateData candidateData = new CandidateData();
                viewCandidateGUI viewGUI = new viewCandidateGUI(candidateData);
                viewGUI.displayCandidateInfo();
                viewGUI.setVisible(true);

                //Close this page
                dispose();
            }
        });

        // Action listener for "View Results" button
        viewResultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Open the Candidate class or perform desired action
                VoteResultGUI viewGUI = new VoteResultGUI();
                viewGUI.displayVoteResults();
                viewGUI.setVisible(true);

                //Close this page
                dispose();
            }
        });

        // Action listener for "Log out" button
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Bring to the previous page
                LoginAdministratorGUI loginAdministratorGUI = new LoginAdministratorGUI();
                loginAdministratorGUI.setVisible(true);

                //Close this page
                dispose();
            }
        });
    }
}
