<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-default">
	<div class="panel-body" align="center">

		<div class="page-header">
			<h1>Preencha o formulario para saber o resultado!</h1>
		</div>
		<form:form action="cadastrarUsuario" method="POST"	commandName="usuario" align="left">
			<div class="form-group">
				<form:label for="idNome" path="nome">Nome</form:label>
				<form:input path="nome"  class="form-control" id="idNome" placeholder="Nome" />
				<form:errors path="nome" />
				
			</div>
			<div class="form-group">
				<form:label for="idEmail" path="email">Email</form:label>
				<form:input path="email" type="email" class="form-control"	id="idEmail" placeholder="Email" />
				<form:errors path="email" />
			</div>
			<button type="submit">Enviar</button>
		</form:form>
	</div>
</div>