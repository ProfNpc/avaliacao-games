var inputSenha = document.querySelector(".geral-info input");
var botaoSenha = document.querySelector(".geral-info button");


botaoSenha.addEventListener("click", () => {
    inputSenha.disabled = !inputSenha.disabled;
})