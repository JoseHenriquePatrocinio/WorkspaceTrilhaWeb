function validaForm() {
	
		if (document.form.nome.value == "") {
		alert("Preencha o campo nome");
		document.form.nome.focus();
		return false;
	} 
	
		if (document.form.endereco.value == "") {
		alert("Preencha o campo endereço");
		document.form.endereco.focus();
		return false;
	} 
	
	
		if (document.form.telefone.value == "") {
		alert("Preencha o campo telefone");
		document.form.telefone.focus();
		return false;
	} 

	alert("Form enviado com sucesso");
	return true;
}