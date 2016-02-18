<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-default">
	<div class="panel-body" align="center">

		<div class="page-header">
			<h1>Preencha o formulario para saber o resultado!</h1>
		</div>
		<form:form action="cadastrarUsuario" method="POST"
			commandName="usuario">
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

	</div>
</div>