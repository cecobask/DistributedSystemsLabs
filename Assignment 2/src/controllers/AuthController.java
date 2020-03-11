package controllers;

import db.StudentService;
import models.Student;
import views.AuthFrame;
import views.ClientFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthController implements ActionListener, DocumentListener {

    private JTextField studentIdField;
    private JButton submitButton;
    private JTextArea feedbackArea;
    private JFrame authFrame;
    private ArrayList<Student> students = new ArrayList<>();
    private final StudentService studentService = new StudentService();

    public AuthController(AuthFrame frame) {
        initComponents(frame);
        submitButton.addActionListener(this); // Add click listener to the button.
        studentIdField.getDocument().addDocumentListener(this); // Listen for text changes.
        loadData();
    }

    private void initComponents(AuthFrame frame) {
        studentIdField = frame.getStudentIdField();
        submitButton = frame.getSubmitButton();
        submitButton.setEnabled(false); // Disabled by default.
        feedbackArea = frame.getFeedbackArea();
        authFrame = frame.getAuthFrame();
    }

    private void loadData() {
        students = studentService.getAllStudents();

        if (students.isEmpty()) {
            submitButton.setEnabled(false);
            showMessageDialog("There are no students in the database! Add new records and restart the application.");
            return;
        }

        feedbackArea.append("> The application is ready!\n" +
                "> Please, enter your student number in the text box above to proceed.\n"
        );
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            int studentID = Integer.parseInt(studentIdField.getText());
            List<Integer> studentIDs = students.stream() // Create a list with student IDs.
                    .map(Student::getStudID)
                    .collect(Collectors.toList());

            // Check if the student ID exists.
            if (studentIDs.contains(studentID)) {
                feedbackArea.append("> Successful authentication!\n");
                authFrame.dispose(); // Close the Authentication window.
                ClientFrame clientFrame = new ClientFrame("Client"); // Open the Client window.
                new ClientController(clientFrame, studentService.getStudentByStudID(studentID));
            } else {
                feedbackArea.append("> Failed to authenticate! This student number is not in the database.\n");
            }

            studentIdField.setText("");
        } catch (NumberFormatException e) {
            showMessageDialog("Student IDs are of numeric format! Please enter a valid student number.");
            studentIdField.setText("");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) { handleChange(documentEvent); }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) { handleChange(documentEvent); }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {}

    private void handleChange(DocumentEvent e) {
        // Disables the button if studentIdField is empty.
        submitButton.setEnabled(e.getDocument().getLength() != 0);
    }
}
