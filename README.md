# Aplicação Cliente-Servidor com Sockets TCP em Java

## Linguagem utilizada

Java 8+

## Modelo de comunicação

Comunicação via sockets TCP (java.net.ServerSocket e java.net.Socket).

## Funcionalidades implementadas

1. Descobrir o signo com base na data de nascimento

2. Ver o horóscopo do dia

## Como compilar e executar

### 1. Compile os arquivos

```bash
javac Servidor.java

javac Cliente.java
```

### 2. Execute o servidor

```bash
java Servidor
```

### 3. Em outro terminal, execute o cliente

```bash
java Cliente
```

## Observações

A interface é via terminal, com entrada de dados via Scanner no cliente.

