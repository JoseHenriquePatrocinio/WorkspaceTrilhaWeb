package br.com.coldigogeladeiras.jdbcinterface;
import java.util.List;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

//	Interface que define o contrato para implementação das operações de acesso do banco de dados
// 	Possui o metodo buscar que retorna a lista de marcas 

public interface MarcaDAO {
	
	public List<Marca> buscar();
	public boolean inserir(Marca marca);

}
