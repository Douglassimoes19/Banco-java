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
    
    public boolean inserirContaPoupanca(String numero, String agencia, double saldo, String conta, Cliente cliente, double taxaRendimento) {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
        String update = "INSERT INTO conta_poupanca (taxa_rendimento, id_conta) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Definir os parâmetros da primeira inserção na tabela conta
            stmt.setString(1, numero);
            stmt.setString(2, agencia);
            stmt.setDouble(3, saldo);
            stmt.setString(4, conta);
            stmt.setInt(5, cliente.getId());

            // insere o primeiro insert
            int rowsAffected = stmt.executeUpdate();

            // Verificar se foi 
            if (rowsAffected > 0) {
                // puxa o ID
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idConta = generatedKeys.getInt(1); 

                        // faz inserção na tabela conta_poupanca com o id da conta
                        try (PreparedStatement stmtUpdate = connection.prepareStatement(update)) {
                            stmtUpdate.setDouble(1, taxaRendimento);
                            stmtUpdate.setInt(2, idConta); 
                            
                            int result = stmtUpdate.executeUpdate();
                            
                            if (result > 0) return true;
                        }
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir conta poupança no banco de dados.");
            
        }
        return false;
    }

    public void inserirContaCorrente(ContaCorrente conta) {
        String sql = "INSERT INTO conta_corrente (numero_conta, limite, data_vencimento) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, conta.getNumero());
            stmt.setDouble(2, conta.getLimite());
            stmt.setDate(3, conta.getDataVencimento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir conta corrente no banco de dados.");
        }
    }	

    // Buscar conta por ID do cliente 
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
                    //  cria as variaveis para instanciar
                    String numeroConta = rs.getString("c.numero_conta");
                    String agencia = rs.getString("c.agencia");
                    double saldo = rs.getDouble("c.saldo");
                    String tipoConta = rs.getString("c.tipo_conta");

                
                    

                    // Verifica o tipo de conta e instancia a conta pra nois
                    if ("poupanca".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = rs.getDouble("cp.taxa_rendimento");
                        System.out.println("passou aqui");
                        return new ContaPoupanca(numeroConta, agencia, saldo,tipoConta, cliente, taxaRendimento);
                    } else if ("corrente".equalsIgnoreCase(tipoConta)) {
                        double limite = rs.getDouble("cc.limite");
                        Date dataVencimento = rs.getDate("cc.data_vencimento");
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
    
    //Busca a conta poupança do cliente
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
                // cria e retorna uma nova instância
                String numeroConta = rs.getString("numero_conta");
                String agencia = rs.getString("agencia");
                double saldo = rs.getDouble("saldo");
                double taxaRendimento = rs.getDouble("taxa_rendimento");

                return new ContaPoupanca(numeroConta, agencia, saldo, "POUPANCA", cliente, taxaRendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Caso o cliente não tenha conta poupança
    }
    
    //deposita o valor na conta
    public boolean depositar(Conta conta, double valor) {
        String sql = "UPDATE conta SET saldo = saldo + ? WHERE numero_conta = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, valor);  
            stmt.setString(2, conta.getNumero());  // número da conta( não confundir com o id do cliente)

            // Executa a atualização
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Retorna true se a atualização foi bem-sucedida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Atualiza o saldo caso ele tenha feito saque parece outro metodo mas não é 
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
