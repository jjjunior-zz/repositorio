package br.com.bluesoft.votacao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;

@Controller
@RequestMapping("/")
public class VotacaoController {
	
	@Autowired
	private PossivelEscolhaRepository escolhaRepository;
	
	private Integer contador  = 51;
	private PossivelEscolha escolha;
	private Map<String, String> map = new HashMap<>();	

	@RequestMapping("/")
	public ModelAndView execute() {
		map.put(RestauranteEnum.BURGER_KING.getNome(), "/resources/images/burgerking.jpg");
		map.put(RestauranteEnum.KFC.getNome(),         "/resources/images/kfc.jpg");
		map.put(RestauranteEnum.MCDONALDS.getNome(), "/resources/images/mcdonalds.jpg");
		map.put(RestauranteEnum.OUTBACK.getNome(), "/resources/images/outback.jpg");
		map.put(RestauranteEnum.SUBWAY.getNome(), "/resources/images/subway.jpg");
		//consultar banco de dados 
//		escolha =  escolhaRepository.buscarPossivelEscolhaPorIndice(contador);
//		String pathImagemDireito = map.get(escolha.getRestauranteLadoDireito());
//		String pathImagemEsquerda = map.get(escolha.getRestauranteLadoEsquerdo());		
//		escolha.setPathImagemLadoDireito(pathImagemDireito);
//		escolha.setPathImagemLadoEsquerdo(pathImagemEsquerda);
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("escolha", escolha);
		if(contador <= 60){
			contador++;
		}
		return modelAndView;
	}
	
	@RequestMapping("/votacaoEsquerda" )
	public String votacaoEsquerda() {		
		if(contador > 60){
			contador = 51;
			return "redirect:/formulario";
		}		
		return "redirect:/";
		
	}
	
	@RequestMapping("/votacaoDireita")
	public String votacaoDireita() {		
		if(contador > 60){
			contador = 51;
			return "redirect:/formulario";
		}
		return "redirect:/";		
	}

}
