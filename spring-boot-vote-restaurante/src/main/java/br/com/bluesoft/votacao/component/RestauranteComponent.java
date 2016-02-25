package br.com.bluesoft.votacao.component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import br.com.bluesoft.votacao.classificacao.CalculadoraDeClassificacao;
import br.com.bluesoft.votacao.classificacao.ClassificaGanhador;
import br.com.bluesoft.votacao.classificacao.ClassificaPerdedor;
import br.com.bluesoft.votacao.classificacao.Classificacao;
import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;
import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.service.DadoMestreService;
import br.com.bluesoft.votacao.service.PossivelEscolhaService;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestauranteComponent {

	@Autowired
	private PossivelEscolhaService				escolhaService;

	@Autowired
	private DadoMestreService					dadoMestreService;

	@Autowired
	private ClassificacaoRestauranteComponent	classificacaoComponent;

	private Integer								contadorInicial	= 1;
	private Integer								contadorFinal	= 1;
	private Integer								contador		= 1;
	private PossivelEscolha						escolha;
	private Map<String, Integer>				votacaoParticipante;

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
		classificacaoComponent.calcularClassificacaoRestauranteGanhador(escolha.getRestauranteLadoEsquerdo());
		classificacaoComponent.calcularClassificacaoRestaurantePerdedor(escolha.getRestauranteLadoDireito());
		classificarPorVotosParticipante(false);
		return redirect();
	}

	public String redirectVotacaoDireita() {
		classificacaoComponent.calcularClassificacaoRestauranteGanhador(escolha.getRestauranteLadoDireito());
		classificacaoComponent.calcularClassificacaoRestaurantePerdedor(escolha.getRestauranteLadoEsquerdo());
		classificarPorVotosParticipante(true);

		return redirect();
	}

	private void classificarPorVotosParticipante(boolean ladoDireito) {
		int classificacaoLadoDireito = votacaoParticipante.get(escolha.getRestauranteLadoDireito().getNome());
		ClassificacaoRestaurante crDireito = ClassificacaoRestaurante.newInstance(escolha.getRestauranteLadoDireito(), classificacaoLadoDireito);

		int classificacaoLadoEsquerdo = votacaoParticipante.get(escolha.getRestauranteLadoEsquerdo().getNome());
		ClassificacaoRestaurante crEsquerdo = ClassificacaoRestaurante.newInstance(escolha.getRestauranteLadoEsquerdo(), classificacaoLadoEsquerdo);

		int diferenca = 0;
		if (classificacaoLadoDireito >= classificacaoLadoEsquerdo) {
			diferenca = classificacaoLadoDireito - classificacaoLadoEsquerdo;
		} else {
			diferenca = classificacaoLadoEsquerdo - classificacaoLadoDireito;
		}

		DiferencaClassificacao dc = this.classificacaoComponent.buscarDiferencaDeClassificacao(diferenca);
		CalculadoraDeClassificacao calculadoraDeClassificacao = CalculadoraDeClassificacao.newIntance();
		Classificacao classificaGanhador = ClassificaGanhador.newIntance();
		Classificacao classificaPerdedor = ClassificaPerdedor.newIntance();

		if (ladoDireito) {
			Integer valorClassificacaoGanhador = calculadoraDeClassificacao.realizarCalculo(dc, crDireito, classificaGanhador);
			Integer valorClassificacaoPerdedor = calculadoraDeClassificacao.realizarCalculo(dc, crEsquerdo, classificaPerdedor);
			votacaoParticipante.put(crDireito.getRestaurante().getNome(), valorClassificacaoGanhador);
			votacaoParticipante.put(crEsquerdo.getRestaurante().getNome(), valorClassificacaoPerdedor);
		} else {
			Integer valorClassificacaoGanhador = calculadoraDeClassificacao.realizarCalculo(dc, crEsquerdo, classificaGanhador);
			Integer valorClassificacaoPerdedor = calculadoraDeClassificacao.realizarCalculo(dc, crDireito, classificaPerdedor);
			votacaoParticipante.put(crEsquerdo.getRestaurante().getNome(), valorClassificacaoGanhador);
			votacaoParticipante.put(crDireito.getRestaurante().getNome(), valorClassificacaoPerdedor);
		}
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

	public List<ClassificacaoRestaurante> getClassificacaoParticipante() {
		List<ClassificacaoRestaurante> lista = new ArrayList<>();		
		lista.add(ClassificacaoRestaurante.newInstance(Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome()),this.votacaoParticipante.get(RestauranteEnum.MCDONALDS.getNome())));
		lista.add(ClassificacaoRestaurante.newInstance(Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome()),this.votacaoParticipante.get(RestauranteEnum.BURGER_KING.getNome())));
		lista.add(ClassificacaoRestaurante.newInstance(Restaurante.newInstance(RestauranteEnum.KFC.getNome()), this.votacaoParticipante.get(RestauranteEnum.KFC.getNome())));
		lista.add(ClassificacaoRestaurante.newInstance(Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome()), this.votacaoParticipante.get(RestauranteEnum.OUTBACK.getNome())));
		lista.add(ClassificacaoRestaurante.newInstance(Restaurante.newInstance(RestauranteEnum.SUBWAY.getNome()), this.votacaoParticipante.get(RestauranteEnum.SUBWAY.getNome())));
		
		Stream<ClassificacaoRestaurante> stream = lista.stream().sorted(Comparator.comparingLong(ClassificacaoRestaurante::getClassificacaoAnterior).reversed());		
		lista =  stream.collect(Collectors.toList());
		addPosicao(lista);			
		return lista;
	}	

	public List<ClassificacaoRestaurante> getClassificacaoGlobal() {		
		Stream<ClassificacaoRestaurante> stream = classificacaoComponent.listaClassificacao().stream().sorted(Comparator.comparingLong(ClassificacaoRestaurante::getClassificacaoAnterior).reversed());		
		List<ClassificacaoRestaurante> lista =  stream.collect(Collectors.toList());
		addPosicao(lista);		
		return lista;		
	}
	
	private void addPosicao(List<ClassificacaoRestaurante> lista){
		
	}
}
