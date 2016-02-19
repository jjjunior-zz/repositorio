package br.com.bluesoft.votacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votacao.domain.PossivelEscolha;
import br.com.bluesoft.votacao.domain.Rating;
import br.com.bluesoft.votacao.domain.Restaurante;
import br.com.bluesoft.votacao.enumeration.RestauranteEnum;
import br.com.bluesoft.votacao.repository.PossivelEscolhaRepository;
import br.com.bluesoft.votacao.repository.RatingRepository;
import br.com.bluesoft.votacao.repository.RestauranteRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DadoMestreService {

	@Autowired
	private PossivelEscolhaRepository	possivelEscolhaRepository;
	
	@Autowired	
	private RestauranteRepository		restauranteRepository;
	
	@Autowired
	private RatingRepository			ratingRepository;

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
	public void carregarRating() {
		
		List<Rating> ratings = new ArrayList<>();
		
		ratings.add(Rating.newIntance(0, 3, 50, 50));
		ratings.add(Rating.newIntance(4, 10, 51, 49));
		ratings.add(Rating.newIntance(11, 17, 52, 48));
		ratings.add(Rating.newIntance(18, 25, 53, 47));
		ratings.add(Rating.newIntance(26, 32, 54, 46));
		ratings.add(Rating.newIntance(33, 39, 55, 45));
		ratings.add(Rating.newIntance(40, 46, 56, 44));
		ratings.add(Rating.newIntance(47, 53, 57, 43));
		ratings.add(Rating.newIntance(54, 61, 58, 42));
		ratings.add(Rating.newIntance(62, 68, 59, 41));
		ratings.add(Rating.newIntance(69, 76, 60, 40));
		ratings.add(Rating.newIntance(77, 83, 61, 39));		
		ratings.add(Rating.newIntance(84, 91, 62, 38));		
		ratings.add(Rating.newIntance(92, 98, 63, 37));		
		ratings.add(Rating.newIntance(99, 106, 64, 36));		
		ratings.add(Rating.newIntance(107, 113, 65, 35));	
		ratings.add(Rating.newIntance(114, 115, 66, 34));	
		ratings.add(Rating.newIntance(122, 129, 67, 33));	
		ratings.add(Rating.newIntance(130, 137, 68, 32));	
		ratings.add(Rating.newIntance(138, 145, 69, 31));	
		ratings.add(Rating.newIntance(146, 153, 70, 30));	
		ratings.add(Rating.newIntance(154, 162, 71, 29));	
		ratings.add(Rating.newIntance(163, 170, 72, 28));	
		ratings.add(Rating.newIntance(171, 179, 73, 23));	
		ratings.add(Rating.newIntance(180, 197, 74, 26));	
		ratings.add(Rating.newIntance(189, 197, 75, 25));		
		ratings.add(Rating.newIntance(198, 206, 76, 24));
		ratings.add(Rating.newIntance(207, 215, 77, 23));
		ratings.add(Rating.newIntance(216, 225, 78, 22));
		ratings.add(Rating.newIntance(226, 235, 79, 21));
		ratings.add(Rating.newIntance(236, 245, 80, 20));
		ratings.add(Rating.newIntance(246, 256, 81, 19));
		ratings.add(Rating.newIntance(257, 267, 82, 18));
		ratings.add(Rating.newIntance(268, 278, 83, 17));
		ratings.add(Rating.newIntance(279, 290, 84, 16));
		ratings.add(Rating.newIntance(291, 302, 85, 15));
		ratings.add(Rating.newIntance(303, 315, 86, 14));
		ratings.add(Rating.newIntance(316, 328, 87, 13));
		ratings.add(Rating.newIntance(329, 344, 88, 12));
		ratings.add(Rating.newIntance(345, 357, 89, 11));
		ratings.add(Rating.newIntance(358, 374, 90, 10));
		ratings.add(Rating.newIntance(375, 391, 91, 9));
		ratings.add(Rating.newIntance(392, 411, 92, 8));
		ratings.add(Rating.newIntance(412, 432, 93, 7));
		ratings.add(Rating.newIntance(433, 456, 94, 6));
		ratings.add(Rating.newIntance(457, 484, 95, 5));
		ratings.add(Rating.newIntance(485, 517, 96, 4));
		ratings.add(Rating.newIntance(518, 559, 97, 3));
		ratings.add(Rating.newIntance(560, 619, 98, 2));
		ratings.add(Rating.newIntance(620, 735, 99, 1));
		ratings.add(Rating.newIntance(735, 1000, 100, 0));
		
		ratings.forEach(rating -> ratingRepository.saveAndFlush(rating));		
	}
	
	
}
