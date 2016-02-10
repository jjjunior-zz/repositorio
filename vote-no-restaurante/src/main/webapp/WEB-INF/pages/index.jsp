<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">
		
		<div class="page-header">
			<h1>Em sua opinião, qual é o melhor restaurante?</h1>
			<h1>
				<small>Vote e descubra qual o primeiro do ranking!!</small>
			</h1>
		</div>

		<div class="row">


			<div class="col-xs-6 col-xs-6">
				<div class="thumbnail">
					<img src="<c:url value="${escolha.pathImagemLadoEsquerdo}"/>" />
					<div class="caption">
						<h3>${escolha.restauranteLadoEsquerdo}</h3>
						<p>
							<a href="votacaoEsquerda" class="btn btn-primary btn-lg" role="button">Votar</a>							
						</p>
					</div>
				</div>
			</div>



			<div class="col-xs-6 col-xs-6">
				<div class="thumbnail">
					<img src="<c:url value="${escolha.pathImagemLadoDireito}"/>" />
					<div class="caption">
						<h3>${escolha.restauranteLadoDireito}</h3>
						<p>
							<a href="votacaoDireita" class="btn btn-primary btn-lg" role="button">Votar</a>							
						</p>
					</div>
				</div>
			</div>


		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>
</html>