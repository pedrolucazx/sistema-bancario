# Sistema Bancário (POO) - Projeto de Exemplo

Projeto simples de sistema bancário escrito em Java usando programação orientada a objetos. Fornece um pequeno aplicativo de console para gerenciar contas correntes e poupança, com operações básicas: criar conta, depositar, sacar, transferir, consultar saldo e listar contas.

Principais pacotes e arquivos

- `src/bank/Main.java` — ponto de entrada da aplicação (interface de console).
- `src/bank/service/BankService.java` — lógica de gerenciamento de contas (camada de serviço).
- `src/bank/model/` — modelos das contas (ex.: `Account`, `CheckingAccount`, `SavingsAccount`).
- `src/bank/exception/BankException.java` — exceções customizadas do domínio.

Estrutura do projeto

```
sistema-bancario/
├─ src/
│  └─ bank/
│     ├─ Main.java
│     ├─ exception/BankException.java
│     ├─ model/ (Account, CheckingAccount, SavingsAccount)
│     └─ service/BankService.java
└─ README.md
```

Pré-requisitos

- Java Development Kit (JDK) instalado. Testado com JDK 11+.
- Shell (ex.: zsh, bash) para executar os comandos abaixo.

Compilar e executar

1. Abra um terminal na raiz do projeto (`sistema-bancario`).
2. Compile os arquivos .java e rode a aplicação com os comandos abaixo:

```bash
# criar diretório de saída
mkdir -p out

# compilar todos os arquivos Java do diretório src
javac -d out $(find src -name "*.java")

# executar a aplicação (classe principal: bank.Main)
java -cp out bank.Main
```

