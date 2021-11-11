package br.com.delivery.app.api.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.delivery.app.Dto.ProductDTO;
import br.com.delivery.app.Model.Product;
import br.com.delivery.app.Repository.CategoryRepository;
import br.com.delivery.app.Repository.ProductRepository;
import br.com.delivery.app.Service.ProductService;
import br.com.delivery.app.Util.FileUploadUtil;

@CrossOrigin(origins = "https://bigburger009.herokuapp.com")
@RestController
@RequestMapping({"/api/product"})
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	 //Save the uploaded file to this folder
        private static String UPLOADED_FOLDER = "src/main/resources/static/photos";
    

	@GetMapping(path = {"/{id}"})
	public ProductDTO findById(@PathVariable long id){
		return service.findById(id);
	}
	
	
	@RequestMapping({"/spotlight"})
	public ResponseEntity<List<Product>> findAllBySpotlight(){
		return ResponseEntity.ok(service.find());
	}
	
	@GetMapping()
	public ResponseEntity<List<Product>> list(){
		return ResponseEntity.ok(service.findAll());
	}
	

	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return repository.findById(id)
	           .map(record -> {
	               repository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}

	@Transactional
	@PostMapping
	public ResponseEntity<?> create(
			@RequestParam(value = "photo2",required = false) MultipartFile multipartFile ,
			@Valid Product product) throws IOException {

			if (multipartFile != null && !multipartFile.isEmpty()) {
			        String fileName =  new Date().getTime() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
				product.setPhoto(fileName);
				FileUploadUtil.saveFile(UPLOADED_FOLDER, fileName, multipartFile);
			}

			repository.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(
			@RequestParam(value = "photo2",required = false) MultipartFile multipartFile ,
			@Valid Product product) throws IOException {

	    if (multipartFile != null && !multipartFile.isEmpty())
	    {
		 String fileName =  new Date().getTime() + StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 product.setPhoto(fileName);
		 FileUploadUtil.saveFile(UPLOADED_FOLDER, fileName, multipartFile);
	    }

	    repository.save(product);
	    return new ResponseEntity<>(HttpStatus.OK);
	}

}
