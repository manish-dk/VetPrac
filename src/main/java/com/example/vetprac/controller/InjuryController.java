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
import com.example.vetprac.model.Injury;
import com.example.vetprac.repository.InjuryRepository;

@RestController
@RequestMapping("/api")
public class InjuryController {

	@Autowired
	InjuryRepository myRepository;

	// Method to create a injury
	@PostMapping("/injury")
	public Injury createinjury(@Valid @RequestBody Injury mSDM) {
		return myRepository.save(mSDM);
	}

	// Method to get a injury
	@GetMapping("injury/{id}")
	public Injury getinjurybyId(@PathVariable(value = "id") Long injuryId) {
		return myRepository.findById(injuryId)
				.orElseThrow(() -> new ResourceNotFoundException("Injury", "id", injuryId));
	}

	// Method to get all injuries
	@GetMapping("/injury")
	public List<Injury> getAllPeople() {
		return myRepository.findAll();
	}

	// Method to update a injury
	@PutMapping("/injury/{id}")
	public Injury updateinjury(@PathVariable(value = "id") Long injuryId,
			@Valid @RequestBody Injury injuryDetails) {
		Injury mSDM = myRepository.findById(injuryId)
				.orElseThrow(() -> new ResourceNotFoundException("injury", "id", injuryId));

		mSDM.setType(injuryDetails.getType());
		mSDM.setStatus(injuryDetails.getStatus());

		Injury updateData = myRepository.save(mSDM);

		return updateData;
	}

	// Method to remove a injury
	@DeleteMapping("/injury/{id}")
	public ResponseEntity<?> deleteinjury(@PathVariable(value = "id") Long injuryId) {
		Injury mSDM = myRepository.findById(injuryId)
				.orElseThrow(() -> new ResourceNotFoundException("injury", "id", injuryId));

		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}

}
