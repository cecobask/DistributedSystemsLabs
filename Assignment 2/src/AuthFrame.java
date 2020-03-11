import javax.swing.*;
import java.awt.*;

public class AuthFrame {

    // Declare Swing components.
    private JPanel panel;
    private JLabel studentIdLabel;
    private JTextField studentIdField;
    private JButton submitButton;
    private JTextArea feedbackArea;

    public AuthFrame(String title) {
        JFrame frame = new JFrame(title); // Set frame title.
        frame.setSize(new Dimension(450, 300));
        frame.setResizable(false); // Prevent resizing frame window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents( // Dynamically add components to the panel.
                panel,
                new JComponent[]{studentIdLabel, studentIdField, submitButton}
        );
        frame.add(panel, BorderLayout.NORTH); // Add panel that represents the first line in the frame.

        panel = new JPanel(); // Reuse JPanel object to create an empty panel.
        panel.add(feedbackArea);
        frame.add(panel); // Add panel that represents the feedback area.
        frame.pack();
        frame.setVisible(true);
    }

    private void initComponents() {
        studentIdLabel = new JLabel("Student ID:");
        studentIdField = new JTextField(20);
        submitButton = new JButton("SUBMIT");
        feedbackArea = new JTextArea(15, 40);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    }

    private void addComponents(JPanel panel, JComponent[] components) {
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
}
