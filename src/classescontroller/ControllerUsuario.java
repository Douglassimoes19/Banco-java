package classescontroller;

import java.sql.Date;
import java.time.LocalDate;

import classesdao.usuarioDao;
import classesmodel.Cliente;
import classesmodel.*;
import classesmodel.Endereco;

public class ControllerUsuario {

    private usuarioDao usuarioDao;

    public ControllerUsuario(usuarioDao usuarioDAO) {
        this.usuarioDao = usuarioDAO;
    }

    // Método para autenticar um cliente ou funcionário
    public Cliente autenticarCliente(String cpf, String senha) {
        return usuarioDao.autenticarCliente(cpf, senha);
    }

    public Funcionario autenticarFuncionario(String cpf, String senha) {
        return usuarioDao.autenticarFuncionario(cpf, senha);
    }

    // Método para cadastrar um novo usuário (Cliente ou Funcionário)
    public boolean cadastrarUsuario(int id,String cpf, String nome, LocalDate dataNascimento, String telefone, Endereco endereco, String senha, String tipo, String codigoFuncionario, String cargo) {
        Usuario usuario;

        // Verificando o tipo e instanciando a classe correta
        if (tipo.equalsIgnoreCase("CLIENTE")) {
            usuario = new Cliente(id, cpf, nome, dataNascimento, telefone ,endereco, senha, tipo);
        } else if (tipo.equalsIgnoreCase("FUNCIONARIO")) {
        	
            usuario =  new Funcionario(id, cpf, nome, dataNascimento, telefone, endereco, senha, tipo, codigoFuncionario, cargo );  // Ajuste para o construtor correto
        } else {
            return false; // Tipo inválido
        }

        return usuarioDao.cadastrarUsuario(usuario);  // Passando o objeto de usuário concreto para a DAO
    }

    public boolean atualizarUsuario(int id,String cpf, String nome, LocalDate dataNascimento, String telefone, Endereco endereco, String senha, String tipo, String codigoFuncionario, String cargo) {
        Usuario usuario;

        // Verificando o tipo e instanciando a classe correta
        if (tipo.equalsIgnoreCase("CLIENTE")) {
            usuario = new Cliente(id, cpf, nome, dataNascimento, telefone ,endereco, senha, tipo);
        } else if (tipo.equalsIgnoreCase("FUNCIONARIO")) {
        	
            usuario =  new Funcionario(id, cpf, nome, dataNascimento, telefone, endereco, senha, tipo, codigoFuncionario, cargo );  // Ajuste para o construtor correto
        } else {
            return false; // Tipo inválido
        }

        return usuarioDao.atualizarUsuario(usuario);  // Passando o objeto de usuário concreto para a DAO
    }


    // Método para deletar um usuário
    public boolean deletarUsuario(String cpf, String tipo) {
        return usuarioDao.deletarUsuario(cpf, tipo);
    }
}
