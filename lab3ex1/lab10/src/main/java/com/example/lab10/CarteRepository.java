package com.example.lab10;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(String autor);
}
