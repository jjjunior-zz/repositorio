package br.com.bluesoft.votacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.ClassificacaoRestaurante;
import br.com.bluesoft.votacao.domain.DiferencaClassificacao;
import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.repository.ClassificacaoRestauranteRepository;
import br.com.bluesoft.votacao.repository.DiferencaClassificacaoRepository;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DadoMestreService {

	@Autowired
	private PossivelEscolhaRepository			possivelEscolhaRepository;

	@Autowired
	private RestauranteRepository				restauranteRepository;

	@Autowired
	private DiferencaClassificacaoRepository	diferencaClassificacaoRepository;

	@Autowired
	private ClassificacaoRestauranteRepository	classificacaoRestauranteRepository;

	@Transactional(readOnly = false)
	public void carregarEscolhas() {

		Restaurante ra = Restaurante.newInstance(RestauranteEnum.MCDONALDS.getNome(), RestauranteEnum.MCDONALDS.getPathImagem());
		Restaurante rb = Restaurante.newInstance(RestauranteEnum.BURGER_KING.getNome(), RestauranteEnum.BURGER_KING.getPathImagem());
		Restaurante rc = Restaurante.newInstance(RestauranteEnum.KFC.getNome(), RestauranteEnum.KFC.getPathImagem());
		Restaurante rd = Restaurante.newInstance(RestauranteEnum.OUTBACK.getNome(), RestauranteEnum.OUTBACK.getPathImagem());
		Restaurante re = Restaurante.newInstance(RestauranteEnum.SUBWAY.getNome(), RestauranteEnum.SUBWAY.getPathImagem());

		List<Restaurante> restaurantes = new ArrayList<>();

		restaurantes.add(ra);
		restaurantes.add(rb);
		restaurantes.add(rc);
		restaurantes.add(rd);
		restaurantes.add(re);

		restaurantes.forEach(restaurante -> restauranteRepository.saveAndFlush(restaurante));

		List<PossivelEscolha> possiveisEscolhas = new ArrayList<>();

		possiveisEscolhas.add(PossivelEscolha.newInstance(rb, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rc, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, ra));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rc, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rb));
		possiveisEscolhas.add(PossivelEscolha.newInstance(rd, rc));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rc));
		possiveisEscolhas.add(PossivelEscolha.newInstance(re, rd));

		possiveisEscolhas.forEach(possivelEscolha -> possivelEscolhaRepository.saveAndFlush(possivelEscolha));

	}

	@Transactional(readOnly = false)
	public void carregarClassificacaoDeRestaurantes() {

		List<Restaurante> restaurantes = restauranteRepository.findAll();

		List<ClassificacaoRestaurante> classificacaoRestaurantes = new ArrayList<>();

		restaurantes.forEach(restaurante -> classificacaoRestaurantes.add(ClassificacaoRestaurante.newInstance(restaurante, 0)));

		classificacaoRestaurantes.forEach(classificacao -> classificacaoRestauranteRepository.saveAndFlush(classificacao));

	}

	@Transactional(readOnly = false)
	public void carregarDiferencaClassificacao() {

		List<DiferencaClassificacao> diferencaClassificacaos = new ArrayList<>();

		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(0, 3, 50, 50));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(4, 10, 51, 49));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(11, 17, 52, 48));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(18, 25, 53, 47));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(26, 32, 54, 46));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(33, 39, 55, 45));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(40, 46, 56, 44));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(47, 53, 57, 43));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(54, 61, 58, 42));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(62, 68, 59, 41));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(69, 76, 60, 40));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(77, 83, 61, 39));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(84, 91, 62, 38));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(92, 98, 63, 37));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(99, 106, 64, 36));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(107, 113, 65, 35));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(114, 115, 66, 34));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(122, 129, 67, 33));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(130, 137, 68, 32));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(138, 145, 69, 31));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(146, 153, 70, 30));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(154, 162, 71, 29));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(163, 170, 72, 28));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(171, 179, 73, 23));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(180, 197, 74, 26));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(189, 197, 75, 25));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(198, 206, 76, 24));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(207, 215, 77, 23));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(216, 225, 78, 22));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(226, 235, 79, 21));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(236, 245, 80, 20));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(246, 256, 81, 19));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(257, 267, 82, 18));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(268, 278, 83, 17));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(279, 290, 84, 16));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(291, 302, 85, 15));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(303, 315, 86, 14));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(316, 328, 87, 13));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(329, 344, 88, 12));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(345, 357, 89, 11));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(358, 374, 90, 10));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(375, 391, 91, 9));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(392, 411, 92, 8));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(412, 432, 93, 7));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(433, 456, 94, 6));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(457, 484, 95, 5));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(485, 517, 96, 4));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(518, 559, 97, 3));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(560, 619, 98, 2));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(620, 735, 99, 1));
		diferencaClassificacaos.add(DiferencaClassificacao.newIntance(735, 1000, 100, 0));

		diferencaClassificacaos.forEach(rating -> diferencaClassificacaoRepository.saveAndFlush(rating));
	}

}
