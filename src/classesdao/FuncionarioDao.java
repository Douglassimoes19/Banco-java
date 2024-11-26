package classesdao;

import classesmodel.*;
import classesutil.dbutil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

	    private Connection connection;

	    public FuncionarioDao() {
	    	connection = dbutil.getConnection();
	    }

	    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException {
	        String sqlUsuario = "INSERT INTO usuario (nome, cpf, data_nascimento, telefone, tipo_usuario, senha) VALUES (?, ?, ?, ?, 'FUNCIONARIO', ?)";
	        String sqlFuncionario = "INSERT INTO funcionario (codigo_funcionario, cargo, id_usuario) VALUES (?, ?, ?)";

	        try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
	            stmtUsuario.setString(1, funcionario.getNome());
	            stmtUsuario.setString(2, funcionario.getCpf());
	            stmtUsuario.setString(3, (funcionario.getDataNascimento().toString()));
	            stmtUsuario.setString(4, funcionario.getTelefone());
	            stmtUsuario.setString(5, funcionario.getSenha());
	            stmtUsuario.executeUpdate();

	            ResultSet rs = stmtUsuario.getGeneratedKeys();
	            if (rs.next()) {
	                int idUsuario = rs.getInt(1);
	                funcionario.setId(idUsuario);
	                try (PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario)) {
	                    stmtFuncionario.setString(1, funcionario.getCodigoFuncionario());
	                    stmtFuncionario.setString(2, funcionario.getCargo());
	                    stmtFuncionario.setInt(3, idUsuario);
	                    stmtFuncionario.executeUpdate();
	                }
	            }
	        }
	    }

	    public List<Funcionario> listarFuncionarios() throws SQLException {
	        String sql = "SELECT f.*, u.* FROM funcionario f INNER JOIN usuario u ON f.id_usuario = u.id_usuario";
	        List<Funcionario> funcionarios = new ArrayList<>();
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Funcionario funcionario = new Funcionario(
	                    rs.getInt("id_usuario"),
	                    rs.getString("nome"),
	                    rs.getString("cpf"),
	                    rs.getDate("data_nascimento").toLocalDate(),
	                    rs.getString("telefone"),
	                    null,
	                    "FUNCIONARIO",
	                    rs.getString("senha"),
	                    rs.getString("codigo_funcionario"),
	                    rs.getString("cargo")
	                );
	                funcionarios.add(funcionario);
	            }
	        }
	        return funcionarios;
	    }
	    
	    public Funcionario buscarPorCodigo(String codigo) {
	        // Consultas SQL
	        String sqlFuncionario = "SELECT codigo_funcionario, cargo, id_usuario FROM funcionario WHERE codigo_funcionario = ?;";
	        String sqlUsuario = "SELECT nome, cpf, data_nascimento, telefone, senha FROM usuario WHERE id_usuario = ?;";
	        String sqlEndereco = "SELECT cep, local, numero_casa, bairro, cidade, estado FROM endereco WHERE id_usuario = ?;";

	        try {
	            // Primeira consulta: Buscar dados do funcionário
	        	System.out.println("chegou o codigo" + codigo);
	            try (PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario, Statement.RETURN_GENERATED_KEYS)) {
	                stmtFuncionario.setString(1, codigo);
	                ResultSet rsFuncionario = stmtFuncionario.executeQuery();

	                if (rsFuncionario.next()) {
	                    // Capturar dados do funcionário
	                    String codigoFuncionario = rsFuncionario.getString("codigo_funcionario");
	                    String cargo = rsFuncionario.getString("cargo");
	                    int idUsuario = rsFuncionario.getInt("id_usuario");
	                    
	                    System.out.println(idUsuario);

	                    // Segunda consulta: Buscar dados do usuário
	                    try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario,Statement.RETURN_GENERATED_KEYS)) {
	                        stmtUsuario.setInt(1, idUsuario);
	                        ResultSet rsUsuario = stmtUsuario.executeQuery();

	                        if (rsUsuario.next()) {
	                            // Capturar dados do usuário
	                            String nome = rsUsuario.getString("nome");
	                            String cpf = rsUsuario.getString("cpf");
	                            java.sql.Date dataNascimentoSQL = rsUsuario.getDate("data_nascimento");
	                            LocalDate dataNascimento = dataNascimentoSQL != null ? dataNascimentoSQL.toLocalDate() : null;
	                            String telefone = rsUsuario.getString("telefone");
	                            String senha = rsUsuario.getString("senha");

	                            //  Buscar dados do endereço
	                            try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco,Statement.RETURN_GENERATED_KEYS)) {
	                                stmtEndereco.setInt(1, idUsuario);
	                                ResultSet rsEndereco = stmtEndereco.executeQuery();

	                                System.out.println("passou");
	                                if (rsEndereco.next()) {
	                                    // Capturar dados do endereço
	                                	System.out.println("passou tambem");
	                                    String cep = rsEndereco.getString("cep");
	                                    System.out.println(cep);
	                                    String local = rsEndereco.getString("local");
	                                    int numeroCasa = rsEndereco.getInt("numero_casa");
	                                    String bairro = rsEndereco.getString("bairro");
	                                    String cidade = rsEndereco.getString("cidade");
	                                    String estado = rsEndereco.getString("estado");

	                                    // Criar objeto Endereco
	                                    Endereco endereco = new Endereco(cep, local, numeroCasa, bairro, cidade, estado);

	                                    // Criar objeto Funcionario e retornar
	                                    Funcionario novofunc = new Funcionario(
	                                        idUsuario,
	                                        nome,
	                                        cpf,
	                                        dataNascimento,
	                                        telefone,
	                                        endereco,
	                                        "FUNCIONARIO", // Exemplo de tipo de usuário
	                                        senha,
	                                        codigoFuncionario,
	                                        cargo
	                                    );
	                                    
	                                    System.out.println(novofunc.toString());
	                                    
	                                    return novofunc;
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Retorna null caso nenhum funcionário seja encontrado
	        return null;
	    }
	    
	    public boolean atualizarFuncionario(Funcionario funcionario) {
	        // SQL para buscar o id_usuario na tabela funcionario
	        String sqlBuscarIdUsuario = "SELECT id_usuario FROM funcionario WHERE codigo_funcionario = ?";
	        // SQL para atualizar as tabelas
	        String sqlFuncionario = "UPDATE funcionario SET cargo = ? WHERE codigo_funcionario = ?";
	        String sqlUsuario = "UPDATE usuario SET nome = ?, cpf = ?, telefone = ? WHERE id_usuario = ?";
	        String sqlEndereco = "UPDATE endereco SET local = ?, numero_casa = ?, cep = ?, bairro = ?, cidade = ?, estado = ? WHERE id_usuario = ?";

	        try (Connection connection = dbutil.getConnection()) {
	            // Desabilita o auto-commit para gerenciar a transação manualmente
	            connection.setAutoCommit(false);

	            // Buscar o id_usuario correspondente ao codigo_funcionario
	            int idUsuario;
	            try (PreparedStatement stmtBuscar = connection.prepareStatement(sqlBuscarIdUsuario)) {
	                stmtBuscar.setString(1, funcionario.getCodigoFuncionario());
	                try (ResultSet rs = stmtBuscar.executeQuery()) {
	                    if (rs.next()) {
	                        idUsuario = rs.getInt("id_usuario");
	                    } else {
	                        // Caso não encontre o id_usuario, aborta a operação
	                        throw new Exception("Usuário não encontrado para o código informado.");
	                    }
	                }
	            }

	            // Atualizando dados na tabela 'funcionario'
	            try (PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario)) {
	                stmtFuncionario.setString(1, funcionario.getCargo());
	                stmtFuncionario.setString(2, funcionario.getCodigoFuncionario());
	                stmtFuncionario.executeUpdate();
	            }

	            // Atualizando dados na tabela 'usuario'
	            try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
	                stmtUsuario.setString(1, funcionario.getNome());
	                stmtUsuario.setString(2, funcionario.getCpf());
	                stmtUsuario.setString(3, funcionario.getTelefone());
	                stmtUsuario.setInt(4, idUsuario); // Usa o id_usuario recuperado
	                stmtUsuario.executeUpdate();
	            }

	            // Atualizando dados na tabela 'endereco'
	            try (PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco)) {
	                stmtEndereco.setString(1, funcionario.getEndereco().getLocal());
	                stmtEndereco.setInt(2, funcionario.getEndereco().getNumeroCasa());
	                stmtEndereco.setString(3, funcionario.getEndereco().getCep());
	                stmtEndereco.setString(4, funcionario.getEndereco().getBairro());
	                stmtEndereco.setString(5, funcionario.getEndereco().getCidade());
	                stmtEndereco.setString(6, funcionario.getEndereco().getEstado());
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
