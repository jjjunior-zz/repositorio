package br.com.bluesoft.votacao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.domain.PossivelEscollha;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;

@Controller
@RequestMapping("/")
public class VotacaoController {
	
	@Autowired
	private PossivelEscolhaRepository escolhaRepository;
	
	private Long contador  = 1L;
	private PossivelEscollha escolha;
	private Map<String, String> map = new HashMap<>();
	
	@PostConstruct
	public void init(){		
		map.put(RestauranteEnum.BURGER_KING.getNome(), "/static/images/burgerking.jpg");
		map.put(RestauranteEnum.KFC.getNome(), "/static/images/kfc.jpg");
		map.put(RestauranteEnum.MCDONALDS.getNome(), "/static/images/mcdonalds.jpg");
		map.put(RestauranteEnum.OUTBACK.getNome(), "/static/images/outback.jpg");
		map.put(RestauranteEnum.SUBWAY.getNome(), "/static/images/subway.jpg");		
	}
	

	@RequestMapping("/")
	public ModelAndView execute() {
		//consultar banco de dados 
		escolha =  escolhaRepository.buscarPossivelEscolhaPorIndice(contador);
		String pathImagemDireito = map.get(escolha.getRestauranteLadoDireito());
		String pathImagemEsquerda = map.get(escolha.getRestauranteLadoEsquerdo());		
		escolha.setPathImagemLadoDireito(pathImagemDireito);
		escolha.setPathImagemLadoEsquerdo(pathImagemEsquerda);
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("escolha", escolha);
		if(contador <= 10){
			contador++;
		}else{
			contador = 1L;
		}
		return modelAndView;
	}
	
	@RequestMapping("/votacaoEsquerda" )
	public String votacaoEsquerda() {
		
		
		System.out.println("Restaurante votado foi: " + this.escolha.getRestauranteLadoEsquerdo());
		if(contador > 10){
			return "redirect:/formulario";
		}		
		return "redirect:/";
		
	}
	
	@RequestMapping("/votacaoDireita")
	public String votacaoDireita() {
		System.out.println("Restaurante votado foi: " + this.escolha.getRestauranteLadoDireito());
		if(contador > 10){
			return "redirect:/formulario";
		}
		return "redirect:/";		
	}

}
