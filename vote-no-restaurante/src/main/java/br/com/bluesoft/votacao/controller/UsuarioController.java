package br.com.bluesoft.votacao.controller;

import br.com.bluesoft.votacao.domain.Usuario;
import br.com.bluesoft.votacao.exception.ModeloException;
import br.com.bluesoft.votacao.repository.UsuarioRepository;
import br.com.bluesoft.votacao.validation.UsuarioValidation;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UsuarioValidation());		
	}	

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
