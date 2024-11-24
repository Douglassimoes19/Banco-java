package classesmodel;

import java.sql.Connection;
import javax.swing.JOptionPane;
import classesutil.dbutil;
import classesviewer.*;

public class BancoMalvader {

	public static void main(String[] args) {
		
		 // Connection conn = null; 
		 // try { 
		 //	  conn = dbutil.getConnection(); 
		 //	  if (conn != null) { 
		 //		  JOptionPane.showMessageDialog(null, "Conexão bem-sucedida com o banco de dados!");
		 //	  } else {
		 //		  JOptionPane.showMessageDialog(null,"Falha na conexão com o banco de dados.");
		 //		  } 
		//	  }  finally { 
		 //		   dbutil.desconectar(conn); 
		 //	  }
		 
		
		//new menuviewer().setVisible(true);
		//new LoginUsuarioViewer().setVisible(true);v
		//new LoginFuncionarioViewer().setVisible(true);x
		//new MenuFuncionarioViewer().setVisible(true);
		new AberturaContaPoupancaViewer().setVisible(true);
		//new AberturaContaCorrenteViewer().setVisible(true);
		//new AberturaContaMenuViewer().setVisible(true);
		//new AlterarDadosClienteViewer().setVisible(true);
		//new AlterarDadosContaViewer().setVisible(true);
		//new AlterarDadosFuncionarioViewer().setVisible(true);
		//new AlterarDadosMenuViewer().setVisible(true);
		//new CadastrarFuncionarioViewer().setVisible(true);
		//new ConsultaDadosContaViewer().setVisible(true);
		//new ConsultaDadosMenuViewer().setVisible(true);
		//new ConsultarDadosClienteViewer().setVisible(true);
		//new EncerramentoContaVeiwer().setVisible(true);
		//new GerarRelatoriosViewer().setVisible(true);
		//new DepositoViewer().setVisible(true);
		//new LimiteViewer().setVisible(true);
		//new MenuClienteViewer().setVisible(true);
		
    }
	 

    public void iniciarSistema(){
        // a implementar
    }
}