package br.com.bluesoft.votacao.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.bluesoft.votacao.domain.Usuario;

public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		 
		return Usuario.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getNome() != null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "nome","Nome não pode estar vazio.");
		}	
		
		if(usuario.getEmail() != null){
			Pattern pattern = Pattern.compile(".+@.+\\..+");
			Matcher matcher = pattern.matcher(usuario.getEmail());
			if(!matcher.matches()){
				errors.rejectValue("email","email", "Por favor, insira um email valido.");
			}			
		}	
	}

}
