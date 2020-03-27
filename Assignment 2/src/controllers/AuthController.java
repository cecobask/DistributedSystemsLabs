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

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class AuthController implements ActionListener, DocumentListener {

    // Class variables.
    private JTextField studentIdField;
    private JButton submitButton;
    private JTextArea feedbackArea;
    private JFrame authFrame;
    private ArrayList<Student> students = new ArrayList<>();
    private final StudentService studentService = new StudentService();

    /**
     * Object constructor, used for generating instances of AuthController.
     * @param frame AuthFrame linked to this controller.
     */
    public AuthController(AuthFrame frame) {
        initComponents(frame);
        submitButton.addActionListener(this); // Add click listener to the button.
        studentIdField.getDocument().addDocumentListener(this); // Listen for text changes.
        loadData();
    }

    /**
     * Initialise all Swing components used by the frame.
     * @param frame AuthFrame
     */
    private void initComponents(AuthFrame frame) {
        studentIdField = frame.getStudentIdField();
        submitButton = frame.getSubmitButton();
        submitButton.setEnabled(false); // Disabled by default.
        feedbackArea = frame.getFeedbackArea();
        authFrame = frame.getAuthFrame();
    }

    /**
     * Load Student objects from the database.
     */
    private void loadData() {
        students = studentService.getAllStudents(); // StudentService class instance.

        if (students.isEmpty()) { // No students in the database.
            submitButton.setEnabled(false); // Disable button.
            showMessageDialog("There are no students in the database! Add new records and restart the application.");
            return;
        }

        // There are students in the database.
        feedbackArea.append("> The application is ready!\n" +
                "> Please, enter your student number in the text box above to proceed.\n"
        );
    }

    /**
     * Helper method that displays a message dialog to the user. Reduces boilerplate code.
     * @param message String
     */
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Handle click events.
     * @param actionEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            // Get input from the user and convert it to an integer.
            int studentID = Integer.parseInt(studentIdField.getText());
            List<Integer> studentIDs = students.stream() // Create a list with student IDs.
                    .map(Student::getStudID)
                    .collect(Collectors.toList());

            // Check if the student ID exists.
            if (studentIDs.contains(studentID)) {
                feedbackArea.append("> Successful authentication!\n");
                authFrame.dispose(); // Close the Authentication window.
                // Open the Client window using a Thread.
                ClientFrame clientFrame = new ClientFrame("Client");
                new Thread(() -> new ClientController(clientFrame, studentService.getStudentByStudID(studentID))).start();
            } else { // Student ID doesn't exist in the database.
                feedbackArea.append("> Failed to authenticate! This student number is not in the database.\n");
            }

            studentIdField.setText(""); // Clear input box.
        } catch (NumberFormatException e) { // Student ID was not of integer format.
            showMessageDialog("Student IDs are of numeric format! Please enter a valid student number.");
            studentIdField.setText("");
        }
    }

    /**
     * Triggered when the user inputs text.
     * @param documentEvent DocumentEvent
     */
    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        handleChange(documentEvent);
    }

    /**
     * Triggered when the user deletes input text.
     * @param documentEvent DocumentEvent
     */
    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        handleChange(documentEvent);
    }

    /**
     * Unused but had to override it.
     * @param documentEvent DocumentEvent
     */
    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
    }

    /**
     * Disables the submit button if the text box is empty.
     * Enables the submit button if the text box is not empty.
     * @param e DocumentEvent
     */
    private void handleChange(DocumentEvent e) {
        // Disables the button if studentIdField is empty.
        submitButton.setEnabled(e.getDocument().getLength() != 0);
    }
}
