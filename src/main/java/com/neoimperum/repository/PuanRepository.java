package com.neoimperum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.Puan;

@Repository
public interface PuanRepository extends JpaRepository<Puan, Long>{

}
