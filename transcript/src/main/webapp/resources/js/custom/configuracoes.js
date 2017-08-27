var nome = $("#nome").val();
var usuario = $("#usuario").val();

function excluirConta(){
	$("#excluir").submit();
}

function salvarConta(){
	if (usuario == "" || nome == ""){
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Preencha todos os campos.";

		var text = "[" + div.textContent + "]";
		return false;
	}
	$("#atualizar").submit();
	return true;
}