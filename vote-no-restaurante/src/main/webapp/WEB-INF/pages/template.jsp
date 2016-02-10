<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Votação para o melhor restaurante</title>
		        
        <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='/static/js/jquery-2.1.3.min.js'/>"/>
		<link rel="stylesheet" href="<c:url value='/static/js/bootstrap.min.js'/>"/>
        		
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
