package ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class Client extends Thread implements ActionListener {

    private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();
    private JButton sendBtn = new JButton("SEND");

    // IO streams
    private DataOutputStream toServer;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        JPanel p = new JPanel();
        JFrame frame = new JFrame();
        p.setLayout(new BorderLayout());
        p.add(sendBtn, BorderLayout.EAST);
        JButton exitBtn = new JButton("EXIT");
        p.add(exitBtn, BorderLayout.WEST);
        p.add(new JScrollPane(jta), BorderLayout.CENTER);
        p.add(jtf, BorderLayout.PAGE_END);
        frame.setLayout(new BorderLayout());
        frame.add(p, BorderLayout.CENTER);

        sendBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        frame.setTitle("Ex1.Client");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            DataInputStream fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
            jta.append("> Connected to localhost:8000 at " + new Date() + "\n");

            while (true) {
                // Receive message from the client.
                String message = fromServer.readUTF();

                if (!message.isEmpty()) jta.append("[server]: " + message + "\n");
            }
        } catch (IOException ex) {
            jta.append("> " + ex.toString() + '\n');
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendBtn) {
            try {
                // Get the text from jtf.
                String message = jtf.getText().trim();

                if (message.isEmpty()) return;

                // Send the message to the server.
                toServer.writeUTF(message);
                toServer.flush();

                jta.append("[client]: " + message + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                jta.append("> Not connected to the server.\n");
            }
            jtf.setText(""); // Clear input box.
        } else {
            System.exit(0);
        }
    }

    @Override
    public void run() {

    }
}