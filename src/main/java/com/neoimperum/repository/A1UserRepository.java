package com.neoimperum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.level.A1User;
import com.neoimperum.model.step.A1Bolum1Step;

@Repository
public interface A1UserRepository extends JpaRepository<A1User, Long> {
	
	List<A1User> findByUserId(Long userId);
	
	A1User findTopByUserIdOrderByIdDesc(Long userId);
}
