<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Votação para o melhor restaurante</title>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	</head>

	<body>
		<nav class="navbar navbar-inverse" role="navigation">				
			<div class="container-fluid">			
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"	data-toggle="collapse" data-target="#navbar-1">
						<span class="sr-only"></span>
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand"	href="${pageContext.request.contextPath}/">Início</a>
				</div>
			</div>			
		</nav>
	
		<div class="panel panel-default">
			<div class="panel-body" align="center">
				<tiles:insertAttribute name="corpo" />
			</div>
		</div>
	</body>

</html>
