<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div class="row" id="idPainelRestaurante">
	<div class="page-header text-primary" >
		<h1>Em sua opinião, qual é o melhor restaurante?</h1>
		<h1>
			<small class="text-primary" >Vote e descubra qual o primeiro do ranking!!</small>
		</h1>
	</div>		

	<div class="col-xs-6 col-xs-6">
		<div class="thumbnail">
			<img src="<c:url value="${escolha.pathImagemLadoEsquerdo}"/>"/>
			<div class="caption">
				<h3>${escolha.restauranteLadoEsquerdo}</h3>
				<button id=idVotacaoEsquerda class="btn btn-primary btn-lg">Votar</button>
			</div>
		</div>
	</div>

	<div class="col-xs-6 col-xs-6">
		<div class="thumbnail">
			<img src="<c:url value="${escolha.pathImagemLadoDireito}"/>"/>
			<div class="caption">
				<h3>${escolha.restauranteLadoDireito}</h3>
				<button id=idVotacaoDireita class="btn btn-primary btn-lg">Votar</button>
			</div>
		</div>
	</div>
	<div id="info"></div>
</div>