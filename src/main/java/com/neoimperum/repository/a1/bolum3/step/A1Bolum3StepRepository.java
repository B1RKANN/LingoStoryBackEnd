package com.neoimperum.repository.a1.bolum3.step;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.step.A1Bolum3Step;

@Repository
public interface A1Bolum3StepRepository extends JpaRepository<A1Bolum3Step, Long> {
    List<A1Bolum3Step> findByUserId(Long userId);
}
