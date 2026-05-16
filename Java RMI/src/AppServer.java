import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class AppServer {
public static void main(String[]  args)
{
     try{
         AppService stub = new AppServiceImpl();
         Registry registry = locateOrCreateRegistry(1099);

         if (registry == null) {
             System.out.println("No RMI registry available -  PROGRAM TERMINATED");
             return;
         }
         registry.rebind("AppService", stub);
         System.out.println("AppService is running and bound to registry.");

     }catch(Exception e)
     {
        e.printStackTrace();
     }
}
private static Registry locateOrCreateRegistry(int port)
{
    try{
        Registry registry = LocateRegistry.getRegistry(port);
        registry.list();
        System.out.println("Located existing RMI registry ");
        return registry;
    }catch(RemoteException e)
    {
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("Created new RMI registry on port " + port);
            return registry;

        } catch (RemoteException ex) {
            System.err.println("Failed to create RMI registry!");
            return null;
        }
    }
}
}
