package com.gdb.tests;

import com.gdb.domain.*;

public class TestInterfaceFactory
{
    public static void main(String[] args)
    {
        System.out.println(
            "=== Activity 11: Interface & Factory Pattern Test ==="
        );

        IAccount savings = AccountFactory.createAccount(
            "SAVINGS",
            2001,
            "Rajesh Sharma",
            25,
            10000
        );

        System.out.println(
            "Factory created: SAVINGS account for "
            + savings.get_customer_name()
        );

        System.out.println();

        IAccount current = AccountFactory.createAccount(
            "CURRENT",
            2002,
            "Priya Patel",
            30,
            15000
        );

        System.out.println(
            "Factory created: CURRENT account for "
            + current.get_customer_name()
        );

        System.out.println();

        IAccount fixedDeposit = AccountFactory.createAccount(
            "FIXED_DEPOSIT",
            2003,
            "Amit Kumar",
            28,
            20000
        );

        System.out.println(
            "Factory created: FIXED_DEPOSIT account for "
            + fixedDeposit.get_customer_name()
        );

        System.out.println();

        IAccount salary = AccountFactory.createAccount(
            "SALARY",
            2004,
            "Sneha Verma",
            26,
            18000
        );

        System.out.println(
            "Factory created: SALARY account for "
            + salary.get_customer_name()
        );

        System.out.println();

        System.out.println(
            "All accounts successfully created through AccountFactory!"
        );
    }
}