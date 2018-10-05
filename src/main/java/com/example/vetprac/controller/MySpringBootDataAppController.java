package com.example.vetprac.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vetprac.exception.ResourceNotFoundException;
import com.example.vetprac.model.MySpringBootDataModel;
import com.example.vetprac.repository.MySpringBootRepository;



@RestController
@RequestMapping("/api")
public class MySpringBootDataAppController {

	@Autowired
	MySpringBootRepository myRepository;

	// Method to create a person
	@PostMapping("/person")
	public MySpringBootDataModel createperson(@Valid @RequestBody MySpringBootDataModel mSDM) {
		return myRepository.save(mSDM);
	}

	// Method to get a person
	@GetMapping("person/{id}")
	public MySpringBootDataModel getpersonbyId(@PathVariable(value = "id") Long personId) {
		return myRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("MySpringBootDataModel", "id", personId));
	}

	// Method to get all persons
	@GetMapping("/person")
	public List<MySpringBootDataModel> getAllPeople() {
		return myRepository.findAll();
	}

	// Method to update a person
	@PutMapping("/person/{id}")
	public MySpringBootDataModel updateperson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody MySpringBootDataModel personDetails) {
		MySpringBootDataModel mSDM = myRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("person", "id", personId));

		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setPhone(personDetails.getPhone());

		MySpringBootDataModel updateData = myRepository.save(mSDM);

		return updateData;
	}

	// Method to remove a person
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deleteperson(@PathVariable(value = "id") Long personId) {
		MySpringBootDataModel mSDM = myRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("person", "id", personId));

		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}

}
