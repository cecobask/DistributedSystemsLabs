/*
Filename: HelloWorldServer.java
*/


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
Classname: HelloWorldServer
Purpose: The RMI server.
*/

public class HelloWorldServer extends UnicastRemoteObject
        implements HelloWorld {

    public HelloWorldServer() throws RemoteException {
        super();
    }

    public String helloWorld() {
        System.out.println("Invocation to helloWorld was successful!");
        return "Hello World from RMI server!";
    }

    public static void main(String[] args) {
        try {

            // Create an object of the HelloWorldServer class.
            HelloWorldServer obj = new HelloWorldServer();

            // Bind this object instance to the name "HelloServer".
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("rmi://HelloWorld", obj);

            System.out.println("HelloWorld bound in registry");
        } catch (Exception e) {
            System.out.println("HelloWorldServer error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
