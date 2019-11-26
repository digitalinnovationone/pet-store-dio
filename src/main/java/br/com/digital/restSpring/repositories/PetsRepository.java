package br.com.digital.restSpring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digital.restSpring.models.Pets;

public interface PetsRepository extends MongoRepository<Pets, String> {

	Pets findBy_id(ObjectId _id);
}
