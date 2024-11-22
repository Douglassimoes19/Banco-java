package classesdao;

import classesmodel.*;
import classesutil.dbutil;
import java.sql.*;
import java.time.LocalDate;

public class ContaDao {
    private Connection connection;

    public ContaDao() {
        connection = dbutil.getConnection();
    }

    // Buscar conta por ID do cliente (supondo que a relação cliente-conta esteja feita)
    public Conta buscarContaPorClienteId(Cliente cliente) {
        String sql = """
            SELECT 
                c.numero_conta, 
                c.agencia, 
                c.saldo, 
                c.tipo_conta, 
                c.id_cliente, 
                cp.taxa_rendimento, 
                cc.limite, 
                cc.data_vencimento
            FROM 
                conta c
            LEFT JOIN conta_poupanca cp ON c.id_conta = cp.id_conta
            LEFT JOIN conta_corrente cc ON c.id_conta = cc.id_conta
            WHERE 
                c.id_cliente = ?;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Dados comuns entre as contas
                    String numeroConta = rs.getString("c.numero_conta");
                    String agencia = rs.getString("c.agencia");
                    double saldo = rs.getDouble("c.saldo");
                    String tipoConta = rs.getString("c.tipo_conta");

                    // Objeto Cliente associado à conta
                    

                    // Verifica o tipo de conta e instancia o objeto correspondente
                    if ("poupanca".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = rs.getDouble("cp.taxa_rendimento");
                        System.out.println("passou aqui");
                        return new ContaPoupanca(numeroConta, agencia, saldo,tipoConta, cliente, taxaRendimento);
                    } else if ("corrente".equalsIgnoreCase(tipoConta)) {
                        double limite = rs.getDouble("cc.limite");
                        LocalDate dataVencimento = rs.getDate("cc.data_vencimento").toLocalDate();
                        return new ContaCorrente(numeroConta, agencia, saldo, cliente, limite,tipoConta, dataVencimento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    // Consultar saldo da conta do cliente
    public double consultarSaldo(Cliente cliente) {
        Conta conta = buscarContaPorClienteId(cliente);
        if (conta != null) {
            return conta.getSaldo();
        }
        return 0.0; // Retorna 0 se não encontrar a conta
    }
    
    public ContaPoupanca buscarContaPoupancaPorCliente(Cliente cliente) {
        String sql = """
            SELECT 
                c.numero_conta, 
                c.agencia, 
                c.saldo, 
                c.tipo_conta, 
                c.id_cliente, 
                cp.taxa_rendimento
            FROM 
                conta c
            LEFT JOIN conta_poupanca cp ON c.id_conta = cp.id_conta
            
            WHERE 
                c.numero_conta = ?;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getConta().getNumero());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Caso haja uma conta poupança, cria e retorna uma nova instância
                String numeroConta = rs.getString("numero_conta");
                String agencia = rs.getString("agencia");
                double saldo = rs.getDouble("saldo");
                double taxaRendimento = rs.getDouble("taxa_rendimento");

                return new ContaPoupanca(numeroConta, agencia, saldo, "POUPANCA", cliente, taxaRendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Caso o cliente não tenha uma conta poupança
    }
    
    public boolean depositar(Conta conta, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero_conta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valor);  // valor a ser depositado
            stmt.setString(2, conta.getNumero());  // número da conta

            // Executa a atualização
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Retorna true se a atualização foi bem-sucedida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean atualizarSaldo(Conta conta) {
        String sql = "UPDATE conta SET saldo = ? WHERE numero_conta = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getNumero());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Se foi possível atualizar o saldo
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Se ocorrer um erro, retorna false
        }
    }


}
