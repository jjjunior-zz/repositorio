<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-default">
	<div class="panel-body" align="center">

		<div class="page-header">
			<h2>Veja qual é o melhor restaurante na opinião de quem voltou!</h2>
		</div>
		
		<h3>Seu Ranking</h3>
		<div>
			<table class="table table-striped">
				<thead class="thead-default">
					<tr>
						<th>Posição</th>
						<th>Restaurante</th>
						<th>Classificação</th>
					</tr>
				</thead>
				<c:forEach items="${rankingParticipante}" var="classificacao">
					<tr>
						<td>${classificacao.posicao}</td>
						<td>${classificacao.restaurante.nome}</td>
						<td>${classificacao.classificacaoAnterior}</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<br></br>
		
		<h3>Ranking Geral</h3>

		<div>
			<table class="table table-striped">
				<thead class="thead-default">
					<tr>
						<th>Posição</th>
						<th>Restaurante</th>
						<th>Classificação</th>
					</tr>
				</thead>
				<c:forEach items="${rankingGlobal}" var="classificacao">
					<tr>
						<td>${classificacao.posicao}</td>
						<td>${classificacao.restaurante.nome}</td>
						<td>${classificacao.classificacaoAnterior}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
