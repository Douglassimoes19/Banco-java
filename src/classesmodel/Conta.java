package classesmodel;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private String numero;
    private String agencia;
    private double saldo;
    private Cliente cliente;
    private String conta;
    private List<String> transacoes = new ArrayList<>();

    public Conta(String numero, String agencia, double saldo, Cliente cliente, String conta) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.cliente = cliente;
        this.setConta(conta);
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos
    public void depositar(double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            transacoes.add("Depósito: R$ " + valor);
        }
    }

    public void sacar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            transacoes.add("Saque: R$ " + valor);
        } else {
            transacoes.add("Tentativa de saque falhou: R$ " + valor);
        }
    }

    public double consultarSaldo(Cliente cliente) {
    	System.out.println("saldo:" + saldo);
        return saldo;
    }

    public String getExtrato() {
        StringBuilder extrato = new StringBuilder();
        extrato.append("Extrato da Conta\n")
                .append("Agência: ").append(agencia).append("\n")
                .append("Número: ").append(numero).append("\n")
                .append("Saldo Atual: R$ ").append(saldo).append("\n")
                .append("Transações:\n");

        for (String transacao : transacoes) {
            extrato.append(transacao).append("\n");
        }

        return extrato.toString();
    }

}
