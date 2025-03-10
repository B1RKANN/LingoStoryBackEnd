package com.neoimperum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.level.A1Bolum;

@Repository
public interface A1BolumRepository extends JpaRepository<A1Bolum, Long>{

}
