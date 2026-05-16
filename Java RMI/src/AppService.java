import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AppService extends Remote {
boolean addAmount(double sum, String id) throws RemoteException;
boolean withdrawAmount(double sum, String id) throws RemoteException;
boolean transferAmount(double sum, String fromId, String toId) throws RemoteException;
double queryAmount(String id) throws RemoteException;
}
