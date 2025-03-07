package com.neoimperum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.Energy;
@Repository
public interface EnergyRepository extends JpaRepository<Energy, Long>{

}
