package views;

import javax.swing.*;
import java.awt.*;

public class ClientFrame {

    // Declare Swing components.
    private JFrame clientFrame;
    private JPanel panel;
    private JLabel circleRadiusLabel;
    private JTextField circleRadiusField;
    private JTextArea feedbackArea;
    private JButton sendButton;

    public ClientFrame(String title) {
        clientFrame = new JFrame(title); // Set frame title.

        initComponents();
        addComponents(new JComponent[]{circleRadiusLabel, circleRadiusField, sendButton});

        clientFrame.add(panel, BorderLayout.NORTH); // Add panel that represents the first line in the frame.
        panel = new JPanel(); // Reuse JPanel object to create an empty panel.
        panel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        clientFrame.setSize(new Dimension(500, 300));
        clientFrame.add(panel);
        clientFrame.pack();
        clientFrame.setVisible(true);
        clientFrame.setResizable(false); // Prevent resizing frame window.
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        circleRadiusLabel = new JLabel("Circle radius:");
        circleRadiusField = new JTextField(20);
        feedbackArea = new JTextArea(20, 50);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        sendButton = new JButton("SEND");
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }

    private void addComponents(JComponent[] components) {
        // Loop over the ArrayList of components and add to panel.
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    public JFrame getClientFrame() {
        return clientFrame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getCircleRadiusField() {
        return circleRadiusField;
    }

    public JTextArea getFeedbackArea() {
        return feedbackArea;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}
