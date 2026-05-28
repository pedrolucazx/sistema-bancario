package bank.exception;

public class BankException extends Exception {

    public BankException(String message) {
        super(message);
    }

    public static class AccountNotFound extends BankException {
        public AccountNotFound(int accountNumber) {
            super("Conta " + accountNumber + " não encontrada.");
        }
    }

    public static class InsufficientFunds extends BankException {
        public InsufficientFunds(double available) {
            super(String.format("Saldo insuficiente. Disponível: R$ %.2f", available));
        }
    }

    public static class InvalidAmount extends BankException {
        public InvalidAmount(String message) {
            super(message);
        }
    }
}
