var alerta = document.querySelector("#alerta");
var celular = document.querySelector("#celular");
var cpf = document.querySelector("#cpf")
var senha = document.querySelector("#senha");
var confirmarSenha = document.querySelector("#confirmarSenha");

function alertar(mensagem) {
	alerta.innerHTML = mensagem;
	alerta.style.display = "block"
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

function confirmarDados() {
	// Confirmar celular
	let cel = celular.value
		.replaceAll(" ", "")
		.replaceAll("-", "")
		.replaceAll("(", "")
		.replaceAll(")", "");
	
	if (!confirmarCelular(cel)) {
		alertar("Celular inválido");
		return false;
	}
	
	// Confirmar CPF
	let codCpf = cpf.value
		.replaceAll(".", "")
		.replaceAll("-", "");
	
	if (!confirmarCpf(codCpf)) {
		alertar("CPF inválido");
		return false;
	}
	
	// Confirmar senhas
	if (!confirmarSenhas(senha.value, confirmarSenha.value)) {
		console.log("senha diferentes");
		alertar("Senhas diferentes");
	    return false;
  	}
  	
  	cpf.value = codCpf;
  	return true;
}

console.log("carregado");
