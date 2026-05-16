import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;
public class AppServiceImpl extends UnicastRemoteObject implements AppService{
    protected HashMap<String,Account >  transactions;
    protected AppServiceImpl() throws  RemoteException
    {
        super();
        transactions=new HashMap<String, Account>();
        transactions.put("RO8990", new Account("Alexandra", 1000.0));
        transactions.put("RO9954", new Account("Bogdan", 500.0));
        transactions.put("RO7654", new Account("Carmen", 250.0));
        transactions.put("RO8876", new Account("Andrei",400.0));
    }

    @Override
    public  boolean addAmount(double sum, String id)
    {
        if (sum <= 0) {
            System.out.println("The amount is lower than 0!!");
            return false;
        }
        Account account= transactions.get(id);
        if(account!=null)
        {
            account.addA(sum);
            System.out.println("The amount of : "+sum+" was added to the account "+id);
            return true;
        }
        System.out.println("The amount couldn't be added!!!");
        return false;
    }

    @Override
    public  boolean withdrawAmount(double sum, String id)
    {
        if (sum <= 0) {
            return false;
        }
        Account account=transactions.get(id);
        if(account!=null)
        {
            return account.withdrawA(sum);
        }
        return false;
    }

    @Override
    public  boolean transferAmount(double sum, String fromId, String toId)
    {
        if (sum <= 0 || fromId.equals(toId)) {
            return false;
        }
        Account sender=transactions.get(fromId);
        Account receiver = transactions.get(toId);
        if(sender==null || receiver == null){
            return false;
        }
        synchronized (fromId)
        {
            synchronized (toId)
            {
                if(!sender.withdrawA(sum)) {
                    System.out.println("The transfer couldn't be made!");
                    return false;
                }
                System.out.println("Transfer was made with succes from the account "+fromId+"to the account"+toId);
                receiver.addA(sum);
                return true;
            }
        }
    }

    @Override
    public double queryAmount(String id)
    {
        Account sender=transactions.get(id);
        if(sender!=null)
        {
            System.out.println("The query balanced was succed!!");
            return sender.getBalance();
        }
        return -1.0;
    }
}
