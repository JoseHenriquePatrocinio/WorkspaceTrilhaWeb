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
				error: function() {
					COLDIGO.exibirAviso(msg);
				}
			});
		}

	}

	COLDIGO.marca.buscar = function() {

		var valorBusca = $("#campoBuscaMarca").val();

		$.ajax({

			type: "GET",
			url: COLDIGO.PATH + "marca/buscarMarca",
			data: "valorBusca=" + valorBusca,
			success: function(dados) {

				console.log(dados);
				dados = JSON.parse(dados);


				$("#listaMarcas").html(COLDIGO.marca.exibir(dados));

			},
			error: function(info) {
				COLDIGO.exibirAviso("Erro ao consultar os contatos: " + info.status + " - " + info.statusText);
			}


		});

	};



	COLDIGO.marca.exibir = function(listaDeMarcas) {

		var tabela = "<table>" +
			"<tr>" +
			"<th>Nome</th>" +
			"<th>Data</th>" +
			"<th class='acoes'>Ações</th>" +
			"<th class='acoes'>Status</th>" +
			"</tr>";

		if (listaDeMarcas != undefined && listaDeMarcas.length > 0) {

			for (var i = 0; i < listaDeMarcas.length; i++) {
				var checkbox = "<input type='checkbox' onclick=\"COLDIGO.marca.inativar('" + listaDeMarcas[i].id + "')\"/>";
				if (listaDeMarcas[i].status == 1) {
					checkbox = "<input type='checkbox' checked onclick=\"COLDIGO.marca.inativar('" + listaDeMarcas[i].id + "')\"/>";
				}

				tabela += "<tr>" +
					"<td>" + listaDeMarcas[i].nomeMarca + "</td>" +
					"<td>" + listaDeMarcas[i].data + "</td>" +
					"<td>" +
					"<a onclick=\"COLDIGO.marca.exibirEdicao('" + listaDeMarcas[i].id + "')\"><img src='../../imgs/edit.png' alt='Editar registro'></a> " +
					"<a onclick=\"COLDIGO.marca.excluir('" + listaDeMarcas[i].id + "')\"><img src='../../imgs/delete.png' alt='Excluir registro'></a>" +
					"</td>" +
					"<td>" +
					"<label class='switch'>" +
					checkbox +
					"<span class='slider round'></span>" +
					"</label>" +
					"</td>" +
					"</tr>";
			}
		} else if (listaDeMarcas == "") {
			tabela += "<tr><td colspan='6'> Nenhum registro encontrado</td></tr>";
		}
		tabela += "</table>";

		return tabela;
	};

	COLDIGO.marca.buscar();

	COLDIGO.marca.excluir = function(id) {
		if (confirm("Você tem certeza que deseja deletar?")) {
			$.ajax({
				type: "DELETE",
				url: COLDIGO.PATH + "marca/excluir/" + id,
				success: function(msg) {
					COLDIGO.exibirAviso(msg);
					COLDIGO.marca.buscar();
				},
				error: function() {
					COLDIGO.exibirAviso(msg);
				}
			});
		}
	};


	COLDIGO.marca.exibirEdicao = function(id) {
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "marca/buscarPorId",
			data: "id=" + id,
			success: function(marca) {

				console.log(marca);
				document.frmEditaMarca.idMarca.value = marca.id;
				document.frmEditaMarca.nome.value = marca.nome;
				document.frmEditaMarca.data.value = marca.data;


				var modalEditaMarca = {
					title: "Editar Marca",
					height: 400,
					width: 550,
					modal: true,
					buttons: {
						"Salvar": function() {
							COLDIGO.marca.editar();
						},
						"Cancelar": function() {
							$(this).dialog("close");
						}
					},
					close: function() {
						//empty
					}
				};

				$("#modalEditaMarca").dialog(modalEditaMarca);

			},
			error: function(info) {
				COLDIGO.exibirAviso("Erro ao buscar marca para edição: " + info.status + " - " + info.statusText);
			}
		});
	};

	COLDIGO.marca.editar = function() {
		var marca = new Object();
		marca.id = document.frmEditaMarca.idMarca.value;
		marca.nome = document.frmEditaMarca.nome.value;
		marca.data = document.frmEditaMarca.data.value;


		$.ajax({
			type: "PUT",
			url: COLDIGO.PATH + "marca/alterar",
			data: JSON.stringify(marca),
			success: function(msg) {
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
				$("#modalEditaMarca").dialog("close");
			},
			error: function() {
				COLDIGO.exibirAviso(msg);
			}
		});
	};

	COLDIGO.marca.inativar = function(id) {
		$.ajax({

			type: "PUT",
			url: COLDIGO.PATH + "marca/inativar/" + id,
			success: function(msg) {
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
			},
			error: function() {
				COLDIGO.exibirAviso(msg);
			}
		});

	};


});