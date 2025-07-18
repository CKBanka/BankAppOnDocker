package com.service;

import com.bean.Account;
import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> getAccountById(Long id);
    Iterable<Account> getAllAccounts();
    Account deposit(Long id, BigDecimal amount);
    Account withdraw(Long id, BigDecimal amount);
}