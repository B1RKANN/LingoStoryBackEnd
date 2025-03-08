package com.neoimperum.repository.a1.bolum1.step;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.step.A1Bolum1Step;

@Repository
public interface A1Bolum1StepRepository extends JpaRepository<A1Bolum1Step, Long>{

	List<A1Bolum1Step> findByUserId(Long userId);
	
	A1Bolum1Step findTopByUserIdOrderByIdDesc(Long userId);

}
