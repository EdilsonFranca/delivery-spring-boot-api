package br.com.delivery.app.api.Controller;

import java.util.List;
import java.util.Optional;

import br.com.delivery.app.Dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.delivery.app.Model.Additional;
import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Repository.CategoryRepository;
import br.com.delivery.app.Service.CategoryService;

@RestController
@CrossOrigin(origins="*",maxAge = 3600)
@RequestMapping({"/api/category"})
public class CategoryController {
	
	@Autowired
	private CategoryService category_service;
	
	@Autowired
	private CategoryRepository repositoryCategory;

	@RequestMapping({"product"})
	public ResponseEntity<List<CategoryDTO>> findAllByCategory(){
		List<CategoryDTO> list = category_service.findCategory();
		return ResponseEntity.ok(list);
	}
	
	
	@RequestMapping
	public ResponseEntity<?> categorys(){
		List<Category> list = repositoryCategory.findAll();
		return ResponseEntity.ok(list);
	}
	

	@DeleteMapping(path ={"{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return repositoryCategory.findById(id)
	           .map(record -> {
	        	   repositoryCategory.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@RequestMapping({"name"})
	public ResponseEntity<List> findNameCategory(){
		List<?> list =  category_service.findNameCategory();
		return ResponseEntity.ok(list);
	}
	
	@PutMapping(value="{id}")
	  public ResponseEntity<Category> update(@PathVariable("id") long id, @RequestBody Category category){
	    return repositoryCategory.findById(id)
	        .map(record -> {
	            record.setName(category.getName());
	            Category updated = repositoryCategory.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
		
	
	@PutMapping(value="additional/{id}")
	  public ResponseEntity<Additional> add_additional(@PathVariable("id") long id,
													   @RequestBody Additional additional){
	    return repositoryCategory.findById(id)
	        .map(record -> {
	            record.addAdditional(additional);
	            repositoryCategory.save(record);
	            return ResponseEntity.ok().body(additional);
	        }).orElse(ResponseEntity.notFound().build());
	  }
	
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category){
		repositoryCategory.save(category);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}
	
	@RequestMapping({"additional/{id}"})
	public ResponseEntity<?> all_additional(@PathVariable("id") long id){
		Optional<Category> list = repositoryCategory.findById(id);
		return ResponseEntity.ok(list);
	}
	
}
