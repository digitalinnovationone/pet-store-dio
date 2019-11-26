package br.com.digital.restSpring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.digital.restSpring.models.Pets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetsControllerTest{

    @LocalServerPort
    private int port;
    
    TestRestTemplate restTemplate = new TestRestTemplate();
    
    HttpHeaders headers = new HttpHeaders();
    
    @BeforeEach
    public void setUp() {
    	headers.setContentType(MediaType.APPLICATION_JSON);
	}
	
    @Test
	public void testCreatePet() throws Exception {
    	Pets pet = new Pets();
    	pet.setName("Cachorro teste 1");
		HttpEntity<Pets> entity = new HttpEntity<Pets>(pet, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/"), HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		HttpStatus status = response.getStatusCode();
        assertTrue(actual.contains("/pets"));
        assertEquals(status, HttpStatus.CREATED);
	}
    
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/pets" + uri;
    }

}
