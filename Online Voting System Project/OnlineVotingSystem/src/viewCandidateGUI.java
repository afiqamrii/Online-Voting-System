import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class viewCandidateGUI extends JFrame {
    private JPanel viewCandidatePanel;
    private JTextArea candidateInfoTextArea;
    private CandidateData candidateData;
    private JButton previousPageButton;

    public viewCandidateGUI(CandidateData candidateData) {
        this.candidateData = candidateData;

        setTitle("View Candidate");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        viewCandidatePanel = new JPanel(new BorderLayout());
        candidateInfoTextArea = new JTextArea();
        candidateInfoTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(candidateInfoTextArea);
        viewCandidatePanel.add(scrollPane, BorderLayout.CENTER);

        previousPageButton = new JButton("Previous Page");
        previousPageButton.setOpaque(true);
        previousPageButton.setBackground(new Color(69, 49, 120)); // Set the background color
        previousPageButton.setForeground(new Color(255, 255, 255)); // Set the foreground (text) color to white
        previousPageButton.setPreferredSize(new Dimension(160, 35));
        previousPageButton.setFont(new Font(previousPageButton.getFont().getName(), Font.BOLD, 16));
        previousPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Bring the page back to the admin home if user press previous page button
                AdminHomeGUI adminHomeGUI = new AdminHomeGUI();
                adminHomeGUI.setVisible(true);

                //Close this page
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Add empty border for spacing
        buttonPanel.add(previousPageButton);
        viewCandidatePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(viewCandidatePanel);
    }

    public void displayCandidateInfo() {
        ArrayList<String[]> people = candidateData.getPeople();
        ArrayList<String[]> furtherInfo = candidateData.getFurtherInfo();

        StringBuilder info = new StringBuilder();
        for (int i = 0; i < people.size(); i++) {
            String[] person = people.get(i);
            String[] infoDetails = furtherInfo.get(i);

            //Display candidate information
            info.append("Name: ").append(infoDetails[0]).append("\n");
            info.append("Surname:").append(person[0]).append("\n");
            info.append("Faculty: ").append(infoDetails[1]).append("\n");
            info.append("Candidate Number : ").append(person[2]).append("\n");
            info.append("Further Info: ").append("\n").append(infoDetails[3]).append("\n\n");
        }
        candidateInfoTextArea.setText(info.toString());
    }
}
