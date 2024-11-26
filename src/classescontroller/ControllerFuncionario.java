package classescontroller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import classesdao.*;
import classesmodel.*; 

public class ControllerFuncionario {

	    private FuncionarioDao funcionarioDao;

	    public ControllerFuncionario() {
	        this.funcionarioDao = new FuncionarioDao();
	    }

	    public String  cadastrarFuncionario(Funcionario funcionario) {
	        try {
	            funcionarioDao.cadastrarFuncionario(funcionario);
	            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
	            return "sucesso";
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + e.getMessage());
	        }
			return "Error";
	    }

	    public List<Funcionario> listarFuncionarios() {
	        try {
	            return funcionarioDao.listarFuncionarios();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao listar funcionários: " + e.getMessage());
	            return Collections.emptyList();
	        }
	    }
	    
	    public boolean atualizarFuncionario(Funcionario funcionario) {
	        
	        return funcionarioDao.atualizarFuncionario(funcionario); // Retorna true se a atualização foi bem-sucedida
	    }

	    
	    public Funcionario buscarFuncionarioPorCodigo(String codigo) {
	        if (codigo == null || codigo.isEmpty()) {
	            throw new IllegalArgumentException("O código do funcionário não pode ser vazio.");
	        }
	        
	        return funcionarioDao.buscarPorCodigo(codigo);
	    }
}


