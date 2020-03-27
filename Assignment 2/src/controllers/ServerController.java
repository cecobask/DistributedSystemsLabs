package controllers;

import views.ServerFrame;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class ServerController {

    // Class variable.
    private JTextArea feedbackArea;

    /**
     * Object constructor, used for generating instances of ServerController.
     * @param frame ServerFrame linked to this controller.
     */
    public ServerController(ServerFrame frame) {
        initComponents(frame);
        setupSocket();
    }

    /**
     * Initialise all Swing components used by the frame.
     * @param frame ClientFrame
     */
    private void initComponents(ServerFrame frame) {
        feedbackArea = frame.getFeedbackArea();
    }

    /**
     * Create a ServerSocket object.
     */
    private void setupSocket() {
        try {
            // Create a server socket.
            ServerSocket serverSocket = new ServerSocket(8000);
            feedbackArea.append("> Server started on " + new Date() + '\n'); // Display a message.

            while (true) {
                // Listen for connection requests.
                Socket socket = serverSocket.accept();
                feedbackArea.append("> Client #localhost:" + socket.getPort() + " established a connection.\n");

                // When a request comes in, create a new Thread.
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handle every Client request as a new Thread.
     */
    private class ClientThread extends Thread {
        // Class variables.
        private Socket socket; // The socket the client is connected through.
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;

        /**
         * Object constructor, used for generating instances of ClientThread.
         * @param socket Server socket
         */
        public ClientThread(Socket socket) throws IOException {
            // Instantiate variables.
            this.socket = socket;
            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());
        }

        /**
         * Start the ClientThread.
         */
        public void run() {
            try {
                while (true) {
                    // Receive messages from the client.
                    double radius = inputFromClient.readDouble(); // Receive numeric value.
                    double area = radius * radius * Math.PI; // Calculate the area of a circle with given radius.
                    DecimalFormat df = new DecimalFormat("0.000"); // Format double to 2 decimal places.
                    // Display message.
                    feedbackArea.append("[client#localhost:" + socket.getPort() + "]: " + radius + "\n");
                    // Send text to the client.
                    outputToClient.writeUTF("The area of a circle with radius " + radius + " is " + df.format(area) + ".\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
