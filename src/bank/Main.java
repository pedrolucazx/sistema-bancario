package bank;

import bank.exception.BankException;
import bank.model.Account;
import bank.model.CheckingAccount;
import bank.model.SavingsAccount;
import bank.service.BankService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner     scanner = new Scanner(System.in);
    private static final BankService bank    = new BankService();

    public static void main(String[] args) {
        int option;
        do {
            printMenu();
            option = readInt("Opção: ");
            System.out.println();

            switch (option) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> checkBalance();
                case 6 -> listAccounts();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> printError("Opção inválida. Escolha entre 0 e 6.");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                ╔═══════════════════════════════════════╗
                ║         SISTEMA BANCÁRIO POO          ║
                ╠═══════════════════════════════════════╣
                ║  1 - Criar conta                      ║
                ║  2 - Depositar                        ║
                ║  3 - Sacar                            ║
                ║  4 - Transferir                       ║
                ║  5 - Consultar saldo                  ║
                ║  6 - Listar contas                    ║
                ║  0 - Sair                             ║
                ╚═══════════════════════════════════════╝""");
    }

    private static void createAccount() {
        printHeader("CRIAR CONTA");
        System.out.println("Tipo de conta:");
        System.out.println("  1 - Corrente");
        System.out.println("  2 - Poupança");

        int type = readInt("Tipo: ");
        if (type != 1 && type != 2) {
            printError("Tipo inválido. Escolha 1 (Corrente) ou 2 (Poupança).");
            return;
        }

        String  name          = readString();
        int     accountNumber = bank.generateAccountNumber();
        Account account;

        if (type == 1) {
            double overdraftLimit = readAmount("Limite de cheque especial (R$): ");
            account = new CheckingAccount(accountNumber, name, overdraftLimit);
        } else {
            account = new SavingsAccount(accountNumber, name);
        }

        bank.addAccount(account);
        printSuccess(String.format("Conta criada! Número: %d | Saldo inicial: R$ 0,00", accountNumber));
    }

    private static void deposit() {
        printHeader("DEPOSITAR");
        int    number = readInt("Número da conta: ");
        double amount = readAmount("Valor (R$): ");
        try {
            bank.deposit(number, amount);
            printSuccess(String.format("Depósito de R$ %.2f realizado.", amount));
        } catch (BankException e) {
            printError(e.getMessage());
        }
    }

    private static void withdraw() {
        printHeader("SACAR");
        int    number = readInt("Número da conta: ");
        double amount = readAmount("Valor (R$): ");
        try {
            bank.withdraw(number, amount);
            printSuccess(String.format("Saque de R$ %.2f realizado.", amount));
        } catch (BankException e) {
            printError(e.getMessage());
        }
    }

    private static void transfer() {
        printHeader("TRANSFERIR");
        int    from   = readInt("Conta de origem:  ");
        int    to     = readInt("Conta de destino: ");
        double amount = readAmount("Valor (R$): ");
        try {
            bank.transfer(from, to, amount);
            printSuccess(String.format("Transferência de R$ %.2f da conta %d para %d realizada.", amount, from, to));
        } catch (BankException e) {
            printError(e.getMessage());
        }
    }

    private static void checkBalance() {
        printHeader("CONSULTAR SALDO");
        int number = readInt("Número da conta: ");
        try {
            System.out.println("  " + bank.findAccount(number));
        } catch (BankException e) {
            printError(e.getMessage());
        }
    }

    private static void listAccounts() {
        printHeader("LISTAR CONTAS");
        List<Account> accounts = bank.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("  Nenhuma conta cadastrada.");
            return;
        }
        accounts.forEach(a -> System.out.println("  " + a));
        System.out.printf("%n  Total: %d conta(s)%n", accounts.size());
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                printError("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double readAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) return value;
                printError("O valor deve ser maior que zero.");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                printError("Entrada inválida. Use ponto como separador decimal (ex: 100.50).");
            }
        }
    }

    private static String readString() {
        while (true) {
            System.out.print("Nome do titular: ");
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            printError("O campo não pode estar vazio.");
        }
    }

    private static void printHeader(String title) {
        System.out.printf("--- %s ---%n", title);
    }

    private static void printSuccess(String message) {
        System.out.println("✔  " + message);
    }

    private static void printError(String message) {
        System.out.println("✖  " + message);
    }
}
