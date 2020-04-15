import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Tsvetoslav Dimov
 * @date 15/04/2020
 * @studentID 20077038
 * @description Server graphical user interface.
 * It is created with Java Swing components and is used by the ServerController.
 */
public class ServerView {

    // Server UI components.
    private static final JTextArea logsTextArea = new JTextArea();
    private static final JScrollPane displayPane = new JScrollPane(logsTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );

    // Constructor for the ServerView class. Used by the ServerController.
    public ServerView() {
        JFrame frame = new JFrame("Server"); // Create frame object with title.
        frame.setSize(500, 500); // Frame measurements.
        frame.setLayout(null); // Do not apply any layouts.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminate the process on exit.
        frame.setResizable(false); // Prevent resizing of the frame.

        displayPane.setSize(500, 475);
        logsTextArea.setSize(450, 450);
        logsTextArea.setEditable(false); // Prevent editing the text.

        // Display the components.
        frame.add(displayPane);
        frame.setVisible(true);
    }

    // Getter methods.
    public JTextArea getLogsTextArea() {
        return logsTextArea;
    }
}
