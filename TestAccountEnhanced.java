import java.util.*;
class Account
{
    private int account_number;
    private String name;
    private int age;
    private double balance;
    private String account_type;
    private String status;
    private double min_balance;
    private Integer pin;
    Account(int account_number, String name, int age , double initial_balance,String account_type,Integer pin)
    {
        this.account_number=account_number;
        this.name=name;
        this.age=(age<18)?18:age;
        this.account_type=(account_type.equals("Savings") || account_type.equals("Current"))?account_type:"Savings";
        if(account_type=="Savings")
        {
            balance=(initial_balance>=500)?initial_balance:500;
            min_balance=500;
        }
        else
        {
            balance=(initial_balance>=1000)?initial_balance:1000;
            min_balance=1000;
        }
        status="Active";
        if(verify_pin(pin))
        {
            this.pin=pin;
        }
        else
        {
            this.pin=null;
        }
    }
    boolean set_pin(Integer pin)
    {
        if(!verify_pin(pin))
        {
            return false;
        }
        this.pin=pin;
        return true;
    }
    boolean verify_pin(Integer pin)
    {
        if(pin==null)
        {
            return true;
        }
        if(count>=1000 && count<=9999)
        {
            return true;
        }
        return false;
    }
    boolean has_pin()
    {
        if(pin!=null)
        {
            return true;
        }
        return false;
    }
    boolean deposit(double amount)
    {
        if(status.equals("Inactive"))
        {
            return false;
        }
        if(amount<=0)
        {
            return false;
        }
        balance+=amount;
        return true;
    }
    boolean withdraw(Integer user_pin,double amount)
    {
        if(!user_pin.equals(pin))
        {
            return false;   
        }
        if(status.equals("Inactive"))
        {
            return false;
        }
        if(balance<amount || amount<=0)
        {
            return false;
        }

        if((balance-amount)<min_balance)
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
        this.age=(age<18)?18:age;
    }
    boolean close_Account()
    {
        status="Inactive";
        return true;
    }
    boolean reopen_Account()
    {
        status="Active";
        return true;
    }
}
class TestAccountEnhanced
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("  ENHANCED ACCOUNT TEST (BOOLEAN RETURNS);     ");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        Account Test1=new Account(1001,"John Doe",25,1000,"Savings",null);
        Account Test2=new Account(1002,"Young Kid",16,500,"Savings",null);
        Account Test3=new Account(1003,"Test User",25,500,"Invalid",null);
        Account Test4=new Account(1004,"Bob Wilson",25,300,"Savings",null);
        Account Test5=new Account(1005,"Alice Brown",30,1000,"Current",1234);
        //Test 5 is wrong
        Account Test6=new Account(1006,"Charlie Green",35,2000,"Savings",null);
        Test6.close_Account();
        System.out.println(Test6.deposit(500));
        Account Test7=new Account(1007,"Diana Prince",28,1300,"Savings",null);
        Test7.set_pin(8188);
        Test7.withdraw(8188,200);
        System.out.println(Test7.get_balance());
        System.out.println(Test7.withdraw(1234,200));
        System.out.println(Test7.get_balance());
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("                TEST COMPLETED!!                   ");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
    }
}