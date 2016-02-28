$(document).ready(function(){
	$('#idVotacaoDireita').click(function() {
		painelRestaurante('#idPainelRestaurante', "/votacaoDireita")
	});	
	
	$('#idVotacaoEsquerda').click(function() {
		painelRestaurante('#idPainelRestaurante', "/votacaoEsquerda")
	});
});

function painelRestaurante(id, url) {
	$(id).load(url,function(response,status,xhr){
			if(status=="error"){
				var msg = "Erro";
				$( "#info" ).html(msg + xhr.status + " " + xhr.statusText );
			}
	});	
}
