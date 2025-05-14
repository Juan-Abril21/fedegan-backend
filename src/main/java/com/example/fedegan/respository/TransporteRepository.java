package com.example.fedegan.respository;

import com.example.fedegan.orm.TransporteORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteRepository extends JpaRepository<TransporteORM, Long> {
}
