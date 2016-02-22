package br.com.bluesoft.votacao.component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.service.DadoMestreService;
import br.com.bluesoft.votacao.service.PossivelEscolhaService;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestauranteComponent {

	@Autowired
	private PossivelEscolhaService	escolhaService;

	@Autowired
	private DadoMestreService		dadoMestreService;
	
	@Autowired
	private ClassificacaoRestauranteComponent classificacaoComponent;

	private Integer					contadorInicial	= 1;
	private Integer					contadorFinal	= 1;
	private Integer					contador		= 1;
	private PossivelEscolha			escolha;
	private Map<String, Integer>	votacaoParticipante;

	public RestauranteComponent() {
		this.escolha = PossivelEscolha.newInstance();		
		votosParticipante();
	}

	@PostConstruct
	private void iniciar() {
		contadorInicial = this.escolhaService.buscarMenorEscolha();

		if (contadorInicial == 0) {
			dadoMestreService.carregarEscolhas();
			dadoMestreService.carregarDiferencaClassificacao();
			dadoMestreService.carregarClassificacaoDeRestaurantes();
			contadorInicial = this.escolhaService.buscarMenorEscolha();
		}

		contadorFinal = contadorInicial + 10;
		contador = contadorInicial;
	}

	public ModelAndView escolherRestaurantes() {

		escolha = escolhaService.buscarPossivelEscolhaPorIndice(contador);
		escolha.setPathImagemLadoDireito(escolha.getRestauranteLadoDireito().getPathImagem());
		escolha.setPathImagemLadoEsquerdo(escolha.getRestauranteLadoEsquerdo().getPathImagem());
		classificacaoComponent.buscarClassificacaoRestaurantes(escolha.getRestauranteLadoDireito(), escolha.getRestauranteLadoEsquerdo());
		classificacaoComponent.buscarDiferencaClassificacao();
		ModelAndView modelAndView = new ModelAndView("votacao_restaurante");
		modelAndView.addObject("escolha", escolha);

		if (contador <= contadorFinal) {
			contador++;
		}
		return modelAndView;
	}

	public String redirectVotacaoEsquerda() {
		classificacaoComponent.calcularClassificacaoRestauranteGanhador(escolha.getRestauranteLadoDireito());
		addVotacao(escolha.getRestauranteLadoDireito());
		
		return redirect();
	}

	public String redirectVotacaoDireita() {
		classificacaoComponent.calcularClassificacaoRestauranteGanhador(escolha.getRestauranteLadoDireito());		
		addVotacao(escolha.getRestauranteLadoDireito());		
		
		return redirect();
	}
	
	private void addVotacao(Restaurante restaurante){		
		Integer votosParaRestaurante = votacaoParticipante.get(restaurante.getNome()) + 1;
		votacaoParticipante.put(restaurante.getNome(), votosParaRestaurante);		
	}	

	private String redirect() {
		if (contador >= contadorFinal) {
			contador = contadorInicial;
			return "redirect:/cadastroParticipante";
		}
		return "redirect:/votacao";
	}

	private void votosParticipante() {
		this.votacaoParticipante = new HashMap<>();
		this.votacaoParticipante.put(RestauranteEnum.MCDONALDS.getNome(), 0);
		this.votacaoParticipante.put(RestauranteEnum.BURGER_KING.getNome(), 0);
		this.votacaoParticipante.put(RestauranteEnum.KFC.getNome(), 0);
		this.votacaoParticipante.put(RestauranteEnum.OUTBACK.getNome(), 0);
		this.votacaoParticipante.put(RestauranteEnum.SUBWAY.getNome(), 0);
	}

	public Map<String, Integer> getVotacaoParticipante() {
		return votacaoParticipante;
	}
	
	
	
}
