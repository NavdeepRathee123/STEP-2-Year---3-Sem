import java.util.*;
class Account
{
    private int account_number;
    private String name;
    private int age;
    private double balance;
    private String account_type;
    private String status;
    Account(int account_number, String name, int age , double initial_balance,String account_type)
    {
        this.account_number=account_number;
        this.name=name;
        this.age=age;
        balance=initial_balance;
        this.account_type=account_type;
        status="Active";
    }
    boolean deposit(double amount)
    {
        if(amount<=0)
        {
            return false;
        }
        balance+=amount;
        return true;
    }
    boolean withdraw(double amount)
    {
        if(balance<amount || amount<=0)
        {
            return false;
        }
        balance-=amount;
        return true;    
    }
    int get_account_number()
    {
        return account_number;
    }
    String get_name()
    {
        return name;
    }
    int get_age()
    {
        return age;
    }
    double get_balance()
    {
        return balance;
    }
    String get_account_type()
    {
        return account_type;
    }
    String get_status()
    {
        return status;
    }
    void set_name(String name)
    {
        this.name=name;
    }
    void set_age(int age)
    {
        this.age=age;
    }
}
class Global_Digital_Bank
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("  GLOBAL DIGITAL BANK - ACCOUNT TEST     ");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        Account account1=new Account(1001,"John Doe",25,1000,"Savings");
        System.out.println("Please enter the amount to be deposited");
        int deposit_money=sc.nextInt();
        System.out.println(account1.deposit(deposit_money));
        System.out.println("Current Balance : "+account1.get_balance());
        System.out.println("Please enter the amount to be deposited");
        int deposit_money2=sc.nextInt();
        System.out.println(account1.deposit(deposit_money2));
        System.out.println("Current Balance : "+account1.get_balance());
        System.out.println("Please enter the amount to be withdrawn");
        int withdraw_money=sc.nextInt();
        System.out.println(account1.withdraw(withdraw_money));
        System.out.println("Current Balance : "+account1.get_balance());
        System.out.println("Please enter the amount to be withdrawn");
        int withdraw_money2=sc.nextInt();
        System.out.println(account1.withdraw(withdraw_money2));
        System.out.println("Current Balance : "+account1.get_balance());
        Account account2=new Account(1002,"Jane Smith",30,2000,"Current");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("                TEST COMPLETED!!                   ");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
    }
}