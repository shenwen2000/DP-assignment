package com.farm.dp_assignment.singleton;

public class SingletonWallet implements Wallet {
    private int totalAmount = 5;
    private static SingletonWallet uniqueWallet = new SingletonWallet();

    private SingletonWallet() {
    }

    public static SingletonWallet getInstance() {
        return uniqueWallet;
    }

    @Override
    public void deductAmount(int amount) {
        int tempTotalAmount = totalAmount;
        tempTotalAmount -= amount;
        if (tempTotalAmount >= 0) {
            totalAmount = tempTotalAmount;
        }
    }

    @Override
    public void addAmount(int amount) {
        totalAmount += amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
