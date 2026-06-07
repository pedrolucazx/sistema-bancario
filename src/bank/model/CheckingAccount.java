package bank.model;

import bank.exception.BankException;

public class CheckingAccount extends Account {

    private final double overdraftLimit;

    public CheckingAccount(int accountNumber, String holderName, double overdraftLimit) {
        super(accountNumber, holderName);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException.InvalidAmount("O valor do saque deve ser positivo.");
        }
        double available = balance + overdraftLimit;
        if (amount > available) {
            throw new BankException.InsufficientFunds(available);
        }
        balance -= amount;
    }
    
    @Override
    public String toString() {
        return super.toString()
             + String.format(" | Corrente | Limite especial: R$ %.2f", overdraftLimit);
    }
}
