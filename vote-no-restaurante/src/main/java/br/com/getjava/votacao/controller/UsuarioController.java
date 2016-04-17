package br.com.getjava.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.getjava.votacao.domain.Usuario;
import br.com.getjava.votacao.exception.ModeloException;
import br.com.getjava.votacao.repository.UsuarioRepository;
import br.com.getjava.votacao.validation.UsuarioValidation;

@Controller
public class UsuarioController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UsuarioValidation());		
	}
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value ="/formulario" , method = RequestMethod.GET)
	public ModelAndView form(Usuario usuario) {
		//consultar banco de dados		
		ModelAndView modelAndView = new ModelAndView("formulario");		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(@Validated Usuario usuario, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return form(usuario);
			}
			usuarioRepository.incluirUsuario(usuario);
		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("ranking");
	}
}
