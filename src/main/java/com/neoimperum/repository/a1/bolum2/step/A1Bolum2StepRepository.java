package com.neoimperum.repository.a1.bolum2.step;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.step.A1Bolum2Step;

@Repository
public interface A1Bolum2StepRepository extends JpaRepository<A1Bolum2Step, Long> {
    List<A1Bolum2Step> findByUserId(Long userId);
}
