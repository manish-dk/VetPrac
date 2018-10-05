package com.example.vetprac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetprac.model.Pet;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	Page<Pet> findByPersonId(Long personId, Pageable pageable);
	Page<Pet> findByInjuryId(Long injuryId, Pageable pageable);
}