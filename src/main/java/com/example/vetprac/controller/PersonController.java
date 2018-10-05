package com.example.vetprac.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.example.vetprac.model.Pet;
import com.example.vetprac.repository.MySpringBootRepository;
import com.example.vetprac.repository.PetRepository;


@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private MySpringBootRepository myRepository;
	
	@Autowired
	private MySpringBootRepository injuryRepository;

	// Method to get a person
	@GetMapping("/person/{personId}/pets")
	public Page<Pet> getAllPetsByPersonId(@PathVariable(value = "personId") Long personId, Pageable pageable) {
		return petRepository.findByPersonId(personId, pageable);
	}

	// Method to create a person
	@PostMapping("/person/{personId}/pets")
	public Pet createComment(@PathVariable(value = "personId") Long personId, @Valid @RequestBody Pet pet) {
		return myRepository.findById(personId).map(MySpringBootDataModel -> {
			pet.setPerson(MySpringBootDataModel);
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("Person", "id", pet));
	}

	@PutMapping("/person/{personId}/pets/{petId}")
	public Pet updatePet(@PathVariable(value = "personId") Long personId,
			@PathVariable(value = "petId") Long petId, @Valid @RequestBody Pet petRequest) {
		if (!myRepository.existsById(personId)) {
			throw new ResourceNotFoundException("Person", "id", petRequest);
		}
		return petRepository.findById(petId).map(pet -> {
			pet.setName(petRequest.getName());
			pet.setType(petRequest.getType());
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("PetId", "id", petRequest));
	}

	@DeleteMapping("/person/{personId}/pets/{petId}")
	public ResponseEntity<?> deleteComment(@PathVariable(value = "personId") Long personId,
			@PathVariable(value = "petId") Long petId) {
		if (!myRepository.existsById(personId)) {
			throw new ResourceNotFoundException("Person", "id", personId);
		}
		return petRepository.findById(petId).map(pet -> {
			petRepository.delete(pet);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("PetId", petId.toString(), null));
	}
	
	
	
	
	
	
	// Method to get a injury
	@GetMapping("/injury/{injuryId}/pets")
	public Page<Pet> getAllPetsByInjuryId(@PathVariable(value = "injuryId") Long injuryId, Pageable pageable) {
		return petRepository.findByInjuryId(injuryId, pageable);
	}

	// Method to create a injury
	@PostMapping("/injury/{injuryId}/pets")
	public Pet createComment1(@PathVariable(value = "injuryId") Long injuryId, @Valid @RequestBody Pet pet) {
		return myRepository.findById(injuryId).map(Injury -> {
			pet.setInjury(Injury);
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("Injury", "id", pet));
	}

	@PutMapping("/injury/{injuryId}/pets/{petId}")
	public Pet updatePet1(@PathVariable(value = "injuryId") Long injuryId,
			@PathVariable(value = "petId") Long petId, @Valid @RequestBody Pet petRequest) {
		if (!myRepository.existsById(injuryId)) {
			throw new ResourceNotFoundException("Injury", "id", petRequest);
		}
		return petRepository.findById(petId).map(pet -> {
			pet.setName(petRequest.getName());
			pet.setType(petRequest.getType());
			return petRepository.save(pet);
		}).orElseThrow(() -> new ResourceNotFoundException("PetId", "id", petRequest));
	}

	@DeleteMapping("/injury/{injuryId}/pets/{petId}")
	public ResponseEntity<?> deleteComment1(@PathVariable(value = "injuryId") Long injuryId,
			@PathVariable(value = "petId") Long petId) {
		if (!myRepository.existsById(injuryId)) {
			throw new ResourceNotFoundException("Injury", "id", injuryId);
		}
		return petRepository.findById(petId).map(pet -> {
			petRepository.delete(pet);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("PetId", petId.toString(), null));
	}

}