package br.com.coldigogeladeiras.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import br.com.coldigogeladeiras.bd.Conexao;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.jdbc.JDBCMarcaDAO;


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

}
