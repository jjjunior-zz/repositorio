<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="panel panel-default">
	<div class="panel-body" align="center">

		<div class="page-header">
			<h1>Preencha o formulário para saber o resultado!</h1>
		</div>
		<form:form action="cadastrarUsuario" method="POST" commandName="usuario" class="form-horizontal" align="left">
			<div class="form-group form-group-lg">				
				<form:label for="idNome" class="col-sm-1 control-label" path="nome">Nome</form:label>
				
				<div class="col-sm-10">					
					<form:input path="nome" class="form-control" id="idNome" placeholder="Nome" type="text" />
					<form:errors path="nome" />
				</div>
			</div>

			<div class="form-group form-group-lg">
				<form:label for="idEmail" class="col-sm-1 control-label" path="email">Email</form:label>
				
				<div class="col-sm-10">					
					<form:input path="email" class="form-control" id="idEmail" placeholder="Email" type="email" />
					<form:errors path="email" />
				</div>
			</div>

			<div class="form-group" align="left">
				<div class="col-sm-offset-1 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg">Cadastrar</button>
				</div>
			</div>
		</form:form>
	</div>
</div>