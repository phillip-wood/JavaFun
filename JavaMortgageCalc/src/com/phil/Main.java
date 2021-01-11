package com.phil;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        //interest rate needs to be divided by 100% and then by 12 months
        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = scanner.nextFloat();
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        //period in years needs to be in months
        System.out.print("Period (Years): ");
        byte years = scanner.nextByte();
        int numberOfPayments = years * MONTHS_IN_YEAR;

        //calculate monthly payments based on formula  m = p * (i * (1 + i) ^ n) / ((1 + i) ^ n -1)
        double mortgageMonthlyPayments = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) -1);

        //format the output as a currency
        String mortgageMonthlyPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mortgageMonthlyPayments);

        System.out.println("Monthly mortgage payments: " + mortgageMonthlyPaymentsFormatted);
    }
}