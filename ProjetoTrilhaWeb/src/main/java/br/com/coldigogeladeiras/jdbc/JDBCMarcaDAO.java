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

	public List<Marca> buscar() {

		String comando = "SELECT * FROM marcas";
		List<Marca> listMarcas = new ArrayList<Marca>();
		Marca marca = null;

		try {

			Statement stmt = conexao.createStatement();

			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {
				marca = new Marca();
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				marca.setId(id);
				marca.setNome(nome);
				listMarcas.add(marca);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listMarcas;
	}

	public boolean inserir(Marca marca) {
		String comando = "INSERT INTO marcas" + "(id, nome, data)" + "VALUES(?,?,?)";

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

	public List<JsonObject> buscarPorMarca(String nome) {

		String comando = "SELECT * FROM marcas ";

		if (!nome.equals("")) {
			comando += "WHERE nome LIKE '%" + nome + "%'";
		}

		List<JsonObject> listaMarcas = new ArrayList<JsonObject>();
		JsonObject marca = null;

		try {
			Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nomeMarca = rs.getString("nome");
				String data = rs.getString("data");
				String status = rs.getString("status");

				marca = new JsonObject();
				marca.addProperty("id", id);
				marca.addProperty("nomeMarca", nomeMarca);
				marca.addProperty("data", data);
				marca.addProperty("status", status);

				listaMarcas.add(marca);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaMarcas;
	}

	public boolean deletar(int id) {
		String comando = "DELETE FROM marcas WHERE id = ?";

		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Marca buscarPorId(int id) {
		String comando = "SELECT * FROM marcas WHERE marcas.id = ?";
		Marca marca = new Marca();
		try {
			PreparedStatement p = this.conexao.prepareStatement(comando);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {

				String nome = rs.getString("nome");
				String data = rs.getString("data");

				marca.setId(id);
				marca.setNome(nome);
				marca.setData(data);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marca;
	}

	public boolean alterar(Marca marca) {
		String comando = "UPDATE marcas " + "SET nome=?, data=?" + " WHERE id=?";
		PreparedStatement p;

		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, marca.getNome());
			p.setString(2, marca.getData());
			p.setInt(3, marca.getId());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean inativar(int id) {
		 String comando = "UPDATE marcas " + "SET status = CASE WHEN status = 0 THEN 1 ELSE 0 END" + " WHERE id = ?";
		

		PreparedStatement p;
		try {

			p = conexao.prepareStatement(comando);
			p.setInt(1, id);
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
