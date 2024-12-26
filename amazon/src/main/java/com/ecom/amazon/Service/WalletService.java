package com.ecom.amazon.Service;

import com.ecom.amazon.Entity.Customer;
import com.ecom.amazon.Entity.Wallet;
import com.ecom.amazon.Repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet addBalanceToWallet(Customer customer, double balance) {
        Wallet wallet = customer.getWallet();
        wallet.setBalance(wallet.getBalance() + balance);
        return walletRepository.save(wallet);
    }

    public Wallet deductBalanceFromWallet(Customer customer, double balance) {
        Wallet wallet = customer.getWallet();
        wallet.setBalance(wallet.getBalance() - balance);
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByCustomer(Customer customer) {
        return customer.getWallet();
    }
}