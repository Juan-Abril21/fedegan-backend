package com.example.fedegan.respository;

import com.example.fedegan.orm.RegistroVacunacionORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroVacunacionRepository extends JpaRepository<RegistroVacunacionORM, Long> {
}
