package br.com.digital.restSpring.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digital.restSpring.models.Pets;
import br.com.digital.restSpring.repositories.PetsRepository;

@RestController
@ResponseBody
@RequestMapping("/pets")
public class PetsController {

	@Autowired
	private PetsRepository repository;
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public ResponseEntity<List<Pets>> getAllPets() {
	  return  ResponseEntity.ok(repository.findAll());
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@GetMapping("{id}")
	public ResponseEntity<Pets> getPetById(@PathVariable("id") ObjectId id) {
		checkIfExist(id);
		Pets pets = repository.findBy_id(id); 
		return ResponseEntity.ok(pets);
//		return new ResponseEntity<Pets>(pets,HttpStatus.FOUND);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PutMapping("{id}")
	public ResponseEntity<Void> modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
		checkIfExist(id);
		pets.set_id(id);
		repository.save(pets);
		return ResponseEntity.accepted().build();
//		return new ResponseEntity<Pets>(HttpStatus.ACCEPTED);
	}

	
	
//	@RequestMapping(value = "/", method = RequestMethod.POST)
	@PostMapping("/")
	public ResponseEntity<Void> createPet(@Valid @RequestBody Pets pets) {
	  pets.set_id(ObjectId.get());
	  repository.save(pets);
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
	            "/{id}").buildAndExpand(pets.get_id()).toUri();
	  return ResponseEntity.created(location).build();
//	  return new ResponseEntity<Pets>(HttpStatus.CREATED);
	}
	
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePet(@PathVariable ObjectId id) {
		checkIfExist(id);
		repository.delete(repository.findBy_id(id));
		return ResponseEntity.accepted().build();
//		return new ResponseEntity<Pets>(HttpStatus.ACCEPTED);
	}
	
	private ResponseEntity<Void> checkIfExist(ObjectId id) {
		if (!repository.existsById(id.toHexString())) {
			return ResponseEntity.noContent().build();
//			return new ResponseEntity<Pets>(HttpStatus.NO_CONTENT);
		};
		
		return null;
	}
	
}
