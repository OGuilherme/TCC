function validarSenha() {
	senha = $("#senha").val();
	confirmarSenha = $("#confirmarSenha").val();
	if (senha != confirmarSenha) {
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Senhas diferentes";

		var text = "[" + div.textContent + "]";
		return false;
	}
	return true;
}

function validarCampos() {
	user = $("#user").val();
	nome = $("#nome").val();
	email = $("#email").val();
	senha = $("#senha").val();
	confirmarSenha = $("#confirmarSenha").val();
	if (user == "" || nome == "" || email == "" || senha == "" || confirmarSenha == "") {
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Preencha todos os campos para prosseguir";

		var text = "[" + div.textContent + "]";
		return false;
	}
	return true;
}

