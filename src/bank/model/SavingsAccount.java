package bank.model;

import bank.exception.BankException;

public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber, String holderName) {
        super(accountNumber, holderName);
    }

    @Override
    public void withdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException.InvalidAmount("O valor do saque deve ser positivo.");
        }
        if (amount > balance) {
            throw new BankException.InsufficientFunds(balance);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return super.toString() + " | Poupança";
    }
}
