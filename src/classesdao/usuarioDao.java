
package classesdao;

import java.sql.Connection;
import java.sql.*;

import classesmodel.*;
import classesutil.dbutil;

public class usuarioDao {

	public void adicionarUser(Usuario user) {
		String query = "INSERT INTO usuario(nome, cpf, data_nascimento, telefone, tipo_usuario, senha) values(?,?,?,?,?,?)";
		try {
			Connection conn = dbutil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getCpf());
			stmt.setDate(3, user.getDataNascimento());
			stmt.setString(4, user.getTelefone());
			stmt.setString(5, user.getTipouser());
			stmt.setString(6, user.getSenha());
			stmt.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Usuario buscarPorCpf(String cpf) {
        // Aqui você vai fazer a consulta ao banco de dados para buscar o usuário
        String query = "SELECT * FROM usuario WHERE cpf = ?";
        try {
        	
        	Connection conn = dbutil.getConnection(); 
       	 	PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	Cliente user = new Cliente(
            				 rs.getInt("Id"),
                             rs.getString("nome"),
                             rs.getString("cpf"),
                             rs.getDate("data_nascimento"),
                             rs.getString("telefone"),
                             null,
                             rs.getString("tipo_usuario"),
                             rs.getString("senha"));
                    
					/*
					 * // define se é um Cliente ou funcionario String tipoUsuario =
					 * rs.getString("tipo_usuario");
					 * 
					 * if ("cliente".equalsIgnoreCase(tipoUsuario)) { user = new Cliente();
					 * ((Cliente)
					 * user).setParametroEspecifico(rs.getString("parametro_especifico")); // Ajuste
					 * para o campo específico de Cliente } else { // Para outros tipos de usuários,
					 * use outra classe concreta que herde de Usuario user = new
					 * OutroTipoDeUsuario(); // Exemplo de outra classe concreta
					 * ((OutroTipoDeUsuario)
					 * user).setOutroParametro(rs.getString("outro_parametro")); }
					 */
                    
                    rs.close();
                    stmt.close();
                    conn.close();
                    
                    return user;
            	}
            }catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se não encontrar o usuário

	}
}
