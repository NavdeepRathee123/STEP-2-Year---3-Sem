package com.gdb.domain;

import com.gdb.exceptions.*;

public class SavingsAccount extends AbstractAccount
{
    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber,String name,int age,double initialBalance)
    {
        super(accountNumber,name,age,initialBalance,"SAVINGS");
        this.minBalance=1000.0;
        this.interestRate=4.0;
    }

    protected void processDebit(double amount) throws MinimumBalanceViolationException
    {
        if(balance-amount<minBalance)
        {
            throw new MinimumBalanceViolationException("Cannot withdraw. Minimum balance of Rs "+minBalance+" required.");
        }

        balance-=amount;
    }

    public void applyInterest() throws InvalidAmountException,InactiveAccountException
    {
        double interestAmount=(interestRate*balance)/100;
        deposit(interestAmount);
    }

    public double getMinBalance()
    {
        return minBalance;
    }

    public double getInterestRate()
    {
        return interestRate;
    }
}