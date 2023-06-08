var form = document.querySelector("#formCadastro");
var nome = document.querySelector("#nome");
var sobrenome = document.querySelector("#sobrenome");
var email = document.querySelector("#email");
var alerta = document.querySelector("#alerta");
var celular = document.querySelector("#celular");
var cpf = document.querySelector("#cpf")
var senha = document.querySelector("#senha");
var confirmarSenha = document.querySelector("#confirmarSenha");

function alertar(mensagem) {
	alerta.innerHTML = mensagem;
	alerta.style.display = "block"
}

function confirmarEmail(e) {
	let usuario = e.substring(0, e.indexOf("@"));
	let dominio = e.substring(e.indexOf("@") + 1, e.length);
	
	return (usuario.length >= 1 &&
			dominio.length >= 3 &&
			usuario.search("@") == -1 &&
			dominio.search("@") == -1 &&
			usuario.search(" ") == -1 &&
			dominio.search(" ") == -1 &&
			dominio.search(".") != -1 &&
			dominio.indexOf(".") >= 1 &&
			dominio.lastIndexOf(".") < dominio.length - 1);
}

function confirmarCelular(celular) {
	if (celular.length != 11) return false;
	
	return true;
}

function confirmarCpf(cpf) {
	// O CPF abaixo é impossível
	if (cpf == "00000000000") return false;
	
	// Também pare se o número de caracteres estiver errado
	if (cpf.length != 11) return false;
	
	// Primeiro dígito verificador
	let soma = 0;
	
	for (let i = 0; i < 9; i++) {
		soma += parseInt(cpf.charAt(i)) * (10 - i);
	}
	
	let resto = (soma * 10) % 11;
	if (resto == 10) resto = 0;
	if (resto != parseInt(cpf.charAt(9))) return false;
	
	// Segundo dígito verificador
	soma = 0;
	
	for (let i = 0; i < 10; i++) {
		soma += parseInt(cpf.charAt(i)) * (11 - i);
	}
	
	resto = (soma * 10) % 11;
	if (resto == 10) resto = 0;
	if (resto != parseInt(cpf.charAt(10))) return false;
	
	// Passou os dois testes, CPF é válido
	return true;
}

function confirmarSenhas(senha1, senha2) {
	return senha1 == senha2;
}

function confirmarDados(event) {
	// Previnir comportamento padrão
	event.preventDefault();
	
	// Confirmar primeiros campos
	if (nome.value == "") {
		alertar("Insira um nome válido");
		nome.focus();
		return;
	}
	
	if (sobrenome.value== "") {
		alertar("Insira um sobrenome válido");
		sobrenome.focus();
		return;
	}
	
	if (!confirmarEmail(email.value)) {
		alertar("Insira um e-mail válido");
		email.focus();
		return;
	}
	
	// Confirmar celular
	let cel = celular.value
		.replaceAll(" ", "")
		.replaceAll("-", "")
		.replaceAll("(", "")
		.replaceAll(")", "");
	
	if (!confirmarCelular(cel)) {
		alertar("Insira um número de celular válido");
		celular.focus();
		return;
	}
	
	// Confirmar CPF
	let codCpf = cpf.value
		.replaceAll(".", "")
		.replaceAll("-", "");
	
	if (!confirmarCpf(codCpf)) {
		alertar("Insira um número de CPF válido");
		cpf.focus();
		return;
	}
	
	// Confirmar senhas
	if (senha.value == "") {
		alertar("Senha não pode estar em branco");
		senha.focus();
		return;
	} else if (confirmarSenha.value == "") {
		alertar("Senha não pode estar em branco");
		confirmarSenha.focus();
		return;
	}else if (!confirmarSenhas(senha.value, confirmarSenha.value)) {
		alertar("As senhas devem ser iguais");
		confirmarSenha.focus();
	    return;
  	}

  	cpf.value = codCpf;
  	form.submit();
}

form.onkeypress = function(event) { 
	var key = event.charCode || event.keyCode || 0;
	if (key == 13) {
		event.preventDefault();
		confirmarDados();
	}
}