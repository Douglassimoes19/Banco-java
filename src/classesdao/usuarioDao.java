package classesdao;

import java.sql.*;
import classesmodel.*;
import classesutil.dbutil;

public class usuarioDao {
    private Connection connection;

    public Cliente autenticarCliente(String cpf, String senha) {
        String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ? AND tipo_usuario = 'CLIENTE';";
        Cliente cliente = null;

        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento"),
                            rs.getString("telefone"),
                            null, // Dependentes ou outros relacionamentos podem ser carregados em outro método
                            rs.getString("tipo_usuario"),
                            rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public Funcionario autenticarFuncionario(String cpf, String senha) {
        String sql = "SELECT * FROM usuario, funcionario WHERE cpf = ? AND senha = ? AND tipo_usuario = 'FUNCIONARIO';";
        Funcionario funcionario = null;

        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	funcionario = new Funcionario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento"),
                            rs.getString("telefone"),
                            null, // Endereço pode ser carregado separadamente, se necessário
                            rs.getString("tipo_usuario"),
                            rs.getString("senha"),
                            rs.getString("codigo_funcionario"), // Novo campo na tabela
                            rs.getString("cargo")              // Novo campo na tabela
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (cpf, nome, data_nascimento, telefone, senha, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setDate(3, usuario.getDataNascimento());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, usuario.getTipouser());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarUsuario(Usuario usuario) {
        String query = "UPDATE usuario SET nome = ?, data_nascimento = ?, telefone = ?, senha = ? WHERE cpf = ? AND tipo_usuario = ?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, usuario.getDataNascimento());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getCpf());
            stmt.setString(6, usuario.getTipouser());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deletarUsuario(String cpf, String tipoUsuario) {
        String sql = "DELETE FROM usuario WHERE cpf = ? AND tipo_usuario = ?";

        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, tipoUsuario);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        String sql = "SELECT * FROM usuario WHERE cpf = ?";
        Usuario usuario = null;

        try (Connection conn = dbutil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipo_usuario");
                    if ("CLIENTE".equalsIgnoreCase(tipoUsuario)) {
                        usuario = new Cliente(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("cpf"),
                                rs.getDate("data_nascimento"),
                                rs.getString("telefone"),
                                null,
                                rs.getString("tipo_usuario"),
                                rs.getString("senha")
                        );
                    } else if ("FUNCIONARIO".equalsIgnoreCase(tipoUsuario)) {
                    	usuario = new Funcionario(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getString("cpf"),
                                rs.getDate("data_nascimento"),
                                rs.getString("telefone"),
                                null, // Endereço pode ser carregado separadamente, se necessário
                                rs.getString("tipo_usuario"),
                                rs.getString("senha"),
                                rs.getString("codigo_funcionario"), // Novo campo na tabela
                                rs.getString("cargo")              // Novo campo na tabela
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
