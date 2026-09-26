package com.gdb.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountRulesEngine
{
    private static final Map<String, Double> MIN_BALANCE_RULES = new HashMap<>();
    private static final Map<String, Double> INTEREST_RATE_RULES = new HashMap<>();

    static
    {
        MIN_BALANCE_RULES.put("NEW", 10000.0);
        MIN_BALANCE_RULES.put("STANDARD", 7500.0);
        MIN_BALANCE_RULES.put("PREMIUM", 5000.0);
        MIN_BALANCE_RULES.put("PRIVILEGE", 2500.0);

        INTEREST_RATE_RULES.put("NEW", 2.70);
        INTEREST_RATE_RULES.put("STANDARD", 3.00);
        INTEREST_RATE_RULES.put("PREMIUM", 3.50);
        INTEREST_RATE_RULES.put("PRIVILEGE", 4.00);
    }

    private static String getTenureTier(int tenureYears)
    {
        if(tenureYears >= 5)
        {
            return "PRIVILEGE";
        }
        else if(tenureYears >= 3)
        {
            return "PREMIUM";
        }
        else if(tenureYears >= 1)
        {
            return "STANDARD";
        }
        else
        {
            return "NEW";
        }
    }

    public static double getSavingsMinBalance(int tenureYears)
    {
        return MIN_BALANCE_RULES.get(getTenureTier(tenureYears));
    }

    public static double getSavingsInterestRate(int tenureYears)
    {
        return INTEREST_RATE_RULES.get(getTenureTier(tenureYears));
    }

    public static double getCurrentOverdraftLimit(double monthlyTurnover)
    {
        return Math.max(25000.0, monthlyTurnover * 2.5);
    }

    public static double getFDInterestRate(int months)
    {
        if(months >= 12)
        {
            return 6.5;
        }
        else if(months >= 6)
        {
            return 6.0;
        }
        else if(months >= 3)
        {
            return 5.5;
        }
        else
        {
            return 5.0;
        }
    }
}