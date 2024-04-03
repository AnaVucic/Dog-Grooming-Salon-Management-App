package com.napredno.doggroom.repository;

import com.napredno.doggroom.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p from Person p WHERE p.contactNumber = ?1")
    Optional<Person> findByContactNumber(String contactNumber);
}
