package com.greatlearning.bankmanager.data;

import com.greatlearning.bankmanager.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class CustomerInfo {
    static List<Customer> data = new ArrayList<Customer>(
            Arrays.asList(new Customer("123", "abc" ),
                    new Customer("1234", "1234")));

    public static Optional<Customer> getCustomer(String accountNumber) {
        return data.stream().filter(customer -> customer.getAccountNumber().equals(accountNumber)).findFirst();
    }

    public static boolean verifyCredentials(String accountNum,String pass) throws CustomerNotFoundException {
        Optional<Customer> customer = getCustomer(accountNum);
        if(customer.isPresent()){
           Customer customer1 = customer.get();
           return customer1.getPassword().equals(pass);
        }
        throw new CustomerNotFoundException();
    }
}
