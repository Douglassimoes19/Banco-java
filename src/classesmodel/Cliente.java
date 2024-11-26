package classesmodel;

import java.sql.Date;
import java.time.LocalDate;


public class Cliente extends Usuario {
    private Conta conta; // Atributo para associar uma conta ao cliente

    public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String tipouser, String senha) {
        super(id, nome, cpf, dataNascimento, telefone, endereco, tipouser, senha);
    }

    // Getter e Setter para a conta
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean login(String senha) {
        // Implementação do login (se necessário)
        return false;
    }

    // Métodos delegando operações para a conta associada
    public double consultarSaldo(Cliente cliente) {
        return conta != null ? conta.consultarSaldo(cliente) : 0.0;
    }

    public void depositar(double valor) {
        if (conta != null) {
            conta.depositar(valor);
        }
    }

    public boolean sacar(double valor) {
        if (conta != null) {
            conta.sacar(valor);
            return true;
        }
        return false;
    }

    public String consultarExtrato() {
        return conta != null ? conta.getExtrato() : "Nenhuma conta associada.";
    }

    public double consultarLimite() {
        if (conta instanceof ContaCorrente) {
            return ((ContaCorrente) conta).getLimite();
        }
        return 0.0;
    }
}
