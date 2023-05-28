package br.com.coldigogeladeiras.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;
import br.com.coldigogeladeiras.jdbc.JDBCProdutoDAO;


//	Essa classe vai tratar das das solicitações HTTP GET
//	Vai estabeler um conexão com o banco de dados usando a classe conexao
//	Uma instancia da JDBCMarcaDAO é usada para buscar todas as marcas
//	O resultado vai ser armazenado em uma lista
//	Retorna a lista de marcas usando o metodo buildresponse ou builderro  da classe utilrest

@Path("marca")
public class MarcaRest extends UtilRest {
	
	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar() {
		
		try {
		List<Marca> listaMarcas = new ArrayList<Marca>();
		
		Conexao conec = new Conexao();
		Connection conexao = conec.abrirConexao();
		JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
		listaMarcas = jdbcMarca.buscar();
		conec.fecharConexao();
		return this.buildResponse(listaMarcas);
		
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
			
		}
		
	}
	

	@POST
	@Path("/inserir")
	@Consumes("application/*")
	public Response inserir(String marcaParam) {

		try {

			Marca marca = new Gson().fromJson(marcaParam, Marca.class);

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();

			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			boolean retorno = jdbcMarca.inserir(marca);
			String msg;

			if (retorno) {
				msg = "Marca cadastrada com sucesso";
			} else {
				msg = "Erro ao cadastrar marca";
			}

			conec.fecharConexao();
			return this.buildResponse(msg);

		} catch (Exception e) {

			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}
	
	@GET
	@Path("/buscarMarca")
	@Consumes("application/*")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorNome(@QueryParam("valorBusca") String nome) {

		try {
			
			
			List<JsonObject> listaMarcas = new ArrayList<JsonObject>();
			
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCMarcaDAO jdbcMarca = new JDBCMarcaDAO(conexao);
			
			listaMarcas = jdbcMarca.buscarPorMarca(nome);
			conec.fecharConexao();
			
			String json = new Gson().toJson(listaMarcas);
			return this.buildResponse(json);

		} catch (Exception e) {

			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}

}
