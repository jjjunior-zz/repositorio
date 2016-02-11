package br.com.bluesoft.votacao.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.bluesoft.votacao.domain.Usuario;

public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		 
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");

		Usuario usuario = (Usuario) target;		
		
		if(usuario.getNome()==null || "".equals(usuario.getNome())){
			errors.rejectValue("nome", "field.required");
		}
		
		if(usuario.getEmail()==null || "".equals(usuario.getEmail())){
			errors.rejectValue("email", "field.required");
		}		
	}

}
