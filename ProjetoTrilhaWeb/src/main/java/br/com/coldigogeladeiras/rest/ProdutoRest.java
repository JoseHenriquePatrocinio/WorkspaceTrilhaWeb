package br.com.coldigogeladeiras.rest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.jdbc.JDBCProdutoDAO;
import br.com.coldigogeladeiras.modelo.Produto;

@Path("produto")
public class ProdutoRest extends UtilRest {

	/* método de acesso a ele é POST */
	@POST

	/* define o caminho como produto/inserir */
	@Path("/inserir")

	/* aguarda alguma informação do lado cliente */
	/* algo deve ser recebido para ele funcionar corretamente */
	@Consumes("application/*")

	/* deve retornar um objeto Response */
	public Response inserir(String produtoParam) {

		try {

			/* converte um conteúdo do formato Json para uma classe modelo especificada */
			Produto produto = new Gson().fromJson(produtoParam, Produto.class);

			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();

			/*
			 * cria uma nova instancia do jdbcproduto e associa com objeto conexao para
			 * interagir com o banco de dados
			 */
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			/* o retorno recebe o objeto produto como parametro para inserção dos dados */
			boolean retorno = jdbcProduto.inserir(produto);
			String msg;

			if (retorno) {
				msg = "Produto cadastrado com sucesso";
			} else {
				msg = "Erro ao cadastrar produto";
			}

			conec.fecharConexao();
			return this.buildResponse(msg);

		} catch (Exception e) {
			/* cobrimos os possíveis erros desse método */

			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}

	@GET
	@Path("/buscar")
	@Consumes("application/*")
	/* produz como resultado informações no formato Json */
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorNome(@QueryParam("valorBusca") String nome) {
		 /*passando o valor da chave valorBusca para a variável nome*/

		try {
			
			
			List<JsonObject> listaProdutos = new ArrayList<JsonObject>();
			
			/*preparação para conectar ao bd*/
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			
			/*chamando o metodo de busca que vai retornar uma lista*/
			listaProdutos = jdbcProduto.buscarPorNome(nome);
			conec.fecharConexao();
			
			String json = new Gson().toJson(listaProdutos);
			return this.buildResponse(json);

		} catch (Exception e) {

			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}
	
	@DELETE
	@Path("/excluir/{id}")
	@Consumes("application/*")
	/*o parametro de caminho é o id e será armenado em uma variavel int*/
	public Response excluir(@PathParam("id") int id) {
		
		try {
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			
			boolean retorno = jdbcProduto.deletar(id);
			
			String msg;
			if(retorno) {
				msg = "Produto excluído com sucesso";
			}else {
				msg = "Erro ao excluir produto";
			}
			
			conec.fecharConexao();
			
			return this.buildResponse(msg);
			
		}catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	
	@GET
	@Path("/buscarPorId")
	@Consumes("application/*")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorNome(@QueryParam("id") int id) {

		try {
			
			Produto produto = new Produto();
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			
			produto = jdbcProduto.buscarPorId(id);
			
			conec.fecharConexao();
			
			return this.buildResponse(produto);
		} catch (Exception e) {

			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}

	}
	
	@PUT
	@Path("/alterar")
	@Consumes("application/*")
	public Response alterar(String produtoParam) {
		
		try {
			Produto produto = new Gson().fromJson(produtoParam, Produto.class);
			Conexao conec = new Conexao();
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			
			boolean retorno = jdbcProduto.alterar(produto);
			String msg="";
			
			if(retorno) {
				msg = "Produto alterado com sucesso";
			}else {
				msg = "Erro ao alterar produto";
			}
			
			conec.fecharConexao();
			return this.buildResponse(msg);
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

}
