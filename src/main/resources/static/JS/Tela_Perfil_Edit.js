var form 			= document.querySelector("#form");

var inputSenha 		= document.querySelector("#senha");
var inputNome 		= document.querySelector("#nome");
var inputSobrenome 	= document.querySelector("#sobrenome");
var inputTelefone 	= document.querySelector("#celular");
var inputCep 		= document.querySelector("#cep");
var inputPais 		= document.querySelector("#pais");
var inputEstado 	= document.querySelector("#estado");
var inputCidade 	= document.querySelector("#cidade");
var inputBairro		= document.querySelector("#bairro");
var inputRua 		= document.querySelector("#rua");
var inputNumero		= document.querySelector("#numero");

var botaoSalvar 	= document.querySelector("#botaoSalvar");
var botaoDescartar 	= document.querySelector("#botaoDescartar");

var alertaSenha		= document.querySelector("#alertaSenha");
var alertaDetalhes	= document.querySelector("#alertaDetalhes");
var alertaEndereco 	= document.querySelector("#alertaEndereco");

function alertar(mensagem, alerta) {
	// Esconde todos os alertas
	alertaSenha.style.display = "none";
	alertaDetalhes.style.display = "none";
	alertaEndereco.style.display = "none";
	
	// Mostra o alerta passado com a mensagem
	alerta.innerHTML = mensagem;
	alerta.style.display = "block";
}

function alternarSenha() {
	inputSenha.disabled = !inputSenha.disabled;
}

function preencherEnderecoComCep() {
	// Para se o cep não está preenchido corretamente
	if (inputCep.value.length < 9) return;
	
	// Cria a URL da requisição
	let cep = inputCep.value.replace(/\D/g, "");
	let urlCep = `https://viacep.com.br/ws/${cep}/json/`;
	
	// Faz a requisição
	fetch(urlCep)
		// Transforma a resposta em um objeto JSON
		.then(response => response.json())
		.then(data => {
			// Preencher primeiro o estado com o nome completo
			fetch(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${data.uf}`)
				.then(response => response.json())
				.then(data => inputEstado.value = data.nome)
				.catch(error => console.log(error));
			
			// Prencheer outros campos
			inputRua.value = data.logradouro;
			inputBairro.value = data.bairro;
			inputCidade.value = data.localidade;
		})
		.catch(error => console.log(error));
}

function confirmarDados() {
	// Verificar se todos os campos estão preenchidos e corretos
	if (inputSenha.value == "") {
		alertar("Senha não pode estar em branco", alertaSenha);
		return;
	}
	
	if (inputNome.value == "") {
		alertar("Nome não pode estar em branco", alertaDetalhes);
		return;
	}
	
	if (inputSobrenome.value == "") {
		alertar("Sobrenome não pode estar em branco", alertaDetalhes);
		return;
	}
	
	if (inputTelefone.value == "") {
		alertar("Telefone não pode estar em branco", alertaDetalhes);
		return;
	} else if (inputTelefone.value.length < 15) {
		alertar("Telefone incorreto", alertaDetalhes);
		return;
	}
	
	if (inputCep.value == "") {
		alertar("CEP não pode estar em branco", alertaEndereco);
		return;
	} else if (inputCep.value.length < 9) {
		alertar("CEP incorreto", alertaEndereco);
		return;
	}
	
	if (inputPais.value == "") {
		alertar("País não pode estar em branco", alertaEndereco);
		return;
	}
	
	 if (inputEstado.value == "") {
		 alertar("Estado não pode estar em branco", alertaEndereco);
		 return;
	 }
	 
	 if (inputCidade.value == "") {
		 alertar("Cidade não pode estar em branco", alertaEndereco);
		 return;
	 }
	 
	 if (inputBairro.value == "") {
		 alertar("Bairro não pode estar em branco", alertaEndereco);
		 return;
	 }
	 
	 if (inputRua.value == "") {
		 alertar("Rua não pode estar em branco", alertaEndereco);
		 return;
	 }
	 
	 if (inputNumero.value == "") {
		 alertar("Número não pode estar em branco", alertaEndereco);
		 return;
	 }
	 
	 // Envia o form
	 form.submit();
}

function descartar() {
	// Redireciona de volta à página anterior
	window.location.href = window.location.href.replace("/edit", "");
}