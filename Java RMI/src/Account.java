public class Account {
    private String ownerName;
    private double amount;
    public Account(String ownerName, double amount)
    {
        this.ownerName=ownerName;
        this.amount=amount;
    }
    public synchronized void addA(double sum)
    {
        this.amount+=sum;
    }
    public synchronized boolean withdrawA(double sum)
    {
        if(this.amount>=sum)
        {
            this.amount-=sum;
            return true;
        }
        System.out.println(" The amount is higher then the current balance! Amount:"+sum+" balance:"+this.amount);
        return false;
    }
    public synchronized double getBalance()
    {
        return this.amount;
    }
    public String ownerName()
    {
        return this.ownerName;
    }
}
