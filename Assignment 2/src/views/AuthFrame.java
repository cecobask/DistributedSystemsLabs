package views;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class AuthFrame {

    // Declare Swing components.
    private JFrame authFrame;
    private JPanel panel;
    private JLabel studentIdLabel;
    private JTextField studentIdField;
    private JButton submitButton;
    private JTextArea feedbackArea;

    /**
     * Object constructor, used for generating instances of AuthFrame.
     * @param title The title of AuthFrame object.
     */
    public AuthFrame(String title) {
        authFrame = new JFrame(title); // Set frame title.

        initComponents();
        addComponents(new JComponent[]{studentIdLabel, studentIdField, submitButton});

        authFrame.add(panel, BorderLayout.NORTH); // Add panel that represents the first line in the frame.
        panel = new JPanel(); // Reuse JPanel object to create an empty panel.
        panel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);

        // Frame specific settings.
        authFrame.setSize(new Dimension(500, 300));
        authFrame.add(panel); // Add panel that represents the feedback area.
        authFrame.pack();
        authFrame.setVisible(true);
        authFrame.setResizable(false); // Prevent resizing frame window.
        authFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise all Swing components used by the frame.
     */
    private void initComponents() {
        studentIdLabel = new JLabel("Student ID:");
        studentIdField = new JTextField(20);
        submitButton = new JButton("SUBMIT");
        feedbackArea = new JTextArea(20, 50);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }

    /**
     * Adds the passed in components to the panel.
     * @param components ArrayList of Swing components.
     */
    private void addComponents(JComponent[] components) {
        // Loop over the ArrayList of components and add to panel.
        for (JComponent component : components) {
            panel.add(component);
        }
    }

    public JTextField getStudentIdField() {
        return studentIdField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextArea getFeedbackArea() {
        return feedbackArea;
    }

    public JFrame getAuthFrame() {
        return authFrame;
    }
}
