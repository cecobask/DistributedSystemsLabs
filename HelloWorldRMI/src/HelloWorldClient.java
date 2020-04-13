/*
Filename: HelloWorldClient.java
*/

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
Classname: HelloWorldClient
Comment: The RMI client.
*/

public class HelloWorldClient {

    static String message = "blank";

    // The HelloWorld object "obj" is the identifier that is
    // used to refer to the remote object that implements
    // the HelloWorld interface.
    static HelloWorld obj = null;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            obj = (HelloWorld) registry.lookup("rmi://HelloWorld");
            message = obj.helloWorld();
            System.out.println("Message from the RMI-server was: \""
                    + message + "\"");
        } catch (Exception e) {
            System.out.println("HelloWorldClient exception: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }
}