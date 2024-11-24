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
	            stmtUsuario.setDate(3, (funcionario.getDataNascimento()));
	            stmtUsuario.setString(4, funcionario.getTelefone());
	            stmtUsuario.setString(5, funcionario.getSenha());
	            stmtUsuario.executeUpdate();

	            ResultSet rs = stmtUsuario.getGeneratedKeys();
	            if (rs.next()) {
	                int idUsuario = rs.getInt(1);
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
	                    rs.getDate("data_nascimento"),
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
	

}
