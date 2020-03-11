public class App {

    public static void main(String[] args) {
        AuthFrame authFrame = new AuthFrame("Authentication");
        new AuthController(authFrame);
    }
}
