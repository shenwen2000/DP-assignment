package com.farm.dp_assignment.singleton;

public class WalletFactory {
    SingletonWallet myWallet = SingletonWallet.getInstance();

    public int getWalletAmount(){
        return myWallet.getTotalAmount();
    }

    public void deposit(int amount){
        myWallet.addAmount(amount);
    }

    public void withdraw(int amount){
        myWallet.deductAmount(amount);
    }
}
