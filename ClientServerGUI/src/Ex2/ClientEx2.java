package Ex2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientEx2 extends JFrame implements ActionListener {

    private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();
    private JButton exitBtn = new JButton("EXIT");
    private JButton sendBtn = new JButton("SEND");

    // IO streams
    private DataOutputStream toServer;
    private DataInputStream fromServer;

    public static void main(String[] args) {
        new ClientEx2();
    }

    public ClientEx2() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(sendBtn, BorderLayout.EAST);
        p.add(exitBtn, BorderLayout.WEST);
        p.add(new JScrollPane(jta), BorderLayout.CENTER);
        p.add(jtf, BorderLayout.PAGE_END);
        setLayout(new BorderLayout());
        add(p, BorderLayout.CENTER);

        sendBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setTitle("Client");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // It is necessary to show the frame here!

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
            jta.append("> Connected to localhost:8000 at " + new Date() + "\n");
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

                jta.append("> Sent message '" + message + "' to the server.\n");
                jta.append(fromServer.readUTF());
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex) {
                jta.append("> Not connected to the server.\n");
            }
        } else {
            System.exit(0);
        }
    }
}