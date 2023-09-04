import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class VoterGUI extends JFrame implements ActionListener {
    private JRadioButton[] buttons;
    private JButton voteButton;
    private ButtonGroup group;
    private JTextArea infoTextArea;
    private CandidateData candidateData;
    private JLabel imageLabels; // Array to store image labels
    private JPanel imagePanel;
    private VoteCounter voteCounter;

    public VoterGUI(CandidateData candidateData) {
        super("Voting Your Leader");
        this.candidateData = candidateData;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        setLayout(new BorderLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Voting Your Leader");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.YELLOW);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        add(contentPanel, BorderLayout.CENTER);

        JPanel candidatePanel = new JPanel();
        candidatePanel.setLayout(new BoxLayout(candidatePanel, BoxLayout.Y_AXIS));
        candidatePanel.setBackground(Color.PINK);
        contentPanel.add(candidatePanel, BorderLayout.WEST);

        ArrayList<String[]> people = candidateData.getPeople();

        buttons = new JRadioButton[people.size()];
        group = new ButtonGroup();

        for (int i = 0; i < buttons.length; i++) {
            String[] person = people.get(i);
            String buttonText = "<html><b>Surname :</b> " + person[0] + "<br><b>Faculty :</b> " + person[1] + "<br><b>Candidate Number :</b> " + person[2] + "<br><br>-----------------------------------" + "</html>";
            buttons[i] = new JRadioButton(buttonText);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttons[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            buttons[i].setBackground(Color.PINK); // Set button background color
            buttons[i].addActionListener(this);
            group.add(buttons[i]);
            candidatePanel.add(buttons[i]);
        }

        infoTextArea = new JTextArea();
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);
        infoTextArea.setEditable(false);
        infoTextArea.setMargin(new Insets(10, 10, 10, 10)); // Add margin
        contentPanel.add(infoTextArea, BorderLayout.CENTER);

        imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(Color.WHITE);
        contentPanel.add(imagePanel, BorderLayout.EAST);

        // Add vote button to the bottom of the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        // Add vote button
        voteButton = new JButton("Vote");
        voteButton.setOpaque(true);
        voteButton.setBackground(new Color(69, 49, 120)); // Set the background color
        voteButton.setForeground(new Color(255, 255, 255)); // Set the foreground (text) color to white
        voteButton.setPreferredSize(new Dimension(180, 40));
        voteButton.setFont(new Font(voteButton.getFont().getName(), Font.PLAIN, 20));
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ask confirmation to vote
                int choice = JOptionPane.showConfirmDialog(
                        VoterGUI.this,
                        "Are you sure to vote this candidate ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    // Return to Main Page / Registration page
                    LoginVoterGUI loginVoterGUI = new LoginVoterGUI();
                    loginVoterGUI.setVisible(true);

                    // Close this page
                    dispose();
                }
            }
        });

        buttonPanel.add(voteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        voteCounter = new VoteCounter(); // Initialize the VoteCounter
    }

    private void saveVoteToFile(String candidateNumber) {
        //Save to the text file total of all the vote
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("countVote.txt", true))) {
            writer.write(candidateNumber);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String[]> people = candidateData.getPeople();
        ArrayList<String[]> furtherInfo = candidateData.getFurtherInfo();

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                String[] person = people.get(i);
                String[] personInfo = furtherInfo.get(i);

                String infoText = "\nSelected Candidate :\n\nCandidate Number: " + person[2];

                String furtherInfoText = "\n\nFurther Information:\n\n" + "Name: " + personInfo[0]
                        + "\nFaculty: " + personInfo[1] + "\nYear Of Study: " + personInfo[2]
                        + "\n\nManifesto: \n" + personInfo[3];

                // Update the infoTextArea with the selected candidate's information
                infoTextArea.setText(infoText + furtherInfoText);

                // Load and display the candidate's image
                ImageIcon icon = new ImageIcon(candidateData.getCandidateImageFileName(i));
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(409, 572, Image.SCALE_SMOOTH); // Scale image
                icon.setImage(scaledImg);

                imagePanel.removeAll(); // Clear previous image
                imageLabels = new JLabel(icon); // Create new image label
                imagePanel.add(imageLabels); // Add image label to the image panel
                imagePanel.revalidate(); // Refresh the image panel

                // Update the vote count using the VoteCounter
                String candidateNumber = person[2];
                voteCounter.countVote(candidateNumber);

                // Save the vote to the text file
                saveVoteToFile(candidateNumber);

                break;
            }
        }
    }
}