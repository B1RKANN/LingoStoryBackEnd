package com.neoimperum.repository.a1.bolum5.step;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.step.A1Bolum5Step;

@Repository
public interface A1Bolum5StepRepository extends JpaRepository<A1Bolum5Step, Long> {
    List<A1Bolum5Step> findByUserId(Long userId);
}
