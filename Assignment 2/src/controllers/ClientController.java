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

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class ClientController implements ActionListener, DocumentListener {

    // Class variables.
    private JTextField circleRadiusField;
    private JTextArea feedbackArea;
    private JButton sendButton;
    private DataOutputStream toServer;

    /**
     * Object constructor, used for generating instances of ClientController.
     * @param frame ClientFrame linked to this controller.
     */
    public ClientController(ClientFrame frame, Student student) {
        initComponents(frame);

        // Display a welcome message.
        feedbackArea.append("> Welcome, " + student.getFirstName() + " " + student.getLastName() + "!\n");

        sendButton.addActionListener(this); // Add click listener to the button.
        circleRadiusField.getDocument().addDocumentListener(this); // Listen for text changes.

        setupServerConnection(); // Connect to the server socket.
    }

    /**
     * Initialise all Swing components used by the frame.
     * @param frame ClientFrame
     */
    private void initComponents(ClientFrame frame) {
        circleRadiusField = frame.getCircleRadiusField();
        feedbackArea = frame.getFeedbackArea();
        sendButton = frame.getSendButton();
        sendButton.setEnabled(false); // Disabled by default.
    }

    /**
     * Connect to the server socket.
     */
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

                // Display the received message.
                if (!message.isEmpty()) feedbackArea.append("[server]: " + message + "\n");
            }
        } catch (IOException ex) { // Handle failure to connect to the server socket.
            feedbackArea.append("> " + ex.getLocalizedMessage() + '\n');
        }
    }

    /**
     * Handle click events.
     * @param actionEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            // Send the circle's radius to the server.
            double circleRadius = Double.parseDouble(circleRadiusField.getText().trim()); // Parse input to Double.
            toServer.writeDouble(circleRadius); // Send data to the server.
            toServer.flush();
            feedbackArea.append("[client]: " + circleRadius + "\n"); // Display a message.
            circleRadiusField.setText(""); // Clear input box.
        } catch (NumberFormatException e) { // Input was not of Double format.
            JOptionPane.showMessageDialog(
                    null,
                    "A circle's radius must be of numeric format! Please enter a number."
            );
            circleRadiusField.setText(""); // Clear input box.
        } catch (IOException ex) {
            ex.printStackTrace();
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
        sendButton.setEnabled(e.getDocument().getLength() != 0);
    }
}
