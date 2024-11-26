package classesdao;

import classesmodel.*;
import classesutil.dbutil;
import java.sql.*;


public class EnderecoDao {
	 private Connection connection;
	
	public EnderecoDao (){
		connection = dbutil.getConnection();
	}
    public boolean insertEndereco(Funcionario funcionario, Endereco endereco) {
        String sql = "INSERT INTO endereco (cep,local, numero_casa, bairro, cidade, estado, id_usuario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
             PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Definir os valores no PreparedStatement
            stmt.setInt(7, funcionario.getId()); // Supondo que o ID do funcionário seja gerado e recuperado
           stmt.setString(2, endereco.getLocal());
            stmt.setInt(3, endereco.getNumeroCasa());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(1, endereco.getCep());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());

            // Executar o comando SQL
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi inserida
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
