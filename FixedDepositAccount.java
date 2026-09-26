package com.gdb.domain;

import com.gdb.exceptions.*;

public class FixedDepositAccount extends AbstractAccount
{
    private int tenureMonths;
    private double interestRate;

    public FixedDepositAccount(int accountNumber,String name,int age,double initialBalance,int tenureMonths,double interestRate)
    {
        super(accountNumber,name,age,initialBalance,"FIXED_DEPOSIT");
        this.tenureMonths=tenureMonths;
        this.interestRate=interestRate;
    }

    protected void processDebit(double amount) throws AccountException
    {
        throw new AccountException("Premature withdrawals are not permitted on Fixed Deposit accounts before maturity.");
    }

    public double calculateMaturityAmount()
    {
        double principal=balance;
        double maturityAmount=principal*Math.pow(1+interestRate/100,tenureMonths/12.0);
        return maturityAmount;
    }

    public int getTenureMonths()
    {
        return tenureMonths;
    }

    public double getInterestRate()
    {
        return interestRate;
    }
}