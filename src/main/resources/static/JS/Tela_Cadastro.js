var senha = document.querySelector("#senha");
var confirmarSenha = document.querySelector("#confirmarSenha");

function confirmPass(_) {
  if (confirmarSenha.value != senha.value) {
    confirmarSenha.setCustomValidity("Senhas diferentes");
    confirmarSenha.reportValidity();
    return false;
  } else {
    confirmarSenha.setCustomValidity("");
    return true;
  }
}
