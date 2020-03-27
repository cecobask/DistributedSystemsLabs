import controllers.AuthController;
import controllers.ServerController;
import views.AuthFrame;
import views.ServerFrame;

/**
 * @author Tsvetoslav Dimov
 * Student ID: 20077038
 * Module: Distributed Systems
 */
public class App {

    /**
     * Main method, used to run the application. It creates two Authentication windows and a Server window.
     * @param args String[]
     */
    public static void main(String[] args) {
        // Launch two Authentication screens.
        AuthFrame authFrame = new AuthFrame("Authentication 1");
        AuthFrame authFrame2 = new AuthFrame("Authentication 2");
        new AuthController(authFrame);
        new AuthController(authFrame2);

        // Launch the Server.
        ServerFrame serverFrame = new ServerFrame("Server");
        new ServerController(serverFrame);
    }
}
