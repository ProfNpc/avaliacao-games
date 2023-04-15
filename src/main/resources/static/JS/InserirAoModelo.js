function inserirAoModelo(modelo, str, func) {
	// Faz uma gambiarra para "inserir" a string no modelo
	let indiceStr = 0;
	let retorno = "";
	for (let i = 0; i < modelo.length; i++) {
		// Se chegou ao final da string passada, pare
		if (indiceStr == str.length) break;
		
		let charModelo = modelo.charAt(i);
		if (charModelo == "x") {
			retorno += str.charAt(indiceStr);
			indiceStr++;
		} else {
			retorno += charModelo;
		}
	}
	
	// Executa a função passada usando a string processada como parâmetro, se ela existe
	if (func) func(retorno);
	
	// Retorna a string processada
	return retorno;
}

function numeros(str) {
	// Retorna a string apenas com os números
	return str.replace(/[^0-9]/g, "");
}