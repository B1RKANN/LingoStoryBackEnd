package com.neoimperum.repository.a1.bolum4.step;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.step.A1Bolum4Step;

@Repository
public interface A1Bolum4StepRepository extends JpaRepository<A1Bolum4Step, Long> {
    List<A1Bolum4Step> findByUserId(Long userId);
}
