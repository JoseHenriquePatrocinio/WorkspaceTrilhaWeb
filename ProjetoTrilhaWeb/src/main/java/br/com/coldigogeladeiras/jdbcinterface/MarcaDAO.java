package br.com.coldigogeladeiras.jdbcinterface;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.modelo.Marca;


//	Interface que define o contrato para implementação das operações de acesso do banco de dados
// 	Possui o metodo buscar que retorna a lista de marcas 

public interface MarcaDAO {
	
	public List<Marca> buscar();
	public boolean inserir(Marca marca);


}
