import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Tsvetoslav Dimov
 * @date 15/04/2020
 * @studentID 20077038
 * @description Server controller class.
 * This class extends UnicastRemoteObject and implements all the methods listed in the CalculatorInterface.
 * In the implementation methods lies the mathematical logic for basic arithmetic operations.
 * Here the ServerController object gets registered in the RMI registry.
 */
public class ServerController extends UnicastRemoteObject implements CalculatorInterface {

    // Class variables.
    public ServerView serverView;

    // Constructor for the ServerController class.
    public ServerController() throws RemoteException {
        serverView = new ServerView(); // Instance of the ServerView class, containing a GUI.
        serverView.getLogsTextArea().append("Started the server.\n"); // Server log message.
    }

    public static void main(String[] args) {
        try {
            ServerController serverController = new ServerController(); // Instance of the ServerController class.
            Registry registry = LocateRegistry.createRegistry(1099); // Obtain a reference to the remote object registry.
            registry.rebind("rmi-calculator", serverController); // Replace the binding for the 'Calculator' entry in the registry with the ServerController object.
            serverController.serverView.getLogsTextArea().append("Calculator was bound to the RMI registry.\n\n"); // Server log message.
        } catch (Exception e) { // Handle exceptions.
            e.printStackTrace(); // Print error to the console.
        }
    }

    // Implementation of the multiply method.
    @Override
    public float multiply(String val1, String val2) throws RemoteException {
        // Convert strings to floats.
        float num1 = Integer.parseInt(val1);
        float num2 = Integer.parseInt(val2);
        float result = num1 * num2; // Multiply the two numbers.

        logCalculation(num1, num2, "*", result); // Display log messages regarding the calculation.

        return result; // Return the result from the calculation.
    }

    // Implementation of the divide method.
    @Override
    public float divide(String val1, String val2) throws RemoteException {
        // Convert strings to floats.
        float num1 = Integer.parseInt(val1);
        float num2 = Integer.parseInt(val2);
        float result = num1 / num2; // Divide the two numbers.

        logCalculation(num1, num2, "/", result); // Display log messages regarding the calculation.

        return result; // Return the result from the calculation.
    }

    // Implementation of the add method.
    @Override
    public float add(String val1, String val2) throws RemoteException {
        // Convert strings to floats.
        float num1 = Integer.parseInt(val1);
        float num2 = Integer.parseInt(val2);
        float result = num1 + num2; // Sum the two numbers.

        logCalculation(num1, num2, "+", result); // Display log messages regarding the calculation.

        return result; // Return the result from the calculation.
    }

    // Implementation of the subtract method.
    @Override
    public float subtract(String val1, String val2) throws RemoteException {
        // Convert strings to floats.
        float num1 = Integer.parseInt(val1);
        float num2 = Integer.parseInt(val2);
        float result = num1 - num2; // Subtract the two numbers.

        logCalculation(num1, num2, "-", result); // Display log messages regarding the calculation.

        return result; // Return the result from the calculation.
    }

    /**
     * @param num1     (float) value used for calculation
     * @param num2     (float) value used for calculation
     * @param operator (String) representation of the arithmetic operation.
     * @param result   (float) result of the calculation
     * @description Takes in two number parameters and an operator. Logs the parameters and the outcome of the calculation.
     */
    private void logCalculation(float num1, float num2, String operator, float result) {
        serverView.getLogsTextArea().append("First Number: " + num1 + "\n");
        serverView.getLogsTextArea().append("Second Number: " + num2 + "\n");
        serverView.getLogsTextArea().append("Operator: " + operator + "\n");
        serverView.getLogsTextArea().append("Result: " + result + "\n\n");
    }
}
