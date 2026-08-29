import java.util.*;
class AccountException extends Exception
{
    AccountException(String message)
    {
        super(message);
    }
}
class InvalidAmountException extends AccountException
{
    InvalidAmountException(String message)
    {
        super(message);
    }
}
class InsufficientBalanceException extends AccountException
{
    InsufficientBalanceException(String message)
    {
        super(message);
    }
}
class MinimumBalanceViolationException extends AccountException
{
    MinimumBalanceViolationException(String message)
    {
        super(message);
    }
}
class InactiveAccountException extends AccountException
{
    InactiveAccountException(String message)
    {
        super(message);
    }
}
class InvalidPinException extends AccountException
{
    InvalidPinException(String message)
    {
        super(message);
    }
}
class Account
{
    private static final double MIN_BALANCE_SAVINGS=500.0;
    private static final double MIN_BALANCE_CURRENT=1000.0;
    private static final int MIN_AGE=18;
    private static final int MIN_PIN=1000;
    private static final int MAX_PIN=9999;
    private int account_number;
    private String name;
    private int age;
    private double balance;
    private String account_type;
    private String status;
    private Integer pin;
    Account(int account_number,String name,int age,double initial_balance,String account_type)
    {
        if(age<MIN_AGE)
        {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: "+age);
        }
        if(!account_type.equals("Savings") && !account_type.equals("Current"))
        {
            throw new IllegalArgumentException("Account type must be 'Savings' or 'Current'. Provided: "+account_type);
        }
        double minimum_balance;
        if(account_type.equals("Savings"))
        {
            minimum_balance=MIN_BALANCE_SAVINGS;
        }
        else
        {
            minimum_balance=MIN_BALANCE_CURRENT;
        }
        if(initial_balance<minimum_balance)
        {
            throw new IllegalArgumentException(account_type+" account requires minimum balance of ₹"+minimum_balance+". Provided: ₹"+initial_balance);
        }
        this.account_number=account_number;
        this.name=name;
        this.age=age;
        this.balance=initial_balance;
        this.account_type=account_type;
        this.status="Active";
        this.pin=null;
    }
    void deposit(double amount) throws InvalidAmountException,InactiveAccountException
    {
        validateActive();
        if(amount<=0)
        {
            throw new InvalidAmountException("Deposit amount must be positive. Provided: ₹"+amount);
        }
        balance+=amount;
    }
    void withdraw(double amount,int pin) throws InvalidAmountException,InsufficientBalanceException,MinimumBalanceViolationException,InactiveAccountException,InvalidPinException
    {
        validateActive();
        if(this.pin==null)
        {
            throw new InvalidPinException("PIN not set for this account");
        }
        if(!verifyPin(pin))
        {
            throw new InvalidPinException("Incorrect PIN");
        }
        if(amount<=0)
        {
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: ₹"+amount);
        }
        if(amount>balance)
        {
            throw new InsufficientBalanceException("Insufficient balance. Available: ₹"+balance+", Requested: ₹"+amount);
        }
        if(balance-amount<getMinimumBalance())
        {
            throw new MinimumBalanceViolationException("Cannot withdraw. Minimum balance of ₹"+getMinimumBalance()+" required. Available after withdrawal: ₹"+(balance-amount));
        }
        balance-=amount;
    }
    void closeAccount() throws IllegalStateException
    {
        if(status.equals("Inactive"))
        {
            throw new IllegalStateException("Account is already inactive");
        }
        status="Inactive";
    }
    void reopenAccount() throws IllegalStateException
    {
        if(status.equals("Active"))
        {
            throw new IllegalStateException("Account is already active");
        }
        status="Active";
    }
    void setPin(int pin) throws IllegalArgumentException
    {
        if(pin<MIN_PIN || pin>MAX_PIN)
        {
            throw new IllegalArgumentException("PIN must be a 4-digit number");
        }
        this.pin=pin;
    }
    boolean verifyPin(int pin)
    {
        if(this.pin==null)
        {
            return false;
        }

        return this.pin==pin;
    }
    boolean hasPin()
    {
        if(pin!=null)
        {
            return true;
        }

        return false;
    }
    private double getMinimumBalance()
    {
        if(account_type.equals("Savings"))
        {
            return MIN_BALANCE_SAVINGS;
        }

        return MIN_BALANCE_CURRENT;
    }
    private void validateActive() throws InactiveAccountException
    {
        if(status.equals("Inactive"))
        {
            throw new InactiveAccountException("Account is inactive. Please reopen the account or contact support.");
        }
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
        if(age<MIN_AGE)
        {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: "+age);
        }

        this.age=age;
    }
}
class TestAccountExceptions
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("  ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println(">>> Test 1: Valid Account Creation");
        Account Test1=null;
        try
        {
            Test1=new Account(1001,"John Doe",25,1000,"Savings");
            System.out.println("Account #"+Test1.get_account_number()+" | "+Test1.get_name()+" ("+Test1.get_age()+" yrs) | "+Test1.get_account_type()+" | ₹"+Test1.get_balance()+" | "+Test1.get_status()+" | PIN: "+(Test1.hasPin()?"Yes":"No"));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");
        try
        {
            Account Test2=new Account(1002,"Young Kid",16,500,"Savings");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println("Creating account with type \"Invalid\"");
        try
        {
            Account Test3=new Account(1003,"Test User",25,500,"Invalid");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println("Creating Savings account with ₹300");
        try
        {
            Account Test4=new Account(1004,"Bob Wilson",25,300,"Savings");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        Account Test5=null;
        try
        {
            Test5=new Account(1005,"Alice Brown",30,1000,"Current");
            System.out.println("Account #"+Test5.get_account_number()+" | "+Test5.get_name()+" ("+Test5.get_age()+" yrs) | "+Test5.get_account_type()+" | ₹"+Test5.get_balance()+" | "+Test5.get_status()+" | PIN: "+(Test5.hasPin()?"Yes":"No"));
            Test5.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            Test5.deposit(500);
            System.out.println("Depositing ₹500.0: SUCCESS");
            System.out.println("Balance after deposit: ₹"+Test5.get_balance());
            Test5.withdraw(200,1234);
            System.out.println("Withdrawing ₹200.0: SUCCESS");
            System.out.println("Balance after withdrawal: ₹"+Test5.get_balance());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 6: Invalid Deposit");
        try
        {
            Test5.deposit(-100);
        }
        catch(InvalidAmountException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        catch(InactiveAccountException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 7: Insufficient Balance");
        Account Test6=null;
        try
        {
            Test6=new Account(1006,"Charlie Green",35,500,"Savings");
            Test6.setPin(1234);
            Test6.withdraw(1000,1234);
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 8: Minimum Balance Violation");
        Account Test7=null;
        try
        {
            Test7=new Account(1007,"Diana Prince",28,1000,"Savings");
            Test7.setPin(1234);
            Test7.withdraw(600,1234);
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 9: Account Status");
        Account Test8=null;
        try
        {
            Test8=new Account(1008,"Eve Wilson",32,2000,"Current");
            System.out.println("Account #"+Test8.get_account_number()+" | "+Test8.get_name()+" ("+Test8.get_age()+" yrs) | "+Test8.get_account_type()+" | ₹"+Test8.get_balance()+" | "+Test8.get_status()+" | PIN: "+(Test8.hasPin()?"Yes":"No"));
            Test8.closeAccount();
            System.out.println("Closing account: SUCCESS");
            try
            {
                Test8.deposit(500);
            }
            catch(InvalidAmountException e)
            {
                System.out.println("EXCEPTION: "+e.getMessage());
            }
            catch(InactiveAccountException e)
            {
                System.out.println("EXCEPTION: "+e.getMessage());
            }
            Test8.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
            Test8.deposit(500);
            System.out.println("Depositing ₹500.0 after reopen: SUCCESS");
            System.out.println("Balance: ₹"+Test8.get_balance());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println(">>> Test 10: PIN Protection");
        Account Test9=null;
        try
        {
            Test9=new Account(1009,"Frank Miller",40,1500,"Savings");
            Test9.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            Test9.withdraw(200,1234);
            System.out.println("Withdrawing ₹200.0 with correct PIN: SUCCESS");
            System.out.println("New balance: ₹"+Test9.get_balance());
            try
            {
                Test9.withdraw(100,9999);
            }
            catch(InvalidPinException e)
            {
                System.out.println("EXCEPTION: "+e.getMessage());
            }
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        Account Test10=null;
        try
        {
            Test10=new Account(1010,"No Pin User",25,1000,"Savings");
            Test10.withdraw(100,1234);
        }
        catch(InvalidPinException e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
        System.out.println("          TEST COMPLETED!!");
        System.out.println("= ".repeat(20));
        System.out.println("= ".repeat(20));
    }
}