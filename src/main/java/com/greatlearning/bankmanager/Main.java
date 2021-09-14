package com.greatlearning.bankmanager;

import java.util.Optional;
import java.util.Scanner;

import com.greatlearning.bankmanager.data.CustomerInfo;
import com.greatlearning.bankmanager.data.CustomerNotFoundException;

public class Main {

    public static void main(String[] args) {
        Optional<Customer> user = checkLogin();
        if (user.isPresent()) {
            Customer customer = user.get();
            Scanner in = new Scanner(System.in);
            Writer writer = new Writer();
            boolean exit = false;
            while (!exit) {
                showMenu();
                int selected = -1;
                try {
                    selected = in.nextInt();
                    in.nextLine();
                    switch (selected) {
                        case 1:
                            System.out.println("Enter the amount you want to deposit");
                            Double deposit = in.nextDouble();
                            in.nextLine();
                            customer.deposit(deposit);
                            System.out.println("Amount deposited successfully");
                            writer.storeTransaction("Amount deposit transaction for account "+ customer.getAccountNumber());
                            customer.showBalance();
                            break;
                        case 2:
                            System.out.println("Enter the amount you want to withdraw");
                            Double withdraw = in.nextDouble();
                            in.nextLine();
                            if (customer.debit(withdraw)) {
                                System.out.println("Amount withdrawn successfully");
                                writer.storeTransaction("Amount withdrawn successfully transaction for account "+ customer.getAccountNumber());
                            } else {
                                writer.storeTransaction("Amount withdrawn failed transaction for account "+ customer.getAccountNumber());
                            }
                            customer.showBalance();
                            break;
                        case 3:
                            System.out.println("Enter the otp");
                            int generateOTP = OtpManager.generateOtp();
                            System.out.println(generateOTP);
                            int otp = in.nextInt();
                            in.nextLine();
                            if (!OtpManager.verifyOtp(generateOTP, otp)) {
                                System.out.println("OTP verification unsuccessful");
                                writer.storeTransaction("Amount transfer failed transaction for account "+ customer.getAccountNumber());
                                break;
                            }
                            System.out.println("OTP verification successful");
                            System.out.println("Enter the amount and account you want to transfer");
                            Double debit = in.nextDouble();
                            in.nextLine();
                            String account = in.nextLine();
                            if (customer.debit(debit)) {
                                System.out.println("Amount " + debit + " transferred successfully to account " + account);
                                writer.storeTransaction("Amount transfer successful transaction for account "+ customer.getAccountNumber());
                            }
                            customer.showBalance();
                            break;
                        case 0:
                            exit = true;
                            System.out.println("Exited successfully");
                            break;
                        default:
                            System.out.println("Please enter a number between 0-3");
                    }
                } catch (Exception e) {
                    System.out.println("Error");
                    break;
                }
            }
            writer.closeWriter();
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Enter the operation that you want to peform");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("0. Logout");
        System.out.println("=============================================");
    }

    private static Optional<Customer> checkLogin() {
        System.out.println("Welcome to login page\n");
        Scanner in = new Scanner(System.in);
        String acc = null;
        String pass = null;
        try {
            System.out.println("Enter the bank account no.");
            acc = in.nextLine();
        } catch (Exception e) {
            System.out.println("enter valid account no.");
            in.close();
            return Optional.empty();
        }
        try {
            System.out.println("Enter the password");
            pass = in.nextLine();
        } catch (Exception e) {
            System.out.println("Enter valid password");
            in.close();
            return Optional.empty();
        }
        try {
            boolean verify = CustomerInfo.verifyCredentials(acc, pass);
            if (!verify) {
                System.out.println("Invalid credentials");
                return Optional.empty();
            }
            return CustomerInfo.getCustomer(acc);
        } catch (CustomerNotFoundException e) {
            System.out.println("Account does not exist");
            in.close();
            return Optional.empty();
        }
    }
}
