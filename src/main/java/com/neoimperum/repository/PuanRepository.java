package com.neoimperum.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neoimperum.model.Puan;

@Repository
public interface PuanRepository extends JpaRepository<Puan, Long>{
    
    List<Puan> findAllByOrderByUserPuanDesc();
    
    @Query("SELECT u.username, u.id, p FROM User u JOIN u.puan p ORDER BY p.userPuan DESC")
    List<Object[]> findAllPuansWithUserInfoOrderByUserPuanDesc();
}
