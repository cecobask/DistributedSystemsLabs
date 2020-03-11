package views;

import javax.swing.*;
import java.awt.*;

public class ServerFrame {

    // Declare Swing components.
    private JFrame serverFrame;
    private JPanel panel;
    private JTextArea feedbackArea;

    public ServerFrame(String title) {
        serverFrame = new JFrame(title); // Set frame title.

        initComponents();

        serverFrame.setSize(new Dimension(500, 300));
        serverFrame.add(panel);
        serverFrame.pack();
        serverFrame.setVisible(true);
        serverFrame.setResizable(false); // Prevent resizing frame window.
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
