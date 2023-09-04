import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.border.EmptyBorder;

public class VoteResultGUI extends JFrame {

    private JTextArea resultTextArea;

    public VoteResultGUI() {
        super("Vote Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Vote Results");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        resultTextArea = new JTextArea();
        resultTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        resultTextArea.setEditable(false);
        resultTextArea.setMargin(new Insets(10, 10, 10, 10)); // Add margin
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        JButton previousPageButton = new JButton("Previous Page");
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
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Map<String, Integer> getVoteCount() {
        Map<String, Integer> voteCount = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("countVote.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (voteCount.containsKey(line)) {
                    voteCount.put(line, voteCount.get(line) + 1);
                } else {
                    voteCount.put(line, 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return voteCount;
    }

    public void displayVoteResults() {
        Map<String, Integer> voteCount = getVoteCount();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : voteCount.entrySet()) {
            String candidateNumber = entry.getKey();
            int count = entry.getValue();
            sb.append("Candidate Number : ").append(candidateNumber)
                    .append("\n\nTotal Votes: ").append(count).append("\n\n");
        }
        resultTextArea.setText(sb.toString());
    }
}
