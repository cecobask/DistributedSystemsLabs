import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Tsvetoslav Dimov
 * @date 15/04/2020
 * @studentID 20077038
 * @description Calculator interface class.
 * It specifies the methods which classes must implement when using this interface.
 * The methods should represent basic arithmetic operations carried out by a calculator.
 */
public interface CalculatorInterface extends Remote {

    /**
     * @param val1 (String) representation of a number
     * @param val2 (String) representation of a number
     * @return (float) result from the multiplication of two numbers
     * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur
     *                         during the execution of a remote method call
     */
    float multiply(String val1, String val2) throws RemoteException;

    /**
     * @param val1 (String) representation of a number
     * @param val2 (String) representation of a number
     * @return (float) result from the division of two numbers
     * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur
     *                         during the execution of a remote method call
     */
    float divide(String val1, String val2) throws RemoteException;

    /**
     * @param val1 (String) representation of a number
     * @param val2 (String) representation of a number
     * @return (float) result from the addition of the two numbers
     * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur
     *                         during the execution of a remote method call
     */
    float add(String val1, String val2) throws RemoteException;

    /**
     * @param val1 (String) representation of a number
     * @param val2 (String) representation of a number
     * @return (float) result from the subtraction of two numbers
     * @throws RemoteException The common superclass for a number of communication-related exceptions that may occur
     *                         during the execution of a remote method call
     */
    float subtract(String val1, String val2) throws RemoteException;
}