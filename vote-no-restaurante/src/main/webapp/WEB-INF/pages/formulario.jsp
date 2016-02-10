<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		<div class="page-header">
			<h1>Preencha o formulario para saber o resultado!</h1>
		</div>
		
		<form:form action="cadastrarUsuario" method="POST" commandName="usuario" >
			<div>
				<label>Nome</label>
				<form:input path="nome" />
				<form:errors path="nome" />
			</div>
			<div>
				<label>Email</label>
				<form:input path="email"/>
				<form:errors path="email" />
			</div>
			
			<button type="submit">Enviar</button>
		</form:form> 

	</tiles:putAttribute>
</tiles:insertDefinition>
</html>