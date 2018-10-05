package com.example.vetprac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetprac.model.MySpringBootDataModel;



@Repository
public interface MySpringBootRepository extends JpaRepository<MySpringBootDataModel,Long> {
	public MySpringBootDataModel findByName(String name);
}