package br.com.coldigogeladeiras.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.com.coldigogeladeiras.jdbcinterface.MarcaDAO;
import br.com.coldigogeladeiras.modelo.Marca;

import java.util.List;

import com.google.gson.JsonObject;

import java.util.ArrayList;



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
	
	public boolean inserir(Marca marca) {
		String comando = "INSERT INTO marcas" + "(id, nome, data)"
				+ "VALUES(?,?,?)";

		PreparedStatement p;

		try {

			p = this.conexao.prepareStatement(comando);
			p.setInt(1, marca.getId());
			p.setString(2, marca.getNome());
			p.setString(3, marca.getData());

			p.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
