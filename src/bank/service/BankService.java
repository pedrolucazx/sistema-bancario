package bank.service;

import bank.exception.BankException;
import bank.model.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankService {

    private final List<Account> accounts = new ArrayList<>();
    private int nextAccountNumber        = 1001;

    public int generateAccountNumber() {
        return nextAccountNumber++;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(int accountNumber) throws BankException {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new BankException.AccountNotFound(accountNumber));
    }

    public List<Account> getAllAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void deposit(int accountNumber, double amount) throws BankException {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(int accountNumber, double amount) throws BankException {
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(int fromNumber, int toNumber, double amount) throws BankException {
        if (fromNumber == toNumber) {
            throw new BankException.InvalidAmount("Conta de origem e destino não podem ser iguais.");
        }
        Account from = findAccount(fromNumber);
        Account to   = findAccount(toNumber);
        from.withdraw(amount);
        to.deposit(amount);
    }
}
