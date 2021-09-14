package com.greatlearning.bankmanager;

import java.util.Random;

public class OtpManager {

    private static final Random random = new Random();

    public static int generateOtp(){
        return random.nextInt(10000);
    }

    public static boolean verifyOtp(int genOtp, int otp){
        return genOtp == otp;
    }
}
