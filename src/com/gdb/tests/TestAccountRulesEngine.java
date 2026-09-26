package com.gdb.tests;

import com.gdb.domain.AccountRulesEngine;

public class TestAccountRulesEngine
{
    public static void main(String[] args)
    {
        System.out.println(
            "=== Activity 13.1: Hardcoded Rules Engine Test ==="
        );

        int[] tenures = {0, 2, 4, 6};

        for(int tenure : tenures)
        {
            System.out.println(
                "Tenure " + tenure
                + " yrs -> Min Balance: Rs "
                + AccountRulesEngine.getSavingsMinBalance(tenure)
                + " | Interest: "
                + AccountRulesEngine.getSavingsInterestRate(tenure)
                + "%"
            );
        }

        System.out.println(
            "Rules Engine lookup completed successfully!"
        );
    }
}