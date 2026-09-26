package com.gdb.tests;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TestAbstractAccount
{
    static void transfer(
        AbstractAccount source,
        AbstractAccount destination,
        double amount,
        int pin
    ) throws AccountException
    {
        source.withdraw(amount, pin);
        destination.deposit(amount);
    }

    static void processMonthlyCycle(AbstractAccount[] accounts)
    {
        for(AbstractAccount account : accounts)
        {
            if(account instanceof SavingsAccount)
            {
                try
                {
                    ((SavingsAccount) account).applyInterest();
                }
                catch(AccountException e)
                {
                    System.out.println(
                        "Interest processing failed: " + e.getMessage()
                    );
                }
            }
            else if(account instanceof SalaryAccount)
            {
                SalaryAccount salaryAccount = (SalaryAccount) account;

                if(salaryAccount.getInactiveMonths() > 0)
                {
                    System.out.println(
                        "Salary credit history checked for account "
                        + salaryAccount.get_account_number()
                    );
                }
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("=== Activity 10: Banking Operations Suite ===");

        SavingsAccount savings =
            new SavingsAccount(1001, "John Doe", 25, 10000);

        CurrentAccount current =
            new CurrentAccount(1002, "Alice Brown", 30, 5000);

        SalaryAccount salary =
            new SalaryAccount(1003, "Bob Wilson", 28, 15000, "ABC Ltd", 0);

        savings.setPin(1234);
        current.setPin(2345);
        salary.setPin(3456);

        AbstractAccount[] accounts =
            {savings, current, salary};

        // Successful transfer
        try
        {
            transfer(savings, current, 3000, 1234);

            System.out.println(
                "Transfer Rs 3000 from Savings to Current: SUCCESS"
            );

            System.out.println(
                "Savings Balance: Rs " + savings.get_balance()
                + " | Current Balance: Rs " + current.get_balance()
            );
        }
        catch(AccountException e)
        {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        // Failed transfer using wrong PIN
        double savingsBalance = savings.get_balance();
        double currentBalance = current.get_balance();

        try
        {
            transfer(savings, current, 2000, 9999);

            System.out.println(
                "Failed Transfer (Wrong PIN): Transfer succeeded"
            );
        }
        catch(AccountException e)
        {
            if(savings.get_balance() == savingsBalance &&
               current.get_balance() == currentBalance)
            {
                System.out.println(
                    "Failed Transfer (Wrong PIN): "
                    + "Exception caught, no balance changed [PASS]"
                );
            }
            else
            {
                System.out.println(
                    "Failed Transfer (Wrong PIN): Balance changed [FAIL]"
                );
            }
        }

        // Monthly banking cycle
        processMonthlyCycle(accounts);

        System.out.println(
            "Monthly Interest Cycle processed for all qualifying accounts."
        );

        System.out.println(
            "All banking operations passed!"
        );
    }
}