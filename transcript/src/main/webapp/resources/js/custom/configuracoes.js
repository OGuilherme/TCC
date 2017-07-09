var id = $("#idUsuario").val();
var nome = $("#nome").val();
var usuario = $("#usuario").val();
var senha = $("#senha").val();

function excluirConta(){
	$.ajax({
		url: "/configuracao/excluirConta",
		type: "POST",
		dataType: "JSON",
		data: {'id' : id},
		success:
			window.location.href = "/login"
	});
}

function salvarConta(){
	if (usuario != "" || nome != "" || senha != ""){
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Preencha todos os campos.";

		var text = "[" + div.textContent + "]";
		return false;
	}
}