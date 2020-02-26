package Ex2;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerEx2 extends JFrame {

  public static void main(String[] args) {
    new ServerEx2();
  }

  public ServerEx2() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    // Text area for displaying contents
    JTextArea jta = new JTextArea();
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("> Server started on " + new Date() + '\n');

      // Listen for a connection request
      Socket socket = serverSocket.accept();
      jta.append("> Connected to client.\n");

      // Create data input and output streams
      DataInputStream inputFromClient = new DataInputStream(
        socket.getInputStream());
      DataOutputStream outputToClient = new DataOutputStream(
        socket.getOutputStream());

      while (true) {
        // Receive message from the client.
        String message = inputFromClient.readUTF();

        if (!message.isEmpty()) jta.append("> Message from client: '" + message + "'\n");
        outputToClient.writeUTF("> Message received.\n");
      }
    }
    catch(IOException ex) {
      ex.printStackTrace();
    }
  }
}