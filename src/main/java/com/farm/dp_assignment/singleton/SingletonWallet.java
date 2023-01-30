package com.farm.dp_assignment.singleton;

public class SingletonWallet implements Wallet {
    private int totalAmount = 2;  //starting wallet amount
    private static SingletonWallet uniqueWallet = new SingletonWallet();  //make sure only one instance created

    private SingletonWallet() {
    }

    public static SingletonWallet getInstance() {
        return uniqueWallet;
    }

    //deduct wallet amount and make sure wallet amount not negative
    @Override
    public boolean deductAmount(int amount) {
        int tempTotalAmount = totalAmount;
        tempTotalAmount -= amount;
        if (tempTotalAmount >= 0) {
            totalAmount = tempTotalAmount;
            return true;
        }
        return false;
    }

    //add wallet amount
    @Override
    public void addAmount(int amount) {
        totalAmount += amount;
    }

    //get wallet amount
    public int getTotalAmount() {
        return totalAmount;
    }
}
