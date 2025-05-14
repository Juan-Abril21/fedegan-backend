package com.example.fedegan.respository;

import com.example.fedegan.orm.AnimalORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalORM, Long> {

}
