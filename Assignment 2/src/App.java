import controllers.AuthController;
import controllers.ServerController;
import views.AuthFrame;
import views.ServerFrame;

public class App {

    public static void main(String[] args) {
        // Launch the Server.
        ServerFrame serverFrame = new ServerFrame("Server");
        new ServerController(serverFrame);

        // Launch two Authentication screens.
        AuthFrame authFrame = new AuthFrame("Authentication");
        AuthFrame authFrame2 = new AuthFrame("Authentication");
        new AuthController(authFrame);
        new AuthController(authFrame2);
    }
}
