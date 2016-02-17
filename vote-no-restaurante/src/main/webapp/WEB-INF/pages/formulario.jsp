<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="corpo">

		<div class="page-header">
			<h1>Preencha o formulario para saber o resultado!</h1>
		</div>



		<form:form action="cadastrarUsuario" method="POST"	commandName="usuario">
			<div>
				<form:label path="nome">Nome</form:label>
				<form:input path="nome" />
				<form:errors path="nome" />
			</div>
			<div>
				<form:label path="email">Email</form:label>
				<form:input path="email" type="email" />
				<form:errors path="email" />
			</div>
			<button type="submit">Enviar</button>
		</form:form>

	</tiles:putAttribute>
</tiles:insertDefinition>
</html>