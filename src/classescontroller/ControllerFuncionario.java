package classescontroller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import classesdao.*;
import classesmodel.*; 

public class ControllerFuncionario {

	    private FuncionarioDao funcionarioDao;

	    public ControllerFuncionario(FuncionarioDao funcionarioDAO) {
	        this.funcionarioDao = funcionarioDAO;
	    }

	    public void cadastrarFuncionario(Funcionario funcionario) {
	        try {
	            funcionarioDao.cadastrarFuncionario(funcionario);
	            JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + e.getMessage());
	        }
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
}


