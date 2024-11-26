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

    public boolean inserirContaCorrente(String numero, String agencia, double saldo, String conta, String senha, Cliente cliente, double limite, LocalDate dataVencimento) {
        String sql = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
        String update = "INSERT INTO conta_corrente (limite, data_vencimento, id_conta) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Definir os parâmetros da primeira inserção na tabela conta
            stmt.setString(1, numero);
            stmt.setString(2, agencia);
            stmt.setDouble(3, saldo);
            stmt.setString(4, conta);
            stmt.setInt(5, cliente.getId());

            // Insere o primeiro insert
            int rowsAffected = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (rowsAffected > 0) {
                // Puxa o ID da conta
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idConta = generatedKeys.getInt(1); 

                        // Inserção na tabela conta_corrente com o id da conta
                        try (PreparedStatement stmtUpdate = connection.prepareStatement(update)) {
                            stmtUpdate.setDouble(1, limite);
                            
                            // Convertendo LocalDate para java.sql.Date
                            java.sql.Date sqlDate = java.sql.Date.valueOf(dataVencimento);
                            stmtUpdate.setDate(2, sqlDate);
                            
                            stmtUpdate.setInt(3, idConta);

                            int result = stmtUpdate.executeUpdate();

                            if (result > 0) return true;  // Se a inserção foi bem-sucedida
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime o erro no console para debugging
        }

        return false;  // Caso a operação falhe
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
    
    public Conta buscarContaPorNumeroConta(String numeroConta) {
        String sql = """
			            SELECT 
			    c.numero_conta, 
			    c.agencia, 
			    c.saldo, 
			    c.tipo_conta, 
			    c.id_cliente, 
			    cp.taxa_rendimento, 
			    cc.limite, 
			    cc.data_vencimento,
			    u.id_usuario, 
			    u.nome, 
			    u.cpf, 
			    u.data_nascimento, 
			    u.telefone, 
			    u.senha
			FROM 
			    conta c
			LEFT JOIN conta_poupanca cp ON c.id_conta = cp.id_conta
			LEFT JOIN conta_corrente cc ON c.id_conta = cc.id_conta
			LEFT JOIN cliente cl ON c.id_cliente = cl.id_cliente
			LEFT JOIN usuario u ON cl.id_usuario = u.id_usuario
			WHERE 
			    c.numero_conta = ?;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, numeroConta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Dados da conta
                    String numeroContaResult = rs.getString("c.numero_conta");
                    String agencia = rs.getString("c.agencia");
                    double saldo = rs.getDouble("c.saldo");
                    String tipoConta = rs.getString("c.tipo_conta");
                    
                    // Dados do cliente
                    int idCliente = rs.getInt("u.id_usuario");
                    String nomeCliente = rs.getString("u.nome");
                    String cpfCliente = rs.getString("u.cpf");
                    LocalDate dataNascimento = rs.getDate("u.data_nascimento").toLocalDate();
                    String telefone = rs.getString("u.telefone");
                    String senha = rs.getString("u.senha");
                    Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, dataNascimento, telefone, null, senha, senha);

                    // Verifica o tipo de conta e instancia a conta de acordo com o tipo
                    if ("poupanca".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = rs.getDouble("cp.taxa_rendimento");
                        return new ContaPoupanca(numeroContaResult, agencia, saldo, tipoConta, cliente, taxaRendimento);
                    } else if ("corrente".equalsIgnoreCase(tipoConta)) {
                        double limite = rs.getDouble("cc.limite");
                        LocalDate dataVencimento = rs.getDate("cc.data_vencimento").toLocalDate();
                        return new ContaCorrente(numeroContaResult, agencia, saldo, cliente, limite, tipoConta, dataVencimento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retorna null caso a conta não seja encontrada
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
    
    public ContaCorrente buscarContaCorrentePorCliente(Cliente cliente) {
        String sql = """
            SELECT 
                c.numero_conta, 
                c.agencia, 
                c.saldo, 
                c.tipo_conta, 
                c.id_cliente, 
                cc.limite,
                cc.data_vencimento
            FROM 
                conta c
            LEFT JOIN conta_corrente cc ON c.id_conta = cc.id_conta
            
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
                double limite = rs.getDouble("limite");
                LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();

                return new ContaCorrente(numeroConta, agencia, saldo, cliente, limite, "CORRENTE", dataVencimento);
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
    
    public boolean excluirConta(String numeroConta) throws Exception {
        String deleteContaCorrente = "DELETE FROM conta_corrente WHERE id_conta = (SELECT id_conta FROM conta WHERE numero_conta = ?)";
        String deleteConta = "DELETE FROM conta WHERE numero_conta = ?";
        
        try (PreparedStatement stmt1 = connection.prepareStatement(deleteContaCorrente);
             PreparedStatement stmt2 = connection.prepareStatement(deleteConta)) {

            // Excluindo registros dependentes
            stmt1.setString(1, numeroConta);
            stmt1.executeUpdate();

            // Excluindo o registro principal
            stmt2.setString(1, numeroConta);
            int rowsAffected = stmt2.executeUpdate();
            
            return rowsAffected > 0;
        }
    }

    public boolean atualizarConta(Conta conta) {
        String sql;
        PreparedStatement stmt = null;

        try {
           
            int idConta;

            if (conta instanceof ContaCorrente) {
                
                String query = "SELECT id_conta FROM conta WHERE numero_conta = ?;";
                PreparedStatement selectStmt = connection.prepareStatement(query);
                selectStmt.setString(1, conta.getNumero());
                
                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    idConta = rs.getInt("id_conta");
                } else {
                    throw new IllegalArgumentException("Conta não encontrada no banco de dados.");
                }
                rs.close();
                selectStmt.close();

                sql = "UPDATE conta_corrente SET limite = ?, data_vencimento = ? WHERE id_conta = ?";
                stmt = connection.prepareStatement(sql);
                ContaCorrente cc = (ContaCorrente) conta;
                stmt.setDouble(1, cc.getLimite());
                stmt.setString(2, cc.getDataVencimento().toString());
                stmt.setInt(3, idConta);

            } else if (conta instanceof ContaPoupanca) {
                
                String query = "SELECT id_conta FROM conta WHERE numero_conta = ?;";
                PreparedStatement selectStmt = connection.prepareStatement(query);
                selectStmt.setString(1, conta.getNumero());

                ResultSet rs = selectStmt.executeQuery();
                if (rs.next()) {
                    idConta = rs.getInt("id_conta");
                } else {
                    throw new IllegalArgumentException("Conta não encontrada no banco de dados.");
                }
                rs.close();
                selectStmt.close();


                sql = "UPDATE conta_poupanca SET taxa_rendimento = ? WHERE id_conta = ?";
                stmt = connection.prepareStatement(sql);
                ContaPoupanca cp = (ContaPoupanca) conta;
                stmt.setDouble(1, cp.getTaxaRendimento());
                stmt.setInt(2, idConta);
            } else {
                throw new IllegalArgumentException("Tipo de conta não suportado.");
            }


            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
