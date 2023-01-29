package com.farm.dp_assignment.singleton;

public class SingletonWallet implements Wallet {
    private int totalAmount = 1000;
    private static SingletonWallet uniqueWallet = new SingletonWallet();
    private SingletonWallet(){}

    public static SingletonWallet getInstance(){
        return uniqueWallet;
    }
    @Override
    public void deductAmount(int amount) {
        totalAmount -= amount;
    }

    @Override
    public void addAmount(int amount) {
        totalAmount += amount;
    }

    public int getTotalAmount(){
        return totalAmount;
    }
}
