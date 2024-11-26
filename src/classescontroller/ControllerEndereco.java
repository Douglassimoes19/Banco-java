package classescontroller;

import classesdao.EnderecoDao;
import classesmodel.Endereco;
import classesmodel.Funcionario;

public class ControllerEndereco {
    private final EnderecoDao enderecoDAO;

    public ControllerEndereco() {
        this.enderecoDAO = new EnderecoDao();
    }

    public String cadastrarEndereco(Funcionario funcionario, Endereco endereco) {
        try {
            // Validação básica dos dados
            if (funcionario == null || endereco == null) {
                return "Funcionário ou Endereço inválido.";
            }

            if (endereco.getLocal().isEmpty() || endereco.getCep().isEmpty() || endereco.getCidade().isEmpty()) {
                return "Preencha todos os campos obrigatórios do endereço.";
            }

            // Chamar o DAO para salvar o endereço
            boolean sucesso = enderecoDAO.insertEndereco(funcionario, endereco);

            if (sucesso) {
                return "sucesso";
            } else {
                return "Erro ao cadastrar endereço no banco de dados.";
            }
        } catch (Exception e) {
            return "Erro ao cadastrar endereço: " + e.getMessage();
        }
    }
}
