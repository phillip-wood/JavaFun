package com.phil;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        int principal = (int) readNumber("Principal: ", 1_000,10_000_000);
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 0,30);
        byte years = (byte) readNumber("Period (Years): ", 1,30);

        printMortgagePayment(principal, annualInterestRate, years);

        printPaymentSchedule(principal, annualInterestRate, years);

    }

    private static void printMortgagePayment(int principal, float annualInterestRate, byte years) {
        double mortgageMonthlyPayments = calculateMortgage(principal, annualInterestRate, years);
        String mortgageMonthlyPaymentsFormatted = NumberFormat.getCurrencyInstance().format(mortgageMonthlyPayments);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly mortgage payments: " + mortgageMonthlyPaymentsFormatted);
    }

    private static void printPaymentSchedule (int principal, float annualInterestRate, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
           double balance =  calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage ( int principal, float annualInterestRate, byte years ) {

        int numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR ;

        double mortgageMonthlyPayments = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return mortgageMonthlyPayments;
    }

    public static double calculateBalance ( int principal, float annualInterestRate, byte years, short numberOfPaymentsMade ) {

        int numberOfPayments = years * MONTHS_IN_YEAR;
        float monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR ;

        double balance = principal
                * (Math.pow(1 + monthlyInterestRate, numberOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        return balance;


    }

    public static double readNumber (String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }



}