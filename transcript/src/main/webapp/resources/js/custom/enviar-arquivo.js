function validarCampos(){
	var nome = $("#nome").val();
	
	if(nome == "" && !$("arquivo").hasClass("selected")){
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "De um nome e selecione um arquivo.";

		var text = "[" + div.textContent + "]";
		return false;
	}else if (nome == ""){
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "De um nome para o arquivo.";

		var text = "[" + div.textContent + "]";
		return false;
	}else if (!$("arquivo").hasClass("selected")){
		var div = document.getElementById("textDiv").className = "alert alert-danger text-center";

		textDiv.textContent = "Selecione um arquivo.";

		var text = "[" + div.textContent + "]";
		return false;
	}
	return true;
}