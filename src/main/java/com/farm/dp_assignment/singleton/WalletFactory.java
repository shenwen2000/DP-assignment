package com.farm.dp_assignment.singleton;

public class WalletFactory {

    public SingletonWallet getWallet() {
        return SingletonWallet.getInstance();
    }
}
