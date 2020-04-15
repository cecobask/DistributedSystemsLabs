import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Tsvetoslav Dimov
 * @date 15/04/2020
 * @studentID 20077038
 * @description Client graphical user interface.
 * It is created with Java Swing components and is used by the ClientController.
 */
public class ClientView {

    // Calculator control components.
    private final JButton multiplyButton = new JButton("*");
    private final JButton divideButton = new JButton("/");
    private final JButton addButton = new JButton("+");
    private final JButton subtractButton = new JButton("-");
    private final JButton zeroButton = new JButton("0");
    private final JButton oneButton = new JButton("1");
    private final JButton twoButton = new JButton("2");
    private final JButton threeButton = new JButton("3");
    private final JButton fourButton = new JButton("4");
    private final JButton fiveButton = new JButton("5");
    private final JButton sixButton = new JButton("6");
    private final JButton sevenButton = new JButton("7");
    private final JButton eightButton = new JButton("8");
    private final JButton nineButton = new JButton("9");
    private final JButton submitButton = new JButton("Submit");
    private final JButton clearButton = new JButton("Clear");
    private static final JTextArea userInputText = new JTextArea();

    // UI component panels.
    private static final JPanel calculationPanel = new JPanel(); // User input.
    private static final JPanel controlPanel = new JPanel(); // Calculator controls.
    private static final JPanel resultPanel = new JPanel(); // Calculation result and server messages.

    // Result UI components.
    private static final JTextArea resultTextArea = new JTextArea();
    private static final JScrollPane displayPane = new JScrollPane(resultTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );

    // Constructor for the ClientView class. Used by the ClientController.
    public ClientView() {
        JFrame frame = new JFrame("Calculator"); // Create frame object with title.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminate the process on exit.
        frame.setSize(450, 500); // Frame measurements.
        frame.setResizable(false); // Prevent resizing of the frame.

        // Set measurements for individual panels and components.
        calculationPanel.setSize(450, 100);
        calculationPanel.setLayout(new FlowLayout());
        controlPanel.setSize(450, 300);
        controlPanel.setLayout(new GridLayout(4, 4));
        resultPanel.setSize(450, 200);
        resultPanel.setLayout(new FlowLayout());
        userInputText.setPreferredSize(new Dimension(440, 140));
        displayPane.setPreferredSize(new Dimension(440, 140));

        // Add components to the relevant panels.
        controlPanel.add(divideButton);
        controlPanel.add(sevenButton);
        controlPanel.add(eightButton);
        controlPanel.add(nineButton);
        controlPanel.add(multiplyButton);
        controlPanel.add(fourButton);
        controlPanel.add(fiveButton);
        controlPanel.add(sixButton);
        controlPanel.add(subtractButton);
        controlPanel.add(oneButton);
        controlPanel.add(twoButton);
        controlPanel.add(threeButton);
        controlPanel.add(addButton);
        controlPanel.add(zeroButton);
        controlPanel.add(submitButton);
        controlPanel.add(clearButton);
        resultPanel.add(displayPane);
        calculationPanel.add(userInputText);

        // Add all panels to the frame.
        frame.add(calculationPanel, BorderLayout.NORTH); // Aligned to the north.
        frame.add(controlPanel, BorderLayout.CENTER); // Center aligned.
        frame.add(resultPanel, BorderLayout.SOUTH); // Aligned to the south.
        frame.setLayout(new GridLayout(3, 1)); // Layout with 3 rows and 1 column.
        frame.setVisible(true); // Display everything.

        // Prevent editing the text.
        userInputText.setEditable(false);
        resultTextArea.setEditable(false);

        submitButton.setEnabled(false); // Disable the 'submit' button.
    }

    // Displays the received text message.
    public void displayResult(String result) {
        resultTextArea.append(result + "\n");
    }

    // Getter methods.
    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getZeroButton() {
        return zeroButton;
    }

    public JButton getOneButton() {
        return oneButton;
    }

    public JButton getTwoButton() {
        return twoButton;
    }

    public JButton getThreeButton() {
        return threeButton;
    }

    public JButton getFourButton() {
        return fourButton;
    }

    public JButton getFiveButton() {
        return fiveButton;
    }

    public JButton getSixButton() {
        return sixButton;
    }

    public JButton getSevenButton() {
        return sevenButton;
    }

    public JButton getEightButton() {
        return eightButton;
    }

    public JButton getNineButton() {
        return nineButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getUserInputText() {
        return userInputText;
    }

    public JTextArea getResultTextArea() {
        return resultTextArea;
    }
}
