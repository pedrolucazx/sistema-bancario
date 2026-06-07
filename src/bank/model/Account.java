package bank.model;

import bank.exception.BankException;

public abstract class Account {

    private final int    accountNumber;
    private final String holderName;
    protected double     balance;

    public Account(int accountNumber, String holderName) {
        this.accountNumber = accountNumber;
        this.holderName    = holderName;
        this.balance       = 0;
    }

    public void deposit(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException.InvalidAmount("O valor do depósito deve ser positivo.");
        }
        balance += amount;
    }

    public abstract void withdraw(double amount) throws BankException;

    public int    getAccountNumber() { return accountNumber; }
    
    @Override
    public String toString() {
        return String.format("Conta %-6d | Titular: %-20s | Saldo: R$ %10.2f",
                accountNumber, holderName, balance);
    }
}
