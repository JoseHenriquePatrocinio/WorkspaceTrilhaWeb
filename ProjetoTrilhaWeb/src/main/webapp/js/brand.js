COLDIGO.marca = new Object();

$(document).ready(function() {
	
	
	
	

	COLDIGO.marca.cadastrar = function() {


		var marca = new Object();
		marca.nome = document.frmAddMarca.nome.value;
		marca.data = document.frmAddMarca.data.value;

		if ((marca.nome == "") || (marca.data == "")) {
			COLDIGO.exibirAviso("Preencha todos os campos");
		} else {

			$.ajax({
				type: "POST",
				url: COLDIGO.PATH + "marca/inserir",
				data: JSON.stringify(marca),
				success: function(msg) {
					COLDIGO.exibirAviso(msg);
					$("#addMarca").trigger("reset");
				},
				error: function(info) {
					COLDIGO.exibirAviso("Erro ao cadastrar uma nova marca: " + info.status + "-" + info.statusText);
				}
			});
		}

	}
	
		COLDIGO.marca.buscar = function(){
		
		var valorBusca = $("#campoBuscaMarca").val();
		
		$.ajax({
			
			type:"GET",
			url: COLDIGO.PATH + "marca/buscarMarca",
			data: "valorBusca="+valorBusca,
			success:function(dados){
				
				alert(dados)
				dados = JSON.parse(dados);
				
				/*!!!!!!!!!!!*/
				$("#listaMarcas").html(COLDIGO.marca.exibir(dados));
				
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro ao consultar os contatos: " + info.status + " - " + info.statusText);
			}
			
			
		});
		
	};
	
	
	
		COLDIGO.marca.exibir = function(listaDeMarcas){
		
		var tabela = "<table>" + 
		"<tr>" + 
		"<th>Nome</th>" +
		"<th>Data</th>" +
		"<th class='acoes'>Ações</th>" +
		"</tr>";
		
		if(listaDeMarcas != undefined && listaDeMarcas.length > 0){
			
			for(var i=0; i<listaDeMarcas.length; i++){
				tabela += "<tr>" + 
				
				"<td>"+listaDeMarcas[i].nomeMarca+"</td>"+
				"<td>"+listaDeMarcas[i].data+"</td>"
/*				"<a onclick=\"COLDIGO.marca.exibirEdicao('"+listaDeMarcas[i].id+"')\"><img src='../../imgs/edit.png' alt='Editar registro'></a> " +
				"<a onclick=\"COLDIGO.marca.excluir('"+listaDeMarcas[i].id+"')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
				"</td>"+
				"</tr>"*/
			}
				
			
			
		}else if(listaDeMarcas == ""){
			tabela += "<tr><td colspan='6'> Nenhum registro encontrado</td></tr>";
		}
		tabela += "</table>";
		
		return tabela;
		
	};
	
	COLDIGO.marca.buscar();
	
	
	

});