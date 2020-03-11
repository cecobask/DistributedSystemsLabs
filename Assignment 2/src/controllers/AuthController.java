package controllers;

import db.StudentService;
import models.Student;
import views.AuthFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthController implements ActionListener {

    private JTextField studentIdField;
    private JButton submitButton;
    private JTextArea feedbackArea;
    private ArrayList<Student> students = new ArrayList<>();
    private final StudentService studentService = new StudentService();

    public AuthController(AuthFrame frame) {
        initComponents(frame);
        submitButton.addActionListener(this); // Add click listener to the button.
        loadData();
    }

    private void initComponents(AuthFrame frame) {
        studentIdField = frame.getStudentIdField();
        submitButton = frame.getSubmitButton();
        feedbackArea = frame.getFeedbackArea();
    }

    private void loadData() {
        students = studentService.getAllStudents();

        if (students.isEmpty()) {
            submitButton.setEnabled(false);
            showMessageDialog("There are no students in the database! Add new records and restart the application.");
            return;
        }

        feedbackArea.append("The application is ready!\n" +
                "Please enter your student number in the text box above to proceed.\n"
        );
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String idInput = studentIdField.getText();
            List<Integer> studentIDs = students.stream() // Create a list with student IDs.
                    .map(Student::getStudID)
                    .collect(Collectors.toList());

            // Check if the student ID exists.
            if (studentIDs.contains(Integer.valueOf(idInput))) {
                feedbackArea.append("Successful authentication!\n");
            } else {
                feedbackArea.append("Failed to authenticate! This student number is not in the database.\n");
            }

            studentIdField.setText("");
        } catch (NumberFormatException e) {
            showMessageDialog("Student IDs are of numeric format! Please enter a valid student number.");
            studentIdField.setText("");
        }
    }
}
