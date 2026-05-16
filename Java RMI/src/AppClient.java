import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class AppClient {
    public static void main(String[] args)
    {
        try{
            Registry registry=LocateRegistry.getRegistry("localHost",1099);
            AppService appService=(AppService) registry.lookup("AppService");
            System.out.println("1. Add amount");
            System.out.println("2. Withdraw amount");
            System.out.println("3. Transfer amount");
            System.out.println("4. Query balance");
            int option=Integer.parseInt(args[0]);
            switch (option) {
                    case 1 : {
                        String id=args[1];
                        System.out.println("Account ID: "+id);
                        double amount=Double.parseDouble(args[2]);
                        boolean ok = appService.addAmount(amount, id);
                        System.out.println(ok ? "Deposit succeeded." : "Deposit failed.");
                    }
                    break;
                    case 2 :
                    {
                        String id=args[1];
                        System.out.println("Account ID: "+id);
                        double amount=Double.parseDouble(args[2]);
                        boolean ok = appService.withdrawAmount(amount, id);
                        System.out.println(ok ? "Withdraw succeeded." : "Withdraw failed.");
                    }
                    break;
                    case 3 : {
                        String fromId=args[1];
                        System.out.println("Account ID1: "+fromId);
                        double amount=Double.parseDouble(args[2]);
                        String toId=args[3];
                        System.out.println("Account ID2:"+toId);

                        boolean ok = appService.transferAmount(amount, fromId, toId);
                        System.out.println(ok ? "Transfer succeeded." : "Transfer failed.");
                    }
                    break;
                    case 4 : {
                        String id=args[1];
                        System.out.println("Account ID: "+id);

                        double balance = appService.queryAmount(id);
                        if (balance >= 0) {
                            System.out.println("Current balance: " + balance);
                        } else {
                            System.out.println("Account not found.");
                        }
                    }
                    break;
                    default : System.out.println("Invalid option.");
                }
            }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
