var inputSenha = document.querySelector(".geral-info input");
var botaoSenha = document.querySelector(".geral-info button");

if (botaoSenha && botaoSenha.innerHTML == "Editar") {
	botaoSenha.addEventListener("click", () => {
	    inputSenha.disabled = !inputSenha.disabled;
	});
}