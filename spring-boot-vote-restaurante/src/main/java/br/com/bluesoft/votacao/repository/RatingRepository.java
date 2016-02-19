package br.com.bluesoft.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bluesoft.votacao.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
