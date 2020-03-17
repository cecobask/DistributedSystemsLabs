import controllers.AuthController;
import controllers.ServerController;
import views.AuthFrame;
import views.ServerFrame;

public class App {

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
