package com.greatlearning.bankmanager;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Customer {

    @NonNull
    private String accountNumber;
    @NonNull
    private String password;
    private Double accountBalance = 0d;

    void showBalance() {
        System.out.println("Balance: " + accountBalance);
    }

    void deposit(double amount) {
        accountBalance = accountBalance + amount;
    }

    boolean debit(double amount) {
        if (accountBalance < amount) {
            System.out.println("Cannot debit the specified amount");
            return false;
        } else {
            accountBalance = accountBalance - amount;
            return true;
        }
    }
}
