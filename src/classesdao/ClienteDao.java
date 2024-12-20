package classesdao;

import classesmodel.*;
import classesutil.dbutil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection connection;

    public ClienteDao() {
    	
    	this.connection = dbutil.getConnection();
    }

    // Inserir cliente no banco de dados
    public boolean inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, ?, ?);";
        String set = "INSERT INTO cliente(id_usuario) VALUES (?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	
        	// Converter a data de java.util.Date para java.sql.Date
        	System.out.println(cliente.getDataNascimento());
            //java.sql.Date sqlDataNascimento = new java.sql.Date(cliente.getDataNascimento().getTime());
            
            if (cliente.getDataNascimento() instanceof LocalDate) {
                LocalDate dataNascimentoUtil = cliente.getDataNascimento();
                //java.sql.Date dataNascimentoSql = new java.sql.Date(dataNascimentoUtil);
                stmt.setString(3, cliente.getDataNascimento().toString());
            } else {
                // Caso a data não seja do tipo java.util.Date, tratar o erro ou logar a informação
                System.out.println("Erro: A data de nascimento não é uma instância de java.util.Date");
            }
            
        	
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            //stmt.setDate(3, cliente.getDataNascimento());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getTipouser());
            stmt.setString(6, cliente.getSenha());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        
                        int idUsuario = generatedKeys.getInt(1);
                        
                        try (PreparedStatement stmtUpdate = connection.prepareStatement(set, Statement.RETURN_GENERATED_KEYS)) {
                            stmtUpdate.setInt(1, idUsuario);
                            int rowsCliente = stmtUpdate.executeUpdate();

                            if (rowsCliente > 0) {
                                try (ResultSet generatedKeysCliente = stmtUpdate.getGeneratedKeys()) {
                                    if (generatedKeysCliente.next()) {
                                        int idCliente = generatedKeysCliente.getInt(1); // Obter id_cliente gerado
                                        cliente.setId(idCliente); // Configurar no objeto cliente
                                        System.out.println("ID do Cliente gerado: " + idCliente);
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Atualizar cliente no banco de dados
	/*
	 * public boolean atualizarCliente(Cliente cliente) { String sql =
	 * "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, tipo_usuario = ?, senha = ? WHERE id_usuario = ?;"
	 * ; try (PreparedStatement stmt = connection.prepareStatement(sql,
	 * Statement.RETURN_GENERATED_KEYS)) { if (cliente.getDataNascimento()
	 * instanceof LocalDate) { LocalDate dataNascimentoUtil =
	 * cliente.getDataNascimento(); //java.sql.Date dataNascimentoSql = new
	 * java.sql.Date(dataNascimentoUtil); stmt.setString(3,
	 * cliente.getDataNascimento().toString()); } else { // Caso a data não seja do
	 * tipo java.util.Date, tratar o erro ou logar a informação System.out.
	 * println("Erro: A data de nascimento não é uma instância de java.util.Date");
	 * }
	 * 
	 * stmt.setString(1, cliente.getNome()); stmt.setString(2, cliente.getCpf());
	 * //stmt.setDate(3, cliente.getDataNascimento()); stmt.setString(4,
	 * cliente.getTelefone()); stmt.setString(5, cliente.getTipouser());
	 * stmt.setString(6, cliente.getSenha()); stmt.setInt(7, cliente.getId());
	 * 
	 * return stmt.executeUpdate() > 0; } catch (SQLException e) {
	 * e.printStackTrace(); } return false; }
	 */

    // Excluir cliente do banco de dados
    public boolean excluirCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Buscar cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_usuario = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        null, // Endereço sera tratado separadamente
                        rs.getString("tipo_usuario"),
                        rs.getString("senha")
                    );
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Busca  todos os clientes
    public List<Cliente> buscarTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente, usuario;";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("telefone"),
                    null, // Endereço sera tratado separadamente
                    rs.getString("tipo_usuario"),
                    rs.getString("senha")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    
    public List<Conta> buscarContasPorCliente(Cliente cliente) {
        List<Conta> contas = new ArrayList<>();

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
                c.id_cliente = ?;;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente.getId());

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    // Dados comuns entre as contas
                    String numeroConta = resultSet.getString("numero_conta");
                    String agencia = resultSet.getString("agencia");
                    double saldo = resultSet.getDouble("saldo");
                    String tipoConta = resultSet.getString("tipo_conta");

                    
                    //System.out.println("passe aqui");
                    

                    // Verifica o tipo de conta e instancia o objeto correspondente
                    if ("POUPANCA".equalsIgnoreCase(tipoConta)) {
                        double taxaRendimento = resultSet.getDouble("cp.taxa_rendimento");
                        contas.add(new ContaPoupanca(numeroConta, agencia, saldo,tipoConta, cliente, taxaRendimento));
                    } else if ("CORRENTE".equalsIgnoreCase(tipoConta)) {
                    	
                        double limite = resultSet.getDouble("limite");
                        LocalDate dataVencimento = resultSet.getDate("data_vencimento").toLocalDate();
                        contas.add(new ContaCorrente(numeroConta, agencia, saldo, cliente, limite,tipoConta, dataVencimento));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }
    
 // Método para verificar se o cliente existe no banco
    public boolean clienteExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?;";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Cliente buscarPorCpf(String cpf) {
        String sqlCliente = "SELECT u.id_usuario, u.nome, u.cpf, u.data_nascimento, u.telefone, u.senha, e.* "
                + "FROM usuario u "
                + "JOIN endereco e ON u.id_usuario = e.id_usuario "
                + "WHERE u.cpf = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sqlCliente,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Montar o endereço
                Endereco endereco = new Endereco(
                    rs.getString("cep"),
                    rs.getString("local"),
                    rs.getInt("numero_casa"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("estado")
                );

                // Montar o cliente
                return new Cliente(
                    rs.getInt("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("telefone"),
                    endereco,
                    "CLIENTE",
                    rs.getString("senha")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Retorna null se o cliente não for encontrado
    }
    
    public boolean atualizarCliente(Cliente cliente) {

        
        String sqlUsuario = "UPDATE usuario SET nome = ?, cpf = ?, telefone = ? WHERE id_usuario = ?;";
        String sqlEndereco = "UPDATE endereco SET local = ?, numero_casa = ?, cep = ?, bairro = ?, cidade = ?, estado = ? WHERE id_usuario = ?;";
        
        // Query para buscar o ID do usuário relacionado ao cliente
        String sqlBuscarIdUsuario = "SELECT id_usuario FROM cliente WHERE id_cliente = ?;";

        try (Connection connection = dbutil.getConnection()) {
            // Desabilita o auto-commit para gerenciar a transação manualmente
            connection.setAutoCommit(false);

            // Buscar o id_usuario correspondente ao id_cliente
            int idUsuario;
            try (PreparedStatement stmtBuscar = connection.prepareStatement(sqlBuscarIdUsuario)) {
                stmtBuscar.setInt(1, cliente.getId()); // Obtém o ID do cliente
                try (ResultSet rs = stmtBuscar.executeQuery()) {
                    if (rs.next()) {
                        idUsuario = rs.getInt("id_usuario");
                    } else {
                        // Caso não encontre o id_usuario, aborta a operação
                        throw new Exception("Usuário não encontrado para o cliente informado.");
                    }
                }
            }

            // Atualizando dados na tabela 'usuario'
            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, cliente.getNome());
                stmtUsuario.setString(2, cliente.getCpf());
                stmtUsuario.setString(3, cliente.getTelefone());
                stmtUsuario.setInt(4, idUsuario); // Usa o id_usuario recuperado
                stmtUsuario.executeUpdate();
            }

            // Atualizando dados na tabela 'endereco'
            try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco)) {
                stmtEndereco.setString(1, cliente.getEndereco().getLocal());
                stmtEndereco.setInt(2, cliente.getEndereco().getNumeroCasa());
                stmtEndereco.setString(3, cliente.getEndereco().getCep());
                stmtEndereco.setString(4, cliente.getEndereco().getBairro());
                stmtEndereco.setString(5, cliente.getEndereco().getCidade());
                stmtEndereco.setString(6, cliente.getEndereco().getEstado());
                stmtEndereco.setInt(7, idUsuario); // Usa o id_usuario recuperado
                stmtEndereco.executeUpdate();
            }

            // Se todas as operações foram bem-sucedidas, confirma a transação
            connection.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro, desfaz todas as operações feitas
            try (Connection connection = dbutil.getConnection()) {
                connection.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }
}



