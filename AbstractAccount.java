package com.gdb.domain;

import com.gdb.exceptions.*;

public abstract class AbstractAccount
{
    protected static final double MIN_BALANCE_SAVINGS=500.0;
    protected static final double MIN_BALANCE_CURRENT=1000.0;
    protected static final int MIN_AGE=18;
    protected static final int MIN_PIN=1000;
    protected static final int MAX_PIN=9999;

    protected int accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected Integer pin;

    AbstractAccount(int accountNumber,String name,int age,double initialBalance,String accountType)
    {
        if(age<MIN_AGE)
        {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: "+age);
        }

        if(!accountType.equals("SAVINGS") && !accountType.equals("CURRENT") && !accountType.equals("FIXED_DEPOSIT") && !accountType.equals("SALARY"))
        {
            throw new IllegalArgumentException("Invalid account type. Provided: "+accountType);
        }

        double minimumBalance=0;

        if(accountType.equals("SAVINGS"))
        {
            minimumBalance=MIN_BALANCE_SAVINGS;
        }
        else if(accountType.equals("CURRENT"))
        {
            minimumBalance=MIN_BALANCE_CURRENT;
        }

        if(initialBalance<minimumBalance)
        {
            throw new IllegalArgumentException(accountType+" account requires minimum balance of Rs "+minimumBalance+". Provided: Rs "+initialBalance);
        }

        this.accountNumber=accountNumber;
        this.name=name;
        this.age=age;
        this.balance=initialBalance;
        this.accountType=accountType;
        this.status="Active";
        this.pin=null;
    }

    public void deposit(double amount) throws InvalidAmountException,InactiveAccountException
    {
        validateActive();

        if(amount<=0)
        {
            throw new InvalidAmountException("Deposit amount must be positive. Provided: Rs "+amount);
        }

        balance+=amount;
    }

    public final void withdraw(double amount,int pin) throws AccountException
    {
        validatePin(pin);

        if(!status.equals("Active"))
        {
            throw new InactiveAccountException("Account is inactive. Please reopen the account.");
        }

        if(amount<=0)
        {
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: Rs "+amount);
        }

        processDebit(amount);
    }

    protected abstract void processDebit(double amount) throws AccountException;

    public boolean validatePin(int pin) throws InvalidPinException
    {
        if(this.pin==null)
        {
            throw new InvalidPinException("PIN not set for this account");
        }

        if(this.pin!=pin)
        {
            throw new InvalidPinException("Incorrect PIN");
        }

        return true;
    }

    public void changePin(int oldPin,int newPin) throws InvalidPinException
    {
        validatePin(oldPin);

        if(newPin<MIN_PIN || newPin>MAX_PIN)
        {
            throw new IllegalArgumentException("PIN must be a 4-digit number");
        }

        this.pin=newPin;
    }

    public void setPin(int pin)
    {
        if(pin<MIN_PIN || pin>MAX_PIN)
        {
            throw new IllegalArgumentException("PIN must be a 4-digit number");
        }

        this.pin=pin;
    }

    public boolean verifyPin(int pin)
    {
        if(this.pin==null)
        {
            return false;
        }

        return this.pin==pin;
    }

    public boolean hasPin()
    {
        return pin!=null;
    }

    public void displayAccountInfo()
    {
        System.out.println("Account Number: "+accountNumber);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Balance: Rs "+balance);
        System.out.println("Account Type: "+accountType);
        System.out.println("Status: "+status);
    }

    public void closeAccount()
    {
        if(status.equals("Inactive"))
        {
            throw new IllegalStateException("Account is already inactive");
        }

        status="Inactive";
    }

    public void reopenAccount()
    {
        if(status.equals("Active"))
        {
            throw new IllegalStateException("Account is already active");
        }

        status="Active";
    }

    private void validateActive() throws InactiveAccountException
    {
        if(status.equals("Inactive"))
        {
            throw new InactiveAccountException("Account is inactive. Please reopen the account.");
        }
    }

    public int get_account_number()
    {
        return accountNumber;
    }

    public String get_name()
    {
        return name;
    }

    public int get_age()
    {
        return age;
    }

    public double get_balance()
    {
        return balance;
    }

    public String get_account_type()
    {
        return accountType;
    }

    public String get_status()
    {
        return status;
    }

    public void set_name(String name)
    {
        this.name=name;
    }

    public void set_age(int age)
    {
        if(age<MIN_AGE)
        {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: "+age);
        }

        this.age=age;
    }
}