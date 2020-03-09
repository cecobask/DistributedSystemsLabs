package ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends JFrame implements ActionListener {

    private JTextArea jta = new JTextArea();

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JButton exitBtn = new JButton("EXIT");
        p.add(exitBtn, BorderLayout.WEST);
        p.add(new JScrollPane(jta), BorderLayout.CENTER);
        JTextField jtf = new JTextField();
        p.add(jtf, BorderLayout.PAGE_END);
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);

        exitBtn.addActionListener(this);

        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a server socket.
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("> Server started on " + new Date() + '\n');

            while (true) {
                // Listen for connection requests.
                Socket socket = serverSocket.accept();
                jta.append("> Connected to a new client#localhost:" + socket.getPort() + ")\n");

                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private class ClientThread extends Thread {
        //The socket the client is connected through
        private Socket socket;
        //The input and output streams to the client
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;

        // The Constructor for the client
        public ClientThread(Socket socket) throws IOException {
            this.socket = socket;
            inputFromClient = new DataInputStream(socket.getInputStream());
            outputToClient = new DataOutputStream(socket.getOutputStream());
        }

        public void run() {
            try {
                while (true) {
                    // Receive messages from the client.
                    String message = inputFromClient.readUTF();

                    if (!message.isEmpty()) jta.append("[client#localhost:" + socket.getPort() + "]: " + message + "\n");

                    // TODO: replace with area of a circle.
                    outputToClient.writeUTF("hello! test");
                }
            } catch (Exception e) {
                System.err.println(e + " on " + socket);
            }
        }
    }
}