package views;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class ServerFrame {

    // Declare Swing components.
    private JPanel panel;
    private JTextArea feedbackArea;

    public ServerFrame(String title) {
        JFrame serverFrame = new JFrame(title); // Set frame title.

        initComponents();

        // Frame specific settings.
        serverFrame.setSize(new Dimension(500, 300));
        serverFrame.add(panel);
        serverFrame.pack();
        serverFrame.setVisible(true);
        serverFrame.setResizable(false); // Prevent resizing frame window.
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialise all Swing components used by the frame.
     */
    private void initComponents() {
        feedbackArea = new JTextArea(20, 50);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JScrollPane(feedbackArea));
    }

    public JTextArea getFeedbackArea() {
        return feedbackArea;
    }
}
