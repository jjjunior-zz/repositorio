package br.com.bluesoft.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.component.RestauranteComponent;

@Controller
public class RestauranteController {

	@Autowired
	private RestauranteComponent restauranteComponent;
	
	@RequestMapping("/")
	public String redirectToVotacao() {
		return "redirect:/votacao";
	}

	@RequestMapping("/votacao")
	public ModelAndView execute() {
		return restauranteComponent.escolherRestaurantes();
	}

	@RequestMapping("/votacaoEsquerda")
	public ModelAndView votacaoEsquerda() {
		return restauranteComponent.redirectVotacaoEsquerda();
	}

	@RequestMapping("/votacaoDireita")
	public ModelAndView votacaoDireita() {
		return restauranteComponent.redirectVotacaoDireita();
	}
}
