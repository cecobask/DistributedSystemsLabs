import java.rmi.*;
import java.net.*;

public class AddServer {
    public static void main(String[] args) {
        try {
            AddRemImpl locobj = new AddRemImpl();
            Naming.rebind("rmi://localhost:1099/AddRem", locobj);
        } catch (RemoteException | MalformedURLException re) {
            re.printStackTrace();
        }
    }
}