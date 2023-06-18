var form 		= document.querySelector("#form");
var cartao 		= document.querySelector("#cartao");
var nome 		= document.querySelector("#nome");
var validade 	= document.querySelector("#validade");
var cvv 		= document.querySelector("#cvv");

var radio = {
	cartao : 	document.querySelector("#radioCartao"),
	pix : 		document.querySelector("#radioPix"),
	boleto : 	document.querySelector("#radioBoleto"),
}

var cartoes = {
    Visa: /^4[0-9]{12}(?:[0-9]{3})/,
    Mastercard: /^5[1-5][0-9]{14}/,
    Amex: /^3[47][0-9]{13}/,
    DinersClub: /^3(?:0[0-5]|[68][0-9])[0-9]{11}/,
    Discover: /^6(?:011|5[0-9]{2})[0-9]{12}/,
    JCB: /^(?:2131|1800|35\d{3})\d{11}/
};

function verificarCartao(c) {
	if (c.length < 19) return false;
	return true;
	/*
	for (var ca in cartoes) if (c.match(cartoes[ca])) return true;
	return false;
	*/
}

function verificarValidade(v) {
	if (v.length < 5) return false;
	
	// Separar mes e ano
	let mesano = v.split("/");
	mes = parseInt(mesano[0]);
	ano = parseInt(mesano[1]);
	
	// Verificar se mês é valido
	if (mes < 1 || mes > 12) return false;
	
	// Verificar se a data não está vencida
	let dataHoje = new Date();
	dataHoje.setDate(1);
	dataHoje.setHours(0);
	dataHoje.setMinutes(0);
	dataHoje.setSeconds(0);
	dataHoje.setMilliseconds(0);
	
	let dataValidade = new Date('20' + ano, mes-1, 1);
	
	if (dataValidade - dataHoje < 0) return false;
	
	return true;
}

function alertar(mensagem) {
	alerta.innerHTML = mensagem;
	alerta.style.display = "block"
}

function confirmarCompra(event) {
	// Previnir comportamento padrão
	event.preventDefault();
	
	// Ver se será pago no cartão
	if (radio.cartao.checked) {
		// Verificar numero do cartão
		if (!verificarCartao(cartao.value)) {
			alertar("Número de cartão inválido");
			return;
		}
		
		// Verificar nome
		if (nome.value.length == 0) {
			alertar("Nome invalido");
			return;
		}
		
		// Verificar validade
		if (!verificarValidade(validade.value)) {
			alertar("Data de validade inválida");
			return;
		}
		
		// Verificar código de segurança
		if (cvv.value.length < 3) {
			alertar("Código de segurança inválido");
			return;
		}
	}
	
	// Tudo certo, enviar form
	form.submit();
}