function validarCampos() {
	user = $("#user").val();
	email = $("#email").val();
	if (user == "" && email == "") {
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Preencha pelo menos um dos campos para prosseguir";

		var text = "[" + div.textContent + "]";
		return false;
	}
	return true;
}
