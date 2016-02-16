package br.com.bluesoft.votacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.exception.ModeloException;
import br.com.bluesoft.votacao.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@RequestMapping("/formulario")
	public ModelAndView form(Usuario usuario) {
		//consultar banco de dados		
		ModelAndView modelAndView = new ModelAndView("formulario");		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(@Valid Usuario usuario, BindingResult result) {
		try {
			if (result.hasErrors()) {
				return new ModelAndView("formulario");		
			}
			usuarioRepository.incluirUsuario(usuario);
		} catch (ModeloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("ranking");
	}
}
