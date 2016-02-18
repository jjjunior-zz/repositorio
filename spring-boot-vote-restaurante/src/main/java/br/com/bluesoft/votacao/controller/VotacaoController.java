package br.com.bluesoft.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.service.DadoMestreService;
import br.com.bluesoft.votacao.service.PossivelEscolhaService;

@Controller
@RequestMapping("/")
public class VotacaoController {

	@Autowired
	private PossivelEscolhaService escolhaService;
	
	@Autowired
	private DadoMestreService dadoMestreService;

	private Integer contadorInicial = 1;
	private Integer contadorFinal = 1;
	private Integer contador = 1;
	private boolean inicializou = false;
	private PossivelEscolha escolha;	

	@RequestMapping("/")
	public ModelAndView execute() {
		if(!inicializou){
			contadorInicial = this.escolhaService.buscarMenorEscolha();
			if (contadorInicial == 0) {
				dadoMestreService.carregar();
				contadorInicial = this.escolhaService.buscarMenorEscolha();
			} 
			contadorFinal = contadorInicial + 10;
			contador = contadorInicial;
			inicializou = true;
		}
		// consultar banco de dados
		escolha = escolhaService.buscarPossivelEscolhaPorIndice(contador);
		escolha.setPathImagemLadoDireito(escolha.getRestauranteLadoDireito().getPathImagem());
		escolha.setPathImagemLadoEsquerdo(escolha.getRestauranteLadoEsquerdo().getPathImagem());
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("escolha", escolha);
		if (contador <= contadorFinal) {
			contador++;
		}
		return modelAndView;
	}

	@RequestMapping("/votacaoEsquerda")
	public String votacaoEsquerda() {
		if (contador >= contadorFinal) {
			contador = contadorInicial;			
			return "redirect:/formulario";
		}
		return "redirect:/";

	}

	@RequestMapping("/votacaoDireita")
	public String votacaoDireita() {
		if (contador >= contadorFinal) {
			contador = contadorInicial;			
			return "redirect:/formulario";
		}
		return "redirect:/";
	}

}
