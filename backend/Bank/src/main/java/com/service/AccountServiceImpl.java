package com.service;

import com.bean.Account;
import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import for @Transactional

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional 
    public Account createAccount(Account account) {
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty() ||
            account.getAccountHolderName() == null || account.getAccountHolderName().isEmpty()) {
            throw new IllegalArgumentException("Account number and account holder name cannot be empty.");
        }
        if (accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent()) {
            throw new IllegalArgumentException("Account with this number already exists.");
        }
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional 
    public Account deposit(Long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        return accountRepository.findById(id)
                .map(account -> {
                    account.setBalance(account.getBalance().add(amount)); 
                    return accountRepository.save(account); 
                })
                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + id));
    }

    @Override
    @Transactional 
    public Account withdraw(Long id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        return accountRepository.findById(id)
                .map(account -> {
                    if (account.getBalance().compareTo(amount) < 0) {
                        throw new IllegalArgumentException("Insufficient funds.");
                    }
                    account.setBalance(account.getBalance().subtract(amount)); 
                    return accountRepository.save(account);
                })
                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + id));
    }
}