package com.example.vetprac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetprac.model.Injury;

@Repository
public interface InjuryRepository extends JpaRepository<Injury,Long> {
	public Injury findByType(String type);
}
