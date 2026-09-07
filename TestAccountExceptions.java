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
        if(!account_type.equals("SAVINGS") && !account_type.equals("CURRENT") &&!account_type.equals("FIXED_DEPOSIT") &&!account_type.equals("SALARY"))
        {
            throw new IllegalArgumentException("Invalid account type. Provided: "+account_type);
        }
        double minimum_balance=0;
        if(account_type.equals("SAVINGS"))
        {
            minimum_balance=MIN_BALANCE_SAVINGS;
        }
        else if(account_type.equals("CURRENT"))
        {
            minimum_balance=MIN_BALANCE_CURRENT;
        }
        if(initial_balance<minimum_balance)
        {
            throw new IllegalArgumentException(account_type+" account requires minimum balance of Rs "+minimum_balance+". Provided: Rs "+initial_balance);
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
            throw new InvalidAmountException("Deposit amount must be positive. Provided: Rs "+amount);
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
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: Rs "+amount);
        }
        if(amount>balance)
        {
            throw new InsufficientBalanceException("Insufficient balance. Available: Rs "+balance+", Requested: Rs "+amount);
        }
        double minimumBalance=getMinimumBalance();
        if(balance-amount<minimumBalance)
        {
            throw new MinimumBalanceViolationException("Cannot withdraw. Minimum balance of Rs "+minimumBalance+" required.");
        }
        balance-=amount;
    }
    void closeAccount()
    {
        if(status.equals("Inactive"))
        {
            throw new IllegalStateException("Account is already inactive");
        }
        status="Inactive";
    }
    void reopenAccount()
    {
        if(status.equals("Active"))
        {
            throw new IllegalStateException("Account is already active");
        }
        status="Active";
    }
    void setPin(int pin)
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
        return pin!=null;
    }
    private double getMinimumBalance()
    {
        if(account_type.equals("SAVINGS"))
        {
            return MIN_BALANCE_SAVINGS;
        }
        else if(account_type.equals("CURRENT"))
        {
            return MIN_BALANCE_CURRENT;
        }
        return 0;
    }
    private void validateActive() throws InactiveAccountException
    {
        if(status.equals("Inactive"))
        {
            throw new InactiveAccountException("Account is inactive. Please reopen the account.");
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
class SavingsAccount extends Account
{
    private double minBalance;
    private double interestRate;
    SavingsAccount(int account_number,String name,int age,double initial_balance)
    {
        super(account_number,name,age,initial_balance,"SAVINGS");
        this.minBalance=1000.0;
        this.interestRate=4.0;
    }
    void applyInterest() throws InvalidAmountException,InactiveAccountException
    {
        double balance=get_balance();
        double interestAmount=(interestRate*balance)/100;
        deposit(interestAmount);
    }
    double getMinBalance()
    {
        return minBalance;
    }

    double getInterestRate()
    {
        return interestRate;
    }
}

class CurrentAccount extends Account
{
    private double overdraftLimit;

    CurrentAccount(int account_number,String name,int age,double initial_balance)
    {
        super(account_number,name,age,initial_balance,"CURRENT");
        this.overdraftLimit=25000.0;
    }

    double getOverdraftLimit()
    {
        return overdraftLimit;
    }

    void setOverdraftLimit(double overdraftLimit)
    {
        if(overdraftLimit<0)
        {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }

        this.overdraftLimit=overdraftLimit;
    }
}
class FixedDepositAccount extends Account
{
    private int tenureMonths;
    private double interestRate;
    FixedDepositAccount(int account_number,String name,int age,double initial_balance,int tenureMonths,double interestRate)
    {
        super(account_number,name,age,initial_balance,"FIXED_DEPOSIT");
        this.tenureMonths=tenureMonths;
        this.interestRate=interestRate;
    }
    double calculateMaturityAmount()
    {
        double principal=get_balance();
        double maturityAmount=principal*Math.pow(1+interestRate/100,tenureMonths/12.0);
        return maturityAmount;
    }
    int getTenureMonths()
    {
        return tenureMonths;
    }
    double getInterestRate()
    {
        return interestRate;
    }
}
class SalaryAccount extends Account
{
    private String employerName;
    private int inactiveMonths;
    SalaryAccount(int account_number,String name,int age,double initial_balance,String employerName,int inactiveMonths)
    {
        super(account_number,name,age,initial_balance,"SALARY");
        this.employerName=employerName;
        this.inactiveMonths=inactiveMonths;
    }
    String getEmployerName()
    {
        return employerName;
    }
    int getInactiveMonths()
    {
        return inactiveMonths;
    }
}

class TestAccountExceptions
{
    public static void main(String[] args)
    {
        System.out.println("=== Activity 7: Account Subclasses Test ===");
        try
        {
            SavingsAccount savings=new SavingsAccount(1001,"John Doe",25,10000);

            System.out.println("Savings Account Created: Balance Rs "+savings.get_balance()+" | Min Balance: Rs "+savings.getMinBalance());

            savings.applyInterest();

            System.out.println("After Interest: Balance Rs "+savings.get_balance());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        try
        {
            CurrentAccount current=new CurrentAccount(1002,"Alice Brown",30,10000);
            System.out.println("Current Account Created: Overdraft Limit Rs "+current.getOverdraftLimit());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        try
        {
            FixedDepositAccount fixedDeposit=new FixedDepositAccount(1003,"Bob Wilson",35,10000,12,6.5);
            System.out.println("Fixed Deposit Created: Tenure "+fixedDeposit.getTenureMonths()+" months | Interest: "+fixedDeposit.getInterestRate()+"%");
            System.out.println("Maturity Amount: Rs "+fixedDeposit.calculateMaturityAmount());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        try
        {
            SalaryAccount salary=new SalaryAccount(1004,"Charlie Green",28,10000,"Infosys",0);
            System.out.println("Salary Account Created: Employer "+salary.getEmployerName());
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION: "+e.getMessage());
        }
        System.out.println("All subclasses instantiated successfully!");
    }
}