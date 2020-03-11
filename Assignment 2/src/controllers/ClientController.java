package controllers;

import models.Student;
import views.ClientFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientController implements ActionListener, DocumentListener {

    private JTextField circleRadiusField;
    private JTextArea feedbackArea;
    private JButton sendButton;
    private Student student;
    private DataOutputStream toServer;

    public ClientController(ClientFrame frame, Student student) {
        initComponents(frame);
        this.student = student;

        feedbackArea.append("> Welcome, " + student.getFirstName() + " " + student.getLastName() + "!\n");

        sendButton.addActionListener(this); // Add click listener to the button.
        circleRadiusField.getDocument().addDocumentListener(this); // Listen for text changes.

        setupServerConnection();
    }

    private void initComponents(ClientFrame frame) {
        circleRadiusField = frame.getCircleRadiusField();
        feedbackArea = frame.getFeedbackArea();
        sendButton = frame.getSendButton();
        sendButton.setEnabled(false); // Disabled by default.
    }

    private void setupServerConnection() {
        try {
            // Create a socket to connect to the server.
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server.
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server.
            toServer = new DataOutputStream(socket.getOutputStream());
            feedbackArea.append("> Connected to localhost:" + socket.getPort() + " on " + new Date() + "\n" +
                    "> Please, enter radius in the text box above to find out the area of the circle.\n");

            while (true) {
                // Receive message from the server.
                String message = fromServer.readUTF();

                if (!message.isEmpty()) feedbackArea.append("[server]: " + message + "\n");
            }
        } catch (IOException ex) {
            feedbackArea.append("> " + ex.getLocalizedMessage() + '\n');
        }
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            // Send the circle's radius to the server.
            double circleRadius = Double.parseDouble(circleRadiusField.getText().trim());
            toServer.writeDouble(circleRadius);
            toServer.flush();
            feedbackArea.append("[client]: " + circleRadius + "\n");
            circleRadiusField.setText("");
        } catch (NumberFormatException e) {
            showMessageDialog("Circle's radius must be of numeric format! Please enter a number.");
            circleRadiusField.setText("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        handleChange(documentEvent);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        handleChange(documentEvent);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
    }

    private void handleChange(DocumentEvent e) {
        // Disables the button if studentIdField is empty.
        sendButton.setEnabled(e.getDocument().getLength() != 0);
    }
}
