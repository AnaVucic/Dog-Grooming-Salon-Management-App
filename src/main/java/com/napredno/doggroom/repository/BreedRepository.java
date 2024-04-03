package com.napredno.doggroom.repository;

import com.napredno.doggroom.domain.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository  extends JpaRepository<Breed, Long> {
}
