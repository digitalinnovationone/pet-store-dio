package br.com.digital.restSpring.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pets")
public class Pets {

	@Id
	private ObjectId _id;
	
	private String name;
	
	private String img;
	
	private String breed;
	
}
