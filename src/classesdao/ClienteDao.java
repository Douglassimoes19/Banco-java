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
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, cliente.getDataNascimento());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getTipouser());
            stmt.setString(6, cliente.getSenha());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Atualizar cliente no banco de dados
    public boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, telefone = ?, tipo_usuario = ?, senha = ? WHERE id_usuario = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, cliente.getDataNascimento());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getTipouser());
            stmt.setString(6, cliente.getSenha());
            stmt.setInt(7, cliente.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
                        rs.getDate("data_nascimento"),
                        rs.getString("telefone"),
                        null, // Endereço seria tratado separadamente
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

    // Buscar todos os clientes
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
                    rs.getDate("data_nascimento"),
                    rs.getString("telefone"),
                    null, // Endereço seria tratado separadamente
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
                c.id_cliente = ?;
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

                    
                    System.out.println("passe aqui");
                    

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


}


