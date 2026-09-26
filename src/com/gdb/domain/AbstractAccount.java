package com.gdb.domain;

import com.gdb.exceptions.*;

public abstract class AbstractAccount implements IAccount
{
    protected int account_number;
    protected String customer_name;
    protected int age;
    protected double balance;
    protected String status;
    protected String pin;

    public AbstractAccount(
        int account_number,
        String customer_name,
        int age,
        double balance)
    {
        this.account_number = account_number;
        this.customer_name = customer_name;
        this.age = age;
        this.balance = balance;
        this.status = "ACTIVE";
        this.pin = null;
    }

    @Override
    public int get_account_number()
    {
        return account_number;
    }

    @Override
    public String get_customer_name()
    {
        return customer_name;
    }

    @Override
    public double get_balance()
    {
        return balance;
    }

    @Override
    public String get_status()
    {
        return status;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException
    {
        if(amount <= 0)
        {
            throw new InvalidAmountException(
                "Deposit amount must be greater than zero"
            );
        }

        balance += amount;
    }

    public void setPin(int pin)
    {
        this.pin = String.valueOf(pin);
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    @Override
    public void withdraw(double amount, String pin)
        throws AccountException
    {
        if(amount <= 0)
        {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than zero"
            );
        }

        if(!isValidPin(pin))
        {
            throw new InvalidPinException("Invalid PIN");
        }

        if(amount > balance)
        {
            throw new InsufficientBalanceException(
                "Insufficient balance"
            );
        }

        processDebit(amount);
    }

    public void withdraw(double amount, int pin)
        throws AccountException
    {
        withdraw(amount, String.valueOf(pin));
    }

    protected void processDebit(double amount)
        throws AccountException
    {
        balance -= amount;
    }

    protected boolean isValidPin(String pin)
    {
        return this.pin != null && this.pin.equals(pin);
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String get_account_type()
    {
        return getClass().getSimpleName();
    }

    @Override
    public void displayAccountInfo()
    {
        System.out.println("Account Number: " + account_number);
        System.out.println("Customer Name: " + customer_name);
        System.out.println("Age: " + age);
        System.out.println("Balance: Rs " + balance);
        System.out.println("Account Type: " + get_account_type());
        System.out.println("Status: " + status);
    }
}