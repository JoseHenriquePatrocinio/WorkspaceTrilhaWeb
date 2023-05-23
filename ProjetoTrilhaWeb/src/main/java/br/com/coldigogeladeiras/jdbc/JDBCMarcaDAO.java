package br.com.coldigogeladeiras.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;
import java.util.List;
import java.util.ArrayList;


//	implementa a interface MarcaDAO, que é responsável por definir o contrato para operações de acesso a dados da tabela marcas 
// 	faz uma consulta sql que traz as marcas e tranforma os dados em objetos para por na lista
//	separa a lógica de acesso a dados

public class JDBCMarcaDAO implements MarcaDAO {
	
	private Connection conexao;
	
	public JDBCMarcaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Marca> buscar(){
		
		String comando = "SELECT * FROM marcas";
		List<Marca> listMarcas = new ArrayList<Marca>();
		Marca marca = null;
		
		try {
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(comando);
			
			while(rs.next()) {
				marca = new Marca();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				marca.setId(id);
				marca.setNome(nome);
				listMarcas.add(marca);
			}
			
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return listMarcas;
	}

}
