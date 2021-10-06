package br.com.delivery.app.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.delivery.app.Model.Additional;
import br.com.delivery.app.Repository.AdditionalRepository;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping({"/api/additional/"})
public class AdditionalController {
	
	@Autowired
	private AdditionalRepository repository;

	
	@DeleteMapping(path ={"{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
		
	   return repository.findById(id)
	           .map(record -> {
	        	   repository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value="{id}")
	  public ResponseEntity<Additional> update(@PathVariable("id") long id, @RequestBody Additional additional){
	    return repository.findById(id)
	        .map(record -> {
	            record.setName(additional.getName());
	            record.setPrice(additional.getPrice());
	            Additional updated = repository.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
}
