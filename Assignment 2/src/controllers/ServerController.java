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

public class ServerController {

    private JTextArea feedbackArea;

    public ServerController(ServerFrame frame) {
        initComponents(frame);
        setupSocket();
    }

    private void initComponents(ServerFrame frame) {
        feedbackArea = frame.getFeedbackArea();
    }

    private void setupSocket() {
        try {
            // Create a server socket.
            ServerSocket serverSocket = new ServerSocket(8000);
            feedbackArea.append("> Server started on " + new Date() + '\n');

            while (true) {
                // Listen for connection requests.
                Socket socket = serverSocket.accept();
                feedbackArea.append("> Client #localhost:" + socket.getPort() + " established a connection.\n");

                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class ClientThread extends Thread {
        private Socket socket; // The socket the client is connected through.
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;

        public ClientThread(Socket socket) throws IOException {
            this.socket = socket;
            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());
        }

        public void run() {
            try {
                while (true) {
                    // Receive messages from the client.
                    double radius = inputFromClient.readDouble();
                    double area = radius * radius * Math.PI;
                    DecimalFormat df = new DecimalFormat("0.000"); // Format double to 2 decimal places.
                    feedbackArea.append("[client#localhost:" + socket.getPort() + "]: " + radius + "\n");
                    outputToClient.writeUTF("The area of a circle with radius " + radius + " is " + df.format(area) + ".\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
