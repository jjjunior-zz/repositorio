$(document).ajaxStop($.unblockUI); 

$(document).ready(function(){
	$('#idVotacaoDireita').click(function() {		 
		painelRestaurante('#idPainelRestaurante', "/votacaoDireita");		 
	});	
	
	$('#idVotacaoEsquerda').click(function() {		 
		painelRestaurante('#idPainelRestaurante', "/votacaoEsquerda");		
	});
});

function painelRestaurante(id, url) {
	$.blockUI({ message: '<h2><img src="/resources/images/busy.gif" /> Aguarde...</h2>' });
	$(id).load(url,function(response,status,xhr){
			if(status=="error"){
				var msg = "Erro";
				$( "#info" ).html(msg + xhr.status + " " + xhr.statusText );
			}
	});	
}
