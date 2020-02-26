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

    private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();
    private JButton sendBtn = new JButton("SEND");

    private DataOutputStream toClient;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(sendBtn, BorderLayout.EAST);
        JButton exitBtn = new JButton("EXIT");
        p.add(exitBtn, BorderLayout.WEST);
        p.add(new JScrollPane(jta), BorderLayout.CENTER);
        p.add(jtf, BorderLayout.PAGE_END);
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);

        sendBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setTitle("Ex1.Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a server socket.
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("> Ex1.Server started on " + new Date() + '\n');

            // Listen for a connection request.
            Socket socket = serverSocket.accept();
            jta.append("> Connected to the client.\n");

            // Create data input and output streams.
            DataInputStream fromClient = new DataInputStream(
                    socket.getInputStream());
            toClient = new DataOutputStream(
                    socket.getOutputStream());

            while (true) {
                // Receive message from the client.
                String message = fromClient.readUTF();

                if (!message.isEmpty()) jta.append("[client]: " + message + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendBtn) {
            try {
                // Get the text from jtf.
                String message = jtf.getText().trim();

                if (message.isEmpty()) return;

                // Send the message to the client.
                toClient.writeUTF(message);
                toClient.flush();

                jta.append("[server]: " + message + "\n");
                jtf.setText("");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                jta.append("> Not connected to the client.\n");
            }
            jtf.setText(""); // Clear input box.
        } else {
            System.exit(0);
        }
    }
}