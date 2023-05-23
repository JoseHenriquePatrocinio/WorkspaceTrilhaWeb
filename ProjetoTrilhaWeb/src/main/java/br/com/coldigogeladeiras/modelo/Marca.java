package br.com.coldigogeladeiras.modelo;
import java.io.Serializable;


//	Aqui foi feito os getters and setters da tabela 'marcas' do banco de dados
//	A implementação dessa interface permite que objetos da classe Marca sejam 
//	convertidos em uma sequência de bytes, que podem ser armazenados pela rede

public class Marca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
