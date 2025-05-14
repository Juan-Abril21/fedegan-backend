package com.example.fedegan.respository;

import com.example.fedegan.orm.BroteORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroteRepository extends JpaRepository<BroteORM, Long> {
}
