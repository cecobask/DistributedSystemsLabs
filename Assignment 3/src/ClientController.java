import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author Tsvetoslav Dimov
 * @date 15/04/2020
 * @studentID 20077038
 * @description Client controller class.
 * This class creates an instance of ClientView, adds action listeners to all button components.
 * Based on the buttons pressed, the ClientController sends requests to the ServerController, where the
 * relevant arithmetic operations are carried out.
 */
public class ClientController {

    // Class variables.
    private String numbers = "";
    private final ClientView clientView;
    private final JTextArea userInputText;

    // Instantiate the CalculatorInterface to null;
    private static CalculatorInterface calculatorInterface = null;

    public static void main(String[] args) {
        new ClientController();
    }

    // Constructor for the ClientController class.
    private ClientController() {
        clientView = new ClientView(); // Initialise the ClientView object.
        addActionListeners(); // Set on-click listeners to all button components.
        userInputText = clientView.getUserInputText(); // Retrieve the user input component.
    }

    // Set on-click listeners to all button components.
    private void addActionListeners() {
        // On click of any number button, append the relevant number to the userInputText component.
        clientView.getZeroButton().addActionListener(
                actionEvent -> userInputText.append("0")
        );
        clientView.getOneButton().addActionListener(
                actionEvent -> userInputText.append("1")
        );
        clientView.getTwoButton().addActionListener(
                actionEvent -> userInputText.append("2")
        );
        clientView.getThreeButton().addActionListener(
                actionEvent -> userInputText.append("3")
        );
        clientView.getFourButton().addActionListener(
                actionEvent -> userInputText.append("4")
        );
        clientView.getFiveButton().addActionListener(
                actionEvent -> userInputText.append("5")
        );
        clientView.getSixButton().addActionListener(
                actionEvent -> userInputText.append("6")
        );
        clientView.getSevenButton().addActionListener(
                actionEvent -> userInputText.append("7")
        );
        clientView.getEightButton().addActionListener(
                actionEvent -> userInputText.append("8")
        );
        clientView.getNineButton().addActionListener(
                actionEvent -> userInputText.append("9")
        );

        clientView.getClearButton().addActionListener(
                actionEvent -> {
                    userInputText.setText(""); // Clear the contents of userInputText component.

                    // Enable all arithmetic action buttons.
                    clientView.getDivideButton().setEnabled(true);
                    clientView.getMultiplyButton().setEnabled(true);
                    clientView.getAddButton().setEnabled(true);
                    clientView.getSubtractButton().setEnabled(true);
                    clientView.getSubmitButton().setEnabled(false); // Disable the 'submit' button.
                    numbers = ""; // Reset the variable 'numbers' to empty string.
                });

        clientView.getSubmitButton().addActionListener(
                actionEvent -> {
                    if (userInputText.getText().length() > 0) { // If the value of 'userInputText' is not empty.
                        try {
                            Integer.parseInt(userInputText.getText()); // Try to parse 'userInputText' to Integer.
                            clientView.displayResult("Calculation request sent to the server."); // Feedback message for the user.

                            // Enable all arithmetic action buttons.
                            clientView.getDivideButton().setEnabled(true);
                            clientView.getMultiplyButton().setEnabled(true);
                            clientView.getAddButton().setEnabled(true);
                            clientView.getSubtractButton().setEnabled(true);
                            clientView.getSubmitButton().setEnabled(false); // Disable the 'submit' button.

                            // Construct a string that represents the calculation "num1, operator, num2".
                            numbers = numbers.concat(userInputText.getText());
                            clientView.displayResult("Connected to the server."); // Feedback message for the user.

                            // Inspect the RMI registry running on localhost for a binding under the name "rmi-calculator".
                            calculatorInterface = (CalculatorInterface) Naming.lookup("//localhost/rmi-calculator");
                            // Send a request to the RMI object and receive the result as an integer.
                            int result = calculatorRequest(numbers.split(","));

                            // Feedback messages for the user.
                            clientView.displayResult("Calculation result received from the server: " + result + "\n");
                            userInputText.setText(""); // Clear the contents of 'userInputText'.
                        } catch (Exception e) { // The number was invalid.
                            // Display error dialog.
                            JOptionPane.showMessageDialog(null,
                                    "Please, enter a valid number.", "ERROR",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else { // The user did not enter second number.
                        // Display error dialog.
                        JOptionPane.showMessageDialog(null,
                                "Please, enter a number!", "ERROR",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });

        // Set action for multiply button.
        clientView.getMultiplyButton().addActionListener(
                actionEvent -> performOperation("*",
                        "Will multiply " + userInputText.getText() + " by the next number input."
                )
        );

        // Set action for divide button.
        clientView.getDivideButton().addActionListener(
                actionEvent -> performOperation("/",
                        "Will divide " + userInputText.getText() + " by the next number input.")
        );

        // Set action for add button.
        clientView.getAddButton().addActionListener(
                actionEvent -> performOperation("+",
                        "Will add " + userInputText.getText() + " to the next number input.")
        );

        // Set action for subtract button.
        clientView.getSubtractButton().addActionListener(
                actionEvent -> performOperation("-",
                        "Will subtract " + userInputText.getText() + " from the next number input.")
        );
    }

    /**
     * @param operatorSymbol (String) symbol from the range [+,-,/,*]
     * @param actionString   (String) message to be displayed for user feedback
     * @description Method that performs the relevant arithmetic operation, based on the parameters passed in.
     */
    private void performOperation(String operatorSymbol, String actionString) {
        if (userInputText.getText().length() > 0) {
            try {
                Integer.parseInt(userInputText.getText()); // Try to parse 'userInputText' to Integer.
                clientView.displayResult(actionString); // Feedback message for the user.

                // Disable all arithmetic action buttons.
                clientView.getDivideButton().setEnabled(false);
                clientView.getMultiplyButton().setEnabled(false);
                clientView.getAddButton().setEnabled(false);
                clientView.getSubtractButton().setEnabled(false);
                clientView.getSubmitButton().setEnabled(true); // Enable the 'submit' button.

                // Construct a string that represents the calculation "num1, operator, num2".
                numbers = numbers.concat(userInputText.getText() + "," + operatorSymbol + ",");
                userInputText.setText(""); // Clear the contents of 'userInputText'.
            } catch (Exception e) { // The number was invalid.
                // Display error dialog.
                JOptionPane.showMessageDialog(null,
                        "Please, enter a valid number.", "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else { // The user did not enter second number.
            // Display error dialog.
            JOptionPane.showMessageDialog(null,
                    "Please, enter a number.", "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * @param numbers [] array of format [num1, operator, num2]
     * @return (int) result from the calculation
     * @description Sends a calculation request to the RMI object and retrieves the result.
     */
    private int calculatorRequest(String[] numbers) {
        float result = 0; // Variable that will store the result value received from the RMI.

        try {
            // Check what operator was passed to the method and perform the relevant calculation.
            switch (numbers[1]) {
                case "+": // Addition.
                    result = calculatorInterface.add(numbers[0], numbers[2]);
                    break;
                case "-": // Subtraction.
                    result = calculatorInterface.subtract(numbers[0], numbers[2]);
                    break;
                case "/": // Division.
                    result = calculatorInterface.divide(numbers[0], numbers[2]);
                    break;
                case "*": // Multiplication.
                    result = calculatorInterface.multiply(numbers[0], numbers[2]);
                    break;
            }

            // Enable all arithmetic action buttons.
            clientView.getDivideButton().setEnabled(true);
            clientView.getMultiplyButton().setEnabled(true);
            clientView.getAddButton().setEnabled(true);
            clientView.getSubtractButton().setEnabled(true);
            clientView.getSubmitButton().setEnabled(false);

            this.numbers = ""; // Reset the variable 'numbers' to empty string.
            clientView.getUserInputText().setText(""); // Clear user input.
        } catch (RemoteException e) { // Handle the exception.
            e.printStackTrace(); // Print to console.
        }

        return (int) result; // Return the result as an integer.
    }

}
